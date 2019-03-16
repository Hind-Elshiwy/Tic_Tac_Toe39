/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe39;

/**
 *
 * @author ahmed
 */

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

   public class PlayRecord extends AnchorPane implements Runnable {
        Stage stage;
        Thread th;
        protected final GridPane gridPane;
        protected final ColumnConstraints columnConstraints;
        protected final ColumnConstraints columnConstraints0;
        protected final ColumnConstraints columnConstraints1;
        protected final RowConstraints rowConstraints;
        protected final RowConstraints rowConstraints0;
        protected final RowConstraints rowConstraints1;
        protected final ImageView imageView;
        protected final ImageView imageView0;
        protected final Label label;
        protected final Label label0;
        protected final Label x_turn;
        protected final Label y_turn;
        protected final  Button back;
        protected String player1 = "You";
        protected String player2 = "Opponent";
        protected boolean gameOver = false;
        protected int nFilled = 0;
        protected char whoseTurn = 'X'; // 'X' or 'O'
        protected Cell[][] cell =  new Cell[3][3];
        protected String record; 

        public PlayRecord(Stage mainStage, String p1, String p2, String rec) {
            record = rec;
            player1 = p1;
            player2 = p2;
            stage = mainStage;
            gridPane = new GridPane();
            columnConstraints = new ColumnConstraints();
            columnConstraints0 = new ColumnConstraints();
            columnConstraints1 = new ColumnConstraints();
            rowConstraints = new RowConstraints();
            rowConstraints0 = new RowConstraints();
            rowConstraints1 = new RowConstraints();
            imageView = new ImageView();
            imageView0 = new ImageView();
            label = new Label();
            label0 = new Label();
            x_turn = new Label();
            y_turn = new Label();
            back = new Button();
            setId("AnchorPane");
            setPrefHeight(450.0);
            setPrefWidth(700.0);

            gridPane.setLayoutX(162.0);
            gridPane.setPrefHeight(450.0);
            gridPane.setPrefWidth(377.0);

            columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
            columnConstraints.setMinWidth(10.0);
            columnConstraints.setPrefWidth(100.0);

            columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
            columnConstraints0.setMinWidth(10.0);
            columnConstraints0.setPrefWidth(100.0);

            columnConstraints1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
            columnConstraints1.setMinWidth(10.0);
            columnConstraints1.setPrefWidth(100.0);

            rowConstraints.setMinHeight(10.0);
            rowConstraints.setPrefHeight(30.0);
            rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

            rowConstraints0.setMinHeight(10.0);
            rowConstraints0.setPrefHeight(30.0);
            rowConstraints0.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

            rowConstraints1.setMinHeight(10.0);
            rowConstraints1.setPrefHeight(30.0);
            rowConstraints1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    gridPane.add(cell[i][j] = new Cell(Integer.toString(i) + Integer.toString(j)), j, i);

            imageView.setFitHeight(140.0);
            imageView.setFitWidth(134.0);
            imageView.setLayoutX(14.0);
            imageView.setLayoutY(14.0);
            imageView.setPickOnBounds(true);
            imageView.setPreserveRatio(true);
            imageView.setImage(new Image(getClass().getResource("Images/guest.png").toExternalForm()));

            imageView0.setFitHeight(140.0);
            imageView0.setFitWidth(134.0);
            imageView0.setLayoutX(552.0);
            imageView0.setLayoutY(219.0);
            imageView0.setPickOnBounds(true);
            imageView0.setPreserveRatio(true);
            imageView0.setImage(new Image(getClass().getResource("Images/guest.png").toExternalForm()));

            label.setLayoutX(28.0);
            label.setLayoutY(159.0);
            label.setText(player2);

            label0.setLayoutX(583.0);
            label0.setLayoutY(373.0);
            label0.setText(player1);

            y_turn.setLayoutX(30.0);
            y_turn.setLayoutY(190.0);
            y_turn.setTextFill(Color.web("#0076a3"));
            y_turn.setText("Y Player");

            x_turn.setLayoutX(585.0);
            x_turn.setLayoutY(405.0);
            x_turn.setTextFill(Color.web("#0076a3"));
            x_turn.setText("X Player");
            
            back.setLayoutX(50.0);
            back.setLayoutY(450.0);
            back.setMnemonicParsing(false);
            back.setText("Go Back");
            
            gridPane.getColumnConstraints().add(columnConstraints);
            gridPane.getColumnConstraints().add(columnConstraints0);
            gridPane.getColumnConstraints().add(columnConstraints1);
            gridPane.getRowConstraints().add(rowConstraints);
            gridPane.getRowConstraints().add(rowConstraints0);
            gridPane.getRowConstraints().add(rowConstraints1);
            getChildren().add(gridPane);
            getChildren().add(imageView);
            getChildren().add(imageView0);
            getChildren().add(label);
            getChildren().add(label0);
            getChildren().add(x_turn);
            getChildren().add(y_turn);
            getChildren().add(back);
            
            th = new Thread(this);
            th.start();
        }
        
        public void run(){
                String[] moves = record.split(",");
                for(String i : moves){
                    String id = i.split("-")[0];
                    char mark = i.split("-")[1].charAt(0);
                    System.out.println(id+" "+mark);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            Cell clicked_cell = (Cell) cell[0][0].getScene().lookup("#"+id);
                            clicked_cell.setToken(mark);
                        }
                    });    
                    try{
                            Thread.sleep(1500);
                    }
                    catch (InterruptedException e){
                            e.printStackTrace();
                    }
                }
            }

        public class Cell extends Pane {

            private char token = ' ';   // one of blank, X, or O

            public Cell(String id) {
                setStyle("-fx-background-color: #88ff1e; -fx-border-color: blue");
                setHeight(139.0);
                setWidth(118.0);
                setId(id);
            }

            public char getToken() {
                return token;
            }

            public void drawX() {
                ImageView x_image = new ImageView();
                x_image.setFitHeight(130.0);
                x_image.setFitWidth(110.0);
                x_image.setX(8.0f);
                x_image.setY(20.0f);
                x_image.setPickOnBounds(true);
                x_image.setPreserveRatio(true);
                x_image.setImage(new Image(getClass().getResource("Images/x.png").toExternalForm()));
                getChildren().add(x_image); 
            }

            public void drawO() {
                ImageView o_image = new ImageView();
                o_image.setFitHeight(130.0);
                o_image.setFitWidth(110.0);
                o_image.setX(8.0f);
                o_image.setY(20.0f);
                o_image.setPickOnBounds(true);
                o_image.setPreserveRatio(true);
                o_image.setImage(new Image(getClass().getResource("Images/tic-tac-toe-O.png").toExternalForm()));
                this.getChildren().add(o_image); 
            }

            public void setToken(char c) {
                if (c == 'X')
                    drawX();
                else
                    drawO();
                token = c;
                nFilled ++;
            }                    
    }
}       
