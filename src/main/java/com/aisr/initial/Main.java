package com.aisr.initial;

import com.aisr.initial.util.dataAccess.FileUtil;
import com.aisr.initial.util.routing.NavigationHelper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This the main entry point of the application
 */
public class Main extends Application {
    private static final String STAFF_CSV_FILE = "staff.csv";
    private static final String LOGIN_PAGE = "Login.fxml";

    public static int noOfEntries = 0;
    /**
     * Starts the JavaFX application with primary stage
     *
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/" + LOGIN_PAGE));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        NavigationHelper.setPrimaryStage(stage);
        stage.setTitle("AIS-R Initial");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Launches the application
     *
     * @param args
     */
    public static void main(String[] args) {
        FileUtil.countNoOfRecords(STAFF_CSV_FILE);
        launch();
    }
}