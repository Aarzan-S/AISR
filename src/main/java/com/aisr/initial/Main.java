package com.aisr.initial;

import com.aisr.initial.util.Constants;
import com.aisr.initial.util.FileUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        stage.setTitle("AIS-R Initial");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        FileUtil.countNoOfRecords(Constants.STAFF_CSV_FILE);
        launch();
    }
}