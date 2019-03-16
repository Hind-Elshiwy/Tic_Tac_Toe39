/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe39;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author ahmed
 */
public class GameTypeSceneBase extends AnchorPane {

        protected final ImageView imageView;
        protected final ImageView imageView0;
        protected final ImageView imageView1;
        protected final ImageView imageView2;
        protected final ImageView imageView3;
        protected final ImageView imageView4;
        protected final ImageView imageView5;
        protected final ImageView imageView6;
        protected final ImageView imageView7;
        protected final Button pcPlayButton;
        protected final Button multiButton;
        protected final Button networkButton;
        protected final Button recordsButton;
        protected final Label connect;

        public GameTypeSceneBase(Stage mainStage) {

            imageView = new ImageView();
            imageView0 = new ImageView();
            imageView1 = new ImageView();
            imageView2 = new ImageView();
            imageView3 = new ImageView();
            imageView4 = new ImageView();
            imageView5 = new ImageView();
            imageView6 = new ImageView();
            imageView7 = new ImageView();
            pcPlayButton = new Button();
            multiButton = new Button();
            networkButton = new Button();
            recordsButton = new Button();
            connect = new Label();

            setId("AnchorPane");
            setPrefHeight(400.0);
            setPrefWidth(600.0);

            imageView.setFitHeight(400.0);
            imageView.setFitWidth(600.0);
            imageView.setPickOnBounds(true);
            imageView.setImage(new Image(getClass().getResource("Images/typebg.png").toExternalForm()));

            imageView0.setFitHeight(81.0);
            imageView0.setFitWidth(99.0);
            imageView0.setLayoutX(31.0);
            imageView0.setLayoutY(31.0);
            imageView0.setPickOnBounds(true);
            imageView0.setPreserveRatio(true);
            imageView0.setImage(new Image(getClass().getResource("Images/guest.png").toExternalForm()));

            imageView1.setFitHeight(150.0);
            imageView1.setFitWidth(200.0);
            imageView1.setLayoutX(349.0);
            imageView1.setLayoutY(14.0);
            imageView1.setPickOnBounds(true);
            imageView1.setPreserveRatio(true);

            imageView2.setFitHeight(81.0);
            imageView2.setFitWidth(99.0);
            imageView2.setLayoutX(112.0);
            imageView2.setLayoutY(31.0);
            imageView2.setPickOnBounds(true);
            imageView2.setPreserveRatio(true);
            imageView2.setImage(new Image(getClass().getResource("Images/pc.png").toExternalForm()));

            imageView3.setFitHeight(81.0);
            imageView3.setFitWidth(99.0);
            imageView3.setLayoutX(349.0);
            imageView3.setLayoutY(31.0);
            imageView3.setPickOnBounds(true);
            imageView3.setPreserveRatio(true);
            imageView3.setImage(new Image(getClass().getResource("Images/guest.png").toExternalForm()));

            imageView4.setFitHeight(81.0);
            imageView4.setFitWidth(99.0);
            imageView4.setLayoutX(450.0);
            imageView4.setLayoutY(31.0);
            imageView4.setPickOnBounds(true);
            imageView4.setPreserveRatio(true);
            imageView4.setImage(new Image(getClass().getResource("Images/guest.png").toExternalForm()));

            imageView5.setFitHeight(81.0);
            imageView5.setFitWidth(99.0);
            imageView5.setLayoutX(170.0);
            imageView5.setLayoutY(200.0);
            imageView5.setPickOnBounds(true);
            imageView5.setPreserveRatio(true);
            imageView5.setImage(new Image(getClass().getResource("Images/guest.png").toExternalForm()));

            imageView6.setFitHeight(81.0);
            imageView6.setFitWidth(99.0);
            imageView6.setLayoutX(338.0);
            imageView6.setLayoutY(200.0);
            imageView6.setPickOnBounds(true);
            imageView6.setPreserveRatio(true);
            imageView6.setImage(new Image(getClass().getResource("Images/guest.png").toExternalForm()));

            imageView7.setFitHeight(50.0);
            imageView7.setFitWidth(166.0);
            imageView7.setLayoutX(217.0);
            imageView7.setLayoutY(220.0);
            imageView7.setPickOnBounds(true);
            imageView7.setPreserveRatio(true);
            imageView7.setImage(new Image(getClass().getResource("Images/lan.png").toExternalForm()));

            pcPlayButton.setLayoutX(80.0);
            pcPlayButton.setLayoutY(130.0);
            pcPlayButton.setMnemonicParsing(false);
            pcPlayButton.setText("Play Wth PC");

            
            pcPlayButton.addEventHandler(ActionEvent.ACTION, (event) ->  {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/tic_tac_toe39/pcgame/views/sceneHome.fxml"));
                    Stage window =  (Stage) ((Node)event.getSource()).getScene().getWindow();
                    window.setScene(new Scene(root));
                    window.show();
                } catch (IOException ex) {
                    Logger.getLogger(Tic_Tac_Toe39.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            multiButton.setLayoutX(384.0);
            multiButton.setLayoutY(130.0);
            multiButton.setMnemonicParsing(false);
            multiButton.setText("Multiplayer Mode");

            networkButton.setLayoutX(227.0);
            networkButton.setLayoutY(298.0);
            networkButton.setMnemonicParsing(false);
            networkButton.setText("Network PLay Mode");
            
            recordsButton.setLayoutX(500.0);
            recordsButton.setLayoutY(365.0);
            recordsButton.setMnemonicParsing(false);
            recordsButton.setText("My Records");
            
            connect.setLayoutX(200.0);
            connect.setLayoutY(350.0);
            connect.setTextFill(javafx.scene.paint.Color.valueOf("#f2eeee"));
            connect.setFont(new Font("FreeSerif Bold Italic", 20.0));
            connect.setText("");
            connect.setText("");

            getChildren().add(imageView);
            getChildren().add(imageView0);
            getChildren().add(imageView1);
            getChildren().add(imageView2);
            getChildren().add(imageView3);
            getChildren().add(imageView4);
            getChildren().add(imageView5);
            getChildren().add(imageView6);
            getChildren().add(imageView7);
            getChildren().add(pcPlayButton);
            getChildren().add(multiButton);
            getChildren().add(networkButton);
            getChildren().add(recordsButton);
            getChildren().add(connect);

        }
    }
