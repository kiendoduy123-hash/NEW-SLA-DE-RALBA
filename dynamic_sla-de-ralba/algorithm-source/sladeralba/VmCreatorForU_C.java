package sladeralba;

import java.util.ArrayList;
import java.util.List;

import org.cloudbus.cloudsim.schedulers.cloudlet.CloudletSchedulerSpaceShared;
import org.cloudbus.cloudsim.vms.Vm;
import org.cloudbus.cloudsim.vms.VmSimple;

public class VmCreatorForU_C {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static List<VMStatusTable> createVM_u_c_hihi(int VM_PES) {
		List<VMStatusTable> vmStatusTables = new ArrayList<>();
		List<Vm> vmList = new ArrayList<>();
		long size = 1000; // image size (MB)
		int ram = 512; // vm memory (MB)
		int mips = 0;
		int id = 0;
		long bw = 1000;
		String vmm = "Xen"; // VMM name

		VMStatusTable vmStatusTable = new VMStatusTable();
		Vm vm;

		mips = 222 ;
		id = 0;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 1;
		mips = 226 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 2;
		mips = 333 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 3;
		mips = 345 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 4;
		mips = 424 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 5;
		mips = 424 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 6;
		mips = 460 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 7;
		mips = 511 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 8;
		mips = 553 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 9;
		mips = 669 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 10;
		mips = 742 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 11;
		mips = 781 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 12;
		mips = 982 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 13;
		mips = 1000 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 14;
		mips = 1046 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 15;
		mips = 3000 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		return vmStatusTables;
	}

	
	public static List<VMStatusTable> createVM_u_c_hilo(int VM_PES) {
		List<VMStatusTable> vmStatusTables = new ArrayList<>();
		List<Vm> vmList = new ArrayList<>();
		long size = 1000; // image size (MB)
		int ram = 512; // vm memory (MB)
		int mips = 0;
		int id = 0;
		long bw = 1000;
		String vmm = "Xen"; // VMM name

		VMStatusTable vmStatusTable = new VMStatusTable();
		Vm vm;

		mips = 605  ;
		id = 0;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 1;
		mips = 634  ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 2;
		mips = 643 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 3;
		mips = 650 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 4;
		mips = 794 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 5;
		mips = 809 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 6;
		mips = 814 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 7;
		mips = 819 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 8;
		mips = 821 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 9;
		mips = 833 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 10;
		mips = 833 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 11;
		mips = 883 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 12;
		mips = 906 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 13;
		mips = 933 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 14;
		mips = 1488 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 15;
		mips = 3000 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		return vmStatusTables;
	}
	
	public static List<VMStatusTable> createVM_u_c_lohi(int VM_PES) {
		List<VMStatusTable> vmStatusTables = new ArrayList<>();
		List<Vm> vmList = new ArrayList<>();
		long size = 1000; // image size (MB)
		int ram = 512; // vm memory (MB)
		int mips = 0;
		int id = 0;
		long bw = 1000;
		String vmm = "Xen"; // VMM name

		VMStatusTable vmStatusTable = new VMStatusTable();
		Vm vm;

		mips = 21  ;
		id = 0;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 1;
		mips = 21 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 2;
		mips = 22;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 3;
		mips = 27 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 4;
		mips = 29 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 5;
		mips = 30 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 6;
		mips = 32 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 7;
		mips = 37 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 8;
		mips = 43 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 9;
		mips = 52 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 10;
		mips = 55 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 11;
		mips = 102 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 12;
		mips = 141 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 13;
		mips = 167 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 14;
		mips = 641 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 15;
		mips = 7000 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		return vmStatusTables;
	}

	public static List<VMStatusTable> createVM_u_c_lolo(int VM_PES) {

		List<VMStatusTable> vmStatusTables = new ArrayList<>();
		List<Vm> vmList = new ArrayList<>();
		long size = 1000; // image size (MB)
		int ram = 512; // vm memory (MB)
		int mips = 0;
		int id = 0;
		long bw = 1000;
		String vmm = "Xen"; // VMM name

		VMStatusTable vmStatusTable = new VMStatusTable();
		Vm vm;

		mips = 375 ;
		id = 0;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 1;
		mips = 404 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 2;
		mips = 466 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 3;
		mips = 522 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 4;
		mips = 678 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 5;
		mips = 687 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 6;
		mips = 857 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 7;
		mips = 902 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 8;
		mips = 940 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 9;
		mips = 1592 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 10;
		mips = 1835 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 11;
		mips = 2260 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 12;
		mips = 2438 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 13;
		mips = 3000 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 14;
		mips = 3000 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 15;
		mips = 3000 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		return vmStatusTables;
	}

	public static List<Vm> getVmlist(List<VMStatusTable> vmStatusTables) {
		List<Vm> list = new ArrayList<>();
		for (VMStatusTable vmStatusTable : vmStatusTables) {
			list.add(vmStatusTable.getVm());
		}
		return list;
	}

}
