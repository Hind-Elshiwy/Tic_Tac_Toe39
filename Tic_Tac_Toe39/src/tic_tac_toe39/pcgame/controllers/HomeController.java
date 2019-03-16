package tic_tac_toe39.pcgame.controllers;

import tic_tac_toe39.pcgame.models.logic.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private Button btnStartGame;
    
    @FXML
    private ImageView imgViewPlayer;

    private static Player player1;
    private static int dimension = 3;
    private static Stage thisStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        player1 = new Player("You", imgViewPlayer.getImage());
        
    }
    
    @FXML
    private void btnStartGame_onAction(ActionEvent event) throws Exception {
        loadGameScene();
    }



    /**
     * Loads the game scene where the game will commence
     * @throws Exception
     */
    private void loadGameScene() throws Exception {

        thisStage = ((Stage) imgViewPlayer.getScene().getWindow());
        thisStage.hide();

        Parent root = FXMLLoader.load(getClass().getResource("/tic_tac_toe39/pcgame/views/sceneGame.fxml"));
        Stage s = new Stage();
        s.setScene(new Scene(root));
        s.centerOnScreen();

        s.setResizable(false);

        s.setTitle("Tic Tac Toe39");
        s.getIcons().add(new Image("/tic_tac_toe39/pcgame/resources/Spaceship.png"));
        s.sizeToScene();

        s.show();
    }

    /**
     *
     * @return The Player 1 instance
     */
    public static Player getPlayer1() {
        return player1;
    }

    /**
     *
     * @return The chosen grid dimension the user has picked
     */
    public static int getDimension() {
       return dimension;
    }

    /**
     *
     * @return The current scene stage
     */
    public static Stage getStage() {
        return thisStage;
    }
}