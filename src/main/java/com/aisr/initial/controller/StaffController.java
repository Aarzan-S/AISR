package com.aisr.initial.controller;

import com.aisr.initial.util.Constants;
import com.aisr.initial.util.NavigationHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class handles mainly Management and Admin registration
 */
public class StaffController implements Controller {
    String userName;
    String userRole;
    @FXML
    Label welcomeLabel;

    /**
     * Initializes userName, userRole, initialize welcomeLabel
     * @param userName name of the logged-in user
     * @param userRole
     */
    @Override
    public void setUp(String userName, String userRole) {
        this.userName = userName;
        this.userRole = userRole;
        this.welcomeLabel.setText("Welcome, " + userName);
    }

    /**
     * This is empty implementation of setUp method.
     * Can be updated as per need
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * Navigates to management registration page
     */
    @FXML
    private void showManagementRegistration() {
        NavigationHelper.navigate("view/" + Constants.MANAGEMENT_REGISTRATION, userName, userRole);
    }

    /**
     * Navigates to admin registration page
     */
    @FXML
    private void showAdminRegistration() {
        NavigationHelper.navigate("view/" + Constants.ADMIN_REGISTRATION, userName, userRole);
    }

    /**
     * This method is bind to 'View History' button, and it navigates to recruit history page.
     */
    @FXML
    private void showRecruitHistory() {
        NavigationHelper.navigate("view/" + Constants.RECRUIT_HISTORY, userName, userRole);
    }

    /**
     * Navigates to login page.
     */
    @FXML
    private void logOut() {
        NavigationHelper.navigate("view/" + Constants.LOGIN_PAGE, "", "");
    }


}
