package com.aisr.initial.model;

/**
 * This class represents Management properties
 */
public class ManagementStaff extends Staff {
    private String level;
    private String branch;

    /**
     * Empty constructor for ManagementStaff
     */
    public ManagementStaff() {
        super();
    }

    /**
     * Parameterized constructor containing all fields
     *
     * @param fullName
     * @param address
     * @param phoneNumber
     * @param email
     * @param username
     * @param password
     * @param staffId
     * @param level
     * @param branch
     */
    public ManagementStaff(String fullName, String address, Long phoneNumber, String email, String username, String password,
                           String staffId, String level, String branch) {
        super(fullName, address, phoneNumber, email, username, password, staffId);
        this.level = level;
        this.branch = branch;
    }

    /**
     * Getter methods to retrieve fields value
     * @return respective fields value
     */

    public String getLevel() {
        return level;
    }

    public String getBranch() {
        return branch;
    }

    /**
     * Setter methods to set fields value
     */
    public void setLevel(String level) {
        this.level = level;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    /**
     * Custom comma separated toString implementation of ManagementStaff
     * @return comma separated string representing ManagementStaff object
     */
    @Override
    public String toString() {
        return String.join(",",  super.toString(), "N/A", level, branch);
    }
}
