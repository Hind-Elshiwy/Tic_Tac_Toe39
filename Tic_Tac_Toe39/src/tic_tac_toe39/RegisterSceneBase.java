/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe39;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author ahmed
 */
public class RegisterSceneBase extends AnchorPane {

        protected final ImageView imageView;
        protected final ImageView imageView0;
        protected final TextField usernameField;
        protected final PasswordField passwordField;
        protected final Button regButton;
        protected final DropShadow dropShadow;
        protected final ImageView imageView1;
        protected final Button loginButton;
        protected final DropShadow dropShadow0;
        protected final Label label;
        protected final Label regMsg;
        protected final Bloom bloom;
        public Stage stage;

        public RegisterSceneBase(Stage mainStage) {
            stage = mainStage;
            imageView = new ImageView();
            imageView0 = new ImageView();
            usernameField = new TextField();
            passwordField = new PasswordField();
            regButton = new Button();
            dropShadow = new DropShadow();
            imageView1 = new ImageView();
            loginButton = new Button();
            dropShadow0 = new DropShadow();
            label = new Label();
            regMsg = new Label();
            bloom = new Bloom();

            setId("AnchorPane");
            setPrefHeight(400.0);
            setPrefWidth(600.0);        

            imageView.setFitHeight(408.0);
            imageView.setFitWidth(611.0);
            imageView.setPickOnBounds(true);
            imageView.setPreserveRatio(true);
            imageView.setImage(new Image(getClass().getResource("Images/24330e81085.jpg").toExternalForm()));

            imageView0.setFitHeight(150.0);
            imageView0.setFitWidth(200.0);
            imageView0.setLayoutX(394.0);
            imageView0.setLayoutY(25.0);
            imageView0.setPickOnBounds(true);
            imageView0.setPreserveRatio(true);
            imageView0.setStyle("-fx-z-index: 999;");
            imageView0.setImage(new Image(getClass().getResource("Images/tttmarqlrg.gif").toExternalForm()));

            usernameField.setLayoutX(409.0);
            usernameField.setLayoutY(178.0);
            usernameField.setPromptText("Username");

            passwordField.setLayoutX(409.0);
            passwordField.setLayoutY(223.0);
            passwordField.setPromptText("Password");

            regButton.setLayoutX(463.0);
            regButton.setLayoutY(273.0);
            regButton.setMnemonicParsing(false);
            regButton.setStyle("-fx-background-color: #00dc27;");
            regButton.setText("Register");
            regButton.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
            regButton.setTextFill(javafx.scene.paint.Color.valueOf("#530fdb"));
            regButton.setWrapText(true);
            regButton.setFont(new Font("FreeSerif Bold Italic", 13.0));
            
            regMsg.setLayoutX(350.0);
            regMsg.setLayoutY(310.0);
            regMsg.setTextFill(javafx.scene.paint.Color.valueOf("#f2eeee"));
            regMsg.setFont(new Font("FreeSerif Bold Italic", 13.0));
            regMsg.setText("");

            regButton.setEffect(dropShadow);

            imageView1.setFitHeight(90.0);
            imageView1.setFitWidth(179.0);
            imageView1.setLayoutX(422.0);
            imageView1.setLayoutY(72.0);
            imageView1.setPickOnBounds(true);
            imageView1.setPreserveRatio(true);
            imageView1.setImage(new Image(getClass().getResource("Images/Join-us.png").toExternalForm()));

            loginButton.setLayoutX(167.0);
            loginButton.setLayoutY(369.0);
            loginButton.setMnemonicParsing(false);
            loginButton.setStyle("-fx-background-color: #00dc27;");
            loginButton.setText("Log in Now");
            loginButton.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
            loginButton.setTextFill(javafx.scene.paint.Color.valueOf("#530fdb"));
            loginButton.setWrapText(true);
            loginButton.setFont(new Font("FreeSerif Bold Italic", 13.0));

            loginButton.setEffect(dropShadow0);

            label.setLayoutX(98.0);
            label.setLayoutY(317.0);
            label.setText("Already Have An Account ? ");
            label.setTextFill(javafx.scene.paint.Color.valueOf("#f2eeee"));
            label.setFont(new Font("Amiri Bold Slanted", 21.0));

            bloom.setThreshold(0.49);
            label.setEffect(bloom);
            regMsg.setEffect(bloom);

            getChildren().add(imageView);
            getChildren().add(imageView0);
            getChildren().add(usernameField);
            getChildren().add(passwordField);
            getChildren().add(regButton);
            getChildren().add(imageView1);
            getChildren().add(loginButton);
            getChildren().add(label);
            getChildren().add(regMsg);

        }
    }
