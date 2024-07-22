
import DAO.OrderDao;
import Entity.Order;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "OrderFilter", urlPatterns = {"/OrderFilter"})
public class OrderFilter extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet OrderFilter</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderFilter at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OrderDao orderDao = new OrderDao();
        String startDay = request.getParameter("startDay");
        String endDay = request.getParameter("endDay");

        Date startDate = null;
        Date endDate = null;

        try {
            startDate = convertStringToDate(startDay);
            endDate = convertStringToDate(endDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (startDate != null && endDate != null) {
            List<Order> orderList = orderDao.getOrder(startDate, endDate);
            request.setAttribute("orderList", orderList);
            request.getRequestDispatcher("static.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            OrderDao orderDao = new OrderDao();
            String startDay = request.getParameter("startDate");
            String endDay = request.getParameter("endDate");

            java.sql.Date startDate = convertStringToDate(startDay);
            java.sql.Date endDate = convertStringToDate(endDay);
            if (startDate != null && endDate != null) {
                List<Order> orderList = orderDao.getOrder(startDate, endDate);
                request.setAttribute("orderList", orderList);
                request.setAttribute("startDate", startDate);
                request.setAttribute("endDate", endDate);
                request.getRequestDispatcher("Static.jsp").forward(request, response);

            }
        } catch (ParseException ex) {
            Logger.getLogger(OrderFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    public static java.sql.Date convertStringToDate(String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        java.util.Date utilDate = sdf.parse(dateString);
        return new java.sql.Date(utilDate.getTime());
    }
}
