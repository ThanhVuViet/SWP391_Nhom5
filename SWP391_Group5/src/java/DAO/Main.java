/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.Course;
import Entity.ExamChoice;
import Entity.Expert;
import Entity.Feedback;
import Entity.Order;
import Entity.Users;
import java.math.BigDecimal;
import java.security.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Admin
 */
public class Main {

    public static void main(String[] args) throws ParseException {
        // Database credentials
        dao dao = new dao();
        //List<Users> u = new ArrayList<>();
        //u = dao.login();
        //for (Users user : u) {
        // System.out.println(user);
        //}
        List<Expert> e = dao.getExpert();

        for (Expert expert : e) {
            System.out.println(expert);
        }
        Expert t = dao.getExpertById(1);
        Map<Integer, List<String>> courseExpert = dao.courseCategory();

        List<Course> courseList = dao.getCourse();
        for (Course course : courseList) {

            List<String> name = courseExpert.get(course.getCourseId());
            for (String s : name) {
                System.err.println(course.getCourseId() + s);
            }
        }
        List<Expert> experts = dao.getExpertByName("h");
        for (Expert expert: experts) {
            System.err.println(expert);
        }
         List<Course> courses = dao.getCoursesByCate("p");
         for (Course course : courses) {
             System.err.println(course);
         }
         
         
         OrderDao orderDao = new OrderDao();
             
        
            String startDay = "2023-06-01";
        String endDay = "2023-06-30";   
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = sdf1.parse(endDay);
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        java.util.Date date1 = sdf1.parse(startDay);
        java.sql.Date sqlDate1 = new java.sql.Date(date1.getTime());
         List<Order> orderList = orderDao.getOrder(sqlDate1, sqlDate);
         for (Order order: orderList) {
             System.err.println(order);
         }
         Map<Integer, Double> monthlyRevenue = orderDao.getMonthlyDouble(2023); 
          for (Map.Entry<Integer, Double> entry: monthlyRevenue.entrySet()) {
             System.err.println(entry.getValue());
         }
          List<Users> top5 = dao.getTop5User();
          for (Users u : top5) {
              System.err.println(u);
          }
        //int userId =dao.createUser("thanhvuádfdsf1111adádsdad1123123123", "123", "v@gmail.com");
        //System.err.println(userId);
        //dao.createExpert(47, "hihi");
        //dao.createExpertAccount("thanhàhsdfhsà", "123", "h@gmail.com", "Web Development", "kkk");
        //dao.insertExpertCategory(10, 1);
        int id = dao.getCategoryByName("Web Development");
        System.err.println(id);
        List<Expert> liE = dao.getExpertByName("t");
        for (Expert eu: liE) {
            System.err.println(eu);
        }
        
        List<Course> c = dao.getPurchasedCourses(10);
        for (Course cu: c) {
            System.err.println("coursname :" +cu);
        }
        Users u = dao.getUsersByID(13);
        System.out.print(u.getUsername());
        int tt = dao.getCorrectChoiceId(269);
        System.err.println(tt);
        int ttt = dao.getExamCountByCourseId(1);
        int tttt = dao.getLatestCourseId();
        System.err.println(tttt);
        System.err.println(ttt);
        int expertId = dao.getExpertIdByUserId(28);
        System.err.println(expertId);
        
       
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date birthDate = sdf.parse("1990-01-01");
            
        List<Users> us = dao.getPendingExperts();
        for (Users uu: us) {
            System.err.println(uu);
        }
        
      
        List<Feedback> fb = dao.getFeedbackByCourseId(1);
        for (Feedback fb1: fb) {
            System.err.println(fb1);
        }
        
         Feedback feedback = new Feedback();
        feedback.setCourseId(1);
        feedback.setUserId(9);
        feedback.setComment("This is a test comment.");
        feedback.setRating(5);
        feedback.setCreatedAt(new java.util.Date());

        dao.addFeedback(feedback);
           ExamChoice choice = new ExamChoice();
        choice.setChoiceText("New Test Choice");
        choice.setCorrect(true); // Set to true or false based on whether this choice is correct
        choice.setQuestionId(269); // Replace with an existing question ID from your database

        // Call the addExamChoice method
     
        boolean isss = dao.validatePassword("ThanhHuongViet", "123");
        System.out.println(isss);
        dao.updatePassword("thanhvu", "1234");
    }
   
    
   
    
}
