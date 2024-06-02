package com.aisr.initial.controller;

import com.aisr.initial.Main;
import com.aisr.initial.model.AdminStaff;
import com.aisr.initial.model.AdminStaffModel;
import com.aisr.initial.model.IModel;
import com.aisr.initial.presenter.AdminRegistrationPresenter;
import com.aisr.initial.util.routing.NavigationHelper;
import com.aisr.initial.util.validator.NumberFieldValidator;
import com.aisr.initial.view.IView;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class handles all the tasks related to Admin Registration
 */
public class AdminRegistrationController implements Controller, IView<AdminStaff> {
    public static final String STAFF_PAGE = "Staff.fxml";

    private AdminRegistrationPresenter presenter;
    private String userName;
    private String userRole;
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
    private Label messageLabel;
    @FXML
    private Button addAdminBtn;
    @FXML
    private Button registerAdminBtn;

    /**
     * initialize userName and userRole for this class
     *
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
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IModel<AdminStaff> model = new AdminStaffModel();
        presenter = new AdminRegistrationPresenter(model, this);
        model.connect();
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
        NavigationHelper.navigate("view/" + STAFF_PAGE, userName, userRole);
    }

    /**
     * Handles the adding of management staff details
     * Handles white spaces, email and phone number validation.
     * Validates uniqueness of username,email and phone so that there is no duplicate/redundant entry.
     * AdminStaff Object is created and added to adminList upon successful validation/
     */
    @FXML
    private void addAdminDetails() {
        AdminStaff adminDetails = new AdminStaff(adminStaffFullName.getText().trim()
                , adminStaffAddress.getText().trim()
                , Long.parseLong(adminStaffPhone.getText())
                , adminStaffEmail.getText().trim()
                , adminStaffUsername.getText().trim()
                , adminStaffPassword.getText().trim()
                , "Admin-" + Main.noOfEntries
                , adminStaffPositionType.getValue().trim()
        );
        presenter.add(adminDetails);
    }

    /**
     * Saves Admin information in the file
     *
     * @throws Exception
     */
    @FXML
    void registerAdminStaff() throws Exception {
        presenter.register();
    }

    @Override
    public void display(AdminStaff object) {

    }

    @Override
    public void display(String message, String type) {
        if ("INFO".equals(type)) {
            messageLabel.setStyle("-fx-text-fill: green;");
            messageLabel.setText(message);
        } else {
            messageLabel.setStyle("-fx-text-fill: red;");
            messageLabel.setText(message);
        }
    }

    /**
     * Clears all the input fields.
     */
    @Override
    public void clearInputs() {
        messageLabel.setText("");
        adminStaffFullName.clear();
        adminStaffAddress.clear();
        adminStaffPhone.clear();
        adminStaffEmail.clear();
        adminStaffUsername.clear();
        adminStaffPassword.clear();
        adminStaffPositionType.setValue(null);
    }

}
