<%--
  Created by IntelliJ IDEA.
  User: alexb
  Date: 1/30/2019
  Time: 11:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add a Song</title>
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
    <a class="btn btn-primary" style="background-color: #3CB371; border-color: #3CB371" href="/home">Home</a>
    &nbsp;&nbsp;
    <a class="btn btn-danger" href="/LogoutServlet">Logout</a>

    <br><br><br>
    <div style="width: 60%;">
        <form method="post" action="/add_song" enctype="multipart/form-data">
            <table class="table table-bordered table-dark">
                <caption style="caption-side: top; text-align: center; color: white;"><h1 class="display-4">Add a
                    Song</h1></caption>
                <br><br>
                <tbody>
                <tr>
                    <td>
                        <input type="text" name="songTitle" placeholder="Title" class="form-control" required>
                    </td>
                    <td>
                        <input type="text" name="songArtist" placeholder="Artist" class="form-control required">
                    </td>
                    <td>
                        <select name="songGenre" size="1" class="form-control">
                            <option value="JAZZ">Jazz</option>
                            <option value="BLUES">Blues</option>
                            <option value="ROCK">Rock</option>
                        </select>
                    </td>
                    <td>
                        <input type="file" name="upload" accept="application/pdf" style="color: white;" required/>
                    </td>
                </tr>
                </tbody>
            </table>
            <input type="submit" value="Add" class="btn btn-primary"
                   style="background-color: #3CB371; border-color: #3CB371;"/>
        </form>
    </div>
</div>
</body>
</html>
