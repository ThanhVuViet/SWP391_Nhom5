<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <link rel="icon" type="image/x-icon" href="images/icontitle.png">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&display=swap" rel="stylesheet">
        <!-- Icon Font Stylesheet -->
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css/mystyle.css" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body class="board-page bg-book">
        <div class="board-sm bg-board">
            <div class="title pt-4">
                <a id="logo-link" href="home"><img id="logo-img" src="images/icon.png" alt="F"/><span id="logo-text">demy</span></a>          </div>
            <div class="title">
                <h2 class="title-text">Login with your account</h2>
            </div>
            <% 
                    Integer failedAttempt = (Integer) request.getAttribute("failedAttempt");
                    if (request.getAttribute("loginFailed") != null) { 
            %>
            <% if (failedAttempt != null && failedAttempt < 5) { %>
            <div class="alert alert-danger" role="alert">
                <%= request.getAttribute("loginFailed") %> (Failed attempts: <%= failedAttempt %>)
            </div>
            <% } %>
            <% } %>

            <% if (request.getAttribute("AccountLocked") != null) { %>
            <div class="alert alert-danger" role="alert">
                <%= request.getAttribute("AccountLocked") %>  
            </div>
            <% } %>

            <% if (failedAttempt != null && failedAttempt == 5) { %>
            <div class="alert alert-danger" role="alert">
                Your account has been locked due to multiple failed login attempts.
            </div>
            <% } %>
            <form class="mx-5 mt-3" action="login" method="POST">
                <p class="mb-1">Username:</p>
                <input class="input-box" type="text" class="form-control" value="<%= request.getAttribute("username") != null ? request.getAttribute("username") : "" %>" name="email" placeholder="Enter email" required maxlength="16">
                <p class="mt-3 mb-1">Password:</p>
                <input class="input-box" type="password" value="<%= request.getAttribute("password") != null ? request.getAttribute("password") : "" %>" class="form-control" id="password" name="password" oninput="validateMaxPasswordLength()" placeholder="Password" required maxlength="16">
                <div id="mess" style="display: none; color: red;">
                    Password must be at most 16 characters long.
                </div>
                <input class="mt-2" type="checkbox" id="showPasswordCheckbox" onclick="showPassword()"> <label for="showPasswordCheckbox">Show password</label><a id="fp" class="link-text" href="forgotPassword">Forgot password?</a>
                <button class="button-normal" type="submit">Login</button>
            </form>
            <hr>
            <% if (request.getAttribute("loginFailedThreeTime") != null) { %>
            <div class="alert alert-danger" role="alert">
                <%= request.getAttribute("loginFailedThreeTime") %>
                <a href="forgotPassword" class="alert-link">Forgot password?</a>
            </div>
            <% }%>
            <div style="text-align: center;">
                <p>Or you can</p>
            </div>
            <div style="text-align: center;">
                <a class="mx-5 button-invert" href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/SWP391_Group5/LoginGoogle&response_type=code&client_id=826239897772-hjalfh6l6mrrl73029j0fqs9lis7ffjo.apps.googleusercontent.com&access_type=online&prompt=consent" >Login with Google</a>
            </div>
            <div class="mx-auto mt-3" style="text-align: center;">
                <p> You dont' have an account? <a class="link-text" href='signup'>Sign in now</a></p>
            </div>
        </div>
        <script src="js/bootstrap-5.3.3-dist/bootstrap.min.js"></script>
        <script src="js/bootstrap-5.3.3-dist/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/2.10.2/umd/popper.min.js" integrity="sha512-nnzkI2u2Dy6HMnzMIkh7CPd1KX445z38XIu4jG1jGw7x5tSL3VBjE44dY4ihMU1ijAQV930SPM12cCFrB18sVw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script>
                    function validateMaxPasswordLength() {
                        var lengthPassword = document.getElementById("password").value.length;
                        var mess = document.getElementById("mess");
                        if (lengthPassword >= 16) {
                            mess.style.display = "block";
                        } else {
                            mess.style.display = "none";
                        }
                    }

                    function showPassword() {
                        var password = document.getElementById("password");
                        if (password.type === "password") {
                            password.type = "text";
                        } else {
                            password.type = "password";
                        }
                    }

                    function resetForm() {


                        var showPasswordCheckbox = document.getElementById("showPasswordCheckbox");
                        if (showPasswordCheckbox) {
                            showPasswordCheckbox.checked = false;
                        }
                        var passwordField = document.getElementById("password");
                        if (passwordField) {
                            passwordField.type = "password";
                        }
                        var alerts = document.querySelectorAll('.alert');
                        alerts.forEach(function (alert) {
                            alert.parentNode.removeChild(alert);
                        });
                    }

        </script>
    </body>
</html>

