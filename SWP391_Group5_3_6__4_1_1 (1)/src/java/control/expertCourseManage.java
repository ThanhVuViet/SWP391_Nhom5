package control;

import DAO.dao;
import Entity.Category;
import Entity.Course;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;

@WebServlet(name = "expertEditCourse", urlPatterns = {"/expertEditCourse"})
public class expertCourseManage extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CourseManage</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CourseManage at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int courseID = Integer.parseInt(request.getParameter("courseId"));
        dao dao = new dao();
        Course course = dao.getCourseByID(courseID);
        Map<Integer, List<String>> courseExpert = dao.expertCourse();
        Map<Integer, List<String>> courseCate = dao.courseCategory();
        List<Category> cateList = dao.getCategories();
        request.setAttribute("courseExpert", courseExpert);
        request.setAttribute("courseCate", courseCate);
        request.setAttribute("courseUpdate", course);
        request.setAttribute("cateList", cateList);

        HttpSession session = request.getSession();
        String message = (String) session.getAttribute("message");
        if (message != null) {
            request.setAttribute("message", message);
            session.removeAttribute("message");
        }

        request.getRequestDispatcher("CourseManage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int courseID = Integer.parseInt(request.getParameter("courseId"));
        dao dao = new dao();
        Course course = dao.getCourseByID(courseID);
        Map<Integer, List<String>> courseExpert = dao.expertCourse();
        Map<Integer, List<String>> courseCate = dao.courseCategory();
        request.setAttribute("courseExpert", courseExpert);
        request.setAttribute("courseCate", courseCate);
        request.setAttribute("courseUpdate", course);

        List<Category> cateList = dao.getCategories();
        request.setAttribute("cateList", cateList);

        HttpSession session = request.getSession();
        String message = (String) session.getAttribute("message");
        if (message != null) {
            request.setAttribute("message", message);
            session.removeAttribute("message");
        }

        request.getRequestDispatcher("CourseManage.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
