<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="Entity.Course" %>
<%@ page import="Entity.Feedback" %>
<%@ page import="Entity.Category" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Expert Management - eLearning</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">
    <link href="img/favicon.ico" rel="icon">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&display=swap" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
    <link href="lib/animate/animate.min.css" rel="stylesheet">
    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <style>
        body {
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }

        .content {
            flex: 1;
        }
    </style>
</head>

<body>
    <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
        <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
            <span class="sr-only">Loading...</span>
        </div>
    </div>

    <nav class="navbar navbar-expand-lg bg-white navbar-light shadow sticky-top p-0">
        <a href="index.html" class="navbar-brand d-flex align-items-center px-4 px-lg-5">
            <h2 class="m-0 text-primary"><i class="fa fa-book me-3"></i>Fdemy</h2>
        </a>
        <button type="button" class="navbar-toggler me-4" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <div class="navbar-nav ms-auto p-4 p-lg-0">
                <a href="home.jsp" class="nav-item nav-link">Home</a>
               
            </div>
            <%
                String username = (String) session.getAttribute("username");
                if (username != null) {
            %>
                <a href="#" class="btn btn-primary py-4 px-lg-5 d-none d-lg-block">Hello, <%= username %><i class="fa fa-arrow-right ms-3"></i></a>
            <%
                } else {
            %>
                <a href="login.jsp" class="btn btn-primary py-4 px-lg-5 d-none d-lg-block">Join Now<i class="fa fa-arrow-right ms-3"></i></a>
            <%
                }
            %>
        </div>
    </nav>

    <div class="container-fluid content">
        <div class="row flex-nowrap">
            <div class="col-auto col-md-3 col-xl-2 px-sm-2 px-0 bg-dark">
                <div class="d-flex flex-column align-items-center align-items-sm-start px-3 pt-2 text-white min-vh-100">
                    <a href="/" class="d-flex align-items-center pb-3 mb-md-0 me-md-auto text-white text-decoration-none">
                        <span class="fs-5 d-none d-sm-inline">Menu</span>
                    </a>
                    <ul class="nav nav-pills flex-column mb-sm-auto mb-0 align-items-center align-items-sm-start" id="menu">
                        <li class="nav-item">
                            <a href="expertHome" class="nav-link align-middle px-0">
                                <i class="fs-4 bi-house"></i> <span class="ms-1 d-none d-sm-inline">Home</span>
                            </a>
                        </li>
                        <li>
                            <a href="ExpertMangeCourse" class="nav-link px-0 align-middle">
                                <i class="fs-4 bi-book"></i> <span class="ms-1 d-none d-sm-inline">Manage Course</span>
                            </a>
                        </li>
                        <li>
                            <a href="ExpertManageFeedback" class="nav-link px-0 align-middle">
                                <i class="fs-4 bi-chat-dots"></i> <span class="ms-1 d-none d-sm-inline">View Feedback</span>
                            </a>
                        </li>
                        <li>
                            <a href="ExpertManageContent" class="nav-link px-0 align-middle">
                                <i class="fs-4 bi-pencil-square"></i> <span class="ms-1 d-none d-sm-inline">Design Content</span>
                            </a>
                        </li>
                    </ul>
                    <hr>
                    <div class="dropdown pb-4">
                        <a href="#" class="d-flex align-items-center text-white text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
                            <img src="https://github.com/mdo.png" alt="hugenerd" width="30" height="30" class="rounded-circle">
                            <span class="d-none d-sm-inline mx-1">Expert</span>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-dark text-small shadow">
                            <li><a class="dropdown-item" href="#">New project...</a></li>
                            <li><a class="dropdown-item" href="#">Settings</a></li>
                            <li><a class="dropdown-item" href="#">Profile</a></li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><a class="dropdown-item" href="logout.jsp">Sign out</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col py-3">
                
                <div id="createCourse" class="mt-5">
                    <h2>Create Course</h2>
                    <p>Create a new course here.</p>
                    <div class="card mb-3">
                        <div class="card-header">Course Information</div>
                        <div class="card-body">
                            <form action="ExpertCreateCourse" method="post">
                                <div class="mb-3">
                                    <label for="courseTitle" class="form-label">Course Title</label>
                                    <input type="text" class="form-control" id="courseTitle" name="courseTitle" required>
                                </div>
                                <div class="mb-3">
                                    <label for="courseCategory" class="form-label">Course Category</label>
                                    <select class="form-control" name="specialty" required>
                                    <option value="" disabled selected>Select a specialty</option>
                                    <% 
                                        List<Category> categories = (List<Category>) request.getAttribute("categoryList");
                                        for (Category category : categories) {
                                    %>
                                    <option value="<%= category.getCateName() %>"><%= category.getCateName() %></option>
                                    <% } %>
                                </select>
                                </div>
                                <div class="mb-3">
                                    <label for="courseDescription" class="form-label">Course Description</label>
                                    <textarea class="form-control" id="courseDescription" name="courseDescription" rows="3" required></textarea>
                                </div>
                                <div class="mb-3">
                                    <label for="coursePrice" class="form-label">Course Price</label>
                                    <input type="number" class="form-control" id="coursePrice" name="coursePrice" step="0.01" required>
                                </div>
                                <button type="submit" class="btn btn-primary">Create Course</button>
                            </form>
                        </div>
                    </div>
                </div>

                <!-- Các ph?n khác c?a trang -->
                <div id="manageCourse" class="mt-5">
                    <h2>Manage Course</h2>
                    <p>Manage all courses here.</p>
                    <div class="card mb-3">
                        <div class="card-header">Course List</div>
                        <div class="card-body">
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Course Name</th>
                                        <th>Participants</th>
                                        <th>Average Rating</th>
                                        <th>Status</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        List<Course> courseList = (List<Course>) request.getAttribute("courseList");
                                        Map<Integer, Integer> participantsMap = (Map<Integer, Integer>) request.getAttribute("participantsMap");
                                        Map<Integer, Integer> ratingsMap = (Map<Integer, Integer>) request.getAttribute("ratingsMap");
                                        int count = 0;
                                        for (Course course : courseList) {
                                            if (count == 2) {
                                                break;
                                            }
                                            int participants = participantsMap.getOrDefault(course.getCourseId(), 0);
                                            int averageRating = ratingsMap.getOrDefault(course.getCourseId(), 0);
                                    %>
                                    <tr>
                                        <td><%= course.getCourseId() %></td>
                                        <td><%= course.getTitle() %></td>
                                        <td><%= participants %></td>
                                        <td><%= String.format("%.2f", (double) averageRating) %></td>
                                        <td><%= course.getStatus() %></td>
                                        <td><a href="editCourse?courseId=<%= course.getCourseId() %>" class="btn btn-primary btn-sm">Edit</a></td>
                                    </tr>
                                    <%
                                            count++;
                                        }
                                    %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <a href="ExpertMangeCourse" class="btn btn-secondary">See More</a>
                </div>

                <div id="viewFeedback" class="mt-5">
                    <h2>View Feedback</h2>
                    <p>View feedback from students here.</p>
                    <div class="card mb-3">
                        <div class="card-header">Feedback List</div>
                        <div class="card-body">
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Course Name</th>
                                        <th>Feedback</th>
                                        <th>Date</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        int count1 = 0;
                                        Map<Integer, List<Feedback>> courseFeedbackMap = (Map<Integer, List<Feedback>>) request.getAttribute("courseFeedback");
                                        for (Course course : courseList) {
                                            List<Feedback> feedbackList = courseFeedbackMap.get(course.getCourseId());
                                            if (feedbackList != null) {
                                                for (Feedback feedback : feedbackList) {
                                                    if (count1 == 2) {
                                                        break;
                                                    }
                                    %>
                                    <tr>
                                        <td><%= feedback.getFeedbackId() %></td>
                                        <td><%= course.getTitle() %></td>
                                        <td><%= feedback.getComment() %></td>
                                        <td><%= feedback.getCreatedAt() %></td>
                                    </tr>
                                    <%
                                                    count1++;
                                                }
                                            }
                                        }
                                    %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <a href="ExpertManageFeedback" class="btn btn-secondary">See More</a>
                </div>

                <div id="manageCourse" class="mt-5">
                    <h2>View Revenue</h2>
                    <p>View total revenue for each course here.</p>
                    <div class="card mb-3">
                        <div class="card-header">Revenue List</div>
                        <div class="card-body">
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Course Name</th>
                                        <th>Total Revenue</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        int count2 = 0;
                                        Map<Integer, Double> revenueMap = (Map<Integer, Double>) request.getAttribute("revenueMap");
                                        for (Course course : courseList) {
                                            if (count2 == 2) {
                                                break;
                                            }
                                            double totalRevenue = revenueMap.getOrDefault(course.getCourseId(), 0.0);
                                    %>
                                    <tr>
                                        <td><%= course.getCourseId() %></td>
                                        <td><%= course.getTitle() %></td>
                                        <td><%= String.format("%.2f", totalRevenue) %></td>
                                    </tr>
                                    <%
                                            count2++;
                                        }
                                    %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <a href="ExpertManageRevenue" class="btn btn-secondary">See More</a>
                </div>

                <div id="designCourseContent" class="mt-5">
                    <h2>Upload Course Content</h2>
                    <p>Upload course content here.</p>
                    <div class="card mb-3">
                        <div class="card-header">Course List</div>
                        <div class="card-body">
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Course Name</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        int count3 = 0;
                                        for (Course course : courseList) {
                                            if (count3 == 2) {
                                                break;
                                            }
                                    %>
                                    <tr>
                                        <td><%= course.getCourseId() %></td>
                                        <td><%= course.getTitle() %></td>
                                        <td>
                                            <a href="ContentUpload?courseId=<%= course.getCourseId() %>" class="btn btn-primary btn-sm">Upload</a>
                                        </td>
                                    </tr>
                                    <%
                                            count3++;
                                        }
                                    %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <a href="viewAllUploadContentCourses" class="btn btn-secondary">See More</a>
                </div>
            </div>
        </div>
    </div>

    <div class="container-fluid bg-dark text-light footer pt-5 mt-5 wow fadeIn" data-wow-delay="0.1s">
        <div class="container py-5">
            <div class="row g-5">
                <div class="col-lg-3 col-md-6">
                    <h4 class="text-white mb-3">Quick Link</h4>
                    <a class="btn btn-link" href="">About Us</a>
                    <a class="btn btn-link" href="">Contact Us</a>
                    <a class="btn btn-link" href="">Privacy Policy</a>
                    <a class="btn btn-link" href="">Terms & Condition</a>
                    <a class="btn btn-link" href="">FAQs & Help</a>
                </div>
                <div class="col-lg-3 col-md-6">
                    <h4 class="text-white mb-3">Contact</h4>
                    <p class="mb-2"><i class="fa fa-map-marker-alt me-3"></i>123 Street, New York, USA</p>
                    <p class="mb-2"><i class="fa fa-phone-alt me-3"></i>+012 345 67890</p>
                    <p class="mb-2"><i class="fa fa-envelope me-3"></i>info@example.com</p>
                    <div class="d-flex pt-2">
                        <a class="btn btn-outline-light btn-social" href=""><i class="fab fa-twitter"></i></a>
                        <a class="btn btn-outline-light btn-social" href=""><i class="fab fa-facebook-f"></i></a>
                        <a class="btn btn-outline-light btn-social" href=""><i class="fab fa-youtube"></i></a>
                        <a class="btn btn-outline-light btn-social" href=""><i class="fab fa-linkedin-in"></i></a>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6">
                    <h4 class="text-white mb-3">Gallery</h4>
                    <div class="row g-2 pt-2">
                        <div class="col-4">
                            <img class="img-fluid bg-light p-1" src="img/course-1.jpg" alt="">
                        </div>
                        <div class="col-4">
                            <img class="img-fluid bg-light p-1" src="img/course-2.jpg" alt="">
                        </div>
                        <div class="col-4">
                            <img class="img-fluid bg-light p-1" src="img/course-3.jpg" alt="">
                        </div>
                        <div class="col-4">
                            <img class="img-fluid bg-light p-1" src="img/course-2.jpg" alt="">
                        </div>
                        <div class="col-4">
                            <img class="img-fluid bg-light p-1" src="img/course-3.jpg" alt="">
                        </div>
                        <div class="col-4">
                            <img class="img-fluid bg-light p-1" src="img/course-1.jpg" alt="">
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6">
                    <h4 class="text-white mb-3">Newsletter</h4>
                    <p>Dolor amet sit justo amet elitr clita ipsum elitr est.</p>
                    <div class="position-relative mx-auto" style="max-width: 400px;">
                        <input class="form-control border-0 w-100 py-3 ps-4 pe-5" type="text" placeholder="Your email">
                        <button type="button" class="btn btn-primary py-2 position-absolute top-0 end-0 mt-2 me-2">SignUp</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="copyright">
                <div class="row">
                    <div class="col-md-6 text-center text-md-start mb-3 mb-md-0">
                        &copy; <a class="border-bottom" href="#">Your Site Name</a>, All Right Reserved.
                        Designed By <a class="border-bottom" href="https://htmlcodex.com">HTML Codex</a><br><br>
                        Distributed By <a class="border-bottom" href="https://themewagon.com">ThemeWagon</a>
                    </div>
                    <div class="col-md-6 text-center text-md-end">
                        <div class="footer-menu">
                            <a href="">Home</a>
                            <a href="">Cookies</a>
                            <a href="">Help</a>
                            <a href="">FQAs</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="lib/wow/wow.min.js"></script>
    <script src="lib/easing/easing.min.js"></script>
    <script src="lib/waypoints/waypoints.min.js"></script>
    <script src="lib/owlcarousel/owl.carousel.min.js"></script>
    <script src="js/main.js"></script>
</body>

</html>
