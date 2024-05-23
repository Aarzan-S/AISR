package com.aisr.initial.util;

import com.aisr.initial.Main;
import com.aisr.initial.controller.StaffController;
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
        try {
            switch (userRole) {
                case "Staff":
                    FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/Staff.fxml"));
                    Parent staffPage = loader.load();
                    StaffController staffController = loader.getController();
                    staffController.setUserInfo(userName, userRole);
                    staffController.setWelcomeMessage();
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setTitle("AIS-R".concat(" Staff Page"));
                    stage.setScene(new Scene(staffPage));
                    stage.show();
                    break;
                case "Management":

                    break;
                case "Admin":

                    break;
            }
        } catch (IOException e) {
            System.out.println("Could not find resource");
            throw new RuntimeException(e);
        }
    }
}
