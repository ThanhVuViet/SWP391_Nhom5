<!DOCTYPE html>
<html>
    <head>
        <title>Sign Up</title>
        <meta charset="UTF-8">
        <link rel="icon" type="image/x-icon" href="images/icontitle.png">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css/mystyle.css" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body class="board-page bg-book">
        <div class="board-md  bg-board">
            <div class="title pt-4">
                <a id="logo-link" href="home"><img id="logo-img" src="images/icon.png" alt="F"/><span id="logo-text">demy</span></a>          </div>
            <div class="title">
                <h2 class="title-text">Sign up new account</h2>
            </div>
            <form class="mx-5 mt-3" action="" method="POST">
                <div id="signup-form">
                    <div class="col-5">
                        <p class="mb-1">Username:</p>
                        <input name="username" class="input-box"  type="text" placeholder="Enter username here"/>
                        <p class="mt-3 mb-1">Password:</p>
                        <input name="password" class="input-box"  type="password" placeholder="Enter password here"/>
                        <p class="mt-3 mb-1">Confirm password:</p>
                        <input name="repassword" class="input-box"  type="password" placeholder="Confirm password"/>
                    </div>
                    <div class="col-5">
                        <p class="mb-1">Full name:</p>
                        <input name="fullname" class="input-box"  type="text" placeholder="Enter full name here"/>
                        <p class="mt-3 mb-1">Email:</p>
                        <input name="email" class="input-box"  type="email" placeholder="Enter email here"/>
                        <p class="mt-3 mb-1">Date of birth:</p>
                        <input name="dob" class="input-box"  type="date" placeholder=""/>
                    </div>
                </div>
                <button class="button-normal" type="submit">Sign up</button>
            </form>
            <hr>
            <div class="mx-auto mt-3" style="text-align: center;">
                <p> You already have an account? <a class="link-text" href='login'>Back to login</a></p>
            </div>
        </div>
        <script src="js/bootstrap-5.3.3-dist/bootstrap.min.js"></script>
        <script src="js/bootstrap-5.3.3-dist/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/2.10.2/umd/popper.min.js" integrity="sha512-nnzkI2u2Dy6HMnzMIkh7CPd1KX445z38XIu4jG1jGw7x5tSL3VBjE44dY4ihMU1ijAQV930SPM12cCFrB18sVw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    </body>
</html>