/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe39;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author ahmed
 */
public class LoginSceneBase extends AnchorPane {

        protected final ImageView imageView;
        protected final Label label;
        protected final TextField usernameField;
        protected final PasswordField passwordField;
        protected final Button loginButton;
        protected final Label label0;
        protected final Label loginMsg;
        protected final Glow glow;
        protected final Button registerButton;

        public LoginSceneBase(Stage mainStage) {

            imageView = new ImageView();
            label = new Label();
            usernameField = new TextField();
            passwordField = new PasswordField();
            loginButton = new Button();
            label0 = new Label();
            loginMsg = new Label();
            glow = new Glow();
            registerButton = new Button();

            setId("AnchorPane");
            setPrefHeight(400.0);
            setPrefWidth(600.0);

            registerButton.addEventHandler(ActionEvent.ACTION, (event) ->  {
                      Tic_Tac_Toe39 game = new Tic_Tac_Toe39();
                      game.start(mainStage);
                });

            imageView.setFitHeight(400.0);
            imageView.setFitWidth(600.0);
            imageView.setPickOnBounds(true);
            imageView.setImage(new Image(getClass().getResource("Images/347075.jpg").toExternalForm()));

            label.setLayoutX(238.0);
            label.setLayoutY(80.0);
            label.setPrefHeight(67.0);
            label.setPrefWidth(111.0);
            label.setText("Sign In");
            label.setTextFill(javafx.scene.paint.Color.valueOf("#c5dd37"));
            label.setFont(new Font("Purisa Bold", 25.0));
            
            loginMsg.setText("");
            loginMsg.setTextFill(javafx.scene.paint.Color.valueOf("#c5dd37"));
            loginMsg.setFont(new Font("Purisa Bold", 15.0));
            loginMsg.setLayoutX(150.0);
            loginMsg.setLayoutY(265.0);

            usernameField.setLayoutX(208.0);
            usernameField.setLayoutY(147.0);
            usernameField.setPromptText("Username");

            passwordField.setLayoutX(208.0);
            passwordField.setLayoutY(187.0);
            passwordField.setPromptText("Password");

            loginButton.setLayoutX(263.0);
            loginButton.setLayoutY(235.0);
            loginButton.setMnemonicParsing(false);
            loginButton.setText("Log in");

            label0.setLayoutX(144.0);
            label0.setLayoutY(293.0);
            label0.setPrefHeight(80.0);
            label0.setPrefWidth(299.0);
            label0.setText("Don't Have an Account ?");
            label0.setTextFill(javafx.scene.paint.Color.valueOf("#c5dd37"));
            label0.setFont(new Font("Purisa Bold", 19.0));

            glow.setLevel(1.0);
            label0.setEffect(glow);

            registerButton.setLayoutX(239.0);
            registerButton.setLayoutY(360.0);
            registerButton.setMnemonicParsing(false);
            registerButton.setText("Register Now ");

            getChildren().add(imageView);
            getChildren().add(label);
            getChildren().add(usernameField);
            getChildren().add(passwordField);
            getChildren().add(loginButton);
            getChildren().add(label0);
            getChildren().add(loginMsg); 
            getChildren().add(registerButton);

        }
    }
