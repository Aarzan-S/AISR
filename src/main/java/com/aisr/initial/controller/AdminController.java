package com.aisr.initial.controller;

import com.aisr.initial.model.*;
import com.aisr.initial.presenter.AdminPresenter;
import com.aisr.initial.util.routing.NavigationHelper;
import com.aisr.initial.view.IView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class handles all the functions of Admin Staff
 */
public class AdminController implements Controller, IView<AdminStaff> {
    private static final String LOGIN_PAGE = "Login.fxml";
    private static final String RECRUIT_HISTORY = "History.fxml";
    private static final String RECRUIT_REGISTRATION_PAGE = "RecruitRegistration.fxml";
    private static final String VIEW_RECRUIT_PAGE = "ViewRecruit.fxml";
    private AdminPresenter presenter;
    private String userName;

    private String userRole;

    @FXML
    private Label welcomeLabel;

    /**
     * Initializes userName and userRole for this class and initializes welcomeLabel for UI
     *
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
     * This is empty implementation, which can be implemented as per need
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        IModel<AdminStaff> model = new AdminStaffModel();
        presenter = new AdminPresenter(model, this);
        model.connect();
    }

    /**
     * Navigates to recruit registration page
     */
    @FXML
    private void regRecruit() {
        NavigationHelper.navigate("view/" + RECRUIT_REGISTRATION_PAGE, userName, userRole);
    }

    /**
     * Navigates to recruit details page.
     */
    @FXML
    private void viewRecruitDetails() {
        NavigationHelper.navigate("view/" + VIEW_RECRUIT_PAGE, userName, userRole);
    }

    /**
     * Navigates to recruit history page.
     */
    @FXML
    private void showRecruitHistory() {
        NavigationHelper.navigate("view/" + RECRUIT_HISTORY, userName, userRole);
    }

    /**
     * Navigates to login page.
     */
    @FXML
    private void logout() {
        NavigationHelper.navigate("view/" + LOGIN_PAGE, "", "");
    }

    @Override
    public void display(AdminStaff object) {

    }

    @Override
    public void display(String message, String type) {

    }

    @Override
    public void clearInputs() {

    }
}