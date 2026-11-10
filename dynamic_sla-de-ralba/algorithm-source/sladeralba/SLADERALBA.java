package sladeralba;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.cloudbus.cloudsim.allocationpolicies.VmAllocationPolicySimple;
import org.cloudbus.cloudsim.brokers.DatacenterBroker;
import org.cloudbus.cloudsim.brokers.DatacenterBrokerSimple;
import org.cloudbus.cloudsim.cloudlets.Cloudlet;
import org.cloudbus.cloudsim.cloudlets.CloudletSimple;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.datacenters.Datacenter;
import org.cloudbus.cloudsim.datacenters.DatacenterSimple;
import org.cloudbus.cloudsim.hosts.Host;
import org.cloudbus.cloudsim.hosts.HostSimple;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.ResourceProvisionerSimple;
import org.cloudbus.cloudsim.resources.Pe;
import org.cloudbus.cloudsim.resources.PeSimple;
import org.cloudbus.cloudsim.schedulers.cloudlet.CloudletSchedulerTimeShared;
import org.cloudbus.cloudsim.schedulers.vm.VmSchedulerTimeShared;
import org.cloudbus.cloudsim.utilizationmodels.UtilizationModel;
import org.cloudbus.cloudsim.utilizationmodels.UtilizationModelDynamic;
import org.cloudbus.cloudsim.vms.Vm;
import org.cloudbus.cloudsim.vms.VmSimple;
import org.cloudsimplus.builders.tables.CloudletsTableBuilder;
import org.cloudsimplus.listeners.CloudletVmEventInfo;
import org.cloudsimplus.listeners.EventInfo;

@SuppressWarnings("deprecation")
public class SLADERALBA {
    String set[] = {"u_c_hilo", "u_c_lohi", "u_c_lolo", "u_i_hilo", "u_i_lohi", "u_i_lolo",
            "u_s_hilo", "u_s_lohi", "u_s_lolo"};
    public static String pathForExcelFile = "results\\sla-deralba-gocj.xlsx";
    private String fileSize = set[1];
    private String Name = "GOCJ " + fileSize + " Dataset";
    private String baseURL = "GoCJ_v1\\Enhanced_GoCJ\\GoCJ_Dataset_1000.csv";
    private static double TIME_TO_TERMINATE_SIMULATION = 5000;
    private static double SCHEDULING_INTERVAL = 1;
    private static double HCSP_POST_ARRIVAL_INTERVAL = 1;
    private static boolean HCSP_MODE = false;
    private static boolean HCSP_INTERVAL_WIDENED = false;
    private static int LAST_INTERRUPT_TIME = 0;
    private static final int HOSTS = 30;
    private static final int HOST_PES = 4;
    private static int VMS = 50;
    private static final int VM_PES = 1;
    private static int CLOUDLETS = 1000;
    private static int ARRIVED_CLOUDLETS = 0;
    private static int REJECTED_CLOUDLETS = 0;
    private static final int CLOUDLET_PES = 1;
    private static int loadedVMID = 0;
    private static double loadedVMReadyTime = 0;
    private static double total_energy;
    private static double total_execution_cost = 0.0; // Added for cost tracking
    private static double total_execution_time = 0.0; // Added for total execution time tracking
    private final CloudSim simulation;
    private List<Host> hostList;
    private List<Vm> vmList;
    private List<VMStatusTable> vmStatusTables;
    private List<CloudletStatusTable> cloudletStatusTables;
    private List<Cloudlet> cloudletlist;
    private List<Cloudlet> waitingCloudletList;
    private List<VMStatusTable> configuredVmStatusTables;
    Map<Cloudlet, Vm> cloudlet2Vm;
    private DatacenterBroker broker;
    private Datacenter datacenter;
    public static boolean dynamicCloudletArrived = false;

    public static void main(String[] args) {
        if (args.length > 0 && "--hcsp".equals(args[0])) {
            if (args.length < 5) {
                throw new IllegalArgumentException(
                        "Usage: --hcsp <matrix.0> <scenario> <GoCJ-metadata.csv> <output.xlsx>");
            }
            try {
                Path matrixPath = new File(args[1]).toPath();
                String scenario = args[2].toLowerCase(java.util.Locale.ROOT);
                Path metadataPath = new File(args[3]).toPath();
                Path outputPath = new File(args[4]).toPath();
                SLADERALBA runner = new SLADERALBA();
                runner.fileSize = "HCSP_" + scenario;
                runner.Name = "HCSP " + scenario;
                runner.configuredVmStatusTables = HcspVmProfileFactory.create(scenario, VM_PES);
                VMS = runner.configuredVmStatusTables.size();
                Path adaptedCsv = new File("dynamic_sla-de-ralba/results/hcsp-adapted/" + scenario + ".csv").toPath();
                HcspCsvAdapter.Preparation preparation = HcspCsvAdapter.prepare(
                        matrixPath, metadataPath, adaptedCsv, scenario, runner.configuredVmStatusTables);
                runner.baseURL = adaptedCsv.toString();
                TIME_TO_TERMINATE_SIMULATION = preparation.getTerminationTime();
                SCHEDULING_INTERVAL = preparation.getSchedulingInterval();
                HCSP_POST_ARRIVAL_INTERVAL = preparation.getPostArrivalInterval();
                HCSP_MODE = true;
                HCSP_INTERVAL_WIDENED = false;
                pathForExcelFile = outputPath.toString();
                System.out.printf(java.util.Locale.ROOT,
                        "HCSP_ADAPTATION,%s,%d,%d,%.3f,%.3f,%s%n", scenario,
                        preparation.getTaskCount(), preparation.getMaxArrival(),
                        preparation.getTerminationTime(), preparation.getSchedulingInterval(),
                        adaptedCsv.toAbsolutePath());
                runner.run();
            } catch (IOException ex) {
                throw new IllegalStateException("Failed to prepare HCSP adaptation", ex);
            }
            return;
        }

        TIME_TO_TERMINATE_SIMULATION = 5000;
        SCHEDULING_INTERVAL = 1;
        HCSP_MODE = false;
        SLADERALBA runner = new SLADERALBA();
        if (args.length > 0 && !args[0].isBlank()) {
            runner.baseURL = args[0];
        }
        if (args.length > 1 && !args[1].isBlank()) {
            pathForExcelFile = args[1];
        }
        runner.fileSize = new File(runner.baseURL).getName();
        runner.Name = "GoCJ " + runner.fileSize;
        runner.run();
    }

    private SLADERALBA() {
        simulation = new CloudSim();
        this.hostList = new ArrayList<>();
        this.vmList = new ArrayList<>();
        this.cloudletlist = new ArrayList<>();
        this.waitingCloudletList = new ArrayList<>();
        this.cloudlet2Vm = new HashMap<>();
    }

    private void run() {
        System.out.println("Starting " + getClass().getSimpleName());
        simulation.terminateAt(TIME_TO_TERMINATE_SIMULATION);

        this.datacenter = createDatacenter();
        this.broker = new DatacenterBrokerSimple(simulation);

        vmStatusTables = configuredVmStatusTables != null
                ? configuredVmStatusTables : VMCreator.createVms(VM_PES, VMS);
        VMS = vmStatusTables.size();
        this.vmList = getVmlist();
        this.broker.submitVmList(vmList);

        System.out.println("Number of VMs submitted: " + vmList.size());

        this.cloudletStatusTables = createDataset();
        CLOUDLETS = cloudletStatusTables.size();
        this.cloudletlist = getCloudletList();
        System.out.println("Number of Cloudlets created: " + cloudletlist.size());

        if (cloudletStatusTables.isEmpty()) {
            System.out.println("ERROR: No cloudlets loaded from dataset!");
            return;
        }

        calculateComputationRatio();
        calculateVMShare();
        printVMShareStatusTable();

        if (!cloudletlist.isEmpty()) {
            broker.submitCloudletList(new ArrayList<>(cloudletlist));
        }

        filler();

        simulation.addOnClockTickListener(this::createRandomCloudlets);
        runSimulationAndPrintResults();

        printVMShareStatusTable();
        List<Cloudlet> newList = broker.getCloudletFinishedList();
        System.out.println("Number of finished cloudlets: " + newList.size());
        if (configuredVmStatusTables != null && newList.size() < CLOUDLETS) {
            throw new IllegalStateException("HCSP run stopped before all jobs finished: "
                    + newList.size() + "/" + CLOUDLETS);
        }
        if (newList.isEmpty()) {
            System.out.println("ERROR: No cloudlets finished! Check simulation time and resource allocation.");
            return;
        }

        this.vmList = getVmList();
        double sumAvgRt = 0.0;
        for (int a = 0; a < VMS; a++) {
            double avgRt = VmAvgRt(newList, vmList.get(a).getId());
            System.out.println("Average Response Time of Vm-" + vmList.get(a).getId() + "   =  " + avgRt);
            sumAvgRt += avgRt;
        }
        sumAvgRt /= VMS;

        for (int a = 0; a < vmList.size(); a++) {
            System.out.println("Makespane of Vm-" + vmList.get(a).getId() + "  with computing power " + vmList.get(a).getMips() + " is equal to  =  " + getVmMakeSpane(newList, vmList.get(a).getId()));
        }

        for (int a = 0; a < vmList.size(); a++) {
            System.out.println("energy consumed by Vm-" + vmList.get(a).getId() + "   =  " + VM_energy_consumption(newList, vmList.get(a).getId()));
        }

        // Calculate and display total execution cost and time
        total_execution_cost = calculateTotalExecutionCost(newList);
        total_execution_time = calculateTotalExecutionTime(newList);
        double avgExecutionCost = vmList.size() > 0 ? total_execution_cost / vmList.size() : 0.0;
        double avgExecutionTime = vmList.size() > 0 ? total_execution_time / vmList.size() : 0.0;

        System.out.println("Total energy consumption of Host: " + total_energy + " Joules");
        System.out.println("Average energy consumption of Host: " + (total_energy / vmList.size()) + " Joules");
        System.out.println("Total execution cost: " + total_execution_cost + " units");
        System.out.println("Average execution cost per VM: " + avgExecutionCost + " units");
        System.out.println("Total execution time: " + total_execution_time + " seconds");
        System.out.println("Average execution time per VM: " + avgExecutionTime + " seconds");

        Vm maxVm = getVmWithMakeSpane(newList, vmList);
        Double makeSpane = maxVm != null ? getVmMakeSpane(newList, maxVm.getId()) : 0.0;
        System.out.println("Throughput = " + (makeSpane > 0 ? CLOUDLETS / makeSpane : 0));
        System.out.println("Average Response Time = " + sumAvgRt);
        System.out.println("ARUR = " + getAvgResourceUtilizationRatio(vmList, newList));
        System.out.println("Makespane ( VM- " + (maxVm != null ? maxVm.getId() : "N/A") + " ) = " + makeSpane);

        double rejectionRatio = CLOUDLETS > 0 ? (double) REJECTED_CLOUDLETS / CLOUDLETS : 0.0;
        System.out.println("Total Jobs Submitted: " + CLOUDLETS);
        System.out.println("Total Jobs Rejected: " + REJECTED_CLOUDLETS);
        System.out.println("Job Rejection Ratio: " + rejectionRatio);
        double throughput = makeSpane > 0 ? CLOUDLETS / makeSpane : 0;
        double arur = getAvgResourceUtilizationRatio(vmList, newList);
        System.out.printf(java.util.Locale.ROOT,
                "METRICS,%s,%.10f,%.10f,%.10f,%.10f,%.10f,%.10f,%.10f%n",
                fileSize, makeSpane, arur, throughput, total_energy,
                total_execution_time, total_execution_cost, rejectionRatio);
        saveResult(fileSize, throughput, sumAvgRt, arur, makeSpane, total_energy,
                total_energy / vmList.size(), rejectionRatio, total_execution_cost,
                avgExecutionCost, total_execution_time, avgExecutionTime);
        System.out.println(getClass().getSimpleName() + " finished!");
    }

    

	// Added method to calculate execution cost for a cloudlet on a VM
    private double calculateCloudletExecutionCost(Cloudlet cloudlet, Vm vm) {
        if (vm == null || vm.getMips() <= 0) {
            return Double.MAX_VALUE;
        }
        
        // Get VM cost per unit time from VMStatusTable
        VMStatusTable vmTable = getVMStatusTableInstance(vm);
        if (vmTable == null) {
            return Double.MAX_VALUE;
        }
        
        double executionTime = cloudlet.getLength() / vm.getMips();
        double executionCost = vmTable.getCostPerUnitTime() * executionTime;
        return executionCost;
    }
    
    
    
    
    
    
    
    
    
 // total execution cost calculation 
    private double calculateTotalExecutionCost(List<Cloudlet> finishedCloudlets) {
        double totalCost = 0.0;
        
        if (finishedCloudlets == null || finishedCloudlets.isEmpty()) {
            return totalCost;
        }
        
        for (Cloudlet cloudlet : finishedCloudlets) {
            if (cloudlet.getVm() != null && cloudlet.getVm().getMips() > 0) {
                // Get the VM status table to access cost per unit time
                VMStatusTable vmTable = getVMStatusTableInstance(cloudlet.getVm());
                if (vmTable != null) {
                    // Calculate execution time: Cloudlet Length (MI) / VM MIPS
                    double executionTime = cloudlet.getLength() / cloudlet.getVm().getMips();
                    
                    // Calculate execution cost: Cost Per Unit Time × Execution Time
                    double executionCost = vmTable.getCostPerUnitTime() * executionTime;
                    
                    totalCost += executionCost;
                    
                    // Optional: Log individual cloudlet costs for debugging
                    System.out.println("Cloudlet " + cloudlet.getId() + 
                                      " on VM " + cloudlet.getVm().getId() + 
                                      " - Execution Time: " + String.format("%.2f", executionTime) + 
                                      " seconds, Cost: " + String.format("%.2f", executionCost) + " units");
                }
            }
        }
        
        return totalCost;
    }
    
    
    
    
    
    
    
    

    // Added method to calculate total execution time for all finished cloudlets
    private double calculateTotalExecutionTime(List<Cloudlet> finishedCloudlets) {
        double totalTime = 0.0;
        for (Cloudlet cloudlet : finishedCloudlets) {
            if (cloudlet.getVm() != null) {
                double executionTime = cloudlet.getLength() / cloudlet.getVm().getMips();
                totalTime += executionTime;
            }
        }
        return totalTime;
    }

    // Modified method to consider cost in VM selection
    private VMStatusTable getVmWithMinCostFinishTime(Cloudlet cloudlet) {
        if (cloudlet == null || vmStatusTables == null || vmStatusTables.isEmpty()) return null;
        
        VMStatusTable bestVM = null;
        double bestScore = Double.MAX_VALUE;
        double avgLoad = vmStatusTables.stream()
            .filter(vm -> vm.getVm().getMips() > 0)
            .mapToDouble(vm -> vm.getRemainingMI() / vm.getVm().getMips())
            .average().orElse(0);

        for (VMStatusTable vm : vmStatusTables) {
            if (vm.getVm().getMips() <= 0) continue;
            
            Double execTime = getCloudletExecTime(cloudlet, vm.getVm(), vm.getReadTime());
            double execCost = calculateCloudletExecutionCost(cloudlet, vm.getVm());
            double utilization = vm.getRemainingMI() / vm.getVm().getMips();
            double balancePenalty = Math.abs(utilization - avgLoad);
            
            // Combined score considering execution time, cost, utilization, and balance
            // Weights: 25% exec time, 35% exec cost, 30% utilization, 10% balance
            double score = (execTime * 0.25) + (execCost * 0.35) + (utilization * 0.30) + (balancePenalty * 0.10);
            
            if (score < bestScore) {
                bestScore = score;
                bestVM = vm;
            }
        }
        return bestVM;
    }

    // Added method to get VM with minimum execution cost
    private VMStatusTable getVmWithMinExecutionCost(Cloudlet cloudlet) {
        if (cloudlet == null || vmStatusTables == null || vmStatusTables.isEmpty()) return null;
        
        VMStatusTable minCostVM = null;
        double minCost = Double.MAX_VALUE;
        
        for (VMStatusTable vm : vmStatusTables) {
            if (vm.getVm().getMips() > 0) {
                double cost = calculateCloudletExecutionCost(cloudlet, vm.getVm());
                if (cost < minCost) {
                    minCost = cost;
                    minCostVM = vm;
                }
            }
        }
        return minCostVM;
    }

    private Datacenter createDatacenter() {
        for (int i = 0; i < HOSTS; i++) {
            Host host = createHost();
            hostList.add(host);
        }
        final Datacenter dc = new DatacenterSimple(simulation, hostList, new VmAllocationPolicySimple());
        dc.setSchedulingInterval(SCHEDULING_INTERVAL);
        return dc;
    }

    private void filler() {
        System.out.println("Starting filler with " + cloudletlist.size() + " cloudlets");
        while (!cloudletlist.isEmpty()) {
        	VMStatusTable vmStatusTable = getOptimalVMForCloudlet();
            if (vmStatusTable == null) {
                System.out.println("No suitable VM found, rejecting remaining cloudlets");
                REJECTED_CLOUDLETS += cloudletlist.size();
                cloudletlist.clear();
                break;
            }

            Cloudlet cloudlet = getMaxCloudletForVmShareWithSLA(vmStatusTable);
            if (cloudlet != null) {
                assignCloudletToVM(vmStatusTable, cloudlet);
                setVMforCloudletTable(vmStatusTable.getVm(), cloudlet);
            } else if (dynamicCloudletArrived) {
                updateVMStatusTable((int) simulation.clock());
                dynamicCloudletArrived = false;
            } else {
                cloudlet = getMaxCloudletWithSLA();
                if (cloudlet == null) {
                    System.out.println("No suitable cloudlet found, skipping iteration");
                    REJECTED_CLOUDLETS++;
                    if (!cloudletlist.isEmpty()) {
                        cloudletlist.remove(0);
                    }
                    continue;
                }
                
                // Use cost-aware VM selection instead of just minimum finish time
                vmStatusTable = getVmWithMinCostFinishTime(cloudlet);
                if (vmStatusTable == null) {
                    System.out.println("No suitable VM found for cloudlet, skipping");
                    REJECTED_CLOUDLETS++;
                    cloudletlist.remove(cloudlet);
                    continue;
                }

                vmStatusTable.setVMShare(Math.max(0, vmStatusTable.getVMShare()));
                if (vmStatusTable.getVMShare() < cloudlet.getLength()) {
                    VMStatusTable heaviestVM = getHeaviestVM();
                    VMStatusTable leastLoadedVM = getLeastLoadedVM();
                    if (heaviestVM != null && leastLoadedVM != null && heaviestVM.getCloudlets().size() > 1) {
                        Cloudlet toOffload = getSmallestCloudlet(heaviestVM.getCloudlets());
                        if (toOffload != null && toOffload.getLength() <= leastLoadedVM.getVMShare() + redistributeLoad(leastLoadedVM)) {
                            assignCloudletToVM(leastLoadedVM, toOffload);
                            setVMforCloudletTable(leastLoadedVM.getVm(), toOffload);
                            heaviestVM.getCloudlets().remove(toOffload);
                            heaviestVM.setVMShare(heaviestVM.getVMShare() + toOffload.getLength());
                            heaviestVM.setRemainingMI(heaviestVM.getRemainingMI() - toOffload.getLength());
                        }
                    }

                    vmStatusTable = getVmWithMinCostFinishTime(cloudlet);
                    if (vmStatusTable != null) {
                        vmStatusTable.setVMShare(Math.max(0, vmStatusTable.getVMShare()));
                        double borrowedCapacity = borrowCapacity(vmStatusTable, cloudlet.getLength());
                    }
                }
                
                if (vmStatusTable != null) {
                    assignCloudletToVM(vmStatusTable, cloudlet);
                    setVMforCloudletTable(vmStatusTable.getVm(), cloudlet);
                }
            }
        }
        System.out.println("Filler completed. Remaining cloudlets: " + cloudletlist.size());
    }

    private void rebalanceVMs() {
        if (vmStatusTables == null || vmStatusTables.isEmpty()) {
            return;
        }
        
        List<VMStatusTable> sortedVMs = vmStatusTables.stream()
                .filter(vm -> vm.getVm().getMips() > 0)
                .sorted(Comparator.comparingDouble(vm -> -(vm.getRemainingMI() / vm.getVm().getMips())))
                .collect(Collectors.toList());

        for (VMStatusTable heaviestVM : sortedVMs) {
            if (heaviestVM.getCloudlets().isEmpty()) {
                continue;
            }
            
            VMStatusTable leastLoadedVM = getLeastLoadedVM();
            if (leastLoadedVM != null && heaviestVM != leastLoadedVM && heaviestVM.getCloudlets().size() > 1) {
                double leastUtilization = leastLoadedVM.getVm().getMips() > 0 ? 
                    leastLoadedVM.getRemainingMI() / leastLoadedVM.getVm().getMips() : 0;
                
                if (leastUtilization < 0.2) {
                    for (int i = 0; i < 5 && !heaviestVM.getCloudlets().isEmpty(); i++) {
                        Cloudlet toOffload = getSmallestCloudlet(heaviestVM.getCloudlets());
                        if (toOffload != null && toOffload.getLength() <= leastLoadedVM.getVMShare() * 1.6) {
                            assignCloudletToVM(leastLoadedVM, toOffload);
                            setVMforCloudletTable(leastLoadedVM.getVm(), toOffload);
                            heaviestVM.getCloudlets().remove(toOffload);
                            heaviestVM.setVMShare(heaviestVM.getVMShare() + toOffload.getLength());
                            heaviestVM.setRemainingMI(heaviestVM.getRemainingMI() - toOffload.getLength());
                            System.out.println("Rebalanced cloudlet " + toOffload.getId() + " from VM " + heaviestVM.getVMID() + " to VM " + leastLoadedVM.getVMID());
                        } else {
                            break;
                        }
                    }
                }
            }
        }
    }

    private VMStatusTable getOptimalVMForCloudlet() {
        if (vmStatusTables == null || vmStatusTables.isEmpty()) return null;
        VMStatusTable optimalVM = null;
        double minScore = Double.MAX_VALUE;
        double avgLoad = vmStatusTables.stream()
            .filter(vm -> vm.getVm().getMips() > 0)
            .mapToDouble(vm -> vm.getRemainingMI() / vm.getVm().getMips())
            .average().orElse(0);
        double loadVariance = vmStatusTables.stream()
            .filter(vm -> vm.getVm().getMips() > 0)
            .mapToDouble(vm -> Math.pow((vm.getRemainingMI() / vm.getVm().getMips()) - avgLoad, 2))
            .average().orElse(0);

        for (VMStatusTable vm : vmStatusTables) {
            if (vm.getVm().getMips() <= 0) continue;
            
            double utilization = vm.getRemainingMI() / vm.getVm().getMips();
            double remainingCapacity = vm.getVMShare() / vm.getVm().getMips();
            double balancePenalty = Math.abs(utilization - avgLoad) / (loadVariance + 1e-6);
            double costFactor = vm.getCostPerUnitTime() / 10.0; // Normalize cost factor
            
            // Include cost consideration in scoring
            double score = (utilization * 0.25) + ((1.0 - remainingCapacity) * 0.45) + (balancePenalty * 0.10) + (costFactor * 0.20);
            if (score < minScore) {
                minScore = score;
                optimalVM = vm;
            }
        }
        return optimalVM;
    }

    private VMStatusTable getLeastLoadedVM() {
        if (vmStatusTables == null || vmStatusTables.isEmpty()) return null;
        
        VMStatusTable leastLoaded = null;
        double minUtilization = Double.MAX_VALUE;
        
        for (VMStatusTable vm : vmStatusTables) {
            if (vm.getVm().getMips() > 0 && vm.getVMShare() > 0) {
                double currentUtil = vm.getRemainingMI() / vm.getVm().getMips();
                if (currentUtil < minUtilization) {
                    minUtilization = currentUtil;
                    leastLoaded = vm;
                }
            }
        }
        return leastLoaded;
    }

    private VMStatusTable getHeaviestVM() {
        if (vmStatusTables == null || vmStatusTables.isEmpty()) return null;
        
        VMStatusTable heaviest = null;
        double maxUtilization = -1;
        
        for (VMStatusTable vm : vmStatusTables) {
            if (vm.getVm().getMips() > 0 && vm.getCloudlets().size() > 1) {
                double currentUtil = vm.getRemainingMI() / vm.getVm().getMips();
                if (currentUtil > maxUtilization) {
                    maxUtilization = currentUtil;
                    heaviest = vm;
                }
            }
        }
        return heaviest;
    }

    private Cloudlet getSmallestCloudlet(List<Cloudlet> cloudlets) {
        if (cloudlets == null || cloudlets.isEmpty()) {
            return null;
        }
        
        Cloudlet smallest = cloudlets.get(0);
        for (Cloudlet c : cloudlets) {
            if (c.getLength() < smallest.getLength()) {
                smallest = c;
            }
        }
        return smallest;
    }

    private double redistributeLoad(VMStatusTable vm) {
        if (vm == null || vm.getVm().getMips() <= 0) {
            return 0;
        }
        double availableCapacity = vm.getVm().getMips() * (TIME_TO_TERMINATE_SIMULATION - vm.getReadTime());
        return Math.min(availableCapacity, vm.getVMShare() * 0.5);
    }

    private double borrowCapacity(VMStatusTable targetVM, long requiredLength) {
        if (targetVM == null || vmStatusTables == null) {
            return 0;
        }
        
        double totalBorrowable = 0;
        double borrowFactor = 0.95;

        for (VMStatusTable vm : vmStatusTables) {
            if (vm != targetVM && vm.getVMShare() > requiredLength * 0.01) {
                double borrowable = Math.min(vm.getVMShare() * borrowFactor, requiredLength - targetVM.getVMShare());
                totalBorrowable += borrowable;
                vm.setVMShare(vm.getVMShare() - borrowable);
                vm.setRemainingMI(vm.getRemainingMI() - borrowable);
            }
        }
        targetVM.setVMShare(targetVM.getVMShare() + totalBorrowable);
        targetVM.setRemainingMI(targetVM.getRemainingMI() + totalBorrowable);
        return totalBorrowable;
    }

    private Cloudlet getMaxCloudletForVmShareWithSLA(VMStatusTable vmStatusTable) {
        if (vmStatusTable == null || cloudletlist == null) {
            return null;
        }
        
        List<Cloudlet> nList = cloudletlist.stream()
                .filter(cloudlet -> cloudlet.getLength() <= vmStatusTable.getVMShare())
                .collect(Collectors.toList());
        if (nList.isEmpty()) return null;

        nList.sort((c1, c2) -> {
            int sla1 = cloudletStatusTables.stream()
                    .filter(t -> t.getCloudlet() != null && t.getCloudlet().getId() == c1.getId())
                    .findFirst().map(CloudletStatusTable::getSlaLevel).orElse(0);
            int sla2 = cloudletStatusTables.stream()
                    .filter(t -> t.getCloudlet() != null && t.getCloudlet().getId() == c2.getId())
                    .findFirst().map(CloudletStatusTable::getSlaLevel).orElse(0);
            if (sla1 != sla2) return sla2 - sla1;
            return Long.compare(c2.getLength(), c1.getLength());
        });
        return nList.get(0);
    }

    private Cloudlet getMaxCloudletWithSLA() {
        if (cloudletlist == null || cloudletlist.isEmpty()) return null;
        return cloudletlist.stream()
                .max(Comparator.comparingLong(c -> {
                    int sla = cloudletStatusTables.stream()
                            .filter(t -> t.getCloudlet() != null && t.getCloudlet().getId() == c.getId())
                            .findFirst().map(CloudletStatusTable::getSlaLevel).orElse(0);
                    return sla * 1000000L + c.getLength();
                }))
                .orElse(null);
    }

    private Host createHost() {
        List<Pe> peList = new ArrayList<>();
        long mips = 2000 * VMS;
        for (int i = 0; i < HOST_PES; i++) {
            peList.add(new PeSimple(mips, new PeProvisionerSimple()));
        }
        long ram = 2048;
        long storage = 1000000;
        long bw = 10000;
        return new HostSimple(ram, bw, storage, peList)
                .setRamProvisioner(new ResourceProvisionerSimple())
                .setBwProvisioner(new ResourceProvisionerSimple())
                .setVmScheduler(new VmSchedulerTimeShared());
    }

    private List<Vm> getVmlist() {
        List<Vm> list = new ArrayList<>();
        for (VMStatusTable vmStatusTable : vmStatusTables) {
            list.add(vmStatusTable.getVm());
        }
        return list;
    }

    private void calculateComputationRatio() {
        double totalMips = getTotalMips();
        if (totalMips > 0) {
            for (VMStatusTable vmStatusTable : vmStatusTables) {
                vmStatusTable.setComputationRatio(vmStatusTable.getMips() / totalMips);
            }
        }
    }

    private double getTotalMips() {
        double mips = 0;
        for (VMStatusTable vmStatusTable : vmStatusTables) {
            mips += vmStatusTable.getMips();
        }
        return mips;
    }

    private List<CloudletStatusTable> createDataset() {
        List<CloudletStatusTable> list = new ArrayList<>();
        File file = new File(baseURL);
        if (!file.exists()) {
            System.out.println("Error: Dataset file does not exist at path: " + baseURL);
            return list;
        }

        try {
            FileReader in = new FileReader(baseURL);
            BufferedReader br = new BufferedReader(in);
            String row = br.readLine();
            System.out.println("Reading dataset from: " + baseURL);
            int counter = 0;
            int validRows = 0;

            while (row != null && counter < 10000) {
                String[] line = row.split(",");
                if (line.length >= 3) {
                    try {
                        double length = Double.parseDouble(line[0].trim());
                        int slaLevel = Integer.parseInt(line[1].trim());
                        int arrivalTime = Integer.parseInt(line[2].trim());
                        if (length > 0 && arrivalTime >= 0) {
                            CloudletStatusTable cloudletStatusTable = new CloudletStatusTable(length, arrivalTime, slaLevel, false);
                            if (arrivalTime == 0) {
                                Cloudlet cloudlet = createCloudlet(counter, (long) length);
                                cloudletStatusTable.setCloudlet(cloudlet);
                                cloudletlist.add(cloudlet);
                            }
                            list.add(cloudletStatusTable);
                            validRows++;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Skipping invalid row: " + row);
                    }
                }
                row = br.readLine();
                counter++;
            }
            br.close();
            in.close();
            System.out.println("Finished reading dataset. Valid cloudlets: " + validRows + " out of " + counter + " rows");
        } catch (Exception ex) {
            System.out.println("Error reading dataset: " + ex.getMessage());
            ex.printStackTrace();
        }
        return list;
    }

    private List<Cloudlet> getCloudletList() {
        return new ArrayList<>(cloudletlist);
    }

    private List<Vm> getVmList() {
        return new ArrayList<>(vmList);
    }

    private double getTotalMI() {
        double totalMIs = 0;
        for (CloudletStatusTable cloudletStatusTable : cloudletStatusTables) {
            if (!cloudletStatusTable.isScheduled() && cloudletStatusTable.getCloudlet() != null) {
                totalMIs += cloudletStatusTable.getLength();
            }
        }
        for (VMStatusTable vmStatusTable : vmStatusTables) {
            totalMIs += vmStatusTable.getRemainingMI();
        }
        return totalMIs;
    }

    private Cloudlet createCloudlet(int id, long length) {
        long fileSize = 300;
        long outputSize = 300;
        UtilizationModel utilizationModel = new UtilizationModelDynamic(0.7);
        Cloudlet cloudlet = new CloudletSimple(id, length, CLOUDLET_PES)
                .setFileSize(fileSize)
                .setOutputSize(outputSize)
                .setUtilizationModel(utilizationModel)
                .setVm(Vm.NULL)
                .addOnFinishListener(this::onCloudletFinishListener)
                .addOnStartListener(this::onCloudletStartListener);
        ARRIVED_CLOUDLETS += 1;
        return cloudlet;
    }

    private void runSimulationAndPrintResults() {
        System.out.println("Starting simulation...");
        simulation.start();
        List<Cloudlet> cloudlets = broker.getCloudletFinishedList();
        System.out.println("Simulation completed. Finished cloudlets: " + cloudlets.size());
        new CloudletsTableBuilder(cloudlets).build();
    }

    private void calculateVMShare() {
        double totalMI = getTotalMI();
        System.out.println("Total MI for VM share calculation: " + totalMI);
        
        if (totalMI <= 0 || vmStatusTables == null || vmStatusTables.isEmpty()) {
            return;
        }
        
        double totalUtilization = vmStatusTables.stream()
                .filter(vm -> vm.getVm().getMips() > 0)
                .mapToDouble(vm -> vm.getRemainingMI() / vm.getVm().getMips())
                .sum();
        
        for (VMStatusTable vmStatusTable : vmStatusTables) {
            if (vmStatusTable.getVm().getMips() > 0) {
                double slaWeight = calculateSlaWeight(vmStatusTable);
                double utilization = vmStatusTable.getRemainingMI() / vmStatusTable.getVm().getMips();
                double utilizationFactor = totalUtilization > 0 ? (1.0 - utilization / totalUtilization) * 2.5 : 1.0 / VMS;
                // Include cost factor in VM share calculation
                double costFactor = 1.0 / (vmStatusTable.getCostPerUnitTime() + 0.1); // Inverse relationship with cost
                double vmShare = vmStatusTable.getComputationRatio() * totalMI * slaWeight * utilizationFactor * costFactor;
                vmStatusTable.setVMShare(Math.max(0, vmShare));
            }
        }
    }

    private double calculateSlaWeight(VMStatusTable vmStatusTable) {
        if (vmStatusTable == null || vmStatusTable.getCloudlets().isEmpty()) {
            return 1.0;
        }
        
        double totalSla = 0;
        int count = 0;
        for (Cloudlet cloudlet : vmStatusTable.getCloudlets()) {
            for (CloudletStatusTable cst : cloudletStatusTables) {
                if (cst.getCloudlet() != null && cst.getCloudlet().getId() == cloudlet.getId()) {
                    totalSla += cst.getSlaLevel();
                    count++;
                    break;
                }
            }
        }
        return count > 0 ? (1.0 + totalSla / count / 10.0) : 1.0;
    }

    private void printVMShareStatusTable() {
        for (VMStatusTable status : vmStatusTables) {
            System.out.println("Vm ID=" + status.getVMID() + " VMShare=" + status.getVMShare() + " MIPS=" + status.getMips() +
                    " Ready Time=" + status.getReadTime() + " TotalMI=" + status.getTotalAssignedMI() +
                    " Executed MIs=" + status.getExecutedMI() + " Remaining MI=" + status.getRemainingMI() +
                    " Cost Per Unit Time=" + status.getCostPerUnitTime());
        }
    }

    private static double getCloudletExecTime(Cloudlet cloudlet, Vm vm, Double vmReadyTime) {
        if (vm.getMips() <= 0) {
            return Double.MAX_VALUE;
        }
        return vmReadyTime + (cloudlet.getLength() / vm.getMips());
    }

    private VMStatusTable getVmWithMinFinishTime(Cloudlet cloudlet) {
        if (cloudlet == null || vmStatusTables == null || vmStatusTables.isEmpty()) return null;
        
        VMStatusTable bestVM = null;
        double bestScore = Double.MAX_VALUE;
        double avgLoad = vmStatusTables.stream()
            .filter(vm -> vm.getVm().getMips() > 0)
            .mapToDouble(vm -> vm.getRemainingMI() / vm.getVm().getMips())
            .average().orElse(0);

        for (VMStatusTable vm : vmStatusTables) {
            if (vm.getVm().getMips() <= 0) continue;
            
            Double execTime = getCloudletExecTime(cloudlet, vm.getVm(), vm.getReadTime());
            double utilization = vm.getRemainingMI() / vm.getVm().getMips();
            double balancePenalty = Math.abs(utilization - avgLoad);
            double score = (execTime * 0.3) + (utilization * 0.6) + (balancePenalty * 0.1);
            
            if (score < bestScore) {
                bestScore = score;
                bestVM = vm;
            }
        }
        return bestVM;
    }

    private void assignCloudletToVM(VMStatusTable vmStatusTable, Cloudlet cloudlet) {
        if (vmStatusTable == null || cloudlet == null) {
            return;
        }
        
        broker.bindCloudletToVm(cloudlet, vmStatusTable.getVm());
        vmStatusTable.setVMShare(vmStatusTable.getVMShare() - cloudlet.getLength());
        vmStatusTable.setTotalAssignedMI(vmStatusTable.getTotalAssignedMI() + cloudlet.getLength());
        vmStatusTable.setRemainingMI(vmStatusTable.getRemainingMI() + cloudlet.getLength());
        vmStatusTable.setReadTime(vmStatusTable.getReadTime() + getExecutionTime(vmStatusTable.getVm(), cloudlet, vmStatusTable.getReadTime()));
        vmStatusTable.addCloudlets(cloudlet);
        
        // Calculate and log execution cost and time
        double executionCost = calculateCloudletExecutionCost(cloudlet, vmStatusTable.getVm());
        double executionTime = cloudlet.getLength() / vmStatusTable.getVm().getMips();
        System.out.println("Cloudlet " + cloudlet.getLength() + " Scheduled on VM " + vmStatusTable.getVMID() + 
                          " with execution cost: " + String.format("%.2f", executionCost) +
                          " and execution time: " + String.format("%.2f", executionTime) + " seconds");
        cloudletlist.remove(cloudlet);
        
        for (VMStatusTable vmTable : vmStatusTables) {
            if (vmTable.getReadTime() > loadedVMReadyTime) {
                loadedVMReadyTime = vmTable.getReadTime();
                loadedVMID = vmTable.getVMID();
            }
        }
    }

    private double getExecutionTime(Vm vm, Cloudlet cloudlet, double readyTime) {
        if (vm.getMips() <= 0) {
            return Double.MAX_VALUE;
        }
        return (cloudlet.getLength() / vm.getMips()) + readyTime;
    }

    private void updateVMStatusTable(int simulationClockTime) {
        System.out.println("UPDATING VM STATUS TABLE");
        System.out.println("Simulation Time Clock " + simulationClockTime);
        
        if (vmStatusTables == null) {
            return;
        }
        
        for (VMStatusTable table : vmStatusTables) {
            double updateTimeDifference = simulationClockTime - table.getLastUpdatedTime();
            if (table.getReadTime() - updateTimeDifference <= 0) {
                table.setReadTime(0);
                table.setRemainingMI(0);
                table.setExecutedMI(table.getTotalAssignedMI());
            } else {
                double executedMI = table.getMips() * updateTimeDifference;
                table.setReadTime(table.getReadTime() - updateTimeDifference);
                table.setExecutedMI(table.getExecutedMI() + executedMI);
                table.setRemainingMI(table.getTotalAssignedMI() - table.getExecutedMI());
            }
            table.setLastUpdatedTime(simulationClockTime);
        }
        
        calculateVMShare();
        printVMShareStatusTable();
        
        double avgLoad = vmStatusTables.stream()
            .filter(vm -> vm.getVm().getMips() > 0)
            .mapToDouble(vm -> vm.getRemainingMI() / vm.getVm().getMips())
            .average().orElse(0);
        double loadVariance = vmStatusTables.stream()
            .filter(vm -> vm.getVm().getMips() > 0)
            .mapToDouble(vm -> Math.pow((vm.getRemainingMI() / vm.getVm().getMips()) - avgLoad, 2))
            .average().orElse(0);
        
        if (loadVariance > 0.02) {
            rebalanceVMs();
        }
    }

    private VMStatusTable updateVMStatusTable(int simulationClockTime, VMStatusTable table) {
        if (table == null) {
            return null;
        }
        
        double updateTimeDifference = simulationClockTime - table.getLastUpdatedTime();
        if (table.getReadTime() - updateTimeDifference <= 0) {
            table.setReadTime(0);
            table.setRemainingMI(0);
            table.setExecutedMI(table.getTotalAssignedMI());
        } else {
            double executedMI = table.getMips() * updateTimeDifference;
            table.setReadTime(table.getReadTime() - updateTimeDifference);
            table.setExecutedMI(table.getExecutedMI() + executedMI);
            table.setRemainingMI(table.getTotalAssignedMI() - table.getExecutedMI());
        }
        table.setLastUpdatedTime(simulationClockTime);
        return table;
    }

    private void onCloudletFinishListener(CloudletVmEventInfo eventInfo) {
        Cloudlet finishedCloudlet = eventInfo.getCloudlet();
        Vm vm = eventInfo.getVm();
        VMStatusTable vmStatusTable = getVMStatusTableInstance(vm);
        
        if (vmStatusTable != null) {
            vmStatusTable.getCloudlets().remove(finishedCloudlet);
            vmStatusTable = updateVMStatusTable((int) eventInfo.getTime(), vmStatusTable);
            
            if (vmStatusTable.getReadTime() <= 0 && loadedVMID < vmStatusTables.size()) {
                VMStatusTable heavyMachine = vmStatusTables.get(loadedVMID);
                List<Cloudlet> list = new ArrayList<>(heavyMachine.getCloudlets());
                
                for (Cloudlet instance : list) {
                    if (!instance.isFinished()) {
                        double currentMachineExecTime = getExecutionTime(vm, instance, vmStatusTable.getReadTime());
                        double heavyMachineExecTime = getExecutionTime(heavyMachine.getVm(), instance, heavyMachine.getReadTime());
                        double currentMachineCost = calculateCloudletExecutionCost(instance, vm);
                        double heavyMachineCost = calculateCloudletExecutionCost(instance, heavyMachine.getVm());
                        
                        // Consider both execution time and cost for migration decision
                        double currentScore = (currentMachineExecTime * 0.6) + (currentMachineCost * 0.4);
                        double heavyScore = (heavyMachineExecTime * 0.6) + (heavyMachineCost * 0.4);
                        
                        if (currentScore < heavyScore) {
                            assignCloudletToVM(vmStatusTable, instance);
                            heavyMachine.setReadTime(heavyMachine.getReadTime() - heavyMachineExecTime);
                            heavyMachine.setTotalAssignedMI(heavyMachine.getTotalAssignedMI() - instance.getLength());
                            heavyMachine.getCloudlets().remove(instance);
                        }
                    }
                }
                updateVMStatusTable((int) simulation.clock());
            }
        }
    }

    private int getMinimum(List<Cloudlet> list) {
        if (list == null || list.isEmpty()) return -1;
        double minimum = list.get(0).getLength();
        int minIndex = 0;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).getLength() < minimum) {
                minimum = list.get(i).getLength();
                minIndex = i;
            }
        }
        return minIndex;
    }

    private void onCloudletStartListener(CloudletVmEventInfo eventInfo) {
        // Optional: Add logging if needed
    }

    private void setVMforCloudletTable(Vm vm, Cloudlet cloudlet) {
        if (vm == null || cloudlet == null || cloudletStatusTables == null) {
            return;
        }
        
        for (CloudletStatusTable table : cloudletStatusTables) {
            if (table.getCloudlet() != null && table.getCloudlet().getId() == cloudlet.getId()) {
                table.setVm(vm);
                table.setScheduled(true);
                break;
            }
        }
    }

    private void createRandomCloudlets(final EventInfo evt) {
        int currentTime = (int) evt.getTime();
        if (ARRIVED_CLOUDLETS < cloudletStatusTables.size()) {
            List<Cloudlet> newCloudlets = new ArrayList<>();
            
            while (ARRIVED_CLOUDLETS < cloudletStatusTables.size()) {
                CloudletStatusTable task = cloudletStatusTables.get(ARRIVED_CLOUDLETS);
                if (task.isScheduled() || currentTime < task.getArrivalTime()) {
                    break;
                }
                
                dynamicCloudletArrived = true;
                System.out.println("Newly Arrived Task Length= " + task.getLength() + " Arrival Time=" + task.getArrivalTime());
                Cloudlet cloudlet = createCloudlet(ARRIVED_CLOUDLETS, (long) task.getLength());
                task.setCloudlet(cloudlet);
                newCloudlets.add(cloudlet);
                waitingCloudletList.add(cloudlet);
            }

            if (!newCloudlets.isEmpty()) {
                cloudletlist.addAll(newCloudlets);
                broker.submitCloudletList(newCloudlets);
                System.out.println(newCloudlets.size() + " New Arrived cloudlets are going for mapping");
                filler();
                LAST_INTERRUPT_TIME = currentTime;
                waitingCloudletList.clear();
            }

            if (HCSP_MODE && !HCSP_INTERVAL_WIDENED
                    && ARRIVED_CLOUDLETS >= cloudletStatusTables.size()) {
                datacenter.setSchedulingInterval(HCSP_POST_ARRIVAL_INTERVAL);
                HCSP_INTERVAL_WIDENED = true;
                System.out.printf(java.util.Locale.ROOT,
                        "HCSP post-arrival scheduling interval widened to %.3f seconds at t=%.3f%n",
                        HCSP_POST_ARRIVAL_INTERVAL, simulation.clock());
            }
        }
    }

    private boolean checkAllVmShareNegative() {
        if (vmStatusTables == null) return true;
        
        for (VMStatusTable object : vmStatusTables) {
            if (object.getVMShare() >= 0) {
                return false;
            }
        }
        return true;
    }

    private static double VmAvgRt(List<Cloudlet> list, long VmId) {
        if (list == null || list.isEmpty()) return 0;
        
        int count = 0;
        double art = 0;
        for (Cloudlet cloudlet : list) {
            if (cloudlet.getVm() != null && cloudlet.getVm().getId() == VmId) {
                art += cloudlet.getExecStartTime();
                count++;
            }
        }
        return count > 0 ? art / count : 0;
    }

    private static Vm getVmWithMakeSpane(List<Cloudlet> cList, List<Vm> vList) {
        if (cList == null || vList == null) return null;
        
        Vm maxVm = null;
        double maxMakeSpane = 0.0;
        for (Vm vm : vList) {
            double vmMakeSpane = getVmMakeSpane(cList, vm.getId());
            if (vmMakeSpane > maxMakeSpane) {
                maxMakeSpane = vmMakeSpane;
                maxVm = vm;
            }
        }
        return maxVm;
    }

    private static double getVmMakeSpane(List<Cloudlet> list, long VmId) {
        if (list == null) return 0;
        
        double mkspane = 0;
        for (Cloudlet cloudlet : list) {
            if (cloudlet.getVm() != null && cloudlet.getVm().getId() == VmId) {
                if (cloudlet.getFinishTime() > mkspane) {
                    mkspane = cloudlet.getFinishTime();
                }
            }
        }
        return mkspane;
    }

    private static Double getAvgResourceUtilizationRatio(List<Vm> vList, List<Cloudlet> cList) {
        if (vList == null || cList == null || vList.isEmpty()) return 0.0;
        
        double sum = 0.0;
        for (Vm vm : vList) {
            sum += getVmMakeSpane(cList, vm.getId());
        }
        double avgMakeSpane = sum / vList.size();
        Vm maxVm = getVmWithMakeSpane(cList, vList);
        double maxMakeSpane = maxVm != null ? getVmMakeSpane(cList, maxVm.getId()) : 0;
        return maxMakeSpane > 0 ? avgMakeSpane / maxMakeSpane : 0;
    }

    private VMStatusTable getVMStatusTableInstance(Vm vm) {
        if (vm == null || vmStatusTables == null) return null;
        
        for (VMStatusTable object : vmStatusTables) {
            if (object.getVMID() == vm.getId()) {
                return object;
            }
        }
        return null;
    }

    private Double VM_energy_consumption(List<Cloudlet> newList, long VmId) {
        if (newList == null) return 0.0;
        
        double power = 0;
        double totalTime = 0;
        double energy = 0;
        for (Cloudlet cloudlet : newList) {
            if (cloudlet.getVm() != null && cloudlet.getVm().getId() == VmId) {
                double cpuUtil = cloudlet.getUtilizationOfCpu();
                double cpuIdleTime = cloudlet.getExecStartTime();
                double cpuFinishTime = cloudlet.getFinishTime();
                totalTime += cpuFinishTime - cpuIdleTime;
                power = (1 - cpuUtil) * 100 + cpuUtil * 150;
                energy += (power * (cpuFinishTime - cpuIdleTime) / 1000);
            }
        }
        total_energy += energy;
        return energy;
    }

    // Updated saveResult method to include cost and execution time parameters
    public static void saveResult(String filesize, double throughput, double art, double arur, double makespane, double te, double ae, double rejectionRatio, double totalCost, double avgCost, double totalExecTime, double avgExecTime) {
        File file = new File(pathForExcelFile);
        File parent = file.getAbsoluteFile().getParentFile();
        if (parent != null && !parent.exists() && !parent.mkdirs()) {
            System.err.println("Could not create results directory: " + parent);
            return;
        }
        Workbook workbook;
        Sheet sheet;

        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                workbook = new XSSFWorkbook(fis);
                sheet = workbook.getSheetAt(0);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        } else {
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Results SLA Dynamic MaxMin");
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Size");
            header.createCell(1).setCellValue("Throughput");
            header.createCell(2).setCellValue("Average Response Time");
            header.createCell(3).setCellValue("ARUR");
            header.createCell(4).setCellValue("Makespane");
            header.createCell(5).setCellValue("Total Energy");
            header.createCell(6).setCellValue("Average Total Energy");
            header.createCell(7).setCellValue("Job Rejection Ratio");
            header.createCell(8).setCellValue("Total Execution Cost");
            header.createCell(9).setCellValue("Average Execution Cost");
            header.createCell(10).setCellValue("Total Execution Time");
            header.createCell(11).setCellValue("Average Execution Time");
        }

        int rowCount = sheet.getLastRowNum();
        Row row = sheet.createRow(rowCount + 1);
        row.createCell(0).setCellValue(filesize);
        row.createCell(1).setCellValue(throughput);
        row.createCell(2).setCellValue(art);
        row.createCell(3).setCellValue(arur);
        row.createCell(4).setCellValue(makespane);
        row.createCell(5).setCellValue(te);
        row.createCell(6).setCellValue(ae);
        row.createCell(7).setCellValue(rejectionRatio);
        row.createCell(8).setCellValue(totalCost);
        row.createCell(9).setCellValue(avgCost);
        row.createCell(10).setCellValue(totalExecTime);
        row.createCell(11).setCellValue(avgExecTime);

        try (FileOutputStream fos = new FileOutputStream(pathForExcelFile)) {
            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


