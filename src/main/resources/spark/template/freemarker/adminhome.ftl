<!DOCTYPE html>
<html lang="en">
<head>
    <title>Welcome!</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
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

    button{
        background-color: #45cb85;
        border-color: #45cb85;
        font-size: 80%;
    }

    .hpl{
        color: darkgreen;
        font-family: "LEMON MILK";
        text-decoration: none;
    }

    .hpl1{
        color: black;
        font-family: "LEMON MILK";
        text-decoration: none;
    }
</style>
<body style="background-color: #272d39; background-image: url(/img/Background2.jpg); background-repeat: no-repeat;
 background-position: center; background-size: 85%">

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
        <button>
            <a href="/creategame" class="hpl">Create Game</a>
        </button>
        <p>Update Game</p>
        <button>
            <a href="/updategame" class="hpl">Update Game</a>
        </button>
        <p>Delete Game</p>
        <button>
            <a href="/deletegame" class="hpl">Delete Game</a>
        </button>
        <p>Manage Game Ranks</p>
        <button>
            <a href="/manageranks" class="hpl">Manage Ranks</a>
        </button>
        <p>Leave session</p>
        <button  style="font-size: 80%;background-color: #ff4655;border-color: #ff4655">
            <a href="/logout" class="hpl1">Log Out</a>
        </button>
    </div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
</body>
</html>