package com.aisr.initial.controller;

import com.aisr.initial.model.Recruit;
import com.aisr.initial.util.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
/**
 * THis class handles recruit registration mechanism
 */
public class RecruitRegistrationController implements Controller {
    @FXML
    private TextField recruitFullname;
    @FXML
    private TextField recruitAddress;
    @FXML
    private TextField recruitPhone;
    @FXML
    private TextField recruitEmail;
    @FXML
    private TextField recruitUsername;
    @FXML
    private TextField recruitPassword;
    @FXML
    private DatePicker recruitInterviewDate;
    @FXML
    private ChoiceBox<String> recruitQualificationLevel = new ChoiceBox<>();
    @FXML
    private Label recruitErrorMessage;
    @FXML
    private Label recruitSuccessMessage;
    @FXML
    private Button addAnotherRecruitBtn;
    List<Recruit> recruitList = new ArrayList<>();

    String userName;
    String userRole;

    /**
     * initialize userName and userRole for this class
     * @param userName name of the logged-in user
     * @param userRole role of the logged-in user e.g. Admin, Management
     */
    @Override
    public void setUp(String userName, String userRole) {
        this.userName = userName;
        this.userRole = userRole;
    }

    /**
     * It initializes necessary fields for the class such as headerLabel, recruitQualificationLevel
     * It adds validation to phone number field to accept only number and 10 digits only.
     * It also binds validation to date picker to disable past dates and set today's date as default date.
     * It will binds disable property to add button if the input fields are empty
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        recruitQualificationLevel.getItems().addAll("Bachelors", "Masters", "PhD");
        recruitPhone.setTextFormatter(new NumberFieldValidator());
        recruitInterviewDate.setDayCellFactory(dataPicker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(date.isBefore(LocalDate.now()));
            }
        });
        recruitInterviewDate.setValue(LocalDate.now());
        addAnotherRecruitBtn.disableProperty().bind(
                Bindings.isEmpty(recruitFullname.textProperty())
                        .or(Bindings.isEmpty(recruitAddress.textProperty()))
                        .or(Bindings.isEmpty(recruitPhone.textProperty()))
                        .or(Bindings.isEmpty(recruitEmail.textProperty()))
                        .or(Bindings.isEmpty(recruitUsername.textProperty()))
                        .or(Bindings.isEmpty(recruitPassword.textProperty()))
                        .or(Bindings.isNull(recruitInterviewDate.valueProperty()))
                        .or(Bindings.isNull(recruitQualificationLevel.valueProperty()))
        );

    }
    /**
     * Navigates to Admin page.
     */
    @FXML
    private void navigateBack() {
        NavigationHelper.navigate("view/" + Constants.ADMIN_PAGE, userName, userRole);
    }

    /**
     * Handles the adding of recruit details
     * Handles white spaces, email and phone number validation.
     * Validates uniqueness of username,email and phone so that there is no duplicate/redundant entry.
     * Recruit Object is created and added to recruitList upon successful validation/
     */
    @FXML
    private void addAnotherRecruit() {
        if (!Validator.validatePhoneNumber(recruitPhone.getText())) {
            showErrorMessage("Phone Number is not valid.", recruitErrorMessage);
            return;
        } else if (!Validator.validateEmail(recruitEmail.getText())) {
            showErrorMessage("Email is not valid.", recruitErrorMessage);
            return;
        }
        String errorMessage = FileUtil.validateUniqueFields(recruitPhone.getText(), recruitEmail.getText(),
                recruitUsername.getText(), Constants.RECRUIT_CSV_FILE);
        if (!errorMessage.isEmpty()) {
            showErrorMessage(errorMessage, recruitErrorMessage);
            return;
        }

        Recruit recruitData = new Recruit(recruitFullname.getText(),
                recruitAddress.getText(),
                Long.parseLong(recruitPhone.getText()),
                recruitEmail.getText(),
                recruitUsername.getText(),
                recruitPassword.getText(),
                recruitInterviewDate.getValue(),
                recruitQualificationLevel.getValue(),
                "N/A",
                "N/A",
                this.userName,
                LocalDate.now());
        this.recruitList.add(recruitData);
        clearInputFields();
        showMessage();
    }

    /**
     *  Saves Recruit information in the file
     * @throws Exception
     */
    @FXML
    private void saveRecuiterInfoForAssigning(ActionEvent event) throws Exception {
        if (recruitList.isEmpty()) {
            recruitErrorMessage.setText("There are no recruit entries.");
            return;
        }
        FileUtil.addRecruitData(recruitList);
        clearInputFields();
        this.recruitList.clear();
        recruitSuccessMessage.setText("Recruit data has been saved.");
        System.out.println("Recruit data has been saved .");
    }

    /**
     * It will clear all the input fields.
     * It is generally used when Admin details are added to adminList or saved to file.
     */
    public void clearInputFields() {
        recruitErrorMessage.setText("");
        recruitFullname.clear();
        recruitAddress.clear();
        recruitPhone.clear();
        recruitEmail.clear();
        recruitUsername.clear();
        recruitPassword.clear();
        recruitInterviewDate.setValue(null);
        recruitQualificationLevel.setValue(null);
    }

    /**
     * Shows error message in the specified label.
     * @param message message to be displayed on error label
     * @param label name of the label in ui
     */
    private static void showErrorMessage(String message, Label label) {
        System.err.println(message);
        label.setText(message);
    }

    /**
     * Shows success message on UI for 5 seconds then it will get reset to empty string
     */
    private void showMessage() {
        recruitSuccessMessage.setText("Recruit data is added. Please Save to persist the data.");
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> recruitSuccessMessage.setText("")));
        timeline.setCycleCount(1);
        timeline.play();
    }
}
