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
            ResultSet rs = DbOperations.getData("select * from booking");
            while(rs.next()){
                Booking booking = new Booking();
                booking.setId(rs.getInt("id"));
                booking.setName(rs.getString("name"));
                booking.setEmail(rs.getString("email"));
                booking.setMobile(rs.getString("mobile"));
                booking.setGuests(rs.getInt("guests"));
                booking.setTableNo(rs.getString("tableNo"));
                booking.setDate(rs.getString("date"));
                booking.setTime(rs.getString("time"));
                arrayList.add(booking);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;    
    }
    
    public static void update(Booking booking){
        String query = "update booking set name ='"+booking.getName()+"',email ='"+booking.getEmail()+"',mobile ='"+booking.getMobile()+"',guests ='"+booking.getGuests()+"',mobile ='"+booking.getMobile()+"',tableNo ='"+booking.getTableNo()+"',date ='"+booking.getDate()+"',time ='"+booking.getTime()+"' where id ='"+booking.getId()+"'";
        DbOperations.setDataOrDelete(query, "Booking Updated Successfully !");
    }
    
    public static void delete(String id){
        String query = "delete from booking where id='"+id+"'";
        DbOperations.setDataOrDelete(query, "Booking Deleted Successfully !");
    } 
}
