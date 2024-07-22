/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package control;

import DAO.dao;
import Entity.Expert;
import Entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author Admin
 */
@MultipartConfig
@WebServlet(name="ExpertEditProfile", urlPatterns={"/ExpertEditProfile"})
public class ExpertEditProfile extends HttpServlet {
   
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
            out.println("<title>Servlet ExpertEditProfile</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ExpertEditProfile at " + request.getContextPath () + "</h1>");
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
        String userIdParam = request.getParameter("user_id");
        if (userIdParam != null) {
            int userId = Integer.parseInt(userIdParam);
            dao userDao = new dao();
            Users user = userDao.getUsersByID(userId);
            Expert expert = userDao.getExpertByUserId(userId);

            if (user != null && expert != null) {
                request.setAttribute("user", user);
                request.setAttribute("expert", expert);
                request.getRequestDispatcher("ExpertEditProfile.jsp").forward(request, response);
            } else {
                response.sendRedirect("login.jsp");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int userId = Integer.parseInt(request.getParameter("user_id"));
            String fullName = request.getParameter("full_name");
            String email = request.getParameter("email");
            String phoneNumber = request.getParameter("phone_number");
            String address = request.getParameter("address");
            String birthDate = request.getParameter("birth_date");
            String description = request.getParameter("description");
            String certification = request.getParameter("certification");

            Part part = request.getPart("profile_image");
            String imagePath = null;

            if (part != null && part.getSize() > 0) {
                String realPath = request.getServletContext().getRealPath("/images");
                String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();

                if (!Files.exists(Paths.get(realPath))) {
                    Files.createDirectories(Paths.get(realPath));
                }

                imagePath = realPath + "/" + filename;
                part.write(imagePath);
                imagePath = "images/" + filename;
            }

            dao userDao = new dao();
            userDao.updateUserProfile(userId, fullName, email, phoneNumber, address, birthDate);
            userDao.updateExpertProfile(userId, description, certification);

            if (imagePath != null) {
                userDao.updateUserProfileImage(userId, imagePath);
            }

            response.sendRedirect("ExpertViewProfile?user_id=" + userId);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while updating the profile.");
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
