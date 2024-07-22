<!DOCTYPE html>
<html lang="en">
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="Entity.Expert" %>
<%@ page import="Entity.Users" %>
<%@ page import="Entity.Course" %>
<%@ page import="Entity.Category" %>
<%@ page session="true" %>
<head>
    <meta charset="utf-8">
    <title>Admin Management - eLearning</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">
    <!-- Favicon -->
    <link href="img/favicon.ico" rel="icon">
    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&display=swap" rel="stylesheet">
    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
    <!-- Libraries Stylesheet -->
    <link href="lib/animate/animate.min.css" rel="stylesheet">
    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <!-- Customized Bootstrap Stylesheet -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Template Stylesheet -->
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
    <!-- Spinner Start -->
    <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
        <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
            <span class="sr-only">Loading...</span>
        </div>
    </div>
    <!-- Spinner End -->

    <!-- Sidebar Start -->
    <div class="container-fluid content">
        <div class="row flex-nowrap">
            <div class="col-auto col-md-3 col-xl-2 px-sm-2 px-0 bg-dark">
                <div class="d-flex flex-column align-items-center align-items-sm-start px-3 pt-2 text-white min-vh-100">
                    <a href="adminDashboard" class="d-flex align-items-center pb-3 mb-md-0 me-md-auto text-white text-decoration-none">
                        <span class="fs-5 d-none d-sm-inline">Menu</span>
                    </a>
                    <ul class="nav nav-pills flex-column mb-sm-auto mb-0 align-items-center align-items-sm-start" id="menu">
                        <li class="nav-item">
                            <a href="adminControl" class="nav-link align-middle px-0">
                                <i class="fs-4 bi-house"></i> <span class="ms-1 d-none d-sm-inline">Home</span>
                            </a>
                        </li>
                        <li>
                            <a href="CourseList" class="nav-link px-0 align-middle">
                                <i class="fs-4 bi-book"></i> <span class="ms-1 d-none d-sm-inline">Manage Course</span>
                            </a>
                        </li>
                        <li>
                            <a href="CustomerList" class="nav-link px-0 align-middle">
                                <i class="fs-4 bi-people"></i> <span class="ms-1 d-none d-sm-inline">Customer Manage</span>
                            </a>
                        </li>
                        <li>
                            <a href="ExpertList" class="nav-link px-0 align-middle">
                                <i class="fs-4 bi-person-badge"></i> <span class="ms-1 d-none d-sm-inline">Expert Account Manage</span>
                            </a>
                        </li>
                        <li>
                            <a href="DoanhThuThang.jsp" class="nav-link px-0 align-middle">
                                <i class="fs-4 bi-calendar"></i> <span class="ms-1 d-none d-sm-inline">Monthly Revenue</span>
                            </a>
                        </li>
                        <li>
                            <a href="Top5User" class="nav-link px-0 align-middle">
                                <i class="fs-4 bi-people"></i> <span class="ms-1 d-none d-sm-inline">Top 5 Customers</span>
                            </a>
                        </li>
                    </ul>
                    <hr>
                    <%
                        Users loggedInUser = (Users) session.getAttribute("acc");
                        if (loggedInUser != null) {
                    %>
                    <div class="dropdown pb-4">
                        <a href="#" class="d-flex align-items-center text-white text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
                            <img src="https://github.com/mdo.png" alt="hugenerd" width="30" height="30" class="rounded-circle">
                            <span class="d-none d-sm-inline mx-1">Hello, <%= loggedInUser.getUsername() %></span>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-dark text-small shadow">
                            <li><a class="dropdown-item" href="Logout">Sign out</a></li>
                        </ul>
                    </div>
                    <%
                        }
                    %>
                </div>
            </div>
            <div class="col py-3">
                <!-- Main Content Start -->
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
                                        <th>Instructor</th>
                                        <th>Status</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        Map<Integer, List<String>> courseCate = (Map<Integer, List<String>>) request.getAttribute("courseCate");
                                        Map<Integer, List<String>> expertCourse = (Map<Integer, List<String>>) request.getAttribute("courseExpert");
                                        int countCourse = 0;
                                        ArrayList<Course> courseList = (ArrayList<Course>) request.getAttribute("courseList");

                                        for (Course course : courseList) {
                                            if (countCourse >= 2) {
                                                break;
                                            }

                                            List<String> experts = expertCourse.get(course.getCourseId());
                                            if (experts == null) {
                                                experts = new ArrayList<>();
                                            }
                                            StringBuilder expertsStrBuilder = new StringBuilder();
                                            for (int i = 0; i < experts.size(); i++) {
                                                expertsStrBuilder.append(experts.get(i));
                                                if (i < experts.size() - 1) {
                                                    expertsStrBuilder.append(", ");
                                                }
                                            }
                                            String expertsStr = expertsStrBuilder.toString();

                                            List<String> categories = courseCate.get(course.getCourseId());
                                            StringBuilder categoriesStrBuilder = new StringBuilder();
                                            if (categories != null) {
                                                for (int i = 0; i < categories.size(); i++) {
                                                    categoriesStrBuilder.append(categories.get(i));
                                                    if (i < categories.size() - 1) {
                                                        categoriesStrBuilder.append(", ");
                                                    }
                                                }
                                            }
                                            String categoriesStr = categoriesStrBuilder.toString();
                                    %>
                                    <tr>
                                        <td><%= course.getCourseId() %></td>
                                        <td><%= course.getTitle() %></td>
                                        <td><%= expertsStr %></td>
                                        <td><%= categoriesStr %></td>
                                        <td> 
                                            <a href="editCourse?courseId=<%= course.getCourseId() %>" class="btn btn-primary btn-sm">Edit</a>
                                            <button class="btn btn-danger btn-sm">Delete</button>
                                        </td>
                                    </tr>
                                    <%
                                            countCourse++;
                                        }
                                    %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <a href="CourseList" class="btn btn-secondary">See More</a>
                </div>
                <div id="customerManage" class="mt-5">
                    <h2>Customer Manage</h2>
                    <p>Manage all customers here.</p>
                    <div class="card mb-3">
                        <div class="card-header">Customer List</div>
                        <div class="card-body">
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Customer Name</th>
                                        <th>Email</th>
                                        <th>Phone</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        int countUser = 0;
                                        ArrayList<Users> userList = (ArrayList<Users>) request.getAttribute("userList");
                                        for (Users user : userList) {
                                            if (countUser >= 2) {
                                                break;
                                            }
                                    %>
                                    <tr>
                                        <td><%= user.getUserId() %></td>
                                        <td><%= user.getUsername() %></td>
                                        <td><%= user.getEmail() %></td>
                                        <td><%= user.getPhoneNumber() %></td>
                                         <td>
                                         <a href="ViewCustomerDetail?userId=<%= user.getUserId() %>" class="btn btn-primary btn-sm">View</a>
                                        <button class="btn btn-danger btn-sm">Delete</button>
                                    </td>
                                    </tr>
                                    <% 
                                            countUser++;
                                        }
                                    %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <a href="CustomerList" class="btn btn-secondary">See More</a>
                </div>
                <div id="expertAccountManage" class="mt-5">
                    <h2>Expert Account Manage</h2>
                    <p>Manage expert accounts here.</p>
                    <div class="card mb-3">
                        <div class="card-header">Expert Accounts</div>
                        <div class="card-body">
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Name</th>
                                        <th>Email</th>
                                        <th>Specialty</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody id="expertResults">
                                    <% 
                                        int count = 0;
                                        ArrayList<Expert> expertList = (ArrayList<Expert>) request.getAttribute("expertList");
                                        Map<Integer, List<String>> expertCategories = (Map<Integer, List<String>>) request.getAttribute("expertCategories");

                                        for (Expert expert : expertList) {
                                            if (count >= 2) { 
                                                break; 
                                            }
                                            Users user = expert.getUser();
                                    %>
                                    <tr>
                                        <td><%= expert.getExpertId() %></td>
                                        <td><%= user.getUsername() %></td>
                                        <td><%= user.getEmail() %></td>
                                        <td>
                                            <%
                                                List<String> categories = expertCategories.get(expert.getExpertId());
                                                if (categories != null) {
                                                    if (categories.size() == 1) {
                                                        out.print(categories.get(0));
                                                    } else {
                                                        for (int i = 0; i < categories.size(); i++) {
                                                            out.print(categories.get(i));
                                                            if (i < categories.size() - 1) {
                                                                out.print(", ");
                                                            }
                                                        }
                                                    }
                                                }
                                            %>
                                        </td>
                                        <td>
                                            <form action="editExpertServlet" method="post" style="display:inline;">
                                                <input type="hidden" name="expertId" value="<%= expert.getExpertId() %>">
                                                <button type="submit" class="btn btn-primary btn-sm">Edit</button>
                                            </form>
                                            <form action="deleteExpertServlet" method="POST" style="display:inline;">
                                                <input type="hidden" name="expertId" value="<%= expert.getExpertId() %>">
                                                <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                                            </form>
                                        </td>
                                    </tr>
                                    <%
                                            count++;
                                        }
                                    %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <a href="ExpertList" class="btn btn-secondary">See More</a>
                </div>
                <div id="pendingCourses" class="mt-5">
                    <h2>Pending Courses</h2>
                    <p>List of courses pending approval.</p>
                    <div class="card mb-3">
                        <div class="card-header">Pending Courses</div>
                        <div class="card-body">
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Course Name</th>
                                        <th>Category</th>
                                        <th>Price</th>
                                        <th>Created At</th>
                                        <th>Updated At</th>
                                        <th>Status</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        List<Course> pendingCourseList = (List<Course>) request.getAttribute("pendingCourseList");
                                        for (Course course : pendingCourseList) {
                                    %>
                                    <tr>
                                        <td><%= course.getCourseId() %></td>
                                        <td><%= course.getTitle() %></td>
                                        <td><%= course.getCategoryId() %></td>
                                        <td><%= course.getPrice() %></td>
                                        <td><%= course.getCreatedAt() %></td>
                                        <td><%= course.getUpdatedAt() %></td>
                                        <td><%= course.getStatus() %></td>
                                        <td>
                                            <a href="AdminCourseDetail?courseId=<%= course.getCourseId() %>" class="btn btn-info btn-sm">View Details</a>
                                        </td>
                                    </tr>
                                    <%
                                        }
                                    %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                
                <div class="col-sm-12">
                    <h2>Create Expert Account</h2>
                    <form action="CreateExpert" method="post">
                        <div class="form-group">
                            <label>Username</label>
                            <input type="text" class="form-control" name="username" placeholder="Enter username" required>
                        </div>
                        <div class="form-group">
                            <label>Password</label>
                            <input type="password" class="form-control" name="password" placeholder="Enter password" required>
                        </div>
                        <div class="form-group">
                            <label>Email</label>
                            <input type="email" class="form-control" name="email" placeholder="Enter email" required>
                        </div>
                        <div class="form-group">
                            <label>Specialty</label>
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
                        <div class="form-group">
                            <label>Bio</label>
                            <textarea class="form-control" name="bio" placeholder="Enter bio" required></textarea>
                        </div>
                        <button type="submit" class="btn btn-primary">Create Expert Account</button>
                    </form>
                </div>
                            <div id="pendingExperts" class="mt-5">
<h2>Pending Expert Registrations</h2>
<p>Manage pending expert registration requests here.</p>
<div class="card mb-3">
    <div class="card-header">Pending Experts</div>
    <div class="card-body">
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Username</th>
                    <th>Email</th>
                    <th>Full Name</th>
                    <th>Birth Date</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Users> pendingExpertList = (List<Users>) request.getAttribute("pendingExpertList");
                    for (Users user : pendingExpertList) {
                %>
                <tr>
                    <td><%= user.getUserId() %></td>
                    <td><%= user.getUsername() %></td>
                    <td><%= user.getEmail() %></td>
                    <td><%= user.getFullName() %></td>
                    <td><%= user.getBirthDate() %></td>
                     <td>
                            <form action="AdminApproveExpert" method="post" style="display:inline;">
                                <input type="hidden" name="userId" value="<%= user.getUserId() %>">
                                <button type="submit" class="btn btn-success btn-sm">Approve</button>
                            </form>
                            <form action="AdminRejectExpert" method="post" style="display:inline;">
                                <input type="hidden" name="userId" value="<%= user.getUserId() %>">
                                <button type="submit" class="btn btn-danger btn-sm">Reject</button>
                            </form>
                            <a href="ViewPendingExpertDetail?userId=<%= user.getUserId() %>" class="btn btn-info btn-sm">View Details</a>
                        </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</div>
</div>
              
                <!-- Main Content End -->
            </div>
        </div>
    </div>
    <!-- Sidebar End -->

    <!-- Footer Start -->
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

                        <!--/*** This template is free as long as you keep the footer author?s credit link/attribution link/backlink. If you'd like to use the template without the footer author?s credit link/attribution link/backlink, you can purchase the Credit Removal License from "https://htmlcodex.com/credit-removal". Thank you for your support. ***/-->
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
    <!-- Footer End -->

    <!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="lib/wow/wow.min.js"></script>
    <script src="lib/easing/easing.min.js"></script>
    <script src="lib/waypoints/waypoints.min.js"></script>
    <script src="lib/owlcarousel/owl.carousel.min.js"></script>

    <!-- Template Javascript -->
    <script src="js/main.js"></script>
</body>
</html>
