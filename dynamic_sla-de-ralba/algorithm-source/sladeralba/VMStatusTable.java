package sladeralba;

import java.util.ArrayList;
import java.util.List;
import org.cloudbus.cloudsim.cloudlets.Cloudlet;
import org.cloudbus.cloudsim.vms.Vm;

public class VMStatusTable {
    int VMID;
    int mips;
    double computationRatio;
    double VMShare;
    double readTime;
    double totalAssignedMI;
    double executedMI;
    double remainingMI;
    double lastUpdatedTime;
    int numberOfTasks;
    double costPerUnitTime; // Added cost parameter
    Vm vm;
    public List<Cloudlet> cloudlets;

    public VMStatusTable() {
        cloudlets = new ArrayList<>();
        lastUpdatedTime = 0;
        readTime = 0;
        totalAssignedMI = 0;
        executedMI = 0;
        remainingMI = 0;
        VMShare = 0;
        numberOfTasks = 0;
        costPerUnitTime = 1.0; // Default cost per unit time
    }

    public VMStatusTable(int vMID, int mips, double computationRatio, double vMShare, double readTime,
            double totalAssignedMI, double executedMI, double remainingMI, double lastUpdatedTime) {
        super();
        VMID = vMID;
        this.mips = mips;
        this.computationRatio = computationRatio;
        VMShare = vMShare;
        this.readTime = readTime;
        this.totalAssignedMI = totalAssignedMI;
        this.executedMI = executedMI;
        this.remainingMI = remainingMI;
        this.lastUpdatedTime = lastUpdatedTime;
        cloudlets = new ArrayList<>();
        numberOfTasks = 0;
        costPerUnitTime = 1.0; // Default cost per unit time
    }

    // Added constructor with cost parameter
    public VMStatusTable(int vMID, int mips, double computationRatio, double vMShare, double readTime,
            double totalAssignedMI, double executedMI, double remainingMI, double lastUpdatedTime, double costPerUnitTime) {
        super();
        VMID = vMID;
        this.mips = mips;
        this.computationRatio = computationRatio;
        VMShare = vMShare;
        this.readTime = readTime;
        this.totalAssignedMI = totalAssignedMI;
        this.executedMI = executedMI;
        this.remainingMI = remainingMI;
        this.lastUpdatedTime = lastUpdatedTime;
        this.costPerUnitTime = costPerUnitTime;
        cloudlets = new ArrayList<>();
        numberOfTasks = 0;
    }

    public Vm getVm() {
        return vm;
    }

    public void setVm(Vm vm) {
        this.vm = vm;
    }

    public int getVMID() {
        return VMID;
    }

    public void setVMID(int vMID) {
        VMID = vMID;
    }

    public int getMips() {
        return mips;
    }

    public void setMips(int mips) {
        this.mips = mips;
    }

    public double getComputationRatio() {
        return computationRatio;
    }

    public void setComputationRatio(double computationRatio) {
        this.computationRatio = computationRatio;
    }

    public double getVMShare() {
        return VMShare;
    }

    public void setVMShare(double vMShare) {
        VMShare = vMShare;
    }

    public double getReadTime() {
        return readTime;
    }

    public void setReadTime(double readTime) {
        this.readTime = readTime;
    }

    public double getTotalAssignedMI() {
        return totalAssignedMI;
    }

    public void setTotalAssignedMI(double totalAssignedMI) {
        this.totalAssignedMI = totalAssignedMI;
    }

    public double getExecutedMI() {
        return executedMI;
    }

    public void setExecutedMI(double executedMI) {
        this.executedMI = executedMI;
    }

    public double getRemainingMI() {
        return remainingMI;
    }

    public void setRemainingMI(double remainingMI) {
        this.remainingMI = remainingMI;
    }

    public double getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(double lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public List<Cloudlet> getCloudlets() {
        return cloudlets;
    }

    public void addCloudlets(Cloudlet cloudlet) {
        this.cloudlets.add(cloudlet);
        numberOfTasks++;
    }

    public int getNumberOfTasks() {
        return numberOfTasks;
    }

    public void setNumberOfTasks(int numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
    }

    // Added getter and setter for cost per unit time
    public double getCostPerUnitTime() {
        return costPerUnitTime;
    }

    public void setCostPerUnitTime(double costPerUnitTime) {
        this.costPerUnitTime = costPerUnitTime;
    }
}