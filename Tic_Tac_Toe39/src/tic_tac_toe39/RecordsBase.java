package tic_tac_toe39;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.effect.Blend;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class RecordsBase extends AnchorPane {

    protected final ImageView imageView;
    protected Button[] buttons=new Button[10];
    protected final Blend blend;
    protected final Button back;

    public RecordsBase(Stage stage) {

        imageView = new ImageView();
        blend = new Blend();    
        blend.setMode(javafx.scene.effect.BlendMode.OVERLAY);
        back = new Button();
        
        setId("AnchorPane");
        setPrefHeight(590.0);
        setPrefWidth(623.0);
        
        back.setLayoutX(50.0);
        back.setLayoutY(550.0);
        back.setMnemonicParsing(false);
        back.setText("Go Back");
        
        imageView.setFitHeight(590.0);
        imageView.setFitWidth(623.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(getClass().getResource("Images/records.jpg").toExternalForm()));

        getChildren().add(imageView);
        getChildren().add(back);

    }
}
