package com.aisr.initial.model;

public class ManagementStaff extends Staff {
    private String level;
    private String branch;

    public ManagementStaff() {
        super();
    }

    public ManagementStaff(String fullName, String address, Long phoneNumber, String email, String username, String password,
                           String staffId, String level, String branch) {
        super(fullName, address, phoneNumber, email, username, password, staffId);
        this.level = level;
        this.branch = branch;
    }

    public String getLevel() {
        return level;
    }

    public String getBranch() {
        return branch;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        return String.join(",", "Management", super.toString(), "N/A", level, branch);
    }
}
