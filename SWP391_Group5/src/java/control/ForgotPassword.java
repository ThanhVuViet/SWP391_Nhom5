/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import DAO.dao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.CommonUtils;

import java.io.IOException;
import utils.EmailUtils;

@WebServlet(name = "forgotPassword", urlPatterns = {"/forgotPassword"})

public class ForgotPassword extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action") == null
                ? "default"
                : request.getParameter("action");
        String url = null;
        switch (action) {
            case "mail":
                url = sendMail(request, response);
                break;
            case "enterOTP":
                url = enterOTP(request, response);
                break;
            case "enterNewPassword":
                url = enterNewPassword(request, response);
                break;
            default:
                url = sendMail(request, response);
                break;
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    private String sendMail(HttpServletRequest request, HttpServletResponse response) {
        String url = null;
        //get email
        String email = request.getParameter("email");
        //check mail exist in DB
        boolean isMailExist = new dao().doesEmailExist(email);
        if (!isMailExist) {
            request.setAttribute("error", "Email does not exist !");
            url = "forgotPassword.jsp";
            return url;
        }
        //generate random number
        String random = CommonUtils.generateSixRandomNumber();
        //content mail
        String subject = "Reset password";
        String content = "Your reset password code is: " + random;
        //send mail
        boolean result = EmailUtils.sendMail(email, subject, content);
        //if send mail success => go to enter otp
        if (result) {
            request.getSession().setAttribute("OTP", random);
            request.getSession().setAttribute("MailReset", email);
            url = "enterOTP.jsp";
        } else {
            //if send mail failed => back to forgotPassword.jsp attach error message
            request.setAttribute("error", "Something wrong !! Can't send mail");
            url = "forgotPassword.jsp";
        }
        return url;
    }

    private String enterOTP(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String url = null;
        String otp = request.getParameter("otp");
        //get otp on session
        String otpSession = (String) session.getAttribute("OTP");
        //nhap dung OTP
        if (otp.equals(otpSession)) {
            //chuyen toi trang nhap password moi
            url = "enterNewPassword.jsp";
        }else {
            //khong dung OTP => set loi va bao sai
            request.setAttribute("error", "Wrong OTP");
            url = "enterOTP.jsp";
        }
        return url;
    }

    private String enterNewPassword(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String url = null;
        
        //get MailReset
        String mailReset = (String) session.getAttribute("MailReset");
        //get password
        String password = request.getParameter("newPassword");
        //get confirm password
        String confirmPassword = request.getParameter("confirmPassword");
        //check password equal confirm password
        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Password and confirm password not match !!");
            url = "enterNewPassword.jsp";
            return url;
        }
        //change password in DB
        new dao().updatePasswordByMail(mailReset, password);
        url = "login";
        return url;
    }

}
