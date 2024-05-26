package com.aisr.initial.controller;

import com.aisr.initial.model.ManagementStaff;
import com.aisr.initial.util.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ManagementRegistrationController implements Controller {
    private String userName;
    private String userRole;
    List<ManagementStaff> managementList = new ArrayList<>();

    @FXML
    private Label messageLabel;
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
    public void setUp(String userName, String userRole) {
        this.userName = userName;
        this.userRole = userRole;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        messageLabel.setText("Management Registration Page");
        managementStaffManagementLevel.getItems().addAll("Senior Manager", "Mid-level Manager", "Supervisor");
        managementStaffBranch.getItems().addAll("Brisbane", "Adelaide", "Sydney", "Melbourne");
        managementStaffPhone.setTextFormatter(new NumberFieldValidator());
        addManagementBtn.disableProperty().bind(
                Bindings.isEmpty(managementStaffFullname.textProperty())
                        .or(Bindings.isEmpty(managementStaffAddress.textProperty()))
                        .or(Bindings.isEmpty(managementStaffPhone.textProperty()))
                        .or(Bindings.isEmpty(managementStaffEmail.textProperty()))
                        .or(Bindings.isEmpty(managementStaffUsername.textProperty()))
                        .or(Bindings.isEmpty(managementStaffPassword.textProperty()))
                        .or(Bindings.isNull(managementStaffManagementLevel.valueProperty()))
                        .or(Bindings.isNull(managementStaffBranch.valueProperty()))
        );
    }

    @FXML
    void goBack() {
        NavigationHelper.navigate("view/" + Constants.STAFF_PAGE, userName, userRole);
    }

    @FXML
    private void addManagementDetails() {
        if (validateWhiteSpace()) return;
        if (!Validator.validatePhoneNumber(managementStaffPhone.getText().trim())) {
            System.err.println("Phone Number is not valid.");
            managementErrorMessage.setText("Phone Number is not valid.");
            return;
        } else if (!Validator.validateEmail(managementStaffEmail.getText().trim())) {
            System.err.println("Email is not valid.");
            managementErrorMessage.setText("Email is not valid.");
            return;
        }
        String errorMessage = FileUtil.validateUniqueFields(managementStaffPhone.getText().trim(),
                managementStaffEmail.getText().trim(), managementStaffUsername.getText().trim(),
                Constants.STAFF_CSV_FILE);
        if (!errorMessage.isEmpty()) {
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
        showMessage();
        clearInputFields();
    }

    @FXML
    private void registerManagementStaff(ActionEvent event) throws Exception {
        if (managementList.isEmpty()) {
            managementSuccessMessage.setText("There are no management entries.");
            return;
        }
        FileUtil.addStaffData(Constants.STAFF_CSV_FILE, managementList);
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

    private boolean validateWhiteSpace() {
        if (managementStaffFullname.getText().isBlank() ||
                managementStaffAddress.getText().isBlank() ||
                managementStaffPhone.getText().isBlank() ||
                managementStaffEmail.getText().isBlank() ||
                managementStaffUsername.getText().isBlank() ||
                managementStaffPassword.getText().isBlank() ||
                managementStaffManagementLevel.getValue() == null ||
                managementStaffBranch.getValue() == null) {
            managementErrorMessage.setText("Please enter all fields.");
            return true;
        }
        return false;
    }

    private static void showErrorMessage(String message, Label label) {
        System.err.println(message);
        label.setText(message);
    }

    private void showMessage() {
        managementSuccessMessage.setText("Management details added. Please Save to persist the data.");
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> managementSuccessMessage.setText("")));
        timeline.setCycleCount(1);
        timeline.play();
    }
}
