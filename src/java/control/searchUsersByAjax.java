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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
@WebServlet(name = "searchUsersByAjax", urlPatterns = {"/searchUsersByAjax"})
public class searchUsersByAjax extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String nameFilter = request.getParameter("filterName");
    String emailFilter = request.getParameter("filterEmail");
    List<Users> userList = new ArrayList<>();
    dao dao = new dao();

    if (nameFilter != null && !nameFilter.isEmpty() && emailFilter != null && !emailFilter.isEmpty()) {
        userList = dao.getUsersByEmailAndName(emailFilter, nameFilter);
    } else if (nameFilter != null && !nameFilter.isEmpty()) {
        userList = dao.getUsersByName(nameFilter);
    } else if (emailFilter != null && !emailFilter.isEmpty()) {
        userList = dao.getUsersByEmail(emailFilter);
    } else {
        userList = dao.getUsers();
    }

    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
        for (Users user : userList) {
            out.println("<tr>");
            out.println("    <td>" + user.getUserId() + "</td>");
            out.println("    <td>" + user.getUsername() + "</td>");
            out.println("    <td>" + user.getEmail() + "</td>");
            out.println("    <td>" + user.getPhoneNumber() + "</td>");
            out.println("    <td>");
            out.println("        <a href=\"#\" class=\"btn btn-primary btn-sm\">Edit</a>");
            out.println("        <button class=\"btn btn-danger btn-sm\">Delete</button>");
            out.println("    </td>");
            out.println("</tr>");
        }
    }
}


// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
