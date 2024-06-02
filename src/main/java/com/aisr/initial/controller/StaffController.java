package com.aisr.initial.controller;

import com.aisr.initial.model.*;
import com.aisr.initial.presenter.StaffPresenter;
import com.aisr.initial.util.routing.NavigationHelper;
import com.aisr.initial.view.IView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class handles mainly Management and Admin registration
 */
public class StaffController implements Controller, IView<Staff> {
    private static final String LOGIN_PAGE = "Login.fxml";
    private static final String RECRUIT_HISTORY = "History.fxml";
    private static final String ADMIN_REGISTRATION = "AdminRegistration.fxml";
    private static final String MANAGEMENT_REGISTRATION = "ManagementRegistration.fxml";

    private StaffPresenter presenter;
    private String userName;
    private String userRole;
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
        IModel<Staff> model = new StaffModel();
        presenter = new StaffPresenter(model, this);
        model.connect();
    }

    /**
     * Navigates to management registration page
     */
    @FXML
    private void showManagementRegistration() {
        NavigationHelper.navigate("view/" + MANAGEMENT_REGISTRATION, userName, userRole);
    }

    /**
     * Navigates to admin registration page
     */
    @FXML
    private void showAdminRegistration() {
        NavigationHelper.navigate("view/" + ADMIN_REGISTRATION, userName, userRole);
    }

    /**
     * This method is bind to 'View History' button, and it navigates to recruit history page.
     */
    @FXML
    private void showRecruitHistory() {
        NavigationHelper.navigate("view/" + RECRUIT_HISTORY, userName, userRole);
    }

    /**
     * Navigates to login page.
     */
    @FXML
    private void logOut() {
        NavigationHelper.navigate("view/" + LOGIN_PAGE, "", "");
    }


    @Override
    public void display(Staff object) {

    }

    @Override
    public void display(String message, String type) {
    }

    @Override
    public void clearInputs() {

    }
}
