package com.example.animeapi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = loader.load();

        // Set up the scene
        Scene scene = new Scene(root, 1200, 650);

        Image icon = new Image(getClass().getResourceAsStream("/com/example/animeapi/logo.png"));
        primaryStage.getIcons().add(icon);
        scene.getStylesheets().add(getClass().getResource("/com/example/animeapi/styles.css").toExternalForm());


        primaryStage.setTitle("Anime Info Viewer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

