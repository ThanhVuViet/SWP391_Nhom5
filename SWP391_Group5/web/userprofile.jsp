<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Profile</title>
    <style>
        .no-underline {
            text-decoration: none;
        }
        .card {
            position: relative;
            display: flex;
            flex-direction: row;
            min-width: 0;
            word-wrap: break-word;
            background-color: #fff;
            background-clip: border-box;
            border: 1px solid rgba(0,0,0,.125);
            border-radius: .25rem;
            padding: 20px;
            margin: 20px 0;
        }
        .img-thumbnail {
            max-width: 150px;
            border-radius: 50%;
        }
        .user-info {
            flex: 1;
            padding-left: 20px;
            padding-right: 50px;
        }
        .update-info {
            flex: 1;
            padding-left: 20px;
            border-left: 1px solid rgba(0,0,0,.125);
        }
        .con {
            margin-bottom: 20px;
        }
        .error-message {
            color: red;
        }
        .success-message {
            color: green;
        }
    </style>
</head>
<body>
    <div class="con">
        <h1>User Profile</h1>
        <p><a href="${pageContext.request.contextPath}/afterlogin.jsp" class="no-underline">Home ></a> Profile</p>
    </div>
    <div class="card">
        <div>
            <div class="img-thumbnail">
                <img src="${pageContext.request.contextPath}/${sessionScope.acc.image != null ? sessionScope.acc.image : 'New-User.jpg'}" alt="img">
            </div>
            <div class="user-info">
                <p>User Name: ${sessionScope.acc.username}</p>
                <p>Full Name: ${sessionScope.acc.fullName != null ? sessionScope.acc.fullName : 'empty'}</p>
                <p>Birth Date: ${sessionScope.acc.birthDate != null ? sessionScope.acc.birthDate : 'empty'}</p>
                <p>Email: ${sessionScope.acc.email != null ? sessionScope.acc.email : 'empty'}</p>
                <p>Phone: ${sessionScope.acc.phoneNumber != null ? sessionScope.acc.phoneNumber : 'empty'}</p>
                <p>Address: ${sessionScope.acc.address != null ? sessionScope.acc.address : 'empty'}</p>
            </div>  
        </div>

        <div class="update-info">
            <h3>Update Information</h3>
            <form action="updateuser" method="POST" enctype="multipart/form-data">
                <span class="success-message">${requestScope.updateSucc}</span>
                <label for="image">User Image:</label><br>
                <input name="image" type="file" id="image"><br><br>

                <label for="fullname">Full Name:</label><br>
                <input type="text" id="fullname" name="fullname" value="${param.fullname != null ? param.fullname : sessionScope.acc.fullName}"><br><br>

                <label for="username">User Name:</label><br>
                <input type="text" id="username" name="username" value="${sessionScope.acc.username}" readonly><br><br>

                <label for="oldPassword">Old Password:</label><br>
                <input type="password" id="oldPassword" name="oldPassword"><br><br>
                <span class="error-message">${requestScope.errorOldPass}</span><br>
                
                <label for="newPassword">New Password:</label><br>
                <input type="password" id="newPassword" name="newPassword"><br><br>
                <span class="error-message">${requestScope.errorNewPass}</span><br>

                <label for="confirmPassword">Confirm Password:</label><br>
                <input type="password" id="confirmPassword" name="confirmPassword"><br><br>
                <span class="error-message">${requestScope.errorPassword}</span><br>

                <label for="birthdate">Birth Date:</label><br>
                <input type="date" id="birthdate" name="birthdate" value="${param.birthdate != null ? param.birthdate : sessionScope.acc.birthDate}"><br><br>

                <label for="phone">Phone:</label><br>
                <input type="text" id="phone" name="phone" value="${param.phone != null ? param.phone : sessionScope.acc.phoneNumber}"><br><br>

                <label for="address">Address:</label><br>
                <input type="text" id="address" name="address" value="${param.address != null ? param.address : sessionScope.acc.address}"><br><br>

                <input type="submit" value="Update">
            </form>
            <br>
            
        </div>
    </div>
</body>
</html>
