/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.Category;
import Entity.Course;
import Entity.Expert;
import Entity.Users;

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

    public List<Users> getUsersByName(String userN) {
        String query = "SELECT * FROM Users u where u.username like ? ";
        List<Users> userList = new ArrayList<>();
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, "%"+userN+"%");
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
            ps.setString(1, "%" + userNameFilter + "%");
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
                + "    WHERE u.username LIKE  ?\n"
                + ") AS subquery\n"
                + "WHERE row_num = 1;";
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
                + "    WHERE ca.name LIKE  ?\n"
                + ") AS subquery\n"
                + "WHERE row_num = 1;";
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
        String query = "select * from Experts e\n"
                + "join Users u on e.user_id = u.user_id\n"
                + "where u.username like ?";
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
                Course course = new Course(courseID, title, categoryID, des, price, createdAt, updatedAt);
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
                Course course = new Course(courseID, title, categoryID, des, price, createdAt, updatedAt);
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
                Course course = new Course(courseID, title, categoryID, des, price, createdAt, updatedAt);
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
            ps.setString(1, courseName);
            ps.setString(2, description);
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
                Course course = new Course(courseID, title, categoryId, description, price, createdAt, updatedAt);
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
                course = new Course(courseID, title, categoryId, description, price, createdAt, updatedAt);

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

}
