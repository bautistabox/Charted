<%--
  Created by IntelliJ IDEA.
  User: alexb
  Date: 1/31/2019
  Time: 11:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>
<center>
    <form method="post" action="/signup">
        <br><br><h1>Make an account</h1>
        <br><br><br>
        Enter your email: <input type="text" name="user"/>
        <br><br>
        Enter your password: <input type="password" name="pass1"/>
        <br><br>
        Retype your password: <input type="password" name="pass2"/>
        <br><br>
        <input type="submit" value="Create Account">
    </form>
    <br><br>
    <a href="javascript:history.back()">Go Back</a>
</center>
</body>
</html>
