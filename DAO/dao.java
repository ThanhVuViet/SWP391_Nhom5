/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.Expert;
import Entity.Users;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 *
 * @author Admin
 */
public class dao extends MyDAO {

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
                return new Users(
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
                        rs.getBoolean("banned"),
                        rs.getInt("failedAttempt"),
                        rs.getLong("lockTime")
                );
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions appropriately in your application
        }

        return null;
    }

    public Users existEmail(String email) {
        String query = "SELECT * FROM Users where email=?";

        try {

            ps = con.prepareStatement(query);
            ps.setString(1, email);

            rs = ps.executeQuery();

            if (rs.next()) {
                return new Users(
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
                        rs.getBoolean("banned"),
                        rs.getInt("failedAttempt"),
                        rs.getLong("lockTime")
                );
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions appropriately in your application
        }

        return null;
    }

    public List<Expert> getExpert() {
        String query = "SELECT * FROM Experts e JOIN Users u ON e.user_id = u.user_id";
        List<Expert> e = new ArrayList<>();
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) { // Use while instead of if
                int expertId = rs.getInt("expert_id");
                int userId = rs.getInt("user_id");
                String description = rs.getString("description");
                String certification = rs.getString("certification");
                int roleId = rs.getInt("role_id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String fullName = rs.getString("full_name");
                String email = rs.getString("email");
                Date birthDate = rs.getDate("birth_date");
                String image = rs.getString("image");
                String phoneNumber = rs.getString("phone_number");
                String address = rs.getString("address");
                Date createdAt = rs.getDate("created_at");
                boolean banned = rs.getBoolean("banned");
                int failedAttempt = rs.getInt("failedAttempt");
                long lockTime = rs.getLong("lockTime");

                Users user = new Users(userId, roleId, username, password, fullName, email, birthDate, image, phoneNumber, address, createdAt, banned, failedAttempt, lockTime);
                Expert expert = new Expert(expertId, user, description, certification);
                e.add(expert);
            }
        } catch (Exception s) {
            s.printStackTrace(); // Handle exceptions appropriately in your application
        }

        return e;
    }

    public Expert getExpertById(int id) {
        String query = "SELECT * FROM Experts e JOIN Users u ON e.user_id = u.user_id WHERE expert_id = ?";
        Expert expert = null;
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                int expertId = rs.getInt("expert_id");
                int userId = rs.getInt("user_id");
                String description = rs.getString("description");
                String certification = rs.getString("certification");
                int roleId = rs.getInt("role_id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String fullName = rs.getString("full_name");
                String email = rs.getString("email");
                Date birthDate = rs.getDate("birth_date");
                String image = rs.getString("image");
                String phoneNumber = rs.getString("phone_number");
                String address = rs.getString("address");
                Date createdAt = rs.getDate("created_at");
                boolean banned = rs.getBoolean("banned");
                int failedAttempt = rs.getInt("failedAttempt");
                long lockTime = rs.getLong("lockTime");
                Users user = new Users(userId, roleId, username, password, fullName, email, birthDate, image, phoneNumber, address, createdAt, banned, failedAttempt, lockTime);
                expert = new Expert(expertId, user, description, certification);
            } else {
                System.out.println("No expert found with ID: " + id);
            }
        } catch (Exception s) {
            s.printStackTrace(); // Handle exceptions appropriately in your application
        }
        return expert;
    }

    public Users findUsersByName(String user) {
        String query = "SELECT * FROM Users where username=? ";
        
        try {

            ps = con.prepareStatement(query);
            ps.setString(1, user);
            
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
                        rs.getBoolean("banned"),
                        rs.getInt("failedAttempt"),
                        rs.getLong("lockTime")
                        
            
                );
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions appropriately in your application
        }

        return null;
    }
    public int getFailedAttempt (String user ) {
         String query = "SELECT u.failedAttempt FROM Users u where username= ? ";
        try {
            
             ps = con.prepareStatement(query);
            ps.setString(1, user);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("failedAttempt");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public long getLockTime( String user) {
        String query = "SELECT u.lockTime FROM Users u where username=? ";
        try {
             
             ps = con.prepareStatement(query);
            ps.setString(1, user);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getLong("lockTime");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
   public void updateFailedAttempt(String user) {
    String query = "UPDATE users SET failedAttempt = failedAttempt + 1 WHERE username = ?";
    try {
        ps = con.prepareStatement(query);
        ps.setString(1, user);
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
    
}
    public void resetFailedAttempt(String user) {
        String query = "UPDATE users SET failedAttempt = 0 WHERE username = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, user);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     public void updateLockTime (String user, long lockTime) {
          String query = "UPDATE Users SET lockTime = ? WHERE username = ? ";
         try {
            
             ps = con.prepareStatement(query);
             ps.setLong(1, lockTime);
            ps.setString(2, user);
             ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
      
    }
      public Users loginUser(String user) {
        String query = "SELECT * FROM Users where username=? ";
        
        try {

            ps = con.prepareStatement(query);
            ps.setString(1, user);     
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
                        rs.getBoolean("banned"),
                        rs.getInt("failedAttempt"),
                        rs.getLong("lockTime")
                        
            
                );
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions appropriately in your application
        }

        return null;
    }


    public boolean isExistMail(String email) {
        String query = "SELECT *\n"
                + "  FROM [Users]\n"
                + "  where email = ?";

        try {

            ps = con.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions appropriately in your application
        }
        return false;
    }

    public void updatePasswordByMail(String mailReset, String password) {
        String query = "UPDATE [Users]\n"
                + "   SET \n"
                + "      [password] = ?\n"
                + " WHERE email = ?";
        try {

            ps = con.prepareStatement(query);
            ps.setObject(1, password);
            ps.setObject(2, mailReset);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions appropriately in your application
        }
        
    }

public boolean ChangePass(int userId, String newPass) throws SQLException {

        String query = "UPDATE Users SET password = ? WHERE user_Id = ?";

        try {

            ps = con.prepareStatement(query);

            ps.setString(1, newPass);

            ps.setInt(2, userId);

            return ps.executeQuery() > 0;

            

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;

    }

}
