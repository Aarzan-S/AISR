package com.aisr.initial.controller;

import com.aisr.initial.model.Recruit;
import com.aisr.initial.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RecruitRegistrationController implements Initializable {
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

    List<Recruit> recruitList = new ArrayList<>();

    String userName;
    String userRole;

    public void setUserInfo(String userName, String userRole) {
        this.userName = userName;
        this.userRole = userRole;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        recruitQualificationLevel.getItems().addAll("Bachelors", "Masters", "PhD");
        recruitPhone.setTextFormatter(new NumberFieldValidator());

    }

    @FXML
    private void navigateBack(ActionEvent event) {
        NavigationHelper.navigate(event, userRole, userName);
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
        String errorMessage = FileUtil.checkForDuplicates(recruitPhone.getText(), recruitEmail.getText(),
                recruitUsername.getText(), Constants.RECRUIT_CSV_FILE);
        if (!errorMessage.isEmpty()) {
            showErrorMessage(errorMessage, recruitErrorMessage);
            return;
        }

        Recruit recruitData = new Recruit(recruitFullname.getText(),
                recruitAddress.getText(),
                recruitPhone.getText(),
                recruitEmail.getText(),
                recruitUsername.getText(),
                recruitPassword.getText(),
                recruitInterviewDate.getValue().toString(),
                recruitQualificationLevel.getValue(),
                "N/A",
                "N/A",
                this.userName,
                DateTimeUtil.formatCurrentDate(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        this.recruitList.add(recruitData);
        clearInputFields();
        recruitSuccessMessage.setText("Recruit data is added. Please click Save to persist the data.");
    }

    @FXML
    private void saveRecuiterInfoForAssigning(ActionEvent event) throws IOException {
        if (recruitList.isEmpty()) {
            recruitErrorMessage.setText("There are no recruit entries.");
            return;
        }
        File file = new File(Constants.RECRUIT_CSV_FILE);
        if (!file.exists()) {
            file.createNewFile();
            FileWriter writer = new FileWriter(Constants.RECRUIT_CSV_FILE, true);
            writer.append("Full Name,Address,Phone Number,Email,Username,Password,Interview Date,Qualification Level," +
                    "Department,Location,Recruited By,Recruited On\n");
            writer.close();
        }
        try (FileWriter writer = new FileWriter(Constants.RECRUIT_CSV_FILE, true)) {
            for (Recruit recruitData : recruitList) {
                writer.append(recruitData.toString());
                writer.append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to Recruit CSV file: " + e.getMessage());
        }
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

}
