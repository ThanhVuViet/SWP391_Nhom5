<%@ page import="java.util.List" %>
<%@ page import="Entity.Course" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Manage Courses - eLearning</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
    <link href="lib/animate/animate.min.css" rel="stylesheet">
    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>

<body>
    <div class="container-fluid">
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
                            <span class="d-none d-sm-inline mx-1">Admin</span>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-dark text-small shadow">
                            <li><a class="dropdown-item" href="#">New project...</a></li>
                            <li><a class="dropdown-item" href="#">Settings</a></li>
                            <li><a class="dropdown-item" href="#">Profile</a></li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><a class="dropdown-item" href="#">Sign out</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col py-3">
                <h2 class="mt-5">Manage Course</h2>
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
                                    for (Course course : courseList) {
                                        int participants = participantsMap.getOrDefault(course.getCourseId(), 0);
                                        int averageRating = ratingsMap.getOrDefault(course.getCourseId(), 0);
                                %>
                                <tr>
                                    <td><%= course.getCourseId() %></td>
                                    <td><%= course.getTitle() %></td>
                                    <td><%= participants %></td>
                                    <td><%= String.format("%.2f", (double) averageRating) %></td>
                                    <td><%= course.getStatus()%></td>
                                    <td>
                                        <a href="expertEditCourse?courseId=<%= course.getCourseId() %>" class="btn btn-primary btn-sm">Edit</a>
                                    </td>
                                </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                </div>
                <a href="expertHome" class="btn btn-secondary">Back</a>
            </div>
        </div>
    </div>
</body>

</html>
