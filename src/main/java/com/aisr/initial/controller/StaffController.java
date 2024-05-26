package com.aisr.initial.controller;

import com.aisr.initial.util.Constants;
import com.aisr.initial.util.NavigationHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class StaffController implements Controller {
    String userName;
    String userRole;
    @FXML
    Label welcomeLabel;

    @Override
    public void setUp(String userName, String userRole) {
        this.userName = userName;
        this.userRole = userRole;
        this.welcomeLabel.setText("Welcome, " + userName);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void showManagementRegistration() {
        NavigationHelper.navigate("view/" + Constants.MANAGEMENT_REGISTRATION, userName, userRole);
    }

    @FXML
    private void showAdminRegistration() {
        NavigationHelper.navigate("view/" + Constants.ADMIN_REGISTRATION, userName, userRole);
    }

    @FXML
    private void logOut() {
        NavigationHelper.navigate("view/" + Constants.LOGIN_PAGE, "", "");
    }

    @FXML
    private void showRecruitHistory() {
        NavigationHelper.navigate("view/" + Constants.RECRUIT_HISTORY, userName, userRole);
    }
}
