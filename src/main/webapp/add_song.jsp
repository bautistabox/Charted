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
</head>
<body>
<center>
    <br>
    <h1>Add a Song</h1>
    <br><br>
    <form method="post" action="/add_song" enctype="multipart/form-data">
        <input type="text" name="songTitle" placeholder="Title">
        <br><br>
        <input type="text" name="songArtist" placeholder="Artist">
        <br><br>
        <select name="songGenre" size="1">
            <option value="JAZZ">Jazz</option>
            <option value="BLUES">Blues</option>
            <option value="ROCK">Rock</option>
        </select>
        <br><br>
        <div>
            <h4>How well do you know this song?</h4>
            <input type="radio" id="radioOne" name="knowledgeLevel" value="1" checked>
            <label for="radioOne">I've listened to it...</label>
            <br>
            <input type="radio" id="radioTwo" name="knowledgeLevel" value="2">
            <label for="radioTwo">I'm an amateur</label>
            <br>
            <input type="radio" id="radioThree" name="knowledgeLevel" value="3">
            <label for="radioThree">I'm average!</label>
            <br>
            <input type="radio" id="radioFour" name="knowledgeLevel" value="4">
            <label for="radioFour">I'm a semi-pro</label>
            <br>
            <input type="radio" id="radioFive" name="knowledgeLevel" value="5">
            <label for="radioFive">I'm a pro</label>
        </div>

        <br><br>
        <input type="file" name="upload" accept="application/pdf"/>
        <br><br><br><br>
        <input type="submit" value="Add">
    </form>
</center>
</body>
</html>
