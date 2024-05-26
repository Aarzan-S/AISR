package com.aisr.initial.controller;

import com.aisr.initial.util.Constants;
import com.aisr.initial.util.NavigationHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ManagementController implements Controller {
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
    private void viewRecruitDetails() {
        NavigationHelper.navigate("view/" + Constants.VIEW_RECRUIT_PAGE, userName, userRole);
    }

    @FXML
    private void logout() {
        NavigationHelper.navigate("view/" + Constants.LOGIN_PAGE, "", "");
    }

}
