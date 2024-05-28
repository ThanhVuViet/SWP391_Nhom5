package control;

import DAO.dao;
import Entity.Users;
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
 * @author MyPC
 */
@WebServlet(name = "SignUpControl", urlPatterns = {"/signup"})
public class SignUpControl extends HttpServlet {

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
            out.println("<title>Servlet SignUpControl</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignUpControl at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("signup.jsp").forward(request, response);
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
        dao u = new dao();
        String username = (String) request.getAttribute("username"),
                   password = (String) request.getAttribute("password"), 
                   rePassword = (String)request.getAttribute("rePassword"),
                   fullName = (String) request.getAttribute("fullName"),
                   email = (String) request.getAttribute("email");
        Validation v = new Validation();
        boolean success = true;
        String errorNote = ",";
        //Checking username
        if (!v.isValidUsername(username)) {
            errorNote += "invalidUsername,";
            success = false;
        } else if (u.doesUsernameExist(username)) {
            errorNote += "duplicateUsername,";
            success = false;
        }
        //Checking password
        if (!v.isValidPassword(password)) {
            errorNote += "invalidPassword,";
            success = false;
        }
        if (!password.endsWith(rePassword)) {
            errorNote += "wrongRePassword";
        }
        //Checking fullname
        if (!v.isValidFullName(fullName)) {
            errorNote += "invalidFullName,";
            success = false;
        }
        //Checking email
        if (!v.isValidEmail(email)) {
            errorNote += "invalidEmail,";
            success = false;
        } else if (u.doesEmailExist(email)) {
            errorNote += "duplicateEmail,";
            success = false;
        }
        if (success) {
                u.addNewUser(2, username, password, fullName, email);
                response.sendRedirect("login");
        } else {
            request.setAttribute("errorNote", errorNote);
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }
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
