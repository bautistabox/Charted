<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Charted</title>
    <style>
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
    </divTop>
    <h1>Welcome to Charted</h1>

    <form method="get" action="/find_song">
        <br>
        <h4>Find a song</h4>
        <input type="text" name="searched_song">
        <input type="submit" value="Go">
        <br><br>
    </form>

    <a href="/add_song"> Add a Song</a>
</center>
</body>
</html>
