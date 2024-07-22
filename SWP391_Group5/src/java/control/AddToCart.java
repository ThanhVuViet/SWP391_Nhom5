package control;

import DAO.dao;
import Entity.Users;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "AddToCart", urlPatterns = {"/AddToCart"})
public class AddToCart extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Users loggedInUser = (Users) request.getSession().getAttribute("acc");
            if (loggedInUser == null) {
                response.sendRedirect("login.jsp");
                return;
            }
            int userId = loggedInUser.getUserId();
            int courseId = Integer.parseInt(request.getParameter("courseId"));

            dao dao = new dao();
            dao.addToCart(userId, courseId);

            response.sendRedirect("ViewCart");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Handles adding courses to the cart";
    }
}
