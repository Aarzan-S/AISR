package com.aisr.initial.model;

/**
 * This is the base class that represent base class both AdminStaff and ManagementStaff
 */
public class Staff {
    private String fullName;
    private String address;
    private Long phoneNumber;
    private String email;
    private String username;
    private String password;
    private String staffId;

    /**
     * Empty constructor for Staff
     */
    public Staff() {
    }

    public Staff(String username, String password, String staffId) {
        this.username = username;
        this.password = password;
        this.staffId = staffId;
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
     */
    public Staff(String fullName, String address, Long phoneNumber, String email, String username, String password,
                 String staffId) {
        this.fullName = fullName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.username = username;
        this.password = password;
        this.staffId = staffId;
    }

    /**
     * Getter methods to retrieve fields value
     * @return respective fields value
     */
    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getStaffId() {
        return staffId;
    }

    /**
     * Setter methods to set fields value
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    /**
     * Custom command separated toString implementation of Staff
     * @return comma separated string representing Staff object
     */
    @Override
    public String toString() {
        return String.join(",", fullName, address, phoneNumber.toString(), email, username, password, staffId);
    }
}
