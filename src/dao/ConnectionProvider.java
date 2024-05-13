/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ASUS
 */
public class ConnectionProvider {
    public static Connection getCon (){
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant", "root", "1234");
            return con;
        }
        catch (Exception e){
            e.printStackTrace(); // Print the stack trace for debugging
            return null;
        }
    }
}
