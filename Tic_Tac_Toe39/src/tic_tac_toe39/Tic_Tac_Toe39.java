/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe39;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.Optional;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author ahmed
 */
public class Tic_Tac_Toe39 extends Application {
    Client c;
    boolean serverAvilability = false;
    String userName;
    String userPass;
    boolean userSigned = false;
    boolean vaildUser = false;
    Stage stage;
    Thread serverListener;
    boolean con_running = false;
    
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        c = new Client();
        RegisterSceneBase root = new RegisterSceneBase(primaryStage);
        
        // Register Registeration Scene Events
        
        root.regButton.addEventHandler(ActionEvent.ACTION, (event) ->  {
                    String name = root.usernameField.getText();
                    String pass = root.passwordField.getText();
                    root.regButton.setVisible(false);
                    if (!(name.equals("")) && !(pass.equals(""))) {
                        root.regMsg.setText("Connecting...");
                        
                        c.startServerConnection("signup", name, pass, null, root);
                        
                        
                    } else {
                        root.regMsg.setText("Username and Pass cant be empty");
                        root.regButton.setVisible(true);
                    }
                });
        root.loginButton.addEventHandler(ActionEvent.ACTION, (event) ->  {
                    LoginSceneBase login = new LoginSceneBase(primaryStage);
                    
                    login.loginButton.addEventHandler(ActionEvent.ACTION, (event1) ->  {
                    String name = login.usernameField.getText();
                    String pass = login.passwordField.getText();
                    login.loginButton.setVisible(false);
                    if (!(name.equals("")) && !(pass.equals(""))) {
                        login.loginMsg.setText("Connecting...");
                        
                        c.startServerConnection("login", name, pass, login, null);
                        
                        
                    } else {
                        login.loginMsg.setText("Please Enter user Name And Pass");
                        login.loginButton.setVisible(true);
                    }
                });
                    Scene scene = new Scene(login, 600, 400);
                    Stage window =  (Stage) ((Node)event.getSource()).getScene().getWindow();
                    window.setScene(scene);
                    window.show();
                });
        
                root.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    c.logOut(userName, userPass);
                    Platform.exit();
                    System.exit(0);
                }
            });
        // Ends of events
        
        Scene scene = new Scene(root, 600, 400);
        
        primaryStage.setTitle("X : O (39)");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void createRegisterevent(Button btn){
        
    }
    
    void showGameTypes(Stage stage){
        GameTypes game = new GameTypes(stage);
            Scene scene = new Scene(game, 600, 400);
            stage.setScene(scene);
            stage.show();
    }
    
    void createRecordsScene(Stage stage, String[] str){
        RecordsBase records = new RecordsBase(stage);
                Scene scene = new Scene(records, 600, 590);
                stage.setScene(scene);
                int i = 0;
                for(String st : str){
                        String x_player = st.split(" ")[0];
                        String y_player = st.split(" ")[1];
                        String record = st.split(" ")[2];
                        records.buttons[i] = new Button();
                        records.buttons[i].setLayoutX(489.0);
                        records.buttons[i].setLayoutY(14.0+(i*40));
                        records.buttons[i].setMnemonicParsing(false);
                        records.buttons[i].setPrefHeight(37.0);
                        records.buttons[i].setPrefWidth(105.0);
                        records.buttons[i].setText("Record "+(i+1));
                        records.buttons[i].setEffect(records.blend);
                        records.buttons[i].setFont(new Font("FreeMono", 16.0));
                        records.getChildren().add(records.buttons[i]);
                        records.buttons[i].addEventHandler(ActionEvent.ACTION, (event1) ->  {
                            PlayRecord game = new PlayRecord(stage,x_player,y_player,record);
                            game.back.addEventHandler(ActionEvent.ACTION, (event) ->  {
                            showGameTypes(stage);
                            });
                            Scene scene2 = new Scene(game, 700, 600);
                            stage.setScene(scene2);
                            stage.show();
                        });
                        i++;
                        System.out.println(x_player+y_player+record);
                }
                stage.show();
                records.back.addEventHandler(ActionEvent.ACTION, (event1) ->  {
                    showGameTypes(stage);
                });
                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        c.logOut(userName, userPass);
                        Platform.exit();
                        System.exit(0);
                    }
                });
    }
    //***** Iner Classes Of Other Scenes
    
    class GameTypes extends GameTypeSceneBase{
        
        public GameTypes(Stage mainStage) {
            super(mainStage);
            
            multiButton.addEventHandler(ActionEvent.ACTION, (event) ->  {
                    GameSceneBase game = new GameSceneBase(mainStage,"You", "Opponent");
                    game.back.addEventHandler(ActionEvent.ACTION, (event1) ->  {
                        showGameTypes(stage);
                });
                    Scene scene = new Scene(game, 700, 500);
                    Stage window =  (Stage) ((Node)event.getSource()).getScene().getWindow();
                    window.setScene(scene);
                    
                    window.show();
            });
            
            networkButton.addEventHandler(ActionEvent.ACTION, (event) ->  {
                connect.setText("Waiting for other player...");
                 c.ps.println("ready:"+userName);   
                        
            });
            
            recordsButton.addEventHandler(ActionEvent.ACTION, (event) ->  {
                c.ps.println("getrecord:"+userName);            
            });
            
            mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    c.logOut(userName, userPass);
                    Platform.exit();
                    System.exit(0);
                }
            });
        }
        
    }
    
    public class GameOnline extends AnchorPane {
        Stage stage;
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
        protected String player1 = "You";
        protected String player2 = "Opponent";
        protected boolean gameOver = false;
        protected int nFilled = 0;
        protected char whoseTurn = 'X'; // 'X' or 'O'
        protected Cell[][] cell =  new Cell[3][3];
        public Vector<Cell> ocubbied = new Vector<Cell>();
        public String playWith;
        public char clientSign;
        boolean recording = false;
        String record = "";

        public GameOnline(Stage mainStage, String p1, String p2) {
            player1 = p1;
            player2 = p2;
            playWith = (userName.equals(player1)) ? player2: player1;
            System.out.println(playWith);
            clientSign = (userName.equals(player1)) ? 'X': 'O';
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
            y_turn.setText("");

            x_turn.setLayoutX(585.0);
            x_turn.setLayoutY(405.0);
            x_turn.setTextFill(Color.web("#0076a3"));
            x_turn.setText("Your Turn");

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
            
            // Ask For Recording
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Recording");
                    alert.setContentText("Do You want to Record This Game?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                        recording = true;
                    }

        }
        public boolean isFull() {
            return nFilled >= 9; // > should never happen
        }

        public boolean hasWon(char tkn) {
            for (int i = 0; i < 3; i++)
                if (cell[i][0].getToken() == tkn &&
                    cell[i][1].getToken() == tkn &&
                    cell[i][2].getToken() == tkn)
                    return true;
            for (int j = 0; j < 3; j++)
                if (cell[0][j].getToken() == tkn &&
                    cell[1][j].getToken() == tkn &&
                    cell[2][j].getToken() == tkn)
                    return true;
            if (cell[0][0].getToken() == tkn &&
                cell[1][1].getToken() == tkn &&
                cell[2][2].getToken() == tkn)
                return true;
            if (cell[0][2].getToken() == tkn &&
                cell[1][1].getToken() == tkn &&
                cell[2][0].getToken() == tkn)
                return true;
            return false;
        }
        void handleRequest(String cell_id) {
                Cell clicked_cell = (Cell) cell[0][0].getScene().lookup("#"+cell_id);
                clicked_cell.setToken(whoseTurn);
                ocubbied.add(clicked_cell);
                if(recording == true){
                    if(record.equals("")){
                    record = cell_id+"-"+whoseTurn;
                    }else{
                        record += "," + cell_id+"-"+whoseTurn;
                    }
                }     
                if (hasWon(whoseTurn)) {
                    gameOver = true;
                    String winner = whoseTurn == 'X' ? player1 : player2;
                    gameOver = true;
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Result");
                    alert.setHeaderText(null);
                    alert.setContentText(winner+" Has Won");
                    alert.showAndWait();
                    if(recording==true)
                        c.ps.println("record:"+userName+":"+player1+":"+player2+":"+record);
                    showGameTypes(stage);
                }
                else if (isFull()) {
                    gameOver = true;
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Result");
                    alert.setHeaderText(null);
                    alert.setContentText("Draw");
                    alert.showAndWait();
                    if(recording==true)
                        c.ps.println("record:"+userName+":"+player1+":"+player2+":"+record);
                    showGameTypes(stage);
                }
                else {
                    whoseTurn = (whoseTurn == 'X') ? 'O' : 'X';
                    if(whoseTurn == 'X'){
                        x_turn.setText(player1+"'s Turn");
                        y_turn.setText("");
                    }
                    else{
                        y_turn.setText(player2+"'s Turn");
                        x_turn.setText("");
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
                setOnMouseClicked(e -> handleMouseClick1());
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

            private void handleMouseClick1() {
                System.out.println(this.getId());
                System.out.println(whoseTurn);
                System.out.println(clientSign);
                if (!gameOver && !ocubbied.contains(this)) {
                    if(whoseTurn == clientSign){
                        setToken(whoseTurn);
                        ocubbied.add(this);
                        if(recording==true){
                            if(record.equals("")){
                            record = this.getId()+"-"+whoseTurn;
                            }else{
                                record += "," + this.getId() +"-"+whoseTurn;
                            }
                        }
                        c.ps.println("cell:"+this.getId()+":"+playWith);
                        if (hasWon(whoseTurn)) {
                            gameOver = true;
                            String winner = whoseTurn == 'X' ? player1 : player2;
                            gameOver = true;
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Result");
                            alert.setHeaderText(null);
                            alert.setContentText(winner+" Has Won");
                            alert.showAndWait();
                            if(recording==true)
                                c.ps.println("record:"+userName+":"+player1+":"+player2+":"+record);
                            showGameTypes(stage);
                        }
                        else if (isFull()) {
                            gameOver = true;
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Result");
                            alert.setHeaderText(null);
                            alert.setContentText("Draw");
                            alert.showAndWait();
                            if(recording==true)
                                c.ps.println("record:"+userName+":"+player1+":"+player2+":"+record);
                            showGameTypes(stage);
                        }
                        else {
                            whoseTurn = (whoseTurn == 'X') ? 'O' : 'X';
                            if(whoseTurn == 'X'){
                                x_turn.setText(player1+"'s Turn");
                                y_turn.setText("");
                            }
                            else{
                                y_turn.setText(player2+"'s Turn");
                                x_turn.setText("");
                            }
                        }    
                    }
                }
            }
            
        }
        
    }
      
    class Client
	{
            Socket socket;
            DataInputStream dis;
            PrintStream ps;
            GameOnline game;
            String serverIp = "127.0.0.1";
        public Client(){
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Server IP");
            dialog.setHeaderText("Please Enter The Server Ip (Choose Cancel if you own the server)");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                serverIp=result.get();
            }
        }
        public void startServerConnection(String mode, String name, String pass, LoginSceneBase l, RegisterSceneBase r) {
            if (!serverAvilability) {
                new Thread() {
                    public void run() {
                        String responseMsg = null;
                        try {

                            socket = new Socket(serverIp, 5555);
                            dis = new DataInputStream(socket.getInputStream());
                            ps = new PrintStream(socket.getOutputStream());
                            serverAvilability = true;

                            if (mode.equals("login")) {
                                //send login message
                                final String loginMsg = "login:name=" + name + ",pass=" + pass;
                                ps.println(loginMsg);
                            } else if (mode.equals("signup")) {
                                //send signup message
                                final String loginMsg = "signup:name=" + name + ",pass=" + pass;
                                ps.println(loginMsg);
                            }

                            //reade server stream 
                            System.out.println("start read");
                            String reply = dis.readLine();

                            //handel server replay message 
                            String msg = serverMessageHandler(reply);

                            if (mode.equals("login")) {
                                if (msg.equals("false")) {
                                    vaildUser = false;
                                    userSigned = false;
                                    serverAvilability = false;
                                    userName = null;
                                    userPass = null;
                                    responseMsg = "invalid user";
                                } else {
                                    vaildUser = true;
                                    userSigned = false;
                                    userName = name;
                                    userPass = pass;
                                    responseMsg = msg;
                                    System.out.println(userSigned);
                                }
                            } else if (mode.equals("signup")) {
                                if (msg.equals("false")) {
                                    //  faild to  sign up 
                                    serverAvilability = false;
                                    userSigned = false;
                                    userName = null;
                                    userPass = null;
                                    responseMsg = "Faild To Sign up username is used Before";
                                } else if (msg.equals("true")) {
                                    userSigned = true;
                                    vaildUser = false;
                                    userName = name;
                                    userPass = pass;
                                    //data inserted
                                }
                            }
                        } catch (IOException ex) {
                            serverAvilability = false;
                            vaildUser = false;
                            userSigned = false;
                            responseMsg = "Faild to connect to server ";
                            System.out.println(responseMsg);
                        } finally {
                            try {
                                this.finalize();
                                Client.this.runtimeUIUpdates(responseMsg, l, r);
                            } catch (Throwable ex2) {
                                System.err.println("Faild to close connection thread " + ex2.getMessage());
                            }
                        }
                    }
                }.start();
            }

        }
        
        synchronized public String serverMessageHandler(String msg) {
            System.err.println(msg);
            String message = msg.split("\\:")[0];
            String replay = "";
            switch (message) {
                case "login": {
                    //login:name=username,pass=pass
                    //login:false
                    replay = msg.split("\\:")[1];
                    return replay;
                }
                case "signup": {
                    //signup:true|false
                    replay = msg.split("\\:")[1];
                    return replay;
                }
                case "ready": {
                    String player1 = msg.split("\\:")[1];
                    String player2 = msg.split("\\:")[2];
                    System.out.println(player1 + " Vs " + player2);
                    Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        game = new GameOnline(stage, player1, player2);
                        Scene scene = new Scene(game, 700, 450);
                        stage.setScene(scene);
                        stage.show();
                    }
                });
                    return "";
                }
                
                case "cell":{
                    String cellId = msg.split("\\:")[1];
                    String player = msg.split("\\:")[2];
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            game.handleRequest(cellId);                       
                        }
                    });
                    return "";
                }
                
                case "getrecord":{
                    String[] main = msg.split("\\:");
                    String[] modified = Arrays.copyOfRange(main, 1, main.length);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                          createRecordsScene(stage,modified);                  
                        }
                    });
                    return "";
                }
            }
            return "";
        }
         public void startServerListener() {
            con_running = true;
            serverListener = new Thread() {
                public void run() {
                    while (con_running) {
                        try {
                            System.out.println("lisin  ....");
                            String message = dis.readLine();
                            serverMessageHandler(message);
                        } catch (IOException ex) {
                            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            };

            serverListener.start();

        }
        private void runtimeUIUpdates(String errorMsg, LoginSceneBase l, RegisterSceneBase r) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    if (errorMsg != null) {
                        if(l != null){
                        l.loginMsg.setText(errorMsg);
                        l.loginButton.setVisible(true);
                        }
                        if(r != null){
                        r.regMsg.setText(errorMsg);
                        r.regButton.setVisible(true);
                        }
                    }

                    if (vaildUser) {
                        startServerListener();
                        showGameTypes(stage);
                        //score = Integer.valueOf(errorMsg.split("\\,")[2]);
                    } else if (userSigned) {
                        startServerListener();
                        showGameTypes(stage);
                        //score = Integer.valueOf(errorMsg.split("\\,")[2]);

                    }
                }
            });

        }
        
        public void logOut(String userName, String userPass) {
            if (serverAvilability) {
                final String loOutMsg = "logout:name=" + userName + ",pass=" + userPass;
                ps.println(loOutMsg);
                try {
                    socket.close();
                    dis.close();
                    ps.close();
                    serverAvilability = false;
                    userName = null;
                    userPass = null;
                    vaildUser = false;

                    con_running = false;
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
