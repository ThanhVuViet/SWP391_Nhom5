package control;

import DAO.dao;
import Entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name="login", urlPatterns={"/login"})
public class LoginControl extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet request
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
            out.println("<title>Servlet LoginControl</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginControl at " + request.getContextPath () + "</h1>");
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
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userC")) {
                    request.setAttribute("username", cookie.getValue());
                }
                if (cookie.getName().equals("passC")) {
                    request.setAttribute("password", cookie.getValue());
                }
            }
        }
        request.getRequestDispatcher("login.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String username = request.getParameter("email");
    String password = request.getParameter("password");
    

    dao dao = new dao();
    Users user = dao.loginUser(username); 

    if (user == null) {
        request.setAttribute("WrongAccount", "This username doesn't exist");
        request.setAttribute("username", username);
        request.setAttribute("password", password);
        request.getRequestDispatcher("login.jsp").forward(request, response);
        return;
    }

    long lockTime = dao.getLockTime(username);
    long currentTime = System.currentTimeMillis();

    // Kiểm tra nếu tài khoản vẫn bị khóa
    if (lockTime > currentTime) {
        request.setAttribute("AccountLocked", "This account is locked. Try again later.");
        request.setAttribute("username", username);
        request.setAttribute("password", password);
        request.getRequestDispatcher("login.jsp").forward(request, response);
        return;
    }

  
    Users loggedInUser = dao.login(username, password);
    if (loggedInUser == null) {
        dao.updateFailedAttempt(username);

        int failedAttempt = dao.getFailedAttempt(username);
        System.out.println("FailedAttempt: " + failedAttempt);
        if (failedAttempt >= 3) {
            request.setAttribute("loginFailedThreeTime", "You forgot the password?");
            request.setAttribute("username", username);
            request.setAttribute("password", password);
        }
        if (failedAttempt >= 5) {
            lockTime = currentTime + (24 * 60 * 60 * 1000); // 24 giờ
            dao.updateLockTime(username, lockTime);
        }
        request.setAttribute("failedAttempt", failedAttempt);
        request.setAttribute("loginFailed", "Invalid username or password.");
        request.setAttribute("username", username);
        request.setAttribute("password", password);
        request.getRequestDispatcher("login.jsp").forward(request, response);
    } else {
        dao.resetFailedAttempt(username);  // Reset số lần thử
        dao.updateLockTime(username, 0);   // Reset thời gian khóa

        HttpSession session = request.getSession();
        session.setAttribute("acc", loggedInUser);  // Sử dụng đối tượng người dùng đã đăng nhập
        session.setMaxInactiveInterval(60 * 60 * 24);

        Cookie u = new Cookie("userC", username);
        Cookie p = new Cookie("passC", password);
        u.setMaxAge(60 * 60 * 24);
        p.setMaxAge(60 * 60 * 24);
        response.addCookie(p);
        response.addCookie(u);
        if (loggedInUser.getRoleId() == 1) {
            response.sendRedirect("adminControl");
        } else if (loggedInUser.getRoleId() == 2) {
            response.sendRedirect("home");
        } else if (loggedInUser.getRoleId() == 3) {
            response.sendRedirect("expertHome");
        }
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
