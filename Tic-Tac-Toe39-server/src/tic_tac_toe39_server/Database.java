/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe39_server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ahmed
 */
public class Database  {
     static Connection con=null;
     static PreparedStatement prepStmt=null;
     static ResultSet res=null;
     static int result_num;
   
    public Database(String DB,String user,String pass)
    {
         try {
             DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
             con=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DB , user , pass);
         } catch (SQLException ex) {
             Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    public static void closseConection()
    {
         try {
             con.close();
             prepStmt.close();
         } catch (SQLException ex) {
             Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
   
    static boolean insertPlayer(String name,String pass )//params
    // adding player
    {
         boolean inserted = false;
         try 
         {
             
             ResultSet rs = Database.selectPlayers(name, pass);
             rs.beforeFirst();
             if(rs.next()){
                 inserted = false;
                 return inserted; 
             }
             String sql="INSERT INTO users( userName, password)"+" VALUES (?,?)";
             prepStmt=con.prepareStatement(sql);
             prepStmt.setString(1, name);
             prepStmt.setString(2, pass);
             result_num = prepStmt.executeUpdate();
             if(result_num == 1){
                 inserted = true;
             }
             
         } catch (SQLException ex) {
             Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
         }
         
         return inserted; 
    }
    
      static ResultSet selectPlayers(String userNamae , String userPass)//username pass
     {
         //get All Players From DataBase  
          //return query result   
        try 
        {
            String sql="SELECT* FROM users where username = ? and password = ?";
             
            prepStmt=con.prepareStatement(sql);
            prepStmt.setString(1, userNamae);
            prepStmt.setString(2, userPass);
               
            res=prepStmt.executeQuery();
         } catch (SQLException ex) {
             Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
         }
         
         return res;
    }
    
  
    static int insertRecord(String request_player,String x_player,String y_player,String record){ 

    
         try {
             String sql="INSERT INTO records( request_player, x_player, y_player,record)"+" VALUES (?,?,?,?)";
             prepStmt=con.prepareStatement(sql);
             prepStmt.setString(1, request_player);
             prepStmt.setString(2, x_player);
             prepStmt.setString(3, y_player);
             prepStmt.setString(4, record);
             //prepStmt.setInt(5, request);
             result_num = prepStmt.executeUpdate();
         } //params //params
         catch (SQLException ex) {
             Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
         }
         return result_num;

    }
    static String getPlayerRecords(String player)
    //accept player id return all player records
    {
         String data = "";
         try 
         {
             String sql="SELECT x_player, y_player, record FROM records WHERE request_player=?"; 
             prepStmt=con.prepareStatement(sql);
             prepStmt.setString(1, player);
             res=prepStmt.executeQuery();
            while(res.next())
            {
                System.out.println(res.getString(1)+" "+res.getString(2)
                        +" "+res.getString(3));
                String row = res.getString(1)+" "+
                    res.getString(2)+" "+res.getString(3);
                    data += ":"+row;
            }
         } catch (SQLException ex) {
             Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
         }
         return data;
    }
        
   

}

