<%--
  Created by IntelliJ IDEA.
  User: alexb
  Date: 2/6/2019
  Time: 9:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>No Results Found</title>
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

<div align="center" class="bg">
    <br>
    <a class="btn btn-primary" style="background-color: mediumseagreen; border-color: mediumseagreen"
       href="/home">Home</a>
    &nbsp;&nbsp;
    <a class="btn btn-danger" href="/logout">Logout</a>


    <br><br><br><br><br><br>
    <h2 style="color: white;">Song named ${title} already exists, <a style="color: navajowhite" href="/home">Try
        searching that title</a></h2>
</div>
</body>
</html>
