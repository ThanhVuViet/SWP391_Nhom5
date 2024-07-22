package control;

import DAO.dao;
import Entity.Article;
import Entity.Course;
import Entity.Exam;
import Entity.Lecture;
import Entity.Section;
import Entity.Video;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name="ContentUpload", urlPatterns={"/ContentUpload"})
public class ContentUpload extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ContentUpload</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ContentUpload at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        dao daoInstance = new dao();

        // Fetch course details
        Course course = daoInstance.getCourseByCourseId(courseId);

        // Fetch sections
        List<Section> sections = daoInstance.getSectionsByCourseId(courseId);

        // Fetch lectures and exams for each section
        for (Section section : sections) {
            List<Lecture> lectures = daoInstance.getLecturesBySectionId(section.getSectionId());
            section.setLectures(lectures); // Assuming Section has a setLectures method
            for (Lecture lecture : lectures) {
                List<Exam> exams = daoInstance.getExamsByLectureId(lecture.getLectureId());
                  List<Video> videos = daoInstance.getVideosByLectureId(lecture.getLectureId());
                   List<Article> articles = daoInstance.getArticlesByLectureId(lecture.getLectureId());
                lecture.setExams(exams); // Assuming Lecture has a setExams method
                lecture.setVideos(videos);
                lecture.setArticles(articles);
                
            }
        }

        // Tính toán examId tiếp theo
        int examId = daoInstance.getLastExamId() + 1;

        // Set attributes for JSP
        request.setAttribute("course", course);
        request.setAttribute("sections", sections);
        request.setAttribute("examId", examId);

        // Forward to the content upload JSP
        request.getRequestDispatcher("uploadCourse.jsp").forward(request, response);
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
