import DAO.dao;
import Entity.ExamChoice;
import Entity.ExamQuestion;
import Entity.ExamResult;
import Entity.UserProgress;
import Entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="SubmitQuiz", urlPatterns={"/SubmitQuiz"})
public class SubmitQuiz extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SubmitQuiz</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SubmitQuiz at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        Users loggedInUser = (Users) request.getSession().getAttribute("acc");
        if (loggedInUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        int userId = loggedInUser.getUserId();
        
        int courseId;
        int examId;
        try {
            courseId = Integer.parseInt(request.getParameter("courseId"));
            examId = Integer.parseInt(request.getParameter("examId"));
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid courseId or examId.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        dao dao = new dao();
        List<ExamQuestion> questions = dao.getExamQuestionsByExamId(examId);

        if (questions == null || questions.isEmpty()) {
            request.setAttribute("error", "No questions found for the given exam.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        int totalQuestions = questions.size();
        int correctAnswers = 0;
          dao.addExamSubmission(userId, examId, 0); // Create an exam submission
        int submissionId = dao.getLastSubmissionId(userId, examId); // Get the ID of the last submission

        for (int i = 0; i < totalQuestions; i++) {
            ExamQuestion question = questions.get(i);
            int correctChoiceId = dao.getCorrectChoiceId(question.getQuestionId());

            String userAnswerStr = request.getParameter("answer_" + i);
            if (userAnswerStr != null) {
                int userAnswerId = Integer.parseInt(userAnswerStr);
                boolean isCorrect = userAnswerId == correctChoiceId;
                if (isCorrect) {
                    correctAnswers++;
                }

                // Save the result for each question
                ExamResult result = new ExamResult();
                result.setSubmissionId(submissionId);
                result.setQuestionId(question.getQuestionId());
                result.setChoiceId(userAnswerId);
                result.setCorrect(isCorrect);
                dao.addExamResult(result);
            }
        }

        double score = (double) correctAnswers / totalQuestions * 100;
        dao.updateExamSubmissionScore(submissionId, score);

        int examCount = dao.getExamCountByCourseId(courseId);
        double progress = 0;

        UserProgress userProgress = dao.getUserProgress(userId, courseId);
        if (userProgress != null) {
            String completedExamIds = userProgress.getCompletedExamIds();
            if (completedExamIds == null) {
                completedExamIds = "";
            }
            if (!completedExamIds.contains(String.valueOf(examId))) {
                completedExamIds += "," + examId;
                userProgress.setCompletedExamIds(completedExamIds);
                progress = (double) completedExamIds.split(",").length / examCount * 100;
                userProgress.setProgress(progress);
                dao.updateUserProgress(userProgress);
            } else {
                progress = userProgress.getProgress();
            }
        } else {
            progress = 100.0 / examCount;
            String completedExamIds = String.valueOf(examId);
            userProgress = new UserProgress(userId, courseId, correctAnswers, progress);
            userProgress.setCompletedExamIds(completedExamIds);
            dao.addUserProgress(userProgress);
        }

        // Save details to session
        request.setAttribute("score", score);
        request.setAttribute("correctAnswers", correctAnswers);
        request.setAttribute("totalQuestions", totalQuestions);
        request.setAttribute("progress", progress);

        request.setAttribute("courseId", courseId);
        request.setAttribute("examId", examId);
        request.getRequestDispatcher("QuizResult.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
