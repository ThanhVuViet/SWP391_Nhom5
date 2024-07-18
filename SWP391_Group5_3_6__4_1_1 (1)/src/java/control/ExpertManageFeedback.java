package control;

import DAO.dao;
import Entity.Course;
import Entity.Feedback;
import Entity.Users;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ExpertManageFeedback", urlPatterns = {"/ExpertManageFeedback"})
public class ExpertManageFeedback extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Users loggedInUser = (Users) request.getSession().getAttribute("acc");
        if (loggedInUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        dao courseDAO = new dao();
        List<Course> courseList = courseDAO.getCourseById(loggedInUser.getUserId());
        Map<Integer, List<Feedback>> courseFeedbackMap = courseDAO.getAllFeedbackMap(loggedInUser.getUserId());

        request.setAttribute("courseList", courseList);
        request.setAttribute("courseFeedback", courseFeedbackMap);

        request.getRequestDispatcher("ExpertManageFeedback.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet to view all feedback for courses";
    }
}
