package com.aisr.initial.util;

/**
 * This class contains all the constant fields that are used in the project
 */
public class Constants {
    public static final String ADMIN = "Admin";
    public static final String STAFF = "Staff";
    public static final String MANAGEMENT = "Management";
    public static final String EMAIL_VALIDATION_REGEX = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
    public static final String PHONE_NO_VALIDATION_REGEX = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
    public static int noOfEntries = 1;
    public static final String STAFF_CSV_FILE = "staff.csv";
    public static final String RECRUIT_CSV_FILE = "recruit.csv";
    public static final String STAFF_CSV_HEADER = "Role,Full Name,Address,Phone Number,Email,Username,Password,Staff ID," +
            "Position Type,Management Level,Branch";
    public static final String RECRUIT_CSV_HEADER = "Full Name,Address,Phone Number,Email,Username,Password,Interview Date," +
            "Qualification Level,Department,Location,Recruited By,Recruited On";
    public static final String LOGIN_PAGE = "Login.fxml";
    public static final String STAFF_PAGE = "Staff.fxml";
    public static final String MANAGEMENT_REGISTRATION = "ManagementRegistration.fxml";
    public static final String ADMIN_REGISTRATION = "AdminRegistration.fxml";
    public static final String ADMIN_PAGE = "Admin.fxml";
    public static final String MANAGEMENT_PAGE = "Management.fxml";
    public static final String RECRUIT_REGISTRATION_PAGE = "RecruitRegistration.fxml";
    public static final String VIEW_RECRUIT_PAGE = "ViewRecruit.fxml";
    public static final String RECRUIT_HISTORY = "History.fxml";

}
