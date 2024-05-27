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
        int failedAttempt = 0;
        long lockTime = 0;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("failedAttempt_" + username)) {
                    failedAttempt = Integer.parseInt(cookie.getValue());
                }
                if (cookie.getName().equals("lockTime_" + username)) {
                    lockTime = Long.parseLong(cookie.getValue());
                }
            }
        }

        long currentTime = System.currentTimeMillis();
        if (lockTime > currentTime) {
            request.setAttribute("AccountLocked", "This account is locked. Try again later.");
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        dao dao = new dao();
        Users a = dao.login(username, password);
        if (a == null) {
            failedAttempt++;
            if (failedAttempt >= 3 ) {
                request.setAttribute("loginFailedThreeTime", "You forgot the password?");
                request.setAttribute("username", username);
                request.setAttribute("password", password);
               
            }
            if (failedAttempt == 5) {
                lockTime = currentTime + (24 * 60 * 60 * 1000); 
                
            }
            Cookie failedAttemptCookie = new Cookie("failedAttempt_" + username, String.valueOf(failedAttempt));
            Cookie lockTimeCookie = new Cookie("lockTime_" + username, String.valueOf(lockTime));
            failedAttemptCookie.setMaxAge(24 * 60 * 60);
            lockTimeCookie.setMaxAge(24 * 60 * 60);
            response.addCookie(failedAttemptCookie);
            response.addCookie(lockTimeCookie);
            request.setAttribute("failedAttempt", failedAttempt);
            request.setAttribute("loginFailed", "Invalid username or password.");
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            
            Cookie failedAttemptCookie = new Cookie("failedAttempt_" + username, "0");
            Cookie lockTimeCookie = new Cookie("lockTime_" + username, "0");
            failedAttemptCookie.setMaxAge(0); // Xóa cookie
            lockTimeCookie.setMaxAge(0); // Xóa cookie
            response.addCookie(failedAttemptCookie);
            response.addCookie(lockTimeCookie);

            HttpSession session = request.getSession();
            session.setAttribute("acc", a);
            session.setMaxInactiveInterval(60 * 60 * 24);

            Cookie u = new Cookie("userC", username);
            Cookie p = new Cookie("passC", password);
            u.setMaxAge(60 * 60 * 24);
            p.setMaxAge(60 * 60 * 24);
            response.addCookie(p);
            response.addCookie(u);

            if (a.getRoleId() == 1) {
                response.sendRedirect("admin");
            } else if (a.getRoleId() == 2) {
                response.sendRedirect("home.jsp");
            } else if (a.getRoleId() == 3) {
                response.sendRedirect("expert");
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
