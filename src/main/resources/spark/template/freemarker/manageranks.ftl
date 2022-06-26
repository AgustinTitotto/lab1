<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Delete game</title>
</head>
<style>
    .container{
        position: absolute;
        text-align: center;
        color: #45cb85;
        font-size: 180%;
        font-family: "LEMON MILK";
        top: 40%;
        left: 50%;
        transform: translate(-50%, -50%);
    }
    button{
        background-color: #45cb85;
        border-color: #45cb85;
        font-size: 80%;
    }
    .hpl{
        color: green;
        font-family: "LEMON MILK";
        text-decoration: none;
    }
    h1{
        font-size: 300%;
        color: white;
        text-align: center;
        font-family: "LEMON MILK";
        margin-top: 20px;
    }

</style>
<body style="background-color: #282e3a; background-image: url(/img/Background2.jpg); background-repeat: repeat;
 background-position: top; background-size: 85%">

    <h1>
        <u>Manage Game Ranks</u>
    </h1>
    <#if message??>
        <div class="alert alert-success" style="color: black; font-size: 150%; font-family: 'LEMON MILK'; text-align: center;
            background-color: lightblue">
            ${message}
        </div>
    </#if>

    <div class="container">
        <p>Create new Rank</p>
        <button>
            <a href="/createrank" class="hpl">Create new Rank</a>
        </button>
        <p>Delete Rank</p>
        <button>
            <a href="/deleterank" class="hpl">Delete Rank</a>
        </button>
    </div>
</body>
</html>