package control;

import DAO.dao;
import Entity.Users;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet(name="Register", urlPatterns={"/Register"})
public class Register extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Register</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Register at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    @Override
      protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dao dao = new dao();
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String fullName = request.getParameter("fullName");
        String birthDateStr = request.getParameter("birthDate");
        int roleId = Integer.parseInt(request.getParameter("roleId"));

        if (!password.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "Passwords do not match");
            request.getRequestDispatcher("home").forward(request, response);
            return;
        }

        if (dao.doesUsernameExist(username)) {
            request.setAttribute("errorMessage", "Username already exists");
            request.getRequestDispatcher("home").forward(request, response);
            return;
        }

        if (dao.doesEmailExist(email)) {
            request.setAttribute("errorMessage", "Email already exists");
            request.getRequestDispatcher("home").forward(request, response);
            return;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date birthDate = sdf.parse(birthDateStr);

            Users user = new Users();
            user.setRoleId(roleId);
            user.setUsername(username);
            user.setPassword(password);
            user.setFullName(fullName);
            user.setEmail(email);
            user.setBirthDate(birthDate);

            if (roleId == 2) { // Regular users
                boolean isRegistered = dao.registerUser(user);
                if (isRegistered) {
                    response.sendRedirect("login.jsp");
                } else {
                    request.setAttribute("errorMessage", "Registration failed. Please try again.");
                    request.getRequestDispatcher("Register.jsp").forward(request, response);
                }
            } else if (roleId == 3) { // Experts
                // Forward to expert registration page to collect additional details
                request.setAttribute("username", username);
                request.setAttribute("email", email);
                request.setAttribute("password", password);
                request.setAttribute("fullName", fullName);
                request.setAttribute("birthDate", birthDateStr);
                request.setAttribute("roleId", roleId);
                request.getRequestDispatcher("ExpertRegistration.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Invalid role selected.");
                request.getRequestDispatcher("Register.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Invalid birth date format");
            request.getRequestDispatcher("Register.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
