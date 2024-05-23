package com.aisr.initial.controller;

import com.aisr.initial.util.NavigationHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ManagementRegistrationController implements Initializable {
    @FXML
    private Label messageLabel;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        messageLabel.setText("Management Registration Page");
    }

    @FXML
    void goBack(ActionEvent event) {
        NavigationHelper.navigate(event,"view/Staff.fxml");
    }
}
