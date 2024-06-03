/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Entity.Category;
import Entity.Course;
import Entity.Expert;
import Entity.Users;
import entity.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
            Users user = new Users(userId, roleId, username, password, fullName, email, birthDate, image, phoneNumber, address, createdAt, banned,failedAttempt, lockTime);
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

    
      public boolean doesUsernameExist(String username) {
        String query = "SELECT * FROM Users WHERE username = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions appropriately in your application
        }
        return false;
    }


    public boolean doesEmailExist(String email) {
        String query = "SELECT * FROM Users WHERE email = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions appropriately in your application
        }
        return false;
    }
    
    public void addNewCustomer(String username, String password, String fullName, String email, Date birthDate) {
        String query = "INSERT INTO Users(role_id, username, password, full_name, email, birth_date) VALUES (?,?,?,?,?,?)";
        try {

            ps = con.prepareStatement(query);
            ps.setInt(1, 2);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.setString(4, fullName);
            ps.setString(5, email);
            ps.setDate(6, new java.sql.Date(birthDate.getTime()));
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions appropriately in your application
        }
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
public Map<Integer, List<String>> categoriesExpert() {
        String query = "select * from Experts c\n"
                + "join ExpertCategories e on c.expert_id = e.expert_id\n"
                + "join Categories ca on e.category_id = ca.category_id";
        Map<Integer, List<String>> catMap = new HashMap<>();
        try {

            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                int expertID = rs.getInt("expert_id");
                String cateName = rs.getString("name");
                if (!catMap.containsKey(expertID)) {
                    catMap.put(expertID, new ArrayList<>());
                }
                catMap.get(expertID).add(cateName);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return catMap;

    }

    public Map<Integer, List<Course>> expertCoures() {
        String query = "select * from ExpertCourses ec\n"
                + "join Experts e on ec.expert_id = e.expert_id\n"
                + "join Courses c on ec.course_id = c.course_id";
        Map<Integer, List<Course>> courseExpert = new HashMap<>();
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int expertID = rs.getInt("expert_id");
                int courseId = rs.getInt("course_id");
                String title = rs.getString("title");

                Integer categoryId = rs.getObject("category_id") != null ? rs.getInt("category_id") : null;
                String description = rs.getString("description");
                BigDecimal price = rs.getBigDecimal("price");
                Date createdAt = rs.getTimestamp("created_at");
                Date updatedAt = rs.getTimestamp("updated_at");

                Course course = new Course(courseId, title, categoryId, description, price, createdAt, updatedAt);
                if (!courseExpert.containsKey(expertID)) {
                    courseExpert.put(expertID, new ArrayList<Course>());
                }
                courseExpert.get(expertID).add(course);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courseExpert;
    }

    public List<Expert> getExpertByName(String userName) {
        String query = """
                       select distinct u.username, c.expert_id, u.email, ca.name from Experts c
                       join ExpertCategories e on c.expert_id = e.expert_id
                       join Categories ca on e.category_id = ca.category_id
                       join Users u on u.user_id = c.user_id
                       where u.username like ? """;
        List<Expert> e = new ArrayList<>();
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, "%" + userName + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
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
            s.printStackTrace();
        }
        return e;
    }

    public List<Expert> getExpertByCate(String cate) {
        String query = "SELECT * FROM Experts c "
                + "JOIN ExpertCategories e ON c.expert_id = e.expert_id "
                + "JOIN Categories ca ON e.category_id = ca.category_id "
                + "JOIN Users u ON u.user_id = c.user_id "
                + "WHERE ca.name LIKE ?";
        List<Expert> e = new ArrayList<>();
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, "%" + cate + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
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
            s.printStackTrace();
        }
        return e;
    }

    public List<Expert> getExpertByCateName(String userName, String cate) {
        String query = "SELECT * FROM Experts c "
                + "JOIN ExpertCategories e ON c.expert_id = e.expert_id "
                + "JOIN Categories ca ON e.category_id = ca.category_id "
                + "JOIN Users u ON u.user_id = c.user_id "
                + "WHERE u.username LIKE ? AND ca.name LIKE ?";
        List<Expert> e = new ArrayList<>();
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, "%" + userName + "%");
            ps.setString(2, "%" + cate + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
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
            s.printStackTrace();
        }
        return e;
    }

    public void updateExpert(String username, String email, String cate, int expertID) {
        String queryUser = "update Users set username = ?, email = ?\n"
                + "where user_id = (select e.user_id\n"
                + "				 from Experts e\n"
                + "				 where expert_id = ?)";
        String queryCate = "UPDATE Categories SET name = ? "
                + "WHERE category_id = (SELECT category_id FROM ExpertCategories WHERE expert_id = ?)";
        try {
            ps = con.prepareStatement(queryUser);
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setInt(3, expertID);
            ps.executeUpdate();
            
             ps = con.prepareStatement(queryCate);
            ps.setString(1, cate);
            ps.setInt(2, expertID);
            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
public List<Category> getCategories() {
        String query = "SELECT * FROM Categories";
        List<Category> categorylist = new ArrayList<>();
        try {

            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                categorylist.add(new Category(
                        rs.getInt("category_id"),
                        rs.getString("name"),
                        rs.getString("description")
                ));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return categorylist;
    }

}
