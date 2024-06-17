/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import DAO.dao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name = "updateExpert", urlPatterns = {"/updateExpert"})
public class updateExpert extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet updateExpert</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet updateExpert at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        int expertId = Integer.parseInt(request.getParameter("expertId"));
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String specialty = request.getParameter("specialty");
        String addSpecialty = request.getParameter("addSpecialty");
        String deleteSpecialty = request.getParameter("deleteSpecialty");

        dao expertDAO = new dao();
        expertDAO.updateExpert(username, email, specialty, expertId);
        if (addSpecialty != null && !addSpecialty.isEmpty()) {
            int categoryId = expertDAO.getCategoryByName(addSpecialty);
            expertDAO.addCategorForExpert(expertId, categoryId);
        }
        if (deleteSpecialty != null && !deleteSpecialty.isEmpty()) {
            int categoryId = expertDAO.getCategoryByName(deleteSpecialty);
            if (categoryId != -1) {
                expertDAO.deleteCategoryForExpert(expertId, categoryId);
            }
        }

        HttpSession session = request.getSession();
        session.setAttribute("message", "Update successfully");

        // Forward the request to the editExpertServlet with updated information
        request.getRequestDispatcher("editExpert").forward(request, response);
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
