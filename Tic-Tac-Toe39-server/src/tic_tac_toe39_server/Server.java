/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe39_server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.print.attribute.standard.Severity;

/**
 *
 * @author ahmed
 */
public class Server extends Application  {
    ServerSocket ss = null;
    int p =5555;
    Socket s = null;
    DataInputStream in = null;
    PrintWriter out = null;
    Thread startThread = null;
    
    static Vector<LogedPlayer> currentLogedPlayers = new Vector<LogedPlayer>();
    static  Database dataBase = null;
    Button stop ;
    Button start ;
     
     public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage primaryStage) {
    
        start = new Button();
        start.setText("start Server");
        start.setOnAction(new EventHandler<ActionEvent>() {            
        @Override
        public void handle(ActionEvent event) {
            try {
                ss = new ServerSocket(p);
                // start background serves which will call accept 
                startServer();
                dataBase = new Database("tic_tac_toe","root","");
                System.out.println("Server Started ");
                start.setVisible(false);
                stop.setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
                }
            });
                
        stop = new Button();
        stop.setText("stop Server");
        stop.setOnAction(new EventHandler<ActionEvent>(){
            
            public void handle(ActionEvent event) {
                stopSErver();
                System.out.println("Server Stopped ");
                start.setVisible(true);
                stop.setVisible(false);
            }
        }
                
                
                
        );
        
        StackPane root = new StackPane();
        root.getChildren().add(start);
        root.getChildren().add(stop);
        stop.setVisible(false);
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Tic-Tac-Toe39 Server!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void startServer(){
        startThread =new Thread(){  
            public void run(){
               System.err.println("started");
               System.err.println("run");    
                while(true){    
                try {
                        s =  ss.accept();
                        System.err.println("some one Logged In");
                        new LogedPlayer(s);
                } catch (IOException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }

                }
            }
               
        };
      
        startThread.start();
    }
    
    public void stopSErver(){
         //send message for each player that server closed 
         // and then clothe its streams, sockets and threads
         //slose server thread that accept sockets
         for(LogedPlayer i : currentLogedPlayers){
             try {
                 i.output.println("logout:server");
                 i.input.close();
                 i.output.close();
                 i.clientSocket.close();
                 //why it reads stopThread although it is private
                 i.stopThread();
             }
             
             catch (IOException ex) {
                 Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
             } catch (Throwable ex) {
                 Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
         startThread.stop();
    }
    
class LogedPlayer extends Thread{

    Socket clientSocket;
    DataInputStream input = null;
    PrintStream output = null;
    String userName = null;
    String userPass = null;
    String player = null;
    String playWith = "";
    boolean ready = false;
    boolean running =true;
    
    LogedPlayer(Socket clientSocket){
        try {
            this.clientSocket = clientSocket;
            input = new DataInputStream(clientSocket.getInputStream());
            output = new PrintStream(clientSocket.getOutputStream());
            Server.currentLogedPlayers.add(this);
            this.start();
        } catch (IOException ex) {
            Logger.getLogger(LogedPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
        
        public void run(){
            while(running){
                try {
                    String loginMsg = input.readLine();
                    if(!loginMsg.equals(""))
                    msgHendler(loginMsg);
                } catch (IOException ex) {
                   // Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    stopThread();
                    running = false;
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        public void stopThread(){ 
            try {
                this.input.close();
                this.output.close();
                this.clientSocket.close();
                this.finalize();
            } catch (Throwable ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        private String msgHendler(String message) {
            String replay = message.split("\\:")[0];
            switch(replay){
                case "login" : {
                    //login:user=name,pass=pass
                    String msg = message.split("\\:")[1];
                    String temp = msg.split("\\,")[0];
                    userName = temp.split("\\=")[1];
                    temp = msg.split("\\,")[1];
                    userPass = temp.split("\\=")[1];
                    getPlayerData(userName,userPass);
                    break;
                }
                case "signup":{
                    //request signup:user=name,pass=pass
                    String msg = message.split("\\:")[1];
                    String temp = msg.split("\\,")[0];
                    userName = temp.split("\\=")[1];
                    temp = msg.split("\\,")[1];
                    userPass = temp.split("\\=")[1];
                    insertPlayer(userName,userPass);
                    break;
                }
                
                case "logout":{
                      //logout:name,pass
                    String msg = message.split("\\:")[1];
                    String name = msg.split("\\,")[0];
                    String pass = msg.split("\\,")[1];
                    this.logOut(); 
                    break;
                }
                case "ready":{
                    ready = true;
                    output.println(message.split("\\:")[1]+" You are ready");
                    for(LogedPlayer i : Server.currentLogedPlayers){
                        if(i.ready == true && i.userName != userName){
                            System.out.println("Preparing again the online game" + i.userName);
                            i.output.println("ready:"+i.userName+":"+userName);
                            output.println("ready:"+i.userName+":"+userName);
                            i.ready = ready = false;
                        }
                    }
                    break;
                }
                case "cell": {
                    String cellId = message.split("\\:")[1];
                    String partner = message.split("\\:")[2];
                    for(LogedPlayer i : Server.currentLogedPlayers){
                        if(i.userName.equals(partner)){
                            System.out.println("Found partner " + i.userName);
                            i.output.println("cell:"+cellId+":"+i.userName);
                        }
                    }
                    break;
                }
                
                case "record":{
                    String request_player = message.split("\\:")[1];
                    String x_player = message.split("\\:")[2];
                    String y_player = message.split("\\:")[3];
                    String record = message.split("\\:")[4];
                    insertRecord(request_player,x_player,y_player,record);
                    break;
                }
                
                case "getrecord":{
                    String request_player = message.split("\\:")[1];
                    getRecords(request_player);
                    break;
                }
               
                
                default:
                    System.out.println(message);          
            }
            return "";
        }

        synchronized private void insertPlayer(String userName, String userPass) {
           if(dataBase != null){
               Boolean inserted = Database.insertPlayer(userName,userPass);
               System.out.println("signup:"+inserted);
               output.println("signup:"+inserted);   
           }
        }
        
        synchronized private void insertRecord(String request_player, 
                String x_player, String y_player,String record) {
           if(dataBase != null){
               int recorded = Database.insertRecord(request_player,x_player,y_player,record);
               System.out.println("Record:"+(recorded>0));
               output.println("Record:"+(recorded>0));   
           }
        }
        
        synchronized private void getRecords(String request_player) {
           if(dataBase != null){
               String data = Database.getPlayerRecords(request_player);
                System.out.println(data);
                output.println("getrecord"+data);

           }
        }

        synchronized private void getPlayerData(String userName , String userPass) {
            /*String loginMsg = input.readLine();
            megHendler(loginMsg);
            */
            if(userName != null && userPass != null){
                if(dataBase != null){
                       ResultSet rs = Database.selectPlayers(userName,userPass);
                       String userdata = "";
                       if(rs != null){
                           try {
                               if(rs.next()){
                                   for(int i = 1 ; i < 4;i++){
                                       userdata += rs.getString(i)+",";
                                   }
                                   userdata +="1";//current user is active
                                   updatePlayerStatus();
                               }else{
                                   userdata ="false";
                                   System.out.println("no next");
                               }
                           } catch (SQLException ex) {
                               Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                           }
                           output.println("login:"+userdata);          
                        } 
                }else{  
                    System.out.println("data base null");
                        //faild to get user data from database
                }
                
            }else{
                System.out.println("fail to get data from client message");
                //faild to get User name and PAss
            }
      
        }
        
        

        synchronized private void logOut(){
            //logout this current player 
            //close thread
            if(Server.currentLogedPlayers.remove(this)){
                System.out.println("removed : "+userName);
                running = false;
            };
            updatePlayerStatus();
            stopThread();
        }
        
        synchronized private void updatePlayerStatus(){
            //send message for all player 
            //that user userName is loged out 
            for(LogedPlayer i : Server.currentLogedPlayers){
                    if(i.userName != this.userName){
                        System.out.println("send update to " + i.userName);
                        i.output.println("update:"+userName);
                    }
                }
        }
        
    }
}
