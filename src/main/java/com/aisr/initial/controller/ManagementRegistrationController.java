package com.aisr.initial.controller;

import com.aisr.initial.model.ManagementStaff;
import com.aisr.initial.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ManagementRegistrationController implements Initializable {
    @FXML
    private Label messageLabel;
    List<ManagementStaff> managementList = new ArrayList<>();
    @FXML
    private TextField managementStaffFullname;
    @FXML
    private TextField managementStaffAddress;
    @FXML
    private TextField managementStaffPhone;
    @FXML
    private TextField managementStaffEmail;
    @FXML
    private TextField managementStaffUsername;
    @FXML
    private TextField managementStaffPassword;
    @FXML
    private ChoiceBox<String> managementStaffManagementLevel = new ChoiceBox<>();
    @FXML
    private ChoiceBox<String> managementStaffBranch = new ChoiceBox<>();
    @FXML
    private Label managementErrorMessage;
    @FXML
    private Label managementSuccessMessage;
    @FXML
    private Button addManagementBtn;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        messageLabel.setText("Management Registration Page");
        managementStaffManagementLevel.getItems().addAll("Senior Manager", "Mid-level Manager", "Supervisor");
        managementStaffBranch.getItems().addAll("Brisbane", "Adelaide", "Sydney", "Melbourne");
        managementStaffPhone.setTextFormatter(new NumberFieldValidator());
    }

    @FXML
    void goBack(ActionEvent event) {
        NavigationHelper.navigate(event,"view/Staff.fxml");
    }

    @FXML
    private void addManagementDetails() {
        if (managementStaffFullname.getText().trim().equalsIgnoreCase("admin")
                || managementStaffFullname.getText().trim().equalsIgnoreCase("superadmin")) {
            System.err.println("Username cannot be admin/superadmin");
            managementErrorMessage.setText("Username cannot be admin/superadmin");
            return;
        }
        if (!Validator.validatePhoneNumber(managementStaffPhone.getText().trim())) {
            System.err.println("Phone Number is not valid.");
            managementErrorMessage.setText("Phone Number is not valid.");
            return;
        } else if (!Validator.validateEmail(managementStaffEmail.getText().trim())) {
            System.err.println("Email is not valid.");
            managementErrorMessage.setText("Email is not valid.");
            return;
        }
        String errorMessage = FileUtil.checkForDuplicates(managementStaffPhone.getText().trim(),
                managementStaffEmail.getText().trim(), managementStaffUsername.getText().trim(),
                Constants.STAFF_CSV_FILE);
        if (!errorMessage.isEmpty()){
            showErrorMessage(errorMessage, managementErrorMessage);
            return;
        }

        ManagementStaff managementDetails = new ManagementStaff(
                managementStaffFullname.getText(),
                managementStaffAddress.getText(),
                Long.parseLong(managementStaffPhone.getText()),
                managementStaffEmail.getText(),
                managementStaffUsername.getText(),
                HashingUtil.generateHash(managementStaffPassword.getText()),
                String.valueOf(Constants.noOfEntries),
                managementStaffManagementLevel.getValue(),
                managementStaffBranch.getValue()
        );
        managementList.add(managementDetails);
        managementSuccessMessage.setText("Management details are added. Please register to persist.");
        clearInputFields();
    }
    @FXML
    private void registerManagementStaff(ActionEvent event) throws IOException {
        if (managementList.isEmpty()) {
            managementSuccessMessage.setText("There are no management entries.");
            return;
        }
        File file = new File(Constants.STAFF_CSV_FILE);
        if (!file.exists()) {
            file.createNewFile();
            FileWriter writer = new FileWriter(Constants.STAFF_CSV_FILE, true);
            writer.append("Role,Full Name,Address,Phone Number,Email,Username,Password,Staff ID,Position Type," +
                    "Management Level,Branch\n");
            writer.close();
        }
        try (FileWriter writer = new FileWriter(Constants.STAFF_CSV_FILE, true)) {
            for (ManagementStaff managementData : managementList) {
                writer.append(managementData.toString()).append("\n");
                Constants.noOfEntries++;
            }

        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
        clearInputFields();
        managementSuccessMessage.setText("Management data saved successfully");
        System.out.println("Management data saved successfully.");

    }

    public void clearInputFields() {
        managementErrorMessage.setText("");
        managementStaffFullname.clear();
        managementStaffAddress.clear();
        managementStaffPhone.clear();
        managementStaffEmail.clear();
        managementStaffUsername.clear();
        managementStaffPassword.clear();
        managementStaffManagementLevel.setValue(null);
        managementStaffBranch.setValue(null);
    }

    private static void showErrorMessage(String message, Label label){
        System.err.println(message);
        label.setText(message);
    }
}
