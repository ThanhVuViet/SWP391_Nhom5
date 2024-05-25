/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Entity.Users;
import entity.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 *
 * @author Admin
 */
public class dao extends MyDAO{

    /**
     *
     * @param user
     * @param pass
     * @return
     */
    public Users login(String user, String pass) {
        String query = "SELECT * FROM Users where username=? and password=?";
        
        try {

            ps = con.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();

            if (rs.next()) {
                return  new Users(
                        rs.getInt("user_Id"),
                        rs.getInt("role_Id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("full_Name"),
                        rs.getString("email"),
                        rs.getDate("birth_date"),
                        rs.getString("image"),
                        rs.getString("phone_number"),
                        rs.getString("address"),
                        rs.getDate("created_at"),
                        rs.getBoolean("banned")
                );
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions appropriately in your application
        }

        return null;
    } 
}
