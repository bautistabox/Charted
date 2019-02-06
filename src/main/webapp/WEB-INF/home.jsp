<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Charted</title>
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
<div class="bg">
    <br>
    <div align="center">
        <a class="btn btn-danger" href="/LogoutServlet">Logout</a>

        <br><br><br>
        <h1 align="center" class="display-3" style="color:white">Welcome to Charted</h1>
        <br>

        <form method="get" action="/find_song">

            <legend style="color: white">Find a song</legend>
            <br><br><br>
            <input type="text" name="searched_song" placeholder="Song Title" required/>
            <input type="submit" value="Go" class="btn-primary"
                   style="background-color: mediumseagreen; border-color: white;"/>

            <br><br>

        </form>
        <a href="/add_song" class="btn btn-primary"
           style="background-color: #3CB371; border-color: #3CB371;"> Add a Song</a>
    </div>
</div>
</body>
</html>
