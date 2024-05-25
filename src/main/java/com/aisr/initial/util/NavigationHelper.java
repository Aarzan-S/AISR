package com.aisr.initial.util;

import com.aisr.initial.Main;
import com.aisr.initial.controller.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/*
    This class will be useful to change scenes as we
    progress through different logics
 */
public class NavigationHelper {
    public static void navigate(ActionEvent event, String sceneName) {
        try {
            Parent loginPage = FXMLLoader.load(Main.class.getResource(sceneName));
            Scene scene = new Scene(loginPage);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Could not find resource");
            throw new RuntimeException(e);
        }
    }

    public static void navigate(ActionEvent event, String userRole, String userName) {
        System.out.println("userRole : "+ userRole + " userName : "+ userName);
        try {
            switch (userRole) {
                case "Staff" -> {
                    FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/Staff.fxml"));
                    Parent staffPage = loader.load();
                    StaffController staffController = loader.getController();
                    staffController.setUserInfo(userName, userRole);
                    staffController.setWelcomeMessage();
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(staffPage));
                    stage.show();
                }
                case "Management" -> {
                    FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/Management.fxml"));
                    Parent staffPage = loader.load();
                    ManagementController mngtController = loader.getController();
                    mngtController.setUserInfo(userName, userRole);
                    mngtController.setWelcomeMessage();
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(staffPage));
                    stage.show();
                }
                case "Admin" -> {
                    FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/Admin.fxml"));
                    Parent staffPage = loader.load();
                    AdminController adminController = loader.getController();
                    adminController.setUserInfo(userName, userRole);
                    adminController.setWelcomeMessage();
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(staffPage));
                    stage.show();
                }
            }
        } catch (IOException e) {
            System.out.println("Could not find resource");
            throw new RuntimeException(e);
        }
    }

    public static void navigateToRecruit(ActionEvent event, String userRole, String userName) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/RecruitRegistration.fxml"));
            Parent staffPage = loader.load();
            RecruitRegistrationController registrationController = loader.getController();
            registrationController.setUserInfo(userName, userRole);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(staffPage));
            stage.show();
        } catch (IOException e) {
            System.out.println("Could not find resource");
            throw new RuntimeException(e);
        }
    }

    public static void navigateToViewRecruit(ActionEvent event, String userRole, String userName) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/ViewRecruit.fxml"));
            Parent staffPage = loader.load();
            ViewRecruitController viewController = loader.getController();
            viewController.setUserInfo(userName, userRole);
            viewController.init();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(staffPage));
            stage.show();
        } catch (IOException e) {
            System.out.println("Could not find resource");
            throw new RuntimeException(e);
        }
    }
}
