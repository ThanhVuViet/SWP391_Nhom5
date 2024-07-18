<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="Entity.Course" %>
<%@ page import="Entity.Category" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Edit Course</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <style>
            body {
                margin: 20px;
            }
        </style>
    </head>
    <body>
        <div class="container-fluid content">
            <div class="row flex-nowrap">
                <div class="col-auto col-md-3 col-xl-2 px-sm-2 px-0 bg-dark">
                    <div class="d-flex flex-column align-items-center align-items-sm-start px-3 pt-2 text-white min-vh-100">
                        <a href="/" class="d-flex align-items-center pb-3 mb-md-0 me-md-auto text-white text-decoration-none">
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
                                <a href="#viewReports" class="nav-link px-0 align-middle">
                                    <i class="fs-4 bi-file-earmark-text"></i> <span class="ms-1 d-none d-sm-inline">View Reports</span>
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
                    <div class="container">
                        <h2>Edit Course Information</h2>
                        <% 
                            String message = (String) request.getAttribute("message");
                            if (message != null) { 
                        %>
                        <div class="alert alert-success" role="alert">
                            <%= message %>
                        </div>
                        <% } %>
                        <%
                            Course course = (Course) request.getAttribute("courseUpdate");
                            Map<Integer, List<String>> courseCate = (Map<Integer, List<String>>) request.getAttribute("courseCate");
                            Map<Integer, List<String>> expertCourse = (Map<Integer, List<String>>) request.getAttribute("courseExpert");
                            
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
                            List<Category> cateList = (List<Category>) request.getAttribute("cateList");
                        %>
                        <form action="updateCourse" method="post">
                            <div class="form-group">
                                <label for="courseName">Course Name</label>
                                <input type="text" class="form-control" id="courseName" name="courseName" value="<%=course.getTitle() %>">
                            </div>
                            <div class="form-group">
                                <label for="courseCategory">Expert</label>
                                <input type="text" class="form-control" id="courseCategory" name="courseCategory" value="<%= expertsStr %>" readonly>
                            </div>
                            <div class="form-group">
                                <label for="specialty">Specialty</label>
                                <input type="text" class="form-control" id="specialty" name="specialty" value="<%= categoriesStr %>" readonly>
                            </div>
                            <div class="form-group">
                                <label for="description">Description</label>
                                <textarea class="form-control" id="description" name="description"><%=course.getDescription() %></textarea>
                            </div>
                            <div class="form-group">
                                <label for="addSpecialty">Add Specialty</label>
                                <select class="form-control" id="addSpecialty" name="addSpecialty">
                                    <option value="" disabled selected>Select a category</option>
                                    <% if (cateList != null) { 
                                        for (Category category : cateList) {
                                            if (!categories.contains(category.getCateName())) { %>
                                    <option value="<%= category.getCateName() %>"><%= category.getCateName() %></option>
                                    <%      } 
                                        } 
                                    } %>
                                </select>
                            </div>
                            <input type="hidden" name="courseId" value="<%=course.getCourseId() %>">
                            <button type="submit" class="btn btn-primary">Save</button>
                            <a href="viewCourseDetails?courseId=<%= course.getCourseId() %>" class="btn btn-secondary">View Course</a>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
