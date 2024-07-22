/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package control;

import DAO.CourseDAO;
import Entity.Course;
import Entity.Feedback;
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
 * @author win
 */
@WebServlet(name="CourseDetailController", urlPatterns={"/course-detail"})
public class CourseDetailController extends HttpServlet {

    CourseDAO cdao = new CourseDAO();
    
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
        Course course = cdao.getCourseById(courseId);
        request.setAttribute("courseId", course.getCourseId());
        request.setAttribute("courseTitle", course.getTitle());
        request.setAttribute("courseDescription", course.getDescription());
        request.setAttribute("coursePrice", course.getPrice().toPlainString());
        request.setAttribute("categoryName", cdao.getCategoryName(courseId));
        request.setAttribute("expertName", cdao.getExpertName(courseId));
        List<Feedback> feedbackList = cdao.getListFeedbackByCourse(courseId);
        request.setAttribute("feedbackList", feedbackList);
        request.getRequestDispatcher("course-detail.jsp").forward(request, response);
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
