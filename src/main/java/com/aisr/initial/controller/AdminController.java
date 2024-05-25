package com.aisr.initial.controller;

import com.aisr.initial.util.NavigationHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    private String userName;

    private String userRole;

    @FXML
    private Label welcomeLabel;


    public void setUserInfo(String userName, String userRole) {
        this.userName = userName;
        this.userRole = userRole;
    }

    public void setWelcomeMessage() {
        this.welcomeLabel.setText("Welcome, " + userName);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void regRecruit(ActionEvent event) {
        NavigationHelper.navigateToRecruit(event, userRole, userName);
    }

    @FXML
    private void viewRecruitDetails(ActionEvent event) {
        // have  to add recruits details page
    }

    @FXML
    private void logout(ActionEvent event) {
        NavigationHelper.navigate(event, "view/Login.fxml");
    }

}