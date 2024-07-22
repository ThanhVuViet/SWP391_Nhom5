/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.Course;
import Entity.Feedback;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author win
 */
public class CourseDAO extends MyDAO {

    public List<Course> searchCourse(String textSearch, int categoryId, int offSet, int noOfRecords) {
        List<Course> courses = new ArrayList<>();
String sql = "SELECT c.* FROM Courses c WHERE 1=1 ";
if (!"".equals(textSearch)) {
    sql += "AND c.title LIKE '%" + textSearch + "%' ";
}
if (categoryId != -1) {
    sql += "AND c.category_id = " + categoryId + " ";
}
sql += "AND c.status = 'approved' ";
sql += "ORDER BY created_at DESC OFFSET " + offSet + " ROWS FETCH NEXT " + noOfRecords + " ROWS ONLY";

try {
    ps = con.prepareStatement(sql);
    rs = ps.executeQuery();

    while (rs.next()) {
        Course course = new Course();
        course.setCourseId(rs.getInt("course_id"));
        course.setTitle(rs.getString("title"));
        course.setCategoryId(rs.getInt("category_id"));
        course.setDescription(rs.getString("description"));
        course.setPrice(rs.getBigDecimal("price"));
        course.setCreatedAt(rs.getDate("created_at"));
        course.setUpdatedAt(rs.getDate("updated_at"));
        courses.add(course);
    }
} catch (SQLException e) {
    e.printStackTrace(); // Handle exceptions appropriately in your application
}
return courses;

    }

    public int getNoOfRecords(String textSearch, int categoryId) {
        String sql = "SELECT COUNT(*) FROM Courses c WHERE 1=1 ";
        if (!"".equals(textSearch)) {
            sql += "AND c.title LIKE '%" + textSearch + "%' ";
        }
        if (categoryId != -1) {
            sql += "AND c.category_id = " + categoryId;
        }
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately in your application
        }
        return -1;
    }

    public int getNoOfPurchases(int courseId) {
        String sql = "SELECT COUNT(*) FROM Purchases WHERE course_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, courseId);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately in your application
        }
        return -1;
    }

    public String getExpertName(int courseId) {
        String sql = "SELECT u.full_name FROM ExpertCourses ec LEFT JOIN Experts e ON ec.expert_id = e.expert_id LEFT JOIN Users u ON e.user_id = u.user_id WHERE course_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, courseId);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately in your application
        }
        return null;
    }

    public String getCategoryName(int courseId) {
        String sql = "SELECT e.name FROM Courses c JOIN Categories e ON c.category_id = e.category_id WHERE c.course_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, courseId);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately in your application
        }
        return null;
    }
    
    public Course getCourseById(int courseId) {
        String sql = "SELECT c.* FROM Courses c WHERE c.course_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, courseId);
            rs = ps.executeQuery();

            if (rs.next()) {
                Course course = new Course();
                course.setCourseId(rs.getInt("course_id"));
                course.setTitle(rs.getString("title"));
                course.setCategoryId(rs.getInt("category_id"));
                course.setDescription(rs.getString("description"));
                course.setPrice(rs.getBigDecimal("price"));
                course.setCreatedAt(rs.getDate("created_at"));
                course.setUpdatedAt(rs.getDate("updated_at"));
                return course;
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately in your application
        }
        return null;
    }
    
    public List<Feedback> getListFeedbackByCourse(int courseId) {
        List<Feedback> feedbackList = new ArrayList<>();
        String sql = "SELECT TOP (4) * FROM [SWP391_SE1815].[dbo].[Feedback] WHERE [course_id] = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, courseId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Feedback feedback = new Feedback(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getDate(6));
                feedbackList.add(feedback);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately in your application
        }
        return feedbackList;
    }
    
    public String getFullname(int userId) {
        String sql = "SELECT full_name FROM Users WHERE user_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately in your application
        }
        return null;
    }
    
    public static void main(String[] args) {
        List<Feedback> f = new CourseDAO().getListFeedbackByCourse(6);
        for (Feedback feedback : f) {
            System.out.println(feedback);
        }
    }
}
