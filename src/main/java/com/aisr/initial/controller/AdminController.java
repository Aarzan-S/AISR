package com.aisr.initial.controller;

import com.aisr.initial.util.Constants;
import com.aisr.initial.util.NavigationHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Controller {
    private String userName;

    private String userRole;

    @FXML
    private Label welcomeLabel;

    @Override
    public void setUp(String userName, String userRole) {
        this.userName = userName;
        this.userRole = userRole;
        this.welcomeLabel.setText("Welcome, " + userName);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void regRecruit() {
        NavigationHelper.navigate("view/" + Constants.RECRUIT_REGISTRATION_PAGE, userName, userRole);
    }

    @FXML
    private void viewRecruitDetails() {
        NavigationHelper.navigate("view/" + Constants.VIEW_RECRUIT_PAGE, userName, userRole);
    }

    @FXML
    private void showRecruitHistory() {
        NavigationHelper.navigate("view/" + Constants.RECRUIT_HISTORY, userName, userRole);
    }

    @FXML
    private void logout() {
        NavigationHelper.navigate("view/" + Constants.LOGIN_PAGE, "", "");
    }
}