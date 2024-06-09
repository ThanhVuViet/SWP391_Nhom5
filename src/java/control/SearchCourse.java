/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import DAO.CourseDAO;
import DAO.MyDAO;
import DAO.dao;
import Entity.Course;
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
@WebServlet(name = "SearchCourse", urlPatterns = {"/search-course"})
public class SearchCourse extends HttpServlet {

    public CourseDAO cdao = new CourseDAO();

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
        String textSearch;
        int categoryId = 0;
        if (request.getParameter("textSearch") == null) {
            textSearch = "";
        } else {
            textSearch = request.getParameter("textSearch");
        }
        if (request.getParameter("categoryId") == null) {
            categoryId = -1;
        } else {
            categoryId = Integer.parseInt(request.getParameter("categoryId"));
        }
        int page = 1;
        int recordsPerPage = 9;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        List<Course> courses = cdao.searchCourse(textSearch, categoryId, (page - 1) * recordsPerPage, recordsPerPage);
        int noOfRecords = cdao.getNoOfRecords(textSearch, categoryId);
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0
                / recordsPerPage);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("categories", new dao().getCategories());
        request.setAttribute("courses", courses);
        request.setAttribute("textSaved", textSearch);
        request.setAttribute("cateSaved", categoryId);
        request.getRequestDispatcher("courses.jsp").forward(request, response);
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
