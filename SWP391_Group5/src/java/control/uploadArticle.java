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

/**
 *
 * @author Admin
 */
@WebServlet(name="uploadArticle", urlPatterns={"/uploadArticle"})
public class uploadArticle extends HttpServlet {
   
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
            out.println("<title>Servlet uploadArticle</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet uploadArticle at " + request.getContextPath () + "</h1>");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Establish database connection
          
            dao articleDAO = new dao();

            int lectureId = Integer.parseInt(request.getParameter("lectureId"));
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String articleLink = request.getParameter("articleLink");
              int courseId = Integer.parseInt(request.getParameter("courseId"));
            // Save article info to the database
            articleDAO.saveArticleLink(lectureId, title, description, articleLink);

            // Set attributes to forward to the JSP
            request.setAttribute("sectionId", request.getParameter("sectionId"));
            request.setAttribute("lectureId", lectureId);
            request.setAttribute("sectionTitle", request.getParameter("sectionTitle"));
            request.setAttribute("lectureTitle", request.getParameter("lectureTitle"));
            request.setAttribute("message", "Article link saved successfully.");

            // Forward to confirmation JSP
             response.sendRedirect("ContentUpload?courseId=" + courseId);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Failed to save article link. Please try again.");
            request.getRequestDispatcher("uploadArticle.jsp").forward(request, response);
        }
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
