package control;

import DAO.dao;
import Entity.Expert;
import Entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@WebServlet(name = "searchByAjax", urlPatterns = {"/searchByAjax"})
public class searchByAjax extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nameFilter = request.getParameter("filterName");
        String specialtyFilter = request.getParameter("filterSpecialty");

        dao dao = new dao();
        List<Expert> expertList;
        if (nameFilter != null && !nameFilter.isEmpty() && specialtyFilter != null && !specialtyFilter.isEmpty()) {
            expertList = dao.getExpertByCateName(nameFilter, specialtyFilter);
        } else if (nameFilter != null && !nameFilter.isEmpty()) {
            expertList = dao.getExpertByName(nameFilter);
        } else if (specialtyFilter != null && !specialtyFilter.isEmpty()) {
            expertList = dao.getExpertByCate(specialtyFilter);
        } else {
            expertList = dao.getExpert();
        }

        Map<Integer, List<String>> expertCategories = dao.categoriesExpert();

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
    for (Expert expert : expertList) {
        Users user = expert.getUser();
        out.println("<tr>");
        out.println("<td>" + expert.getExpertId() + "</td>");
        out.println("<td>" + user.getUsername() + "</td>");
        out.println("<td>" + user.getEmail() + "</td>");
        out.println("<td>");
        List<String> categories = expertCategories.get(expert.getExpertId());
        if (categories != null) {
            if (categories.size() == 1) {
                out.print(categories.get(0));
            } else {
                for (int i = 0; i < categories.size(); i++) {
                    out.print(categories.get(i));
                    if (i < categories.size() - 1) {
                        out.print(", ");
                    }
                }
            }
        }
        out.println("</td>");
        out.println("<td>");
        out.println("<form action=\"editExpertServlet\" method=\"post\" style=\"display:inline;\">");
        out.println("<input type=\"hidden\" name=\"expertId\" value=\"" + expert.getExpertId() + "\">");
        out.println("<button type=\"submit\" class=\"btn btn-primary btn-sm\">Edit</button>");
        out.println("</form>");
        out.println("<form action=\"deleteExpertServlet\" method=\"POST\" style=\"display:inline;\">");
        out.println("<input type=\"hidden\" name=\"expertId\" value=\"" + expert.getExpertId() + "\">");
        out.println("<button type=\"submit\" class=\"btn btn-danger btn-sm\">Delete</button>");
        out.println("</form>");
        out.println("</td>");
        out.println("</tr>");
    }
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
        return "Short description";
    }
}
