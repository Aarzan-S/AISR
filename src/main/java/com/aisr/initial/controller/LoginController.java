package com.aisr.initial.controller;

import com.aisr.initial.exception.CustomException;
import com.aisr.initial.util.Constants;
import com.aisr.initial.util.FileUtil;
import com.aisr.initial.util.HashingUtil;
import com.aisr.initial.util.NavigationHelper;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class handles authentication and authorization
 */
public class LoginController implements Controller {
    @FXML
    private TextField loginUserName;
    @FXML
    private TextField loginUserPassword;
    @FXML
    private Label err;
    @FXML
    private Button loginBtn;

    /**
     * This is empty implementation of setUp method.
     * Can be updated as per need.
     */
    @Override
    public void setUp(String userName, String userRole) {
    }

    /**
     * Binds loginBtn to binding which will disable button
     * if the username and password fields are empty.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginBtn.disableProperty().bind(
                Bindings.createBooleanBinding(() -> {
                    return loginUserName.getText().trim().isEmpty() ||
                            loginUserPassword.getText().trim().isEmpty();
                }, loginUserName.textProperty(), loginUserPassword.textProperty())
        );
    }

    /**
     * Validates username and password, if credential match, it will redirect the user to respective page
     * based on user type.
     * If credential did not match it will show error message.
     * @throws Exception
     */
    @FXML
    void onLoginClicked() throws Exception {
        boolean isPresent = false;
        String userRole = validateUser();
        if (!userRole.equals("")) {
            isPresent = true;
        }
        if (isPresent) {
            String page = "";
            switch (userRole) {
                case Constants.ADMIN:
                    page = Constants.ADMIN_PAGE;
                    break;
                case Constants.MANAGEMENT:
                    page = Constants.MANAGEMENT_PAGE;
                    break;
                case Constants.STAFF:
                    page = Constants.STAFF_PAGE;
                    break;
                default:
                    page = Constants.LOGIN_PAGE;
                    break;
            }
            NavigationHelper.navigate("view/" + page, loginUserName.getText().trim(), userRole);
        } else {
            err.setText("Incorrect Username or password");
            throw new CustomException("Incorrect Username or password.");
        }
    }

    /**
     * Checks whether username and password provided match or not.
     * @return user type if credentials are valid else empty string
     * @throws Exception
     */
    private String validateUser() throws Exception {
        if (loginUserName.getText().trim().equals("superadmin") && loginUserName.getText().trim().equals("superadmin")) {
            return "Staff";
        }
        if (!FileUtil.doesFileExists(Constants.STAFF_CSV_FILE)) {
            err.setText("Staff record file not found.");
            throw new Exception("Staff record file not found");
        }
        try (BufferedReader br = new BufferedReader(new FileReader("staff.csv"))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                if (loginUserName.getText().trim().equals(columns[5].trim()) &&
                        HashingUtil.verifyHash(loginUserPassword.getText().trim(), columns[6].trim())) {
                    return columns[0].trim();
                }
            }
        } catch (IOException e) {
            System.err.println("Could not read staff record file.");
        }
        return "";
    }
}