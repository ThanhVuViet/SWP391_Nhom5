<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <%@ page import="Entity.Users" %>
    <head>
        <title>Fdemy</title>
        <meta charset="UTF-8">
        <link rel="icon" type="image/x-icon" href="images/icontitle.png">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css/mystyle.css" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body class="bg-home">
        <!-- Top of the page -->
        <div class="fixed-top navbar top-bar bg-light">
            <div class="navbar px-4" >
                <!-- Title -->
                <a id="logo-link" href="home"><img id="logo-img" src="images/icon.png" alt="F"/><span id="logo-text">demy</span></a>
                <!-- Nav Bar -->

            </div>
            <div class="navbar">
                <!-- Search bar -->
                <ul id="ref" class="navbar mb-0 mx-3">
                    <li class="nav-item">
                        <a class="ref-item ref-active" href="">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="ref-item" href="courses">Courses</a>
                    </li>
                    <!--                    <li class="nav-item">
                                            <div class="dropdown">
                                                <a href="" class="ref-item dropdown-toggle" data-bs-toggle="dropdown">Category</a>
                                                <div id="categories" class="dropdown-menu m-0">
                                                    <a href="" class="dropdown-item">Web Design</a>
                                                    <a href="" class="dropdown-item">Program Design</a>
                                                    <a href="" class="dropdown-item">Database</a>
                                                    <a href="" class="dropdown-item">Others</a>
                                                </div>
                                            </div>
                                        </li>-->
                    <li class="nav-item">
                        <a class="ref-item " href="">About Us</a>
                    </li>
                </ul>
                <!-- Cart -->
                <a class="mx-2" href=""><i id="cart" class="fa fa-shopping-cart"> 0</i></a>
                <% if ( (Users) request.getSession().getAttribute("acc") == null) {%>
                <div id="login" class="mx-2">
                    <a href="login">Login</a>
                </div>
                <%} else {
                Users acc = (Users) request.getSession().getAttribute("acc"); %>
                <div class="dropdown mx-2">
                    <a href="" class="ref-item dropdown-toggle" data-bs-toggle="dropdown"><img id="pfp-icon" src="<%=acc.getImage()%>" alt="Profile"/></a>
                    <div id="settings" class="dropdown-menu m-0">
                        <p class="mx-3">Login as <%=acc.getUsername()%></p>
                        <hr class="m-0">
                        <a href="profile?id=<%=acc.getUserId()%>" class="dropdown-item">Profile</a>
                        <a href="" class="dropdown-item">Purchased Courses</a>
                        <a href="" class="dropdown-item">Log out</a>
                    </div>
                </div>
                <%}%>
            </div>
        </div>
        <div id="main">
            <div id="welcome" class="col-12">
                <div class="col-6 px-5 ">
                    <h1>Welcome to Fdemy</h1>
                    <p>
                        The worst online learning website
                    </p>
                </div>
                <div class="col-6 p-5">
                    <img src="images/welcome_img.png" alt="Welcome"/>
                </div>
            </div>
        </div>
            <!-- Footer -->
        <div class="footer">
            
        </div>
        <script src="js/bootstrap-5.3.3-dist/bootstrap.min.js"></script>
        <script src="js/bootstrap-5.3.3-dist/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/2.10.2/umd/popper.min.js" integrity="sha512-nnzkI2u2Dy6HMnzMIkh7CPd1KX445z38XIu4jG1jGw7x5tSL3VBjE44dY4ihMU1ijAQV930SPM12cCFrB18sVw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    </body>
</html>