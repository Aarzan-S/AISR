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

    @Override
    public void setUp(String userName, String userRole) {
        this.userName = userName;
        this.userRole = userRole;
    }

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

    @FXML
    private void navigateBack() {
        NavigationHelper.navigate("view/" + Constants.ADMIN_PAGE, userName, userRole);
    }

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

    private static void showErrorMessage(String message, Label label) {
        System.err.println(message);
        label.setText(message);
    }

    private void showMessage() {
        recruitSuccessMessage.setText("Recruit data is added. Please Save to persist the data.");
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> recruitSuccessMessage.setText("")));
        timeline.setCycleCount(1);
        timeline.play();
    }
}
