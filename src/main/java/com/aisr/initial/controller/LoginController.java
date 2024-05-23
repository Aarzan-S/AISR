package com.aisr.initial.controller;

import com.aisr.initial.util.NavigationHelper;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField loginUserName;
    @FXML
    private TextField loginUserPassword;
    @FXML
    private Label err;
    @FXML
    private Button loginBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginBtn.disableProperty().bind(
                Bindings.isEmpty(loginUserName.textProperty())
                        .or(Bindings.isEmpty(loginUserPassword.textProperty()))
        );
    }

    @FXML
    void onLoginClicked(ActionEvent event) throws Exception {
        boolean isPresent = false;
        String userRole = "";
        if (loginUserName.getText().trim().equals("superadmin") && loginUserName.getText().trim().equals("superadmin")) {
            userRole = "Staff";
            NavigationHelper.navigate(event, userRole, loginUserName.getText().trim());
            return;
        }
        File file = new File("staff.csv");
        if (!file.exists()) {
            err.setText("Staff record file not found.");
            throw new Exception("Staff record file not found");
        }

        try (BufferedReader br = new BufferedReader(new FileReader("staff.csv"))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                if (loginUserName.getText().trim().equals(columns[5].trim()) &&
                        loginUserPassword.getText().trim().equals(columns[6].trim())) {
                    isPresent = true;
                    userRole = columns[0].trim();
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Could not read staff record file.");
        }

        if (isPresent) {
            NavigationHelper.navigate(event, userRole, loginUserName.getText().trim());
        } else {
            err.setText("Incorrect Username or password");
            throw new Exception("Incorrect Username or password.");
        }
    }
}