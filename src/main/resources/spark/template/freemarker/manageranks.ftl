<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Delete game</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
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
    h1{
        font-size: 300%;
        color: white;
        text-align: center;
        font-family: "LEMON MILK";
        margin-top: 20px;
    }

</style>
<body style="background-color: #282e3a; background-image: url(/img/Background2.jpg); background-repeat: repeat-y;
 background-position: top; background-size: 80%">

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
        <a href="/createrank" class="btn btn-success" role="button" style="color: #45cb85; font-size: 25px; font-family: LEMON MILK">Create new Rank</a>
        <br>
        <br>
        <p>Delete Rank</p>
        <a href="/deleterank" class="btn btn-success" role="button" style="color: #45cb85; font-size: 25px; font-family: LEMON MILK">Delete Rank</a>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>