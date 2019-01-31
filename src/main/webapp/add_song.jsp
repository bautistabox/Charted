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
        <form method="post" action="/add_song">
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
                <label for="radioTwo">I've starting learning</label>
                <br>
                <input type="radio" id="radioThree" name="knowledgeLevel" value="3">
                <label for="radioThree">I'm getting there!</label>
                <br>
                <input type="radio" id="radioFour" name="knowledgeLevel" value="4">
                <label for="radioFour">F*** it.. We'll do it live! ಠ_ಠ</label>
                <br>
                <input type="radio" id="radioFive" name="knowledgeLevel" value="5">
                <label for="radioFive">Perfection</label>
            </div>

            <br><br>
            <input type="file" name="upload" accept="application/pdf"/>
            <br><br>
            <input type="submit" value="Add">
        </form>
    </center>
</body>
</html>
