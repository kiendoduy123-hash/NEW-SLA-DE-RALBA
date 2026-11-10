package sladeralba;

import java.util.ArrayList;
import java.util.List;

import org.cloudbus.cloudsim.schedulers.cloudlet.CloudletSchedulerSpaceShared;
import org.cloudbus.cloudsim.vms.Vm;
import org.cloudbus.cloudsim.vms.VmSimple;

public class VMCreator {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
    }

    public static List<VMStatusTable> createVms(int VM_PES, int VMS) {
        List<VMStatusTable> list = new ArrayList<>();
        long size = 10000; // image size (Megabyte)
        int ram = 512; // vm memory (Megabyte)
        long bw = 1000;
        int baseMips = 500;

        for (int i = 0; i < VMS; i++) {
            VMStatusTable vmStatusTable = new VMStatusTable();
            Vm vm;
            double costPerUnitTime; // Cost per unit time for each VM type
            
            switch (i % 8) {
                case 0:
                    vm = new VmSimple(i, baseMips - 200, VM_PES).setRam(ram).setBw(bw).setSize(size)
                            .setCloudletScheduler(new CloudletSchedulerSpaceShared());
                    vmStatusTable.setMips(baseMips - 200);
                    costPerUnitTime = 0.5; // Lowest performance, lowest cost
                    break;
                case 1:
                    vm = new VmSimple(i, baseMips, VM_PES).setRam(ram).setBw(bw).setSize(size)
                            .setCloudletScheduler(new CloudletSchedulerSpaceShared());
                    vmStatusTable.setMips(baseMips);
                    costPerUnitTime = 0.75;
                    break;
                case 2:
                    vm = new VmSimple(i, baseMips + 50, VM_PES).setRam(ram).setBw(bw).setSize(size)
                            .setCloudletScheduler(new CloudletSchedulerSpaceShared());
                    vmStatusTable.setMips(baseMips + 50);
                    costPerUnitTime = 1.0;
                    break;
                case 3:
                    vm = new VmSimple(i, baseMips * 2, VM_PES).setRam(ram).setBw(bw).setSize(size)
                            .setCloudletScheduler(new CloudletSchedulerSpaceShared());
                    vmStatusTable.setMips(baseMips * 2);
                    costPerUnitTime = 2.0; // High performance, high cost
                    break;
                case 4:
                    vm = new VmSimple(i, baseMips + 300, VM_PES).setRam(ram).setBw(bw).setSize(size)
                            .setCloudletScheduler(new CloudletSchedulerSpaceShared());
                    vmStatusTable.setMips(baseMips + 300);
                    costPerUnitTime = 1.5;
                    break;
                case 5:
                    vm = new VmSimple(i, baseMips + 150, VM_PES).setRam(ram).setBw(bw).setSize(size)
                            .setCloudletScheduler(new CloudletSchedulerSpaceShared());
                    vmStatusTable.setMips(baseMips + 150);
                    costPerUnitTime = 1.25;
                    break;
                case 6:
                    vm = new VmSimple(i, baseMips + 350, VM_PES).setRam(ram).setBw(bw).setSize(size)
                            .setCloudletScheduler(new CloudletSchedulerSpaceShared());
                    vmStatusTable.setMips(baseMips + 350);
                    costPerUnitTime = 1.75;
                    break;
                case 7:
                    vm = new VmSimple(i, baseMips, VM_PES).setRam(ram).setBw(bw).setSize(size)
                            .setCloudletScheduler(new CloudletSchedulerSpaceShared());
                    vmStatusTable.setMips(baseMips);
                    costPerUnitTime = 0.8;
                    break;
                default:
                    vm = new VmSimple(i, baseMips, VM_PES).setRam(ram).setBw(bw).setSize(size)
                            .setCloudletScheduler(new CloudletSchedulerSpaceShared());
                    vmStatusTable.setMips(baseMips);
                    costPerUnitTime = 1.0;
                    break;
            }

            vmStatusTable.setVm(vm);
            vmStatusTable.setVMID(i);
            vmStatusTable.setCostPerUnitTime(costPerUnitTime); // Set the cost per unit time
            list.add(vmStatusTable);
        }

        return list;
    }
}