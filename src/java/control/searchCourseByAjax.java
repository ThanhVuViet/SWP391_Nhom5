/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import DAO.dao;
import Entity.Course;
import Entity.Expert;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
@WebServlet(name = "searchCourseByAjax", urlPatterns = {"/searchCourseByAjax"})
public class searchCourseByAjax extends HttpServlet {

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
    String nameFilter = request.getParameter("filterName");
    String specialtyFilter = request.getParameter("filterSpecialty");
    dao dao = new dao();
    List<Course> CourseList;

    if (nameFilter != null && !nameFilter.isEmpty() && specialtyFilter != null && !specialtyFilter.isEmpty()) {
        CourseList = dao.getCoursesByCateAndName(nameFilter, specialtyFilter);
    } else if (nameFilter != null && !nameFilter.isEmpty()) {
        CourseList = dao.getCoursesByName(nameFilter);
    } else if (specialtyFilter != null && !specialtyFilter.isEmpty()) {
        CourseList = dao.getCoursesByCate(specialtyFilter);
    } else {
        CourseList = dao.getCourse();
    }

    // If CourseList is not filtered, fetch additional details
    Map<Integer, List<String>> courseExpert = dao.expertCourse();
    Map<Integer, List<String>> courseCate = dao.courseCategory();

    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
    out.println("<table>");
    for (Course course : CourseList) {
        // Get experts and categories for the course
        List<String> experts = courseExpert.get(course.getCourseId());
        List<String> categories = courseCate.get(course.getCourseId());
        String expertsStr = experts != null ? String.join(", ", experts) : "";
        String categoriesStr = categories != null ? String.join(", ", categories) : "";

        out.print("<tr>\n"
                + "    <td>" + course.getCourseId() + "</td>\n"
                + "    <td>" + course.getTitle() + "</td>\n"
                + "    <td>" + expertsStr + "</td>\n"
                + "    <td>" + categoriesStr + "</td>\n"
                + "    <td>\n"
                + "        <a href=\"editCourse?courseId=" + course.getCourseId() + "\" class=\"btn btn-primary btn-sm\">Edit</a>\n"
                        + "        <a href=\"delete?courseId=" + course.getCourseId() + "\" class=\"btn btn-danger btn-sm\">Delete</a>\n"
                        
                
                + "    </td>\n"
                + "</tr>");
    }
    out.println("</table>");
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
        processRequest(request, response);
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
