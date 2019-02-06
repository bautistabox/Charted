<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Charted</title>
    <link href="bootstrap.css" type="text/css" rel="stylesheet">
    <style>
        body, html {
            height: 100%;
        }

        .bg {
            background-image: url("https://www.maxpixel.net/static/photo/1x/Music-Instruments-On-Table-Musical-Background-2842924.jpg");
            height: 100%;
            background-position: center;
            background-repeat: no-repeat;
            background-size: cover;
        }
    </style>
</head>
<body>
<div class="bg">
    <br><br><br><br><br>
    <h1 align="center" class="display-3" style="color:white">Welcome to Charted</h1>
    <h6 align="center" style="color:white">Please Login or Sign up</h6>
    <br><br><br>
    <div align="center">
        <c:if test="${not empty errorMessage}">
        <span style="color: white">
            <c:out value="${errorMessage}"/>
        </span>
        </c:if>
    </div>
    <form action="home" method="post">
        <table align="center" style="border-collapse:separate; border-spacing:0 7px;">
            <tr>
                <td><input type="text" name="username" placeholder="Username" class="form-control" required/></td>
            </tr>
            <tr>
                <td><input type="password" name="userpass" placeholder="Password" class="form-control" required/></td>
            </tr>
        </table>
        <br>
        <div align="center">
            <input type="submit" value="Login" class="btn btn-primary"
                   style="background-color:#3CB371; border-color:#3CB371"/>
            <a href="/signup" class="btn btn-primary" style="background-color:#3CB371; border-color:#3CB371">Sign Up</a>
        </div>
    </form>
    <br>
</div>
</body>
</html>