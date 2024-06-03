<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="Entity.Expert" %>
<%@ page import="Entity.Users" %>
<%@ page import="java.util.Map" %>
<head>
    <meta charset="UTF-8">
    <title>Expert Account Manage</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <style>
        body {
            margin: 20px;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        function searchByName(input) {
            var filterName = $('#filterName').val();
            var filterSpecialty = $('#filterSpecialty').val();
            $.ajax({
                url: 'searchByAjax',
                type: 'GET',
                data: {
                    filterName: filterName,
                    filterSpecialty: filterSpecialty
                },
                dataType: 'html',
                success: function(response) {
                    $('#expertResults').html(response);
                },
                error: function(xhr, status, error) {
                    console.error('Error: ' + error);
                }
            });
        }

        function searchByCate(input) {
            searchByName(input); // Using the same function for simplicity
        }
    </script>
</head>

<body>
    <!-- Sidebar Start -->
    <div class="container-fluid content">
        <div class="row flex-nowrap">
            <div class="col-auto col-md-3 col-xl-2 px-sm-2 px-0 bg-dark">
                <div class="d-flex flex-column align-items-center align-items-sm-start px-3 pt-2 text-white min-vh-100">
                    <a href="/" class="d-flex align-items-center pb-3 mb-md-0 me-md-auto text-white text-decoration-none">
                        <span class="fs-5 d-none d-sm-inline">Menu</span>
                    </a>
                    <ul class="nav nav-pills flex-column mb-sm-auto mb-0 align-items-center align-items-sm-start" id="menu">
                        <li class="nav-item">
                            <a href="#" class="nav-link align-middle px-0">
                                <i class="fs-4 bi-house"></i> <span class="ms-1 d-none d-sm-inline">Home</span>
                            </a>
                        </li>
                        <li>
                            <a href="#manageCourse" class="nav-link px-0 align-middle">
                                <i class="fs-4 bi-book"></i> <span class="ms-1 d-none d-sm-inline">Manage Course</span>
                            </a>
                        </li>
                        <li>
                            <a href="#customerManage" class="nav-link px-0 align-middle">
                                <i class="fs-4 bi-people"></i> <span class="ms-1 d-none d-sm-inline">Customer Manage</span>
                            </a>
                        </li>
                        <li>
                            <a href="#expertAccountManage" class="nav-link px-0 align-middle">
                                <i class="fs-4 bi-person-badge"></i> <span class="ms-1 d-none d-sm-inline">Expert Account Manage</span>
                            </a>
                        </li>
                        <li>
                            <a href="#viewReports" class="nav-link px-0 align-middle">
                                <i class="fs-4 bi-file-earmark-text"></i> <span class="ms-1 d-none d-sm-inline">View Reports</span>
                            </a>
                        </li>
                        <li>
                            <a href="#settings" class="nav-link px-0 align-middle">
                                <i class="fs-4 bi-gear"></i> <span class="ms-1 d-none d-sm-inline">Settings</span>
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
                <!-- Main Content Start -->
                <div id="expertAccountManage" class="mt-5">
                    <h2>Expert Account Manage</h2>
                    <p>Manage expert accounts here.</p>
                    <div class="card mb-3">
                        <div class="card-header">Filter Experts</div>
                        <div class="card-body">
                            <form id="filterForm" method="GET" action="experts.jsp">
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="filterName">Name</label>
                                        <input oninput="searchByName(this)" type="text" class="form-control" id="filterName" name="filterName" placeholder="Enter name">
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="filterSpecialty">Specialty</label>
                                        <input oninput="searchByCate(this)" type="text" class="form-control" id="filterSpecialty" name="filterSpecialty" placeholder="Enter specialty">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="card mb-3">
                        <div class="card-header">Expert Accounts</div>
                        <div class="card-body">
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Expert Name</th>
                                        <th>Email</th>
                                        <th>Specialty</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody id="expertResults">
                                    <% 
                                        ArrayList<Expert> expertList = (ArrayList<Expert>) request.getAttribute("expertList");
                                        Map<Integer, List<String>> expertCategories = (Map<Integer, List<String>>) request.getAttribute("expertCategories");
                                        for (Expert expert : expertList) {
                                            Users user = expert.getUser();
                                    %>
                                    <tr>
                                        <td><%=expert.getExpertId()%></td>
                                        <td><%=user.getUsername()%></td>
                                        <td><%=user.getEmail()%></td>
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
                            <input type="hidden" name="expertId" value="<%=expert.getExpertId()%>">
                            <button type="submit" class="btn btn-primary btn-sm">Edit</button>
                        </form>
                        <form action="deleteExpertServlet" method="POST" style="display:inline;">
                            <input type="hidden" name="expertId" value="<%=expert.getExpertId()%>">
                            <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                        </form>
                    </td>
                                    </tr>
                                    <% } %>
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
</body>
</html>
