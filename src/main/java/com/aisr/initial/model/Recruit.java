package com.aisr.initial.model;

import java.time.LocalDate;

public class Recruit {
    private String fullName;
    private String address;
    private String phoneNumber;
    private String email;
    private String username;
    private String password;
    private String interviewDate;
    private String highestQualification;
    private String department;
    private String location;
    private String recruitedBy;
    private String recruitedOn;

    public Recruit() {
    }

    public Recruit(String fullName, String address, String phoneNumber, String email,
                   String username, String password, String interviewDate, String highestQualification, String department,
                   String location, String recruitedBy, String recruitedOn) {
        this.fullName = fullName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.username = username;
        this.password = password;
        this.interviewDate = interviewDate;
        this.highestQualification = highestQualification;
        this.department = department;
        this.location = location;
        this.recruitedBy = recruitedBy;
        this.recruitedOn = recruitedOn;
    }

    // Getter methods
    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
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

    public String getInterviewDate() {
        return interviewDate;
    }

    public String getHighestQualification() {
        return highestQualification;
    }

    public String getDepartment() {
        return department;
    }

    public String getLocation() {
        return location;
    }

    public String getRecruitedBy() {
        return recruitedBy;
    }

    public String getRecruitedOn() {
        return recruitedOn;
    }

    // Setter methods
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
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

    public void setInterviewDate(String interviewDate) {
        this.interviewDate = interviewDate;
    }

    public void setHighestQualification(String highestQualification) {
        this.highestQualification = highestQualification;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setRecruitedBy(String recruitedBy) {
        this.recruitedBy = recruitedBy;
    }

    public void setRecruitedOn(String recruitedOn) {
        this.recruitedOn = recruitedOn;
    }

    @Override
    public String toString() {
        return String.join(",", getFullName(), getAddress(), getPhoneNumber(),
                getEmail(), getUsername(), getPassword(), getInterviewDate(),
                getHighestQualification(), getDepartment(), getLocation(), getRecruitedBy(), getRecruitedOn());
    }
}
