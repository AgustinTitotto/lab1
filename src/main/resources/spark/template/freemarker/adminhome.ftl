<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
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

</body>
</html>