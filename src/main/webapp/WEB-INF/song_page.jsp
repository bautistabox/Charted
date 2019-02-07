<%--
  Created by IntelliJ IDEA.
  User: alexb
  Date: 1/30/2019
  Time: 4:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>${title}</title>
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
    <a class="btn btn-primary" style="background-color: #3CB371; border-color: #3CB371" href="/home">Home</a>
    &nbsp;&nbsp;
    <a class="btn btn-danger" href="/LogoutServlet">Logout</a>

    <br><br><br><br><br><br>


    <table style="width:75%" class="table table-bordered table-dark">
        <tr align="center">
            <th>Title</th>
            <th>Artist</th>
            <th>Genre</th>
            <th>PDF</th>
            <th>Uploaded By</th>
            <th></th>
            <th></th>
        </tr>
        <tr align="center">
            <td>${title}</td>
            <td>${artist}</td>
            <td>${genre}</td>
            <td><a href="download_song?song_id=${id}">Download PDF</a></td>
            <td>${uploader}</td>
            <td align="center">
                <form method="get" action="/update">
                    <input type="submit" value="Update" class="btn btn-warning"/>
                    <input type="hidden" name="secret_id" value="${id}"/>
                </form>
            </td>
            <td align="center">
                <form method="post" action="/delete">
                    <input type="submit" value="Delete" class="btn btn-danger"/>
                    <input type="hidden" name="secret_id" value="${id}"/>
                </form>
            </td>
        </tr>
    </table>
</div>
</body>
</html>