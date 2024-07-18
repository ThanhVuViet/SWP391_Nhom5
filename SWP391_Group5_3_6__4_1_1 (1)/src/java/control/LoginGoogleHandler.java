package control;

import DAO.dao;
import Entity.Users;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

/**
 *
 * @author Admin
 */
@WebServlet(name="LoginGoogleHandler", urlPatterns={"/LoginGoogle"})
public class LoginGoogleHandler extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String code = request.getParameter("code");
        String accessToken = getToken(code);
        Users user = getUserInfo(accessToken);
        dao dao = new dao();
        Users checkAccount = dao.existEmail(user.getEmail());
        if (checkAccount != null) {
            response.sendRedirect("home.jsp");
        } else {
            // User does not exist, create a new user
            response.sendRedirect("register.jsp");
        }

        
    }

    public static String getToken(String code) throws ClientProtocolException, IOException {
     
        String response = Request.Post(ConstantsGoogle.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form()
                        .add("client_id", ConstantsGoogle.GOOGLE_CLIENT_ID)
                        .add("client_secret", ConstantsGoogle.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", ConstantsGoogle.GOOGLE_REDIRECT_URI)
                        .add("code", code)
                        .add("grant_type", ConstantsGoogle.GOOGLE_GRANT_TYPE)
                        .build())
                .execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").getAsString();
        return accessToken;
    }

    public static Users getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = ConstantsGoogle.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();

        Users googlePojo = new Gson().fromJson(response, Users.class);

        return googlePojo;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
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
        processRequest(request, response);
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