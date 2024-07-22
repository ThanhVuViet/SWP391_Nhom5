/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package control;

import DAO.dao;
import Entity.Article;
import Entity.Course;
import Entity.Exam;
import Entity.Lecture;
import Entity.Section;
import Entity.Video;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author Admin
 */
@WebServlet(name="AdminCourseDetail", urlPatterns={"/AdminCourseDetail"})
public class AdminCourseDetail extends HttpServlet {
   
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
            out.println("<title>Servlet AdminCourseDetail</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminCourseDetail at " + request.getContextPath () + "</h1>");
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
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        dao daoInstance = new dao();

        // Fetch course details
        Course course = daoInstance.getCourseByCourseId(courseId);

        // Fetch sections
        List<Section> sections = daoInstance.getSectionsByCourseId(courseId);

        // Fetch lectures and exams for each section
        for (Section section : sections) {
            List<Lecture> lectures = daoInstance.getLecturesBySectionId(section.getSectionId());
            section.setLectures(lectures); // Assuming Section has a setLectures method
            for (Lecture lecture : lectures) {
                List<Exam> exams = daoInstance.getExamsByLectureId(lecture.getLectureId());
                  List<Video> videos = daoInstance.getVideosByLectureId(lecture.getLectureId());
                   List<Article> articles = daoInstance.getArticlesByLectureId(lecture.getLectureId());
                lecture.setExams(exams); // Assuming Lecture has a setExams method
                lecture.setVideos(videos);
                lecture.setArticles(articles);
                
            }
        }

        // Tính toán examId tiếp theo
        int examId = daoInstance.getLastExamId() + 1;

        // Set attributes for JSP
        request.setAttribute("course", course);
        request.setAttribute("sections", sections);
        request.setAttribute("examId", examId);

        // Forward to the content upload JSP
        request.getRequestDispatcher("adminCourseDetail.jsp").forward(request, response);
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
        processRequest(request, response);
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
