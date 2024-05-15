/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Booking;

/**
 *
 * @author Kavini
 */
public class BookingDao {
    public static void save(Booking booking){
        String query = "insert into reservationtable(name) values('"+booking.getName()+"')";
        DbOperations.setDataOrDelete(query, "Table Added Successfully !");
    }
    
    public static ArrayList<Booking> getAllRecords(){
        ArrayList<Booking> arrayList = new ArrayList<>();
        try{
            ResultSet rs = DbOperations.getData("select * from reservationtable");
            while(rs.next()){
                Booking booking = new Booking();
                booking.setId(rs.getInt("id"));
                booking.setName(rs.getString("name"));
                arrayList.add(booking);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;    
    }
    
    public static void delete(String id){
        String query = "delete from booking where id='"+id+"'";
        DbOperations.setDataOrDelete(query, "Booking Deleted Successfully !");
    } 
}
