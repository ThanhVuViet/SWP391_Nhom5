/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.Course;
import Entity.Expert;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class Main {

    public static void main(String[] args) {
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
        List<Expert> experts = dao.getExpertByCate("p");
        for (Expert expert: experts) {
            System.err.println(expert);
        }
         List<Course> courses = dao.getCoursesByCate("p");
         for (Course course : courses) {
             System.err.println(course);
         }
    }
}
