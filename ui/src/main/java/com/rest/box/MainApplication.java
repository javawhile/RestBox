package com.rest.box;

import com.rest.box.constants.Scenes;
import com.rest.box.utils.FXMLFetcher;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) {
        Pane mainPane = FXMLFetcher.getAsPane(Scenes.restMain);
        if (mainPane != null) {
            stage.setScene(new Scene(mainPane));
            stage.show();
        } else {
            System.err.println("Error while starting application: resources not found");
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
