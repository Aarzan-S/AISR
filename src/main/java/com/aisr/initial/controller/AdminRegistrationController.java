package com.aisr.initial.controller;

import com.aisr.initial.model.AdminStaff;
import com.aisr.initial.util.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
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

/**
 * This class handles all the tasks related to Admin Registration
 */
public class AdminRegistrationController implements Controller {
    private String userName;
    private String userRole;
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

    /**
     * initialize userName and userRole for this class
     * @param userName name of the logged-in user
     * @param userRole role of the logged-in user e.g. Admin, Management
     */
    @Override
    public void setUp(String userName, String userRole) {
        this.userName = userName;
        this.userRole = userRole;
    }

    /**
     * Initializes necessary fields for the class such as headerLabel, adminStaffPositionType
     * adds validation to phone number fields
     * binds disable property to add button if the input fields are empty
     * @param url
     * @param resourceBundle
     */
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

    /**
     * Navigates to Staff page
     */
    @FXML
    void goBack() {
        NavigationHelper.navigate("view/" + Constants.STAFF_PAGE, userName, userRole);
    }

    /**
     * Handles the adding of management staff details
     * Handles white spaces, email and phone number validation.
     * Validates uniqueness of username,email and phone so that there is no duplicate/redundant entry.
     * AdminStaff Object is created and added to adminList upon successful validation/
     */
    @FXML
    private void addAdminDetails() {
        if (validateWhiteSpace()) return;
        if (!Validator.validatePhoneNumber(adminStaffPhone.getText().trim())) {
            showErrorMessage("Phone Number is not valid.", adminErrorMessage);
            return;
        } else if (!Validator.validateEmail(adminStaffEmail.getText().trim())) {
            showErrorMessage("Email is not valid.", adminErrorMessage);
            return;
        }
        String errorMessage = FileUtil.validateUniqueFields(adminStaffPhone.getText().trim(),
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

    /**
     *  Saves Admin information in the file
     * @throws Exception
     */
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

    /**
     * Clears all the input fields.
     */
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

    /**
     * Validates the input fields against whitespace characters.
     * Returns true if any of the input fields contain only white space otherwise it will return false.
     * @return
     */
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

    /**
     * Shows error message in the specified label.
     * @param message message to be displayed on error label
     * @param label name of the label in ui
     */
    private static void showErrorMessage(String message, Label label) {
        System.err.println(message);
        label.setText(message);
    }

    /**
     * Shows success message on UI for 5 seconds then it will get reset to empty string
     */
    private void showMessage() {
        adminSuccessMessage.setText("Admin details added. Please click Save to persist the data.");
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> adminSuccessMessage.setText("")));
        timeline.setCycleCount(1);
        timeline.play();
    }
}
