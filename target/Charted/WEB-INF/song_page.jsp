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
    <style>
        table, th, td {
            border: 1px solid black;
            text-align: center;
        }

        divTop {
            border-bottom: 1px solid black;
            text-align: center;
        }
    </style>
</head>
<body>
<br>
<divTop>
    <a href="/LogoutServlet">Logout</a>
    &nbsp;&nbsp;
    <a href="/home">Home</a>
</divTop>

<table style="width:100%">
    <tr>
        <th>Title</th>
        <th>Artist</th>
        <th>Genre</th>
        <th>How Well You Know It</th>
        <th>PDF</th>
        <th>Uploaded By</th>
        <th></th>
        <th></th>
    </tr>
    <tr>
        <td>${title}</td>
        <td>${artist}</td>
        <td>${genre}</td>
        <th>${level}</th>
        <td><a href="download_song?song_id=${id}">Download PDF</a></td>
        <td>${uploader}</td>
        <td>
            <form method="get" action="/update">
                <input type="submit" value="Update"/>
                <input type="hidden" name="secret_id" value="${id}"/>
            </form>
        </td>
        <td>
            <form method="post" action="/delete">
                <input type="submit" value="Delete"/>
                <input type="hidden" name="secret_id" value="${id}"/>
            </form>
        </td>
    </tr>
</table>
</body>
</html>