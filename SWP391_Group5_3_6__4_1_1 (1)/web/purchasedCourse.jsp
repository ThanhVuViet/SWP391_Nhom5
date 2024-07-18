<%@ page import="java.util.List" %>
<%@ page import="your.package.Course" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <!-- (existing head content) -->
</head>
<body>
    <!-- (existing body content) -->

    <!-- Purchased Courses Start -->
    <div class="container-xxl py-5" id="purchased-courses-section">
        <div class="container">
            <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                <h6 class="section-title bg-white text-center text-primary px-3">Purchased Courses</h6>
                <h1 class="mb-5">Your Courses</h1>
            </div>
            <div class="row g-4 justify-content-center" id="purchased-courses-list">
                <%
                    List<Course> purchasedCourses = (List<Course>) request.getAttribute("purchasedCourses");
                    for (Course course : purchasedCourses) {
                %>
                <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.1s">
                    <div class="course-item bg-light">
                        <div class="position-relative overflow-hidden">
                            <img class="img-fluid" src="path/to/your/image/folder/<%= course.getCourseId() %>.jpg" alt="">
                            <div class="w-100 d-flex justify-content-center position-absolute bottom-0 start-0 mb-4">
                                <a href="course-details.jsp?courseId=<%= course.getCourseId() %>" class="flex-shrink-0 btn btn-sm btn-primary px-3 border-end" style="border-radius: 30px 0 0 30px;">Read More</a>
                                <a href="join-course.jsp?courseId=<%= course.getCourseId() %>" class="flex-shrink-0 btn btn-sm btn-primary px-3" style="border-radius: 0 30px 30px 0;">Join Now</a>
                            </div>
                        </div>
                        <div class="text-center p-4 pb-0">
                            <h3 class="mb-0">$<%= course.getPrice() %></h3>
                            <h5 class="mb-4"><%= course.getTitle() %></h5>
                            <p class="mb-4"><%= course.getDescription() %></p>
                        </div>
                        <div class="d-flex border-top">
                            <small class="flex-fill text-center border-end py-2"><i class="fa fa-user-tie text-primary me-2"></i>Instructor Name</small>
                            <small class="flex-fill text-center border-end py-2"><i class="fa fa-clock text-primary me-2"></i>Duration</small>
                            <small class="flex-fill text-center py-2"><i class="fa fa-user text-primary me-2"></i>Number of Students</small>
                        </div>
                    </div>
                </div>
                <%
                    }
                %>
            </div>
        </div>
    </div>
    <!-- Purchased Courses End -->

    <!-- (existing footer content) -->

    <!-- JavaScript Libraries -->
    <!-- (existing scripts) -->
</body>
</html>
