package com.aisr.initial.controller;

import com.aisr.initial.model.AdminStaff;
import com.aisr.initial.util.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminRegistrationController implements Initializable {
    private List<AdminStaff> adminList = new ArrayList<>();
    @FXML
    private Label headerLabel;
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
        addAdminBtn.disableProperty().bind(
                Bindings.isEmpty(adminStaffFullName.textProperty())
                        .or(Bindings.isEmpty(adminStaffAddress.textProperty()))
                        .or(Bindings.isEmpty(adminStaffPhone.textProperty()))
                        .or(Bindings.isEmpty(adminStaffEmail.textProperty()))
                        .or(Bindings.isEmpty(adminStaffUsername.textProperty()))
                        .or(Bindings.isEmpty(adminStaffPassword.textProperty()))
                        .or(Bindings.isNull(adminStaffPositionType.valueProperty()))

        );
    }

    @FXML
    void goBack(ActionEvent event) {
        NavigationHelper.navigate(event, Constants.STAFF_PAGE);
    }

    @FXML
    private void addAdminDetails() {
        if (validateWhiteSpace()) return;
        if (adminStaffUsername.getText().trim().equalsIgnoreCase("admin")
                || adminStaffUsername.getText().trim().equalsIgnoreCase("superadmin")) {
            showErrorMessage("Username cannot be admin/superadmin", adminErrorMessage);
            return;
        }
        if (!Validator.validatePhoneNumber(adminStaffPhone.getText().trim())) {
            showErrorMessage("Phone Number is not valid.", adminErrorMessage);
            return;
        } else if (!Validator.validateEmail(adminStaffEmail.getText().trim())) {
            showErrorMessage("Email is not valid.", adminErrorMessage);
            return;
        }
        String errorMessage = FileUtil.checkForDuplicates(adminStaffPhone.getText().trim(),
                adminStaffEmail.getText().trim(), adminStaffUsername.getText().trim(), Constants.STAFF_CSV_FILE);
        if (!errorMessage.isEmpty()) {
            showErrorMessage(errorMessage, adminErrorMessage);
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
        showMessage();
        clearInputFields();
    }

    @FXML
    void registerAdminStaff() throws Exception {
        if (adminList.isEmpty()) {
            adminErrorMessage.setText("There are no admin entries.");
            return;
        }
        FileUtil.addStaffData(Constants.STAFF_CSV_FILE, adminList);
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

    private boolean validateWhiteSpace() {
        if (adminStaffFullName.getText().isBlank()
                || adminStaffAddress.getText().isBlank()
                || adminStaffPhone.getText().isBlank()
                || adminStaffEmail.getText().isBlank()
                || adminStaffUsername.getText().isBlank()
                || adminStaffPassword.getText().isBlank()
                || adminStaffPositionType.getValue() == null) {
            adminErrorMessage.setText("Please enter all fields.");
            return true;
        }
        return false;
    }

    private static void showErrorMessage(String message, Label label) {
        System.err.println(message);
        label.setText(message);
    }

    private void showMessage() {
        adminSuccessMessage.setText("Admin details added. Please click Save to persist the data.");
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> adminSuccessMessage.setText("")));
        timeline.setCycleCount(1);
        timeline.play();
    }

}
