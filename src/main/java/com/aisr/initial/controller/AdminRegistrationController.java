package com.aisr.initial.controller;

import com.aisr.initial.model.AdminStaff;
import com.aisr.initial.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminRegistrationController implements Initializable {
    private static final String STAFF_CSV = "staff.csv";
    @FXML
    private Label headerLabel;
    private List<AdminStaff> adminList = new ArrayList<>();
    @FXML
    private TextField adminStaffFullName;
    @FXML
    private TextField adminStaffAddress;
    @FXML
    private TextField adminStaffPhone;
    @FXML
    private TextField adminStaffEmail;
    @FXML
    private TextField adminStaffUsername;
    @FXML
    private TextField adminStaffPassword;
    @FXML
    private ChoiceBox<String> adminStaffPositionType = new ChoiceBox<>();
    @FXML
    private Label adminErrorMessage;
    @FXML
    private Label adminSuccessMessage;
    @FXML
    private Button addAdminBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        headerLabel.setText("Admin Registration Page");
        adminStaffPositionType.getItems().addAll("Full-time", "Part-time", "Volunteer");
        adminStaffPhone.setTextFormatter(new NumberFieldValidator());
    }

    @FXML
    void goBack(ActionEvent event) {
        NavigationHelper.navigate(event, "view/Staff.fxml");
    }

    @FXML
    private void addAdminDetails() {
        if (adminStaffFullName.getText().trim().equalsIgnoreCase("admin")
                || adminStaffFullName.getText().trim().equalsIgnoreCase("superadmin")) {
            System.err.println("Username cannot be admin/superadmin");
            adminErrorMessage.setText("Username cannot be admin/superadmin");
            return;
        }
        if (!Validator.validatePhoneNumber(adminStaffPhone.getText().trim())) {
            System.err.println("Phone Number is not valid.");
            adminErrorMessage.setText("Phone Number is not valid.");
            return;
        } else if (!Validator.validateEmail(adminStaffEmail.getText().trim())) {
            System.err.println("Email is not valid.");
            adminErrorMessage.setText("Email is not valid.");
            return;
        }
        AdminStaff adminDetails = new AdminStaff(adminStaffFullName.getText().trim()
                , adminStaffAddress.getText().trim()
                , Long.parseLong(adminStaffPhone.getText())
                , adminStaffEmail.getText().trim()
                , adminStaffUsername.getText().trim()
                , HashingUtil.generateHash(adminStaffPassword.getText().trim())
                , String.valueOf(Constants.noOfEntries)
                , adminStaffPositionType.getValue().trim()
        );
        adminList.add(adminDetails);
        adminSuccessMessage.setText("Admin details are added. Please register to persist.");
        clearInputFields();
    }

    @FXML
    void registerAdminStaff() throws IOException {
        if (adminList.isEmpty()) {
            adminErrorMessage.setText("There are no admin entries.");
            return;
        }
        File file = new File(STAFF_CSV);
        if (!file.exists()) {
            file.createNewFile();
            FileWriter writer = new FileWriter(STAFF_CSV, true);
            writer.append("Role, Full Name, Address, Phone Number, Email, Username, Password, Staff ID, Position Type," +
                    " Management Level, Branch\n");
            writer.close();
        }
        try (FileWriter writer = new FileWriter(STAFF_CSV, true)) {
            for (AdminStaff adminData : adminList) {
                writer.append(adminData.toString()).append("\n");
                Constants.noOfEntries++;
            }
        } catch (IOException e) {
            System.err.println("Error writing to Staff CSV file: " + e.getMessage());
        }
        this.clearInputFields();
        adminSuccessMessage.setText("Admin data saved successfully");
        System.out.println("Admin data saved successfully.");
    }

    public void clearInputFields() {
        adminErrorMessage.setText("");
        adminStaffFullName.clear();
        adminStaffAddress.clear();
        adminStaffPhone.clear();
        adminStaffEmail.clear();
        adminStaffUsername.clear();
        adminStaffPassword.clear();
        adminStaffPositionType.setValue(null);
    }

}
