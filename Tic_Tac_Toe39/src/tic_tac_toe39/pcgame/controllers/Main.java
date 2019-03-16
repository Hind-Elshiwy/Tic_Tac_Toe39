package tic_tac_toe39.pcgame.controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/tic_tac_toe39/pcgame/views/sceneHome.fxml"));

        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.getIcons().add(new Image("/game/resources/Spaceship.png"));
        primaryStage.sizeToScene();

        primaryStage.setTitle("Tic Tac Toe39");
        primaryStage.show();
        primaryStage.toFront();
    }
}