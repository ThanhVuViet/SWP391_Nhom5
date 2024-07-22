/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package control;

import DAO.dao;
import Entity.Article;
import Entity.Course;
import Entity.Exam;
import Entity.Feedback;
import Entity.Lecture;
import Entity.Section;
import Entity.Users;
import Entity.Video;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import org.apache.catalina.User;

/**
 *
 * @author Admin
 */
@WebServlet(name="Learning", urlPatterns={"/Learning"})
public class Learning extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Learning</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Learning at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
      protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
           Users loggedInUser = (Users) request.getSession().getAttribute("acc");
        if (loggedInUser == null) {
            response.sendRedirect("home.jsp");
            return;
        }
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        dao daoInstance = new dao();

        // Fetch course details
        Course course = daoInstance.getCourseByCourseId(courseId);

        // Fetch sections
        List<Section> sections = daoInstance.getSectionsByCourseId(courseId);
        
        // Fetch lectures, exams, videos, and articles for each section
        for (Section section : sections) {
            List<Lecture> lectures = daoInstance.getLecturesBySectionId(section.getSectionId());
            section.setLectures(lectures);
            for (Lecture lecture : lectures) {
                List<Exam> exams = daoInstance.getExamsByLectureId(lecture.getLectureId());
                List<Video> videos = daoInstance.getVideosByLectureId(lecture.getLectureId());
                List<Article> articles = daoInstance.getArticlesByLectureId(lecture.getLectureId());
                lecture.setExams(exams);
                lecture.setVideos(videos);
                lecture.setArticles(articles);
            }
        }
         List<Feedback> feedbackList = null;
          List<Users> users = new ArrayList<>();
        try {
            feedbackList = daoInstance.getFeedbackByCourseId(courseId);
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions
        }
           for (Feedback feedback : feedbackList) {
            Users user = daoInstance.getUsersByID(feedback.getUserId());
            users.add(user);
        }

        // Set attributes for JSP
        request.setAttribute("course", course);
        request.setAttribute("sections", sections);
         request.setAttribute("courseId", courseId); // Truyền courseId sang JSP
           request.setAttribute("feedbackList", feedbackList); // Set feedbackList for JSP
            request.setAttribute("users", users);

        // Forward to the course details JSP
        request.getRequestDispatcher("Learning.jsp").forward(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int rating = Integer.parseInt(request.getParameter("rating"));
        Users loggedInUser = (Users) request.getSession().getAttribute("acc");
        if (loggedInUser == null) {
            response.sendRedirect("home.jsp");
            return;
        }
      
        dao daoInstance = new dao();

        // Fetch course details
        Course course = daoInstance.getCourseByCourseId(courseId);

        // Fetch sections
        List<Section> sections = daoInstance.getSectionsByCourseId(courseId);
        
        // Fetch lectures, exams, videos, and articles for each section
        for (Section section : sections) {
            List<Lecture> lectures = daoInstance.getLecturesBySectionId(section.getSectionId());
            section.setLectures(lectures);
            for (Lecture lecture : lectures) {
                List<Exam> exams = daoInstance.getExamsByLectureId(lecture.getLectureId());
                List<Video> videos = daoInstance.getVideosByLectureId(lecture.getLectureId());
                List<Article> articles = daoInstance.getArticlesByLectureId(lecture.getLectureId());
                lecture.setExams(exams);
                lecture.setVideos(videos);
                lecture.setArticles(articles);
            }
        }
        
          List<Users> users = new ArrayList<>();
        List<Feedback> feedbackList = daoInstance.getFeedbackByRating(rating);
           for (Feedback feedback : feedbackList) {
            Users user = daoInstance.getUsersByID(feedback.getUserId());
            users.add(user);
        }

        // Set attributes for JSP
        request.setAttribute("course", course);
        request.setAttribute("sections", sections);
         request.setAttribute("courseId", courseId); // Truyền courseId sang JSP
           request.setAttribute("feedbackList", feedbackList); // Set feedbackList for JSP
            request.setAttribute("users", users);

        // Forward to the course details JSP
        request.getRequestDispatcher("Learning.jsp").forward(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
