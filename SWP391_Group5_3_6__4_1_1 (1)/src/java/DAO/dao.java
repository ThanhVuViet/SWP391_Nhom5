/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.Article;
import Entity.Category;
import Entity.Course;
import Entity.Expert;
import Entity.Feedback;
import Entity.Users;
import Entity.Exam;
import Entity.ExamChoice;
import Entity.ExamQuestion;
import Entity.ExamResult;
import Entity.Lecture;
import Entity.Question;
import Entity.Section;
import Entity.UserProgress;
import Entity.Video;
import java.beans.Statement;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<Users> getUsers() {
        String query = "SELECT * FROM Users ";
        List<Users> userList = new ArrayList<>();
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) { // Use while instead of if
                int userId = rs.getInt("user_id");
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
                userList.add(user);
            }
        } catch (Exception s) {
            s.printStackTrace(); // Handle exceptions appropriately in your application
        }

        return userList;
    }
     public Users getUsersByID(int userID) {
        String query = "SELECT * FROM Users u  where u.user_id = ? ";
       
        List<Users> userList = new ArrayList<>();
        try {
            ps = con.prepareStatement(query);
             ps.setInt(1, userID);
            rs = ps.executeQuery();

            while (rs.next()) { // Use while instead of if
                int userId = rs.getInt("user_id");
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
                return user;
            }
        } catch (Exception s) {
            s.printStackTrace(); // Handle exceptions appropriately in your application
        }

        return null;
    }

    public List<Users> getUsersByName(String userN) {
        String query = "SELECT * FROM Users u where u.username like ? ";
        List<Users> userList = new ArrayList<>();
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, "%" + userN + "%");
            rs = ps.executeQuery();

            while (rs.next()) { // Use while instead of if
                int userId = rs.getInt("user_id");
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
                userList.add(user);
            }
        } catch (Exception s) {
            s.printStackTrace(); // Handle exceptions appropriately in your application
        }

        return userList;
    }

    public List<Users> getTop5User() {
        String query = "SELECT TOP 5 u.user_id, u.role_id, u.username, u.password, u.full_name, u.email, \n"
                + "       u.birth_date, u.image, u.phone_number, u.address, u.created_at, \n"
                + "       u.banned, u.failedAttempt, u.lockTime, SUM(o.total_amount) as totalSpent \n"
                + "FROM Users u\n"
                + "JOIN Orders o ON u.user_id = o.user_id\n"
                + "GROUP BY u.user_id, u.role_id, u.username, u.password, u.full_name, u.email, \n"
                + "         u.birth_date, u.image, u.phone_number, u.address, u.created_at, \n"
                + "         u.banned, u.failedAttempt, u.lockTime\n"
                + "ORDER BY totalSpent DESC; ";
        List<Users> userList = new ArrayList<>();
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) { // Use while instead of if
                int userId = rs.getInt("user_id");
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
                userList.add(user);
            }
        } catch (Exception s) {
            s.printStackTrace(); // Handle exceptions appropriately in your application
        }

        return userList;
    }

    public Map<Integer, Double> getTotalSpent() {
        String query = "SELECT TOP 5 u.user_id, u.username, u.email, SUM(o.total_amount) as totalSpent \n"
                + "FROM Users u\n"
                + "JOIN Orders o ON u.user_id = o.user_id\n"
                + "GROUP BY u.user_id, u.username, u.email\n"
                + "ORDER BY totalSpent DESC; ";
        Map<Integer, Double> totalMap = new HashMap<>();
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) { // Use while instead of if
                int userId = rs.getInt("user_id");
                double totalSpent = rs.getDouble("totalSpent");
                totalMap.put(userId, totalSpent);
            }
        } catch (Exception s) {
            s.printStackTrace(); // Handle exceptions appropriately in your application
        }

        return totalMap;
    }

    public List<Users> getUsersByEmail(String emailFilter) {
        String query = "SELECT * FROM Users u where u.email like ? ";
        List<Users> userList = new ArrayList<>();
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, "%" + emailFilter + "%");
            rs = ps.executeQuery();

            while (rs.next()) { // Use while instead of if
                int userId = rs.getInt("user_id");
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
                userList.add(user);
            }
        } catch (Exception s) {
            s.printStackTrace(); // Handle exceptions appropriately in your application
        }

        return userList;
    }

    public List<Users> getUsersByEmailAndName(String emailFilter, String userNameFilter) {
        String query = "SELECT * FROM Users u where u.email like ? and u.username like ?";
        List<Users> userList = new ArrayList<>();
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, "%" + emailFilter + "%");
            ps.setString(2, "%" + userNameFilter + "%");
            rs = ps.executeQuery();

            while (rs.next()) { // Use while instead of if
                int userId = rs.getInt("user_id");
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
                userList.add(user);
            }
        } catch (Exception s) {
            s.printStackTrace(); // Handle exceptions appropriately in your application
        }

        return userList;
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

    public int getFailedAttempt(String user) {
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

    public long getLockTime(String user) {
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

    public void updateLockTime(String user, long lockTime) {
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

    public Map<Integer, List<String>> expertCourse() {
        String query = "select u.username, c.title, c.course_id from ExpertCourses ec\n"
                + "                 join Experts e on ec.expert_id = e.expert_id\n"
                + "                 join Courses c on ec.course_id = c.course_id\n"
                + "				 join Users u on e.user_id = u.user_id";
        Map<Integer, List<String>> catMap = new HashMap<>();
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                int courseId = rs.getInt("course_id");
                String expertName = rs.getString("username");
                if (!catMap.containsKey(courseId)) {
                    catMap.put(courseId, new ArrayList<>());
                }
                catMap.get(courseId).add(expertName);
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
                String status = rs.getString("status");
                Course course = new Course(courseId, title, categoryId, description, price, createdAt, updatedAt, status);
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
                       SELECT *
                       FROM (
                           SELECT 
                              c.expert_id, 
                              u.user_id, 
                              c.description, 
                              c.certification, 
                              u.role_id,
                              u.username,
                              u.password,
                              u.full_name,
                              u.email,
                              u.birth_date,
                              u.image,
                              u.phone_number,
                              u.address,
                              u.created_at,
                              u.banned,
                              u.failedAttempt,
                       	   u.lockTime,
                              ROW_NUMBER() OVER (PARTITION BY u.username ORDER BY c.expert_id) AS row_num
                           FROM Experts c
                           JOIN ExpertCategories e ON c.expert_id = e.expert_id
                           JOIN Categories ca ON e.category_id = ca.category_id
                           JOIN Users u ON u.user_id = c.user_id
                           WHERE u.username LIKE ?
                       ) AS subquery """;
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
        String query = """
                       SELECT *
                       FROM (
                           SELECT 
                              c.expert_id, 
                              u.user_id, 
                              c.description, 
                              c.certification, 
                              u.role_id,
                              u.username,
                              u.password,
                              u.full_name,
                              u.email,
                              u.birth_date,
                              u.image,
                              u.phone_number,
                              u.address,
                              u.created_at,
                              u.banned,
                              u.failedAttempt,
                       	   u.lockTime,
                              ROW_NUMBER() OVER (PARTITION BY u.username ORDER BY c.expert_id) AS row_num
                           FROM Experts c
                           JOIN ExpertCategories e ON c.expert_id = e.expert_id
                           JOIN Categories ca ON e.category_id = ca.category_id
                           JOIN Users u ON u.user_id = c.user_id
                           WHERE ca.name LIKE ?'
                       ) AS subquery """;
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
        String query = "SELECT *\n"
                + "FROM (\n"
                + "    SELECT \n"
                + "       c.expert_id, \n"
                + "       u.user_id, \n"
                + "       c.description, \n"
                + "       c.certification, \n"
                + "       u.role_id,\n"
                + "       u.username,\n"
                + "       u.password,\n"
                + "       u.full_name,\n"
                + "       u.email,\n"
                + "       u.birth_date,\n"
                + "       u.image,\n"
                + "       u.phone_number,\n"
                + "       u.address,\n"
                + "       u.created_at,\n"
                + "       u.banned,\n"
                + "       u.failedAttempt,\n"
                + "	   u.lockTime,\n"
                + "       ROW_NUMBER() OVER (PARTITION BY u.username ORDER BY c.expert_id) AS row_num\n"
                + "    FROM Experts c\n"
                + "    JOIN ExpertCategories e ON c.expert_id = e.expert_id\n"
                + "    JOIN Categories ca ON e.category_id = ca.category_id\n"
                + "    JOIN Users u ON u.user_id = c.user_id\n"
                + "    WHERE u.username LIKE ? and ca.name LIKE ?\n"
                + ") AS subquery";
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

    public List<Course> getCoursesByName(String name) {
        String query = """
                       select * from Courses co
                       join Categories c
                       on co.category_id = c.category_id
                       where co.title like ?""";
        List<Course> courseList = new ArrayList<>();
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, "%" + name + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                int courseID = rs.getInt("course_id");
                String title = rs.getString("title");
                int categoryID = rs.getInt("category_id");
                String des = rs.getString("description");
                BigDecimal price = rs.getBigDecimal("price");
                Date createdAt = rs.getDate("created_at");
                Date updatedAt = rs.getDate("updated_at");
                String status = rs.getString("status");
                Course course = new Course(courseID, title, categoryID, des, price, createdAt, updatedAt, status);
                courseList.add(course);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courseList;
    }

    public List<Course> getCoursesByCate(String name) {
        String query = """
                       select * from Courses co
                       join Categories c
                       on co.category_id = c.category_id
                       where c.name like ?""";
        List<Course> courseList = new ArrayList<>();
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, "%" + name + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                int courseID = rs.getInt("course_id");
                String title = rs.getString("title");
                int categoryID = rs.getInt("category_id");
                String des = rs.getString("description");
                BigDecimal price = rs.getBigDecimal("price");
                Date createdAt = rs.getDate("created_at");
                Date updatedAt = rs.getDate("updated_at");
                String status = rs.getString("status");
                Course course = new Course(courseID, title, categoryID, des, price, createdAt, updatedAt, status);
                courseList.add(course);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courseList;
    }

    public List<Course> getCoursesByCateAndName(String name, String cate) {
        String query = """
                       select * from Courses co
                       join Categories c
                       on co.category_id = c.category_id
                       where c.name like ? and co.title like ?""";
        List<Course> courseList = new ArrayList<>();
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, "%" + name + "%");
            ps.setString(2, "%" + cate + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                int courseID = rs.getInt("course_id");
                String title = rs.getString("title");
                int categoryID = rs.getInt("category_id");
                String des = rs.getString("description");
                BigDecimal price = rs.getBigDecimal("price");
                Date createdAt = rs.getDate("created_at");
                Date updatedAt = rs.getDate("updated_at");
                String status = rs.getString("status");
                Course course = new Course(courseID, title, categoryID, des, price, createdAt, updatedAt, status);
                courseList.add(course);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courseList;
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

    public void updateCourse(String courseName, String description, int courseId) {
        String queryUser = "update Courses set description = ?, Title = ? where course_id =?;";
        try {
            ps = con.prepareStatement(queryUser);
            ps.setString(1, description);
            ps.setString(2, courseName);
            ps.setInt(3, courseId);
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

    public List<Course> getCourse() {
        String query = "SELECT * FROM Courses";
        List<Course> e = new ArrayList<>();
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) { // Use while instead of if
                int courseID = rs.getInt("course_id");
                String title = rs.getString("title");
                Integer categoryId = rs.getInt("category_id");
                String description = rs.getString("description");
                BigDecimal price = rs.getBigDecimal("price");
                Date createdAt = rs.getDate("created_at");
                Date updatedAt = rs.getDate("updated_at");
                String status = rs.getString("status");
                Course course = new Course(courseID, title, categoryId, description, price, createdAt, updatedAt, status);
                e.add(course);
            }
        } catch (Exception s) {
            s.printStackTrace(); // Handle exceptions appropriately in your application
        }

        return e;
    }

    public Course getCourseByID(int courseId) {
        String query = "select * From Courses c\n"
                + "where c.course_id = ? ";
        Course course = null;
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, courseId);
            rs = ps.executeQuery();

            while (rs.next()) { // Use while instead of if
                int courseID = rs.getInt("course_id");
                String title = rs.getString("title");
                Integer categoryId = rs.getInt("category_id");
                String description = rs.getString("description");
                BigDecimal price = rs.getBigDecimal("price");
                Date createdAt = rs.getDate("created_at");
                Date updatedAt = rs.getDate("updated_at");
                String status = rs.getString("status");
                course = new Course(courseID, title, categoryId, description, price, createdAt, updatedAt, status);
            }
        } catch (Exception s) {
            s.printStackTrace(); // Handle exceptions appropriately in your application
        }

        return course;
    }

    public HashMap<Integer, List<String>> courseCategory() {
        String query = "select * from Courses co\n"
                + "join Categories c\n"
                + "on co.category_id = c.category_id";
        HashMap<Integer, List<String>> courseCate = new HashMap<>();
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("course_id");
                String name = rs.getString("name");
                if (!courseCate.containsKey(id)) {
                    courseCate.put(id, new ArrayList<>());
                }
                courseCate.get(id).add(name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courseCate;
    }

    public void addCategorForExpert(int expertId, int categoryId) {
        String query = "Insert into ExpertCategories Values (?, ?)";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, expertId);
            ps.setInt(2, categoryId);
            ps.executeUpdate();
            rs = ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCategoryForExpert(int expertId, int categoryId) {
        String query = "DELETE FROM ExpertCategories WHERE expert_id = ? AND category_id = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, expertId);
            ps.setInt(2, categoryId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addCategorForCourse(int categoryId, int courseId) {
        String query = "update Courses set category_id = ? where course_id = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, categoryId);
            ps.setInt(2, courseId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getCategoryByName(String courseId) {
        String query = "select * from Categories where Categories.name =?";
        Course course = null;
        int categoryID = 0;
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, courseId);
            rs = ps.executeQuery();

            while (rs.next()) { // Use while instead of if
                categoryID = rs.getInt("category_id");
            }
        } catch (Exception s) {
            s.printStackTrace(); // Handle exceptions appropriately in your application
        }

        return categoryID;
    }

    public void saveExam(int lectureId, String title, String descripion) {
        String query = "INSERT INTO Exams (lecture_id, title, description) VALUES (?, ?, ?)";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, lectureId);
            ps.setString(2, title);
            ps.setString(3, descripion);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveSection(int sectionId, int lectureId, String description) {
        String query = "INSERT INTO sections (section_id, course_id, title) VALUES (?, ?, ?)";
        String enableIdentityInsert = "SET IDENTITY_INSERT Sections ON";
        String disableIdentityInsert = "SET IDENTITY_INSERT Sections OFF";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, sectionId);
            ps.setInt(2, lectureId);
            ps.setString(3, description);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addSection(int lectureId, String title) {
        String query = "INSERT INTO sections ( course_id, title) VALUES ( ?, ?)";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, lectureId);
            ps.setString(2, title);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteSection(int sectionId) {
        String query = "delete from sections where section_id = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, sectionId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean sectionExists(int sectionId) {
        String query = "SELECT 1 FROM sections WHERE section_id = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, sectionId);
            rs = ps.executeQuery();
            return rs.next();  // Returns true if the query found at least one row
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void saveLecture(int lectureId, int sectionId, String title, String description, String filePath) {
        String query = "INSERT INTO Lectures (lecture_id,section_id, title, content_type, file_path) VALUES (?, ?, ?, ?, ?);";
        String enableIdentityInsert = "SET IDENTITY_INSERT Lectures ON";
        String disableIdentityInsert = "SET IDENTITY_INSERT Lectures OFF";
        try {
            con.prepareStatement(enableIdentityInsert).execute();
            ps = con.prepareStatement(query);
            ps.setInt(1, lectureId);
            ps.setInt(2, sectionId);
            ps.setString(3, title);
            ps.setString(4, description);
            ps.setString(5, filePath);
            ps.executeUpdate();
            con.prepareStatement(disableIdentityInsert).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addLecture(int sectionId, String title, String contentType, String filePath) {
        String query = "INSERT INTO Lectures (section_id, title, content_type, file_path) VALUES ( ?, ?, ?, ?);";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, sectionId);
            ps.setString(2, title);
            ps.setString(3, contentType);
            ps.setString(4, filePath);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveQuestion(int examId, String questionText) {
        String query = "INSERT INTO ExamQuestions (exam_id, question_text) VALUES (?, ?)";
        try {
              
        
            ps = con.prepareStatement(query);
            ps.setInt(1, examId);
            ps.setString(2, questionText);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private int getDeletedExamId() {
    
    int deletedExamId = -1;
    
    try {
        String query = "SELECT TOP 1 exam_id FROM DeletedExamIDs ORDER BY exam_id";
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();
        
        if (rs.next()) {
            deletedExamId = rs.getInt("exam_id");
        }
    } catch (Exception e) {
            e.printStackTrace();
        }
    
    
    return deletedExamId;
}

private void removeDeletedExamId(int examId)  {
    
    
    try {
        String query = "DELETE FROM DeletedExamIDs WHERE exam_id = ?";
        ps = con.prepareStatement(query);
        ps.setInt(1, examId);
        ps.executeUpdate();
    } catch (Exception e) {
            e.printStackTrace();
        }
}

    public int getLastExamId() {
        int lastExamId = 0;
        String query = "SELECT MAX(exam_id) FROM Exams";
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                lastExamId = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lastExamId;
    }

    public void saveChoices(int questionId, String choiceText, boolean isCorrect) {
        String query = "INSERT INTO ExamChoices (question_id, choice_text, is_correct) VALUES (?, ?, ?)";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, questionId);
            ps.setString(2, choiceText);
            ps.setBoolean(3, isCorrect);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getGeneratedQuestionId() {
        // Add your code to retrieve the last inserted question ID from the database
        // You can use the ResultSet's getGeneratedKeys method to get the generated ID
        // Example:
        int lastId = 0;
        try {
            String query = "SELECT MAX(question_id) AS last_id FROM ExamQuestions";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                lastId = rs.getInt("last_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lastId;
    }

    public Map<Integer, Integer> getNumberOfParticipants() {
        Map<Integer, Integer> participantsMap = new HashMap<>();
        String query = "SELECT course_id, COUNT(DISTINCT user_id) AS number_of_users FROM Purchases GROUP BY course_id";
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int courseId = rs.getInt("course_id");
                int numberOfUsers = rs.getInt("number_of_users");
                participantsMap.put(courseId, numberOfUsers);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return participantsMap;
    }
   public List<Course> getPurchasedCourses(int userId) {
    List<Course> coursesList = new ArrayList<>();
    String query = "SELECT c.course_id, c.title, c.category_id, c.description, c.price, c.created_at, c.updated_at, c.status " +
                   "FROM Courses c INNER JOIN Purchases p ON c.course_id = p.course_id WHERE p.user_id = ?";
    try {
         ps = con.prepareStatement(query);
        ps.setInt(1, userId);
         rs = ps.executeQuery();
        while (rs.next()) {
            Course course = new Course(
                rs.getInt("course_id"),
                rs.getString("title"),
                rs.getInt("category_id"),
                rs.getString("description"),
                rs.getBigDecimal("price"),
                rs.getDate("created_at"),
                rs.getDate("updated_at"),
                rs.getString("status")
            );
            coursesList.add(course);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return coursesList;
}


    public Map<Integer, Integer> getAverageRatings() {
        Map<Integer, Integer> ratingsMap = new HashMap<>();
        String query = "SELECT course_id, AVG(rating) AS average_rating FROM Feedback GROUP BY course_id";
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int courseId = rs.getInt("course_id");
                int averageRating = rs.getInt("average_rating");
                ratingsMap.put(courseId, averageRating);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ratingsMap;
    }

    public List<Course> getCourseById(int expertId) {
        String query = "select * from Courses c \n"
                + "join ExpertCourses ec on c.course_id = ec.course_id\n"
                + "join Experts e on ec.expert_id = e.expert_id\n"
                + "join Users u on u.user_id = e.user_id\n"
                + "where u.user_id = ?";
        List<Course> e = new ArrayList<>();
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, expertId);
            rs = ps.executeQuery();

            while (rs.next()) { // Use while instead of if
                int courseID = rs.getInt("course_id");
                String title = rs.getString("title");
                Integer categoryId = rs.getInt("category_id");
                String description = rs.getString("description");
                BigDecimal price = rs.getBigDecimal("price");
                Date createdAt = rs.getDate("created_at");
                Date updatedAt = rs.getDate("updated_at");
                String status = rs.getString("status");
                Course course = new Course(courseID, title, categoryId, description, price, createdAt, updatedAt, status);
                e.add(course);
            }
        } catch (Exception s) {
            s.printStackTrace(); // Handle exceptions appropriately in your application
        }

        return e;
    }

    public Course getCourseByCourseId(int expertId) {
        String query = "select * from Courses c where c.course_id=?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, expertId);
            rs = ps.executeQuery();

            while (rs.next()) { // Use while instead of if
                int courseID = rs.getInt("course_id");
                String title = rs.getString("title");
                Integer categoryId = rs.getInt("category_id");
                String description = rs.getString("description");
                BigDecimal price = rs.getBigDecimal("price");
                Date createdAt = rs.getDate("created_at");
                Date updatedAt = rs.getDate("updated_at");
                String status = rs.getString("status");
                Course course = new Course(courseID, title, categoryId, description, price, createdAt, updatedAt, status);
                return course;
            }
        } catch (Exception s) {
            s.printStackTrace(); // Handle exceptions appropriately in your application
        }

        return null;
    }

    public List<Feedback> getAllFeedback(int userID) {
        String query = """
                       select * from Feedback f
                       join Courses c on f.course_id = c.course_id
                       join ExpertCourses ec on ec.course_id = c.course_id
                       join Experts e on e.expert_id = ec.expert_id
                       join Users u on u.user_id = e.user_id
                       where u.user_id = ?""";
        List<Feedback> feedbackList = new ArrayList<>();
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, userID);
            rs = ps.executeQuery();

            while (rs.next()) {
                int feedbackId = rs.getInt("feedback_id");
                int userId = rs.getInt("user_id");
                int courseId = rs.getInt("course_id");
                int rating = rs.getInt("rating");
                String comment = rs.getString("comment");
                Date createdAt = rs.getTimestamp("created_at"); // Use getTimestamp to capture both date and time

                Feedback feedback = new Feedback();
                feedback.setFeedbackId(feedbackId);
                feedback.setUserId(userId);
                feedback.setCourseId(courseId);
                feedback.setRating(rating);
                feedback.setComment(comment);
                feedback.setCreatedAt(createdAt);
                feedbackList.add(feedback);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions appropriately in your application
        }

        return feedbackList;
    }

    public Map<Integer, List<Feedback>> getAllFeedbackMap(int userID) {
        String query = """
                       select * from Feedback f
                       join Courses c on f.course_id = c.course_id
                       join ExpertCourses ec on ec.course_id = c.course_id
                       join Experts e on e.expert_id = ec.expert_id
                       join Users u on u.user_id = e.user_id
                       where u.user_id = ?""";
        List<Feedback> feedbackList = new ArrayList<>();
        Map<Integer, List<Feedback>> courseFeedbackMap = new HashMap<>();
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, userID);
            rs = ps.executeQuery();

            while (rs.next()) {
                int feedbackId = rs.getInt("feedback_id");
                int userId = rs.getInt("user_id");
                int courseId = rs.getInt("course_id");
                int rating = rs.getInt("rating");
                String comment = rs.getString("comment");
                Date createdAt = rs.getTimestamp("created_at"); // Use getTimestamp to capture both date and time

                Feedback feedback = new Feedback();
                feedback.setFeedbackId(feedbackId);
                feedback.setUserId(userId);
                feedback.setCourseId(courseId);
                feedback.setRating(rating);
                feedback.setComment(comment);
                feedback.setCreatedAt(createdAt);
                feedbackList.add(feedback);
                if (!courseFeedbackMap.containsKey(courseId)) {
                    courseFeedbackMap.put(courseId, new ArrayList<>());
                }
                courseFeedbackMap.get(courseId).add(feedback);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions appropriately in your application
        }

        return courseFeedbackMap;
    }

    public Map<Integer, Double> getTotalProfitCourse(int userID) {
        String query = """
                       SELECT 
                           c.course_id, 
                           c.title, 
                           c.price, 
                           COUNT(DISTINCT p.user_id) AS number_of_users, 
                           (c.price * COUNT(DISTINCT p.user_id)) AS total_revenue
                       FROM 
                           Courses c
                       JOIN 
                           Purchases p ON c.course_id = p.course_id
                       join ExpertCourses ec on ec.course_id = c.course_id
                       join Experts e on e.expert_id = ec.expert_id
                       join Users u on u.user_id = e.user_id
                       where u.user_id =?
                       GROUP BY 
                           c.course_id, c.title, c.price;""";
        Map<Integer, Double> revenueMap = new HashMap<>();
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, userID);
            rs = ps.executeQuery();

            while (rs.next()) {
                int courseId = rs.getInt("course_id");
                double totalRevenue = rs.getDouble("total_revenue");
                revenueMap.put(courseId, totalRevenue);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions appropriately in your application
        }

        return revenueMap;
    }

    public List<Section> getSectionsByCourseId(int courseId) {
        List<Section> sections = new ArrayList<>();
        String query = "SELECT section_id, course_id, title FROM Sections WHERE course_id = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, courseId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Section section = new Section();
                section.setSectionId(rs.getInt("section_id"));
                section.setCourseId(rs.getInt("course_id"));
                section.setTitle(rs.getString("title"));
                sections.add(section);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sections;
    }

    public List<Lecture> getLecturesBySectionId(int sectionId) {
        List<Lecture> lectures = new ArrayList<>();
        String query = "SELECT lecture_id, section_id, title FROM Lectures WHERE section_id = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, sectionId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Lecture lecture = new Lecture();
                lecture.setLectureId(rs.getInt("lecture_id"));
                lecture.setSectionId(rs.getInt("section_id"));
                lecture.setTitle(rs.getString("title"));
                lectures.add(lecture);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lectures;
    }

    public List<Exam> getExamsByLectureId(int lectureId) {
        List<Exam> exams = new ArrayList<>();
        String query = "SELECT exam_id, lecture_id, title, description FROM Exams WHERE lecture_id = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, lectureId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Exam exam = new Exam();
                exam.setExamId(rs.getInt("exam_id"));
                exam.setLectureId(rs.getInt("lecture_id"));
                exam.setTitle(rs.getString("title"));
                exam.setDescription(rs.getString("description"));
                exams.add(exam);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return exams;
    }

    public List<ExamQuestion> getQuestionsByExamId(int examId) {
        List<ExamQuestion> questions = new ArrayList<>();
        String query = "SELECT question_id, exam_id, question_text FROM ExamQuestions WHERE exam_id = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, examId);
            rs = ps.executeQuery();

            while (rs.next()) {
                ExamQuestion question = new ExamQuestion();
                question.setQuestionId(rs.getInt("question_id"));
                question.setExamId(rs.getInt("exam_id"));
                question.setQuestionText(rs.getString("question_text"));
                questions.add(question);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return questions;
    }

    public List<ExamChoice> getChoicesByQuestionId(int questionId) {
        List<ExamChoice> choices = new ArrayList<>();
        String query = "SELECT choice_id, question_id, choice_text, is_correct FROM ExamChoices WHERE question_id = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, questionId);
            rs = ps.executeQuery();

            while (rs.next()) {
                ExamChoice choice = new ExamChoice();
                choice.setChoiceId(rs.getInt("choice_id"));
                choice.setQuestionId(rs.getInt("question_id"));
                choice.setChoiceText(rs.getString("choice_text"));
                choice.setCorrect(rs.getBoolean("is_correct"));
                choices.add(choice);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return choices;
    }

    public void saveVideoLink(int lectureId, String title, String description, String youtubeLink) {
        String query = "INSERT INTO Videos (lecture_id, title, description, youtube_link) VALUES (?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, lectureId);
            ps.setString(2, title);
            ps.setString(3, description);
            ps.setString(4, youtubeLink);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Video> getVideosByLectureId(int lectureId) {
        List<Video> videos = new ArrayList<>();
        String query = "SELECT * FROM Videos WHERE lecture_id = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, lectureId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Video video = new Video();
                video.setVideoId(rs.getInt("video_id"));
                video.setLectureId(rs.getInt("lecture_id"));
                video.setTitle(rs.getString("title"));
                video.setDescription(rs.getString("description"));
                video.setYoutubeLink(rs.getString("youtube_link"));
                videos.add(video);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return videos;
    }

    public void saveArticleLink(int lectureId, String title, String description, String articleLink) {
        String query = "INSERT INTO Articles (lecture_id, title, description, article_link) VALUES (?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, lectureId);
            ps.setString(2, title);
            ps.setString(3, description);
            ps.setString(4, articleLink);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addLecture(int sectionId, String lectureTitle) {
        String query = "INSERT INTO Lectures (section_id, title) VALUES (?, ?)";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, sectionId);
            ps.setString(2, lectureTitle);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteLecture(int lectureId) {
        String query = "DELETE FROM Lectures WHERE lecture_id = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, lectureId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void submitCourseForApproval(int courseId) {
        String query = "UPDATE Courses SET status = 'Pending Approval' WHERE course_id = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, courseId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Course> getPendingCourses() {
        String query = "SELECT * FROM Courses WHERE status = 'Pending Approval'";
        List<Course> pendingCourses = new ArrayList<>();
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int courseID = rs.getInt("course_id");
                String title = rs.getString("title");
                Integer categoryId = rs.getInt("category_id");
                String description = rs.getString("description");
                BigDecimal price = rs.getBigDecimal("price");
                Date createdAt = rs.getDate("created_at");
                Date updatedAt = rs.getDate("updated_at");
                String status = rs.getString("status");
                Course course = new Course(courseID, title, categoryId, description, price, createdAt, updatedAt, status);
                pendingCourses.add(course);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pendingCourses;
    }

    public List<Article> getArticlesByLectureId(int lectureId) {
        List<Article> articles = new ArrayList<>();
        String sql = "SELECT * FROM articles WHERE lecture_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, lectureId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Article article = new Article();
                article.setArticleId(rs.getInt("article_id"));
                article.setTitle(rs.getString("title"));
                article.setArticleLink(rs.getString("article_link"));
                article.setDescription(rs.getString("description"));
                articles.add(article);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articles;
    }

    public void approveCourse(int courseId) {
        String sql = "UPDATE courses SET status = 'approved' WHERE course_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, courseId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void rejectCourse(int courseId) {
        String sql = "UPDATE courses SET status = 'rejected' WHERE course_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, courseId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

   public void deleteExam(int examId) {
    try {
        // Step 1: Delete from ExamResults
        String deleteExamResultsSQL = "DELETE FROM ExamResults WHERE question_id IN (SELECT question_id FROM ExamQuestions WHERE exam_id = ?)";
        ps = con.prepareStatement(deleteExamResultsSQL);
        ps.setInt(1, examId);
        ps.executeUpdate();

        // Step 2: Delete from ExamSubmissions
        String deleteExamSubmissionsSQL = "DELETE FROM ExamSubmissions WHERE exam_id = ?";
        ps = con.prepareStatement(deleteExamSubmissionsSQL);
        ps.setInt(1, examId);
        ps.executeUpdate();

        // Step 3: Delete from ExamChoices
        String deleteExamChoicesSQL = "DELETE FROM ExamChoices WHERE question_id IN (SELECT question_id FROM ExamQuestions WHERE exam_id = ?)";
        ps = con.prepareStatement(deleteExamChoicesSQL);
        ps.setInt(1, examId);
        ps.executeUpdate();

        // Step 4: Delete from ExamQuestions
        String deleteExamQuestionsSQL = "DELETE FROM ExamQuestions WHERE exam_id = ?";
        ps = con.prepareStatement(deleteExamQuestionsSQL);
        ps.setInt(1, examId);
        ps.executeUpdate();

        // Step 5: Delete from Exams
        String deleteExamsSQL = "DELETE FROM Exams WHERE exam_id = ?";
        ps = con.prepareStatement(deleteExamsSQL);
        ps.setInt(1, examId);
        ps.executeUpdate();

        // Step 6: Add the deleted exam_id to DeletedExamIDs
        String insertDeletedExamIdSQL = "INSERT INTO DeletedExamIDs (exam_id) VALUES (?)";
        ps = con.prepareStatement(insertDeletedExamIdSQL);
        ps.setInt(1, examId);
        ps.executeUpdate();

    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public void deleteVideo(int videoId) {
        String sql = "DELETE FROM videos WHERE video_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, videoId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void deleteArticle(int articleId) {
        String sql = "DELETE FROM articles WHERE article_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, articleId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void createExpert(String username, String password, String email, String specialty, String bio) {
        String createUserSql = "INSERT INTO Users (username, password, email) VALUES (?, ?, ?)";
        String createExpertSql = "INSERT INTO Experts (description) VALUES ( ?)";
        String insertExpertCategorySql = "INSERT INTO ExpertCategories (expert_id, category_id) VALUES (?, ?)";

        try {

            // Create user
            ps = con.prepareStatement(createUserSql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.executeUpdate();

            if (rs.next()) {
                int userId = rs.getInt(1);

                // Create expert with the new user ID
                ps = con.prepareStatement(createExpertSql);

                ps.setString(1, bio);
                ps.executeUpdate();

                if (rs.next()) {
                    int expertId = rs.getInt(1);

                    // Fetch category_id for the selected category
                    int categoryId = getCategoryIdByName(specialty);

                    // Insert into ExpertCategories
                    ps = con.prepareStatement(insertExpertCategorySql);
                    ps.setInt(1, expertId);
                    ps.setInt(2, categoryId);
                    ps.executeUpdate();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public int createUser(String username, String password, String email) {
        int userId = -1;
        String createUserSql = "INSERT INTO Users (username, role_id, password, email, created_at) VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)";
        try {
            ps = con.prepareStatement(createUserSql);
            ps.setString(1, username);
            ps.setInt(2, 3); // Assuming role_id 3 for experts
            ps.setString(3, password);
            ps.setString(4, email);
            ps.executeUpdate();

            String getUserIdSql = "SELECT MAX(user_id) AS last_id FROM Users";
            ps = con.prepareStatement(getUserIdSql);
            rs = ps.executeQuery();
            if (rs.next()) {
                userId = rs.getInt("last_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userId;
    }

    public int createExpert(int userId, String bio) {
        int expertId = -1;
        String createExpertSql = "INSERT INTO Experts (user_id, description) VALUES (?, ?)";
        try {
            ps = con.prepareStatement(createExpertSql);
            ps.setInt(1, userId);
            ps.setString(2, bio);
            ps.executeUpdate();

            String getExpertIdSql = "SELECT MAX(expert_id) AS last_id FROM Experts";
            ps = con.prepareStatement(getExpertIdSql);
            rs = ps.executeQuery();
            if (rs.next()) {
                expertId = rs.getInt("last_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return expertId;
    }

    public void insertExpertCategory(int expertId, int categoryId) {
        String insertExpertCategorySql = "INSERT INTO ExpertCategories (expert_id, category_id) VALUES (?, ?)";
        try {
            ps = con.prepareStatement(insertExpertCategorySql);
            ps.setInt(1, expertId);
            ps.setInt(2, categoryId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createExpertAccount(String username, String password, String email, String specialty, String bio) {
        try {
            con.setAutoCommit(false);  // Bắt đầu transaction

            int userId = createUser(username, password, email);
            if (userId == -1) {
                throw new Exception("User creation failed.");
            }

            int expertId = createExpert(userId, bio);
            if (expertId == -1) {
                throw new Exception("Expert creation failed.");
            }

            int categoryId = getCategoryIdByName(specialty);
            insertExpertCategory(expertId, categoryId);

            con.commit();  // Commit transaction
        } catch (Exception e) {
            e.printStackTrace();
            try {
                con.rollback();  // Rollback transaction on error
            } catch (Exception rollbackEx) {
                rollbackEx.printStackTrace();
            }
        } finally {
            try {
                con.setAutoCommit(true);  // Reset to default commit mode
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int getCategoryIdByName(String categoryName) {
        String sql = "SELECT category_id FROM Categories WHERE name = ?";
        int categoryId = -1;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, categoryName);
            rs = ps.executeQuery();
            if (rs.next()) {
                categoryId = rs.getInt("category_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryId;
    }
    
      public double getTotalRevenueToday() {
        String sql = "SELECT SUM(total_amount) AS total_revenue FROM Orders WHERE DATEDIFF(day, order_date, GETDATE()) = 0";
        double totalRevenue = 0.0;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                totalRevenue = rs.getDouble("total_revenue");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return totalRevenue;
    }
       public int getFeedbackCountToday() {
        String sql = "SELECT COUNT(*) AS feedback_count FROM Feedback WHERE DATEDIFF(day, created_at, GETDATE()) = 0";
        int feedbackCount = 0;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                feedbackCount = rs.getInt("feedback_count");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return feedbackCount;
    }
        public int getUserRegistrationCountToday() {
        String sql = "SELECT COUNT(*) AS user_count FROM Users WHERE DATEDIFF(day, created_at, GETDATE()) = 0";
        int userCount = 0;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                userCount = rs.getInt("user_count");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return userCount;
    }
          public int getCourseRegistrationCountToday() {
        String sql = "SELECT COUNT(*) AS course_count FROM Courses WHERE DATEDIFF(day, created_at, GETDATE()) = 0";
        int courseCount = 0;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                courseCount = rs.getInt("course_count");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return courseCount;
    }
     public List<ExamQuestion> getExamQuestionsByExamId(int examId) {
    List<ExamQuestion> questions = new ArrayList<>();
    String query = "SELECT q.question_id, q.question_text, c.choice_id, c.choice_text, c.is_correct " +
                   "FROM ExamQuestions q " +
                   "JOIN ExamChoices c ON q.question_id = c.question_id " +
                   "WHERE q.exam_id = ?";

    try {
        ps = con.prepareStatement(query);
        ps.setInt(1, examId);
        rs = ps.executeQuery();

        ExamQuestion currentQuestion = null;
        List<ExamChoice> choices = null;

        while (rs.next()) {
            int questionId = rs.getInt("question_id");
            String questionText = rs.getString("question_text");
            int choiceId = rs.getInt("choice_id");
            String choiceText = rs.getString("choice_text");
            boolean isCorrect = rs.getBoolean("is_correct");

            if (currentQuestion == null || currentQuestion.getQuestionId() != questionId) {
                if (currentQuestion != null) {
                    currentQuestion.setChoices(choices);
                    questions.add(currentQuestion);
                }

                currentQuestion = new ExamQuestion(questionId, questionText);
                choices = new ArrayList<>();
            }

            choices.add(new ExamChoice(choiceId, choiceText, isCorrect));
        }

        if (currentQuestion != null) {
            currentQuestion.setChoices(choices);
            questions.add(currentQuestion);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } 

    return questions;
}
public int getCorrectChoiceId(int questionId) {
        int correctChoiceId = -1;
                
        try {
            // Query để lấy đáp án đúng dựa trên question_id và is_correct = 1 (được đánh dấu là đáp án đúng)
            String sql = "SELECT choice_id FROM ExamChoices WHERE question_id = ? AND is_correct = 1";
            ps = con.prepareStatement(sql);
        ps.setInt(1, questionId);
        rs = ps.executeQuery();
            
            if (rs.next()) {
                correctChoiceId = rs.getInt("choice_id");
            }
        }
        catch (Exception e) {
        e.printStackTrace();
    } 
        
        return correctChoiceId;
    }
      public UserProgress getUserProgress(int userId, int courseId) {
        UserProgress userProgress = null;
        try {
            String sql = "SELECT * FROM UserProgress WHERE user_id = ? AND course_id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, courseId);
            rs = ps.executeQuery();
            if (rs.next()) {
                userProgress = new UserProgress();
                userProgress.setUserId(rs.getInt("user_id"));
                userProgress.setCourseId(rs.getInt("course_id"));
                userProgress.setGrade(rs.getInt("grade"));
                userProgress.setProgress(rs.getDouble("progress"));
                userProgress.setCompletedExamIds(rs.getString("completed_exam_ids"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userProgress;
    }

    public void updateUserProgress(UserProgress userProgress) {
        try {
            String sql = "UPDATE UserProgress SET grade = ?, progress = ?, completed_exam_ids = ? WHERE user_id = ? AND course_id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userProgress.getGrade());
            ps.setDouble(2, userProgress.getProgress());
            ps.setString(3, userProgress.getCompletedExamIds());
            ps.setInt(4, userProgress.getUserId());
            ps.setInt(5, userProgress.getCourseId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addUserProgress(UserProgress userProgress) {
        try {
            String sql = "INSERT INTO UserProgress (user_id, course_id, grade, progress, completed_exam_ids) VALUES (?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userProgress.getUserId());
            ps.setInt(2, userProgress.getCourseId());
            ps.setInt(3, userProgress.getGrade());
            ps.setDouble(4, userProgress.getProgress());
            ps.setString(5, userProgress.getCompletedExamIds());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Lấy số lượng bài thi trong khóa học
    public int getExamCountByCourseId(int courseId) {
    int examCount = 0;
    try {
        String sql = "SELECT COUNT(*) AS exam_count " +
                     "FROM Exams e " +
                     "JOIN Lectures l ON l.lecture_id = e.lecture_id " +
                     "JOIN Sections s ON s.section_id = l.section_id " +
                     "JOIN Courses c ON c.course_id = s.course_id " +
                     "WHERE c.course_id = ?";
        ps = con.prepareStatement(sql);
        ps.setInt(1, courseId);
        rs = ps.executeQuery();

        if (rs.next()) {
            examCount = rs.getInt("exam_count");
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return examCount;
}
     public List<ExamResult> getExamResultsBySubmissionId(int submissionId) {
        List<ExamResult> examResults = new ArrayList<>();
        String query = "SELECT er.result_id, er.submission_id, er.question_id, er.choice_id, er.is_correct, " +
                       "eq.question_text, ec.choice_text " +
                       "FROM ExamResults er " +
                       "JOIN ExamQuestions eq ON er.question_id = eq.question_id " +
                       "JOIN ExamChoices ec ON er.choice_id = ec.choice_id " +
                       "WHERE er.submission_id = ?";
        try {
             ps = con.prepareStatement(query);
            ps.setInt(1, submissionId);
             rs = ps.executeQuery();
            while (rs.next()) {
                ExamResult result = new ExamResult();
                result.setResultId(rs.getInt("result_id"));
                result.setSubmissionId(rs.getInt("submission_id"));
                result.setQuestionId(rs.getInt("question_id"));
                result.setChoiceId(rs.getInt("choice_id"));
                result.setCorrect(rs.getBoolean("is_correct"));
                result.setQuestionText(rs.getString("question_text"));
                result.setChoiceText(rs.getString("choice_text"));
                examResults.add(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return examResults;
    }
   public void addExamSubmission(int userId, int examId, double score) {
        String query = "INSERT INTO ExamSubmissions (user_id, exam_id, submission_date, score) VALUES (?, ?, GETDATE(), ?)";
        try {
             ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setInt(2, examId);
            ps.setDouble(3, score);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get the last submission ID for a specific user and exam
    public int getLastSubmissionId(int userId, int examId) {
        int submissionId = -1;
        String query = "SELECT TOP 1 submission_id FROM ExamSubmissions WHERE user_id = ? AND exam_id = ? ORDER BY submission_date DESC";
        try {
             ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setInt(2, examId);
             rs = ps.executeQuery();
            if (rs.next()) {
                submissionId = rs.getInt("submission_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return submissionId;
    }

    // Update the score of an exam submission
    public void updateExamSubmissionScore(int submissionId, double score) {
        String query = "UPDATE ExamSubmissions SET score = ? WHERE submission_id = ?";
        try {
             ps = con.prepareStatement(query);
            ps.setDouble(1, score);
            ps.setInt(2, submissionId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Add an exam result
    public void addExamResult(ExamResult result) {
        String query = "INSERT INTO ExamResults (submission_id, question_id, choice_id, is_correct) VALUES (?, ?, ?, ?)";
        try {
             ps = con.prepareStatement(query);
            ps.setInt(1, result.getSubmissionId());
            ps.setInt(2, result.getQuestionId());
            ps.setInt(3, result.getChoiceId());
            ps.setBoolean(4, result.isCorrect());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     public void addCourse(Course course) {
        String sql = "INSERT INTO Courses (title, category_id, description, price, created_at) VALUES (?, ?, ?, ?, ?)";
        
        try { 
            ps = con.prepareStatement(sql); 
            ps.setString(1, course.getTitle());
            ps.setInt(2, course.getCategoryId());
            ps.setString(3, course.getDescription());
            BigDecimal price = course.getPrice();
        if (price != null) {
            ps.setDouble(4, price.doubleValue());
        } else {
            ps.setNull(4, java.sql.Types.DOUBLE);
        }
            ps.setTimestamp(5, (Timestamp) course.getCreatedAt());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
public int getLatestCourseId() {
    String sqlGetMaxCourseId = "SELECT MAX(course_id) AS max_id FROM Courses";
    
    int latestCourseId = -1;

    try {
        ps = con.prepareStatement(sqlGetMaxCourseId);
        rs = ps.executeQuery();
        if (rs.next()) {
            latestCourseId = rs.getInt("max_id");
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Close resources
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    return latestCourseId;
}

public void addExpertCourse(int expertId, int courseId) {
        String sqlInsertExpertCourse = "INSERT INTO ExpertCourses (expert_id, course_id) VALUES (?, ?)";
        
        try {
            ps = con.prepareStatement(sqlInsertExpertCourse);
            ps.setInt(1, expertId);
            ps.setInt(2, courseId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
  public int getExpertIdByUserId(int userId) {
        int expertId = -1;
        String query = "SELECT expert_id FROM Experts WHERE user_id = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
             rs = ps.executeQuery();
            if (rs.next()) {
                expertId = rs.getInt("expert_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return expertId;
    }




}
