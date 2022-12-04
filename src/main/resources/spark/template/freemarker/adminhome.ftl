<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<style>

    h1{
        font-size: 300%;
        color: white;
        text-align: center;
        font-family: "LEMON MILK";
        margin-top: 20px;
    }
    .container{
        text-align: center;
        color: #45cb85;
        font-size: 180%;
        font-family: "LEMON MILK";
    }
</style>
<body style="background-color: #272d39; background-image: url(/img/Background2.jpg); background-repeat: repeat-y;
 background-position: top; background-size: 80%">

    <h1>
        <u>Welcome Administrator</u>
    </h1>

    <#if message??>
    <div class="alert alert-success" style="color: black; font-size: 150%; font-family: 'LEMON MILK'; text-align: center;
        background-color: lightblue">
        ${message}
    </div>
    </#if>

    <div class="container" >
        <p>Create a new Game</p>
        <a href="/creategame" class="btn btn-success" role="button" style="color: #45cb85; font-size: 25px; font-family: LEMON MILK">Create Game</a>
        <br>
        <br>
        <p>Update Game</p>
        <a href="/updategame" class="btn btn-success" role="button" style="color: #45cb85; font-size: 25px; font-family: LEMON MILK">Update Game</a>
        <br>
        <br>
        <p>Delete Game</p>
        <a href="/deletegame" class="btn btn-success" role="button" style="color: #45cb85; font-size: 25px; font-family: LEMON MILK">Delete Game</a>
        <br>
        <br>
        <p>Manage Game Ranks</p>
        <a href="/manageranks" class="btn btn-success" role="button" style="color: #45cb85; font-size: 25px; font-family: LEMON MILK">Manage Ranks</a>
        <br>
        <br>
        <p>Leave session</p>
        <a href="/logout" class="btn btn-danger" role="button" style="color: darkred; font-size: 25px; font-family: LEMON MILK">Log Out</a>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>