package sladeralba;

import java.util.ArrayList;
import java.util.List;

import org.cloudbus.cloudsim.schedulers.cloudlet.CloudletSchedulerSpaceShared;
import org.cloudbus.cloudsim.vms.Vm;
import org.cloudbus.cloudsim.vms.VmSimple;

public class VmCreatorForU_I {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static List<VMStatusTable> createVM_u_i_hihi(int VM_PES) {
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

		mips = 226;
		id = 0;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 1;
		mips = 277 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 2;
		mips = 281 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 3;
		mips = 310 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 4;
		mips = 311 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 5;
		mips = 441 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 6;
		mips = 493 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 7;
		mips = 509  ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 8;
		mips = 531 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 9;
		mips = 559 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 10;
		mips = 568 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 11;
		mips = 766 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 12;
		mips = 855 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 13;
		mips = 1516 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 14;
		mips = 2405 ;
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

	
	public static List<VMStatusTable> createVM_u_i_hilo(int VM_PES) {
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

		mips = 396;
		id = 0;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 1;
		mips = 447;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 2;
		mips = 543;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 3;
		mips = 543;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 4;
		mips = 606;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 5;
		mips = 621;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 6;
		mips = 622;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 7;
		mips = 641;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 8;
		mips = 720;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 9;
		mips = 741;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 10;
		mips = 966;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 11;
		mips = 980;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 12;
		mips = 1610;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 13;
		mips = 1630;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 14;
		mips = 1944;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 15;
		mips = 3000;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		return vmStatusTables;
	}
	
	public static List<VMStatusTable> createVM_u_i_lohi(int VM_PES) {
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

		mips = 74;
		id = 0;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 1;
		mips = 90;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 2;
		mips = 90;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 3;
		mips = 93;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 4;
		mips = 100;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 5;
		mips = 103 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 6;
		mips = 118 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 7;
		mips = 122 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 8;
		mips = 140 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 9;
		mips = 192 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 10;
		mips = 214 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 11;
		mips = 226 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 12;
		mips = 353 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 13;
		mips = 375 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 14;
		mips = 2795 ;
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

	public static List<VMStatusTable> createVM_u_i_lolo(int VM_PES) {

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

		mips = 508 ;
		id = 0;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 1;
		mips = 532 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 2;
		mips = 567 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 3;
		mips = 697 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 4;
		mips = 707 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 5;
		mips = 827 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 6;
		mips = 911 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 7;
		mips = 1006 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 8;
		mips = 1991 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 9;
		mips = 1349 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 10;
		mips = 1368 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 11;
		mips = 1410 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 12;
		mips = 1608 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 13;
		mips = 1746 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 14;
		mips = 1793 ;
		vm = new VmSimple(id, mips, VM_PES).setRam(ram).setBw(bw).setSize(size)
				.setCloudletScheduler(new CloudletSchedulerSpaceShared());
		vmStatusTable = new VMStatusTable();
		vmStatusTable.setMips(mips);
		vmStatusTable.setVm(vm);
		vmStatusTable.setVMID(id);
		vmStatusTables.add(vmStatusTable);

		id = 15;
		mips = 2000 ;
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
