package com.aisr.initial.controller;

import com.aisr.initial.util.Constants;
import com.aisr.initial.util.NavigationHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class handles all the functionality of Management page.
 */
public class ManagementController implements Controller {
    private String userName;

    private String userRole;

    @FXML
    private Label welcomeLabel;

    /**
     * Initializes userName,userRole and initialize welcomeLabel.
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
     * This is empty implementation of setUp method
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    /**
     * Navigates to recruit details page.
     */
    @FXML
    private void viewRecruitDetails() {
        NavigationHelper.navigate("view/" + Constants.VIEW_RECRUIT_PAGE, userName, userRole);
    }

    /**
     * Navigates to login page.
     */
    @FXML
    private void logout() {
        NavigationHelper.navigate("view/" + Constants.LOGIN_PAGE, "", "");
    }

}
