package com.aisr.initial.model;

import java.time.LocalDate;
/**
 * This class represents Recruit properties
 */
public class Recruit {
    private String fullName;
    private String address;
    private Long phoneNumber;
    private String email;
    private String username;
    private String password;
    private LocalDate interviewDate;
    private String highestQualification;
    private String department;
    private String location;
    private String recruitedBy;
    private LocalDate recruitedOn;

    /**
     * Empty constructor for Recruit
     */
    public Recruit() {
    }

    /**
     * Parameterized constructor containing all fields
     * @param fullName
     * @param address
     * @param phoneNumber
     * @param email
     * @param username
     * @param password
     * @param interviewDate
     * @param highestQualification
     * @param department
     * @param location
     * @param recruitedBy
     * @param recruitedOn
     */
    public Recruit(String fullName, String address, Long phoneNumber, String email,
                   String username, String password, LocalDate interviewDate, String highestQualification, String department,
                   String location, String recruitedBy, LocalDate recruitedOn) {
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

    /**
     * overloaded Parameterized constructor containing few fields
     * @param fullName
     * @param phoneNumber
     * @param email
     * @param username
     * @param recruitedBy
     * @param recruitedOn
     */
    public Recruit(String fullName, Long phoneNumber, String email, String username, String recruitedBy,
                   LocalDate recruitedOn) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.username = username;
        this.recruitedBy = recruitedBy;
        this.recruitedOn = recruitedOn;
    }

    /**
     * Getter methods to retrieve fields value
     * @return respective fields value
     */    public String getFullName() {
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

    public LocalDate getInterviewDate() {
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

    public LocalDate getRecruitedOn() {
        return recruitedOn;
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

    public void setInterviewDate(LocalDate interviewDate) {
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

    public void setRecruitedOn(LocalDate recruitedOn) {
        this.recruitedOn = recruitedOn;
    }

    /**
     * Custom command separated toString implementation of Recruit
     * @return comma separated string representing Recruit object
     */
    @Override
    public String toString() {
        return String.join(",", getFullName(), getAddress(), getPhoneNumber().toString(),
                getEmail(), getUsername(), getPassword(), getInterviewDate().toString(),
                getHighestQualification(), getDepartment(), getLocation(), getRecruitedBy(), getRecruitedOn().toString());
    }
}
