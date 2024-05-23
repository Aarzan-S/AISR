package com.aisr.initial.controller;

import com.aisr.initial.util.NavigationHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StaffController {
    String userName;
    String userRole;
    @FXML
    Label welcomeText;

    public void setWelcomeMessage() {
        welcomeText.setText("Welcome, "+ this.userName);
    }

    public void setUserInfo(String userName, String userRole) {
        this.userName = userName;
        this.userRole = userRole;
    }
    @FXML
    private void showManagementRegistration(ActionEvent event) {
        NavigationHelper.navigate(event, "view/ManagementRegistration.fxml");
    }

    @FXML
    private void showAdminRegistration(ActionEvent event) {
        NavigationHelper.navigate(event, "view/AdminRegistration.fxml");
    }

    @FXML
    private void logOut(ActionEvent event) {
        NavigationHelper.navigate(event, "view/Login.fxml");
    }
}
