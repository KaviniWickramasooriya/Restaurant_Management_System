/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Reservationtable;
/**
 *
 * @author Kavini
 */
public class ReservationtableDao {
    
    public static void save(Reservationtable reservationtable){
        String query = "insert into reservationtable(name) values('"+reservationtable.getName()+"')";
        DbOperations.setDataOrDelete(query, "Table Added Successfully !");
    }
    
    public static ArrayList<Reservationtable> getAllRecords(){
        ArrayList<Reservationtable> arrayList = new ArrayList<>();
        try{
            ResultSet rs = DbOperations.getData("select * from reservationtable");
            while(rs.next()){
                Reservationtable reservationtable = new Reservationtable();
                reservationtable.setId(rs.getInt("id"));
                reservationtable.setName(rs.getString("name"));
                arrayList.add(reservationtable);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;    
    }
    
    public static void delete(String id){
        String query = "delete from reservationtable where id='"+id+"'";
        DbOperations.setDataOrDelete(query, "Table Deleted Successfully !");
    } 
}
