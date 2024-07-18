package control;

import DAO.dao;
import Entity.ExamResult;
import Entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="ViewResult", urlPatterns={"/ViewResult"})
public class ViewResult extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ViewResult</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewResult at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int userId = ((Users) request.getSession().getAttribute("acc")).getUserId();
        int examId;
        try {
            examId = Integer.parseInt(request.getParameter("examId"));
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid examId.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        dao dao = new dao();
        int submissionId = dao.getLastSubmissionId(userId, examId);
        List<ExamResult> examResults = dao.getExamResultsBySubmissionId(submissionId);

        request.setAttribute("examResults", examResults);
        request.getRequestDispatcher("ViewResult.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
