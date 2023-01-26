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

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
</body>
</html>