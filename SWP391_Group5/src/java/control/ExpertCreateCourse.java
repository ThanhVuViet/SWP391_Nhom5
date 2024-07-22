/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package control;

import DAO.dao;
import Entity.Course;
import Entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.security.Timestamp;

/**
 *
 * @author Admin
 */
@WebServlet(name="ExpertCreateCourse", urlPatterns={"/ExpertCreateCourse"})
public class ExpertCreateCourse extends HttpServlet {
   
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
            out.println("<title>Servlet ExpertCreateCourse</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ExpertCreateCourse at " + request.getContextPath () + "</h1>");
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
        processRequest(request, response);
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
       Users loggedInUser = (Users) request.getSession().getAttribute("acc");
        if (loggedInUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }
       dao courseDAO = new dao();
    String title = request.getParameter("courseTitle");
    String category = request.getParameter("specialty");
    int categoryId = courseDAO.getCategoryIdByName(category);
    String description = request.getParameter("courseDescription");
    double priceValue = Double.parseDouble(request.getParameter("coursePrice"));
    BigDecimal price = BigDecimal.valueOf(priceValue);

    Course newCourse = new Course();
    newCourse.setTitle(title);
    newCourse.setCategoryId(categoryId);
    newCourse.setDescription(description);
    newCourse.setPrice(price);
    newCourse.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis())); // Ensure correct import and usage

   
    courseDAO.addCourse(newCourse);
    int latestCourseId = courseDAO.getLatestCourseId();
    if (latestCourseId != -1) {
        int expertId = courseDAO.getExpertIdByUserId(loggedInUser.getUserId());
        courseDAO.addExpertCourse(expertId, latestCourseId);
    }

    response.sendRedirect("expertHome");
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
