package com.aisr.initial.model;

/**
 * This class represents Admin properties
 */
public class AdminStaff extends Staff {
    private String positionType;

    /**
     * Empty constructor for AdminStaff
     */
    public AdminStaff() {
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
     * @param positionType
     */
    public AdminStaff(String fullName, String address, Long phoneNumber, String email, String username, String password,
                      String staffId, String positionType) {
        super(fullName, address, phoneNumber, email, username, password, staffId);
        this.positionType = positionType;
    }

    /**
     * Getter methods to retrieve fields value
     * @return respective fields value
     */
    public String getPositionType() {
        return positionType;
    }

    /**
     * Setter methods to set fields value
     */
    public void setPositionType(String positionType) {
        this.positionType = positionType;
    }

    /**
     * Custom command separated toString implementation of AdminStaff
     * @return comma separated string representing AdminStaff object
     */
    @Override
    public String toString() {
        return String.join(",", "Admin", super.toString(), positionType, "N/A", "N/A");

    }

}
