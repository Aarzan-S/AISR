package com.aisr.initial.util;

import com.aisr.initial.Main;
import com.aisr.initial.controller.Controller;
import com.aisr.initial.exception.CustomException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/*
    This class will be useful to change scenes as we
    progress through different pages
 */
public class NavigationHelper {
    private static NavigationHelper instance;
    private static Stage primaryStage;

    private NavigationHelper() {
    }

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    public static void navigate(String fxmlPath, String userName, String userRole) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxmlPath));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            System.out.println("Could not load fxml file");
            throw new CustomException("Could not load fxml file : "+e.getMessage());
        }
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        Controller controller = loader.getController();
        if (controller != null) {
            controller.setUp(userName, userRole);
        }
        primaryStage.show();
    }
}
