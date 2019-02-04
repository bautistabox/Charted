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
    <title>${title}</title>
    <style>
        table, th, td {
            border: 1px solid black;
        }

        divTop {
            border-bottom: 1px solid black;
        }
    </style>
</head>
<body>
<center>
    <br>
    <divTop>
        <a href="/LogoutServlet">Logout</a>
        &nbsp;&nbsp;
        <a href="/home">Home</a>
    </divTop>
</center>

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
        <form method="post" action="update_song" enctype="multipart/form-data">
            <td><input type="text" name="songTitle" placeholder="${title}"/></td>
            <td><input type="text" name="songArtist" placeholder="${artist}"/></td>
            <td><select name="songGenre" size="1">
                <option value="JAZZ">Jazz</option>
                <option value="BLUES">Blues</option>
                <option value="ROCK">Rock</option>
            </select></td>
            <td><select name="knowledgeLevel" size="1">
                <option value="1">I've listened to it...</option>
                <option value="2">I'm an amateur</option>
                <option value="3">I'm average!</option>
                <option value="4">I'm a semi-pro</option>
                <option value="5">I'm a pro</option>
            </select></td>
            <td><input type="file" name="upload" accept="application/pdf"/></td>

            <td>${uploader}</td>
            <td><input type="submit" value="update"/></td>
        </form>
        <td><a href="/delete?song_id=${id}">Delete</a></td>
    </tr>
</table>
</body>
</html>
