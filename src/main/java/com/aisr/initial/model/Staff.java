package com.aisr.initial.model;

public class Staff {
    private String fullName;
    private String address;
    private Long phoneNumber;
    private String email;
    private String username;
    private String password;
    private String staffId;

    public Staff() {
    }


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

    @Override
    public String toString() {
        return String.join(",", fullName, address, phoneNumber.toString(), email, username, password, staffId);
    }
}
