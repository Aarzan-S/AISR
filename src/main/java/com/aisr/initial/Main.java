package com.aisr.initial;

import com.aisr.initial.util.Constants;
import com.aisr.initial.util.FileUtil;
import com.aisr.initial.util.NavigationHelper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This the main entry point of the application
 */
public class Main extends Application {
    /**
     * Starts the JavaFX application with primary stage
     *
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/" + Constants.LOGIN_PAGE));
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
        FileUtil.countNoOfRecords(Constants.STAFF_CSV_FILE);
        launch();
    }
}