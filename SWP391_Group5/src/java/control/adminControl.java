/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package control;

import DAO.dao;
import Entity.Category;
import Entity.Course;
import Entity.Expert;
import Entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
@WebServlet(name="adminControl", urlPatterns={"/adminControl"})
public class adminControl extends HttpServlet {
   
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
            out.println("<title>Servlet adminControl</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet adminControl at " + request.getContextPath () + "</h1>");
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
            response.sendRedirect("login.jsp");
            return;
        }

        dao dao = new dao();
        ArrayList<Expert> expertList = (ArrayList<Expert>) dao.getExpert();
        ArrayList<Category> categoryList = (ArrayList<Category>) dao.getCategories();
        Map<Integer, List<String>> expertCategories = dao.categoriesExpert();
        ArrayList<Users> userList = (ArrayList<Users>) dao.getUsers();
        ArrayList<Course> courseList = (ArrayList<Course>) dao.getCourse();
        Map<Integer, List<String>> courseExpert = dao.expertCourse();
        Map<Integer, List<String>> courseCate = dao.courseCategory();
        List<Course> pendingCourseList = dao.getPendingCourses();
        List<Users> pendingExpertList = dao.getPendingExperts();

        request.setAttribute("username", loggedInUser.getUsername());
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("courseExpert", courseExpert);
        request.setAttribute("courseList", courseList);
        request.setAttribute("userList", userList);
        request.setAttribute("expertList", expertList);
        request.setAttribute("expertCategories", expertCategories);
        request.setAttribute("courseCate", courseCate);
        request.setAttribute("pendingCourseList", pendingCourseList);
        request.setAttribute("pendingExpertList", pendingExpertList);

        request.getRequestDispatcher("Admin.jsp").forward(request, response);
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
