package control;

import DAO.dao;
import Entity.Question;
import Entity.Questions;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@MultipartConfig
@WebServlet(name = "uploadTxt", urlPatterns = {"/uploadTxt"})
public class uploadTxt extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet uploadFile</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet uploadFile at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("upload1.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            dao daoInstance = new dao();
            
            int sectionId = Integer.parseInt(request.getParameter("sectionId"));
            int lectureId = Integer.parseInt(request.getParameter("lectureId"));
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            Part part = request.getPart("file");
            String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
             int courseId = Integer.parseInt(request.getParameter("courseId"));
            if (filename.endsWith(".txt")) {
                String realPath = request.getServletContext().getRealPath("/uploads");
                if (!Files.exists(Paths.get(realPath))) {
                    Files.createDirectory(Paths.get(realPath));
                }
                String filePath = realPath + "/" + filename;
                part.write(filePath);

               
                Questions questions = new Questions(filePath);
                List<Question> questionList = questions.getQuestions();
                int examId = daoInstance.getLastExamId() +1;
               
                saveQuestionsToDatabase(examId, lectureId, title, description, questionList);
                
               
                request.setAttribute("questions", questionList);
                 response.sendRedirect("ContentUpload?courseId=" + courseId);
            } else {
                request.setAttribute("message", "Invalid file type. Please upload a .txt file.");
                RequestDispatcher rd = request.getRequestDispatcher("upload1.jsp");
                rd.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveQuestionsToDatabase(int examId,int lectureId, String title, String description, List<Question> questionList) {
        dao daoInstance = new dao();
         daoInstance.saveExam(lectureId, title, description);
        for (Question question : questionList) {
            daoInstance.saveQuestion(examId, question.getQuestion());
            int questionId = daoInstance.getGeneratedQuestionId(); // Implement this method to retrieve the last inserted question ID

            // Save the choices for the question
            String[] alternatives = question.getAlternatives();
            for (int i = 0; i < alternatives.length; i++) {
                daoInstance.saveChoices(questionId, alternatives[i], i == question.getAnswer() - 1);
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
