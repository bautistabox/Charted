<%--
  Created by IntelliJ IDEA.
  User: alexb
  Date: 2/4/2019
  Time: 11:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Updating &nbsp;${title}</title>
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
<div align="center" class="bg">
    <br>
    <a class="btn btn-primary" style="background-color: #3CB371; border-color: #3CB371" href="/home">Home</a>
    &nbsp;&nbsp;
    <a class="btn btn-danger" href="/LogoutServlet">Logout</a>

    <br><br><br><br><br><br>
    <h4 class="display-4" style="color: #fff;">Update</h4>


    <table style="width:75%" class="table table-bordered table-dark">
        <tr align="center">
            <th>Title</th>
            <th>Artist</th>
            <th>Genre</th>
            <th>PDF - Null entries remain unchanged</th>
            <th></th>
            <th></th>
            <th></th>
        </tr>
        <tr align="center">
            <form method="post" action="/update" enctype="multipart/form-data">
                <td><input type="text" name="newSongTitle" value="${oldTitle}" class="form-control" placeholder="Title" required/></td>
                <td><input type="text" name="newSongArtist" value="${oldArtist}" class="form-control" required placeholder="Artist"/></td>
                <td><select name="newSongGenre" size="1" class="form-control">
                    <option value="JAZZ">Jazz</option>
                    <option value="BLUES">Blues</option>
                    <option value="ROCK">Rock</option>
                </select></td>
                <td><input type="file" name="upload" accept="application/pdf"/></td>
                <td><input type="submit" value="Save" class="btn btn-success"/></td>
                <td><a class="btn btn-warning" href="javascript:history.back()">Undo</a></td>
            </form>
            <td><a class="btn btn-danger" href="/delete?song_id=${id}">Delete</a></td>
        </tr>
    </table>
</div>
</body>
</html>
