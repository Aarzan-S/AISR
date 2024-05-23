package com.aisr.initial.model;

public class AdminStaff extends Staff {
    private String positionType;

    public AdminStaff() {
        super();
    }

    public AdminStaff(String fullName, String address, Long phoneNumber, String email, String username, String password,
                      String staffId, String positionType) {
        super(fullName, address, phoneNumber, email, username, password, staffId);
        this.positionType = positionType;
    }

    public String getPositionType() {
        return positionType;
    }

    public void setPositionType(String positionType) {
        this.positionType = positionType;
    }

    @Override
    public String toString() {
        return String.join(",", "Admin", super.toString(), positionType, "N/A", "N/A");

    }

}
