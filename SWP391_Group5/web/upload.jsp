<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <title>JSP Page</title>
</head>
<body>
    <div class="container" style="margin-top: 10px;">
        <div class="row" style="border: 1px darkgrey solid; border-radius: 10px; width: 50%; margin: 0 auto; padding: 20px;">
            <div class="col-sm-12">
                <h2 class="myClass">Upload Photo</h2>
                <form action="uploadFile" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label>ID</label>
                        <input type="text" class="form-control" name="id" placeholder="Enter id">
                    </div>
                    <div class="form-group">
                        <label>Full Name</label>
                        <input type="text" class="form-control" name="name" placeholder="Enter name">
                    </div>
                    <div class="form-group">
                        <label>Photo</label><br>
                        <input type="file" class="form-control" name="photo" placeholder="Enter photo">
                    </div>
                    <button type="submit" class="btn btn-primary">Save</button>
                    <button type="reset" class="btn btn-primary">Cancel</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
