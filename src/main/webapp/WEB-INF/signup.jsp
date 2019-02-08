<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: alexb
  Date: 1/31/2019
  Time: 11:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Sign Up</title>
    <link href="bootstrap.css" type="text/css" rel="stylesheet">
    <style>
        body, html {
            height: 100%;
        }

        .bg {
            background-image: url("../Charted_Background.jpg");
            height: 100%;
            background-position: center;
            background-repeat: no-repeat;
            background-size: cover;
        }
    </style>
</head>
<body>
<div class="bg" align="center">
    <br>
    <a class="btn btn-danger" href="javascript:history.back()">Go Back</a>
    <br><br><br>
    <h1 align="center" class="display-3" style="color:white">Make an Account</h1>
    <br><br><br>

    <c:if test="${not empty errorMessage}">
        <span style="color: gold">
            <c:out value="${errorMessage}"/>
        </span>
    </c:if>

    <form action="/signup" method="post">
        <table align="center" style="border-collapse:separate; border-spacing:0 7px;">
            <tr>
                <td>
                    <input type="text" name="user" placeholder="Username" class="form-control" required/>

                </td>
            </tr>
            <tr>
                <td>
                    <input type="password" name="pass1" placeholder="Password" class="form-control" required/>
                </td>
            </tr>
            <tr>
                <td><input type="password" name="pass2" placeholder="Verify Password" class="form-control" required/>
                </td>
            </tr>
        </table>
        <br>
        <input type="submit" value="Sign Up" class="btn btn-primary"
               style="background-color:#3CB371; border-color:#3CB371"/>
    </form>
</div>
</body>
</html>
