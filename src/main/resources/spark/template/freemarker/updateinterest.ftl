<#-- @ftlvariable name="interests" type="java.util.List<GamerInterest>" -->
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
        color: white;
        font-size: 300%;
        text-align: center;
        font-family: "LEMON MILK";
    }

    .content {
        padding: 20px 20px;
        height: 768px;
        margin-left: 250px;
        background-color: #272d39;
        background-image: url(/img/Background2.jpg);
        background-position: top;
        background-size: contain;
        background-repeat: repeat-y;
        text-align: center;
    }

    .container{
        font-size: 125%;
        color: #45cb85;
        text-align: left;
        padding-left: 100px;
        font-family: "LEMON MILK";
    }

    .sidebar {
        height: 100%;
        width: 250px;
        position: fixed;
        z-index: 1;
        top: 0;
        left: 0;
        overflow-x: hidden;
        padding-top: 20px;
        background-color: #272d39;
    }

    .sidebar a {
        padding: 16px;
        background-color: #45cb85;
        font-family: "LEMON MILK";
        color: green;
        display: block;
        font-size: 250%;
        text-decoration: none;
    }

    .sidebar a.active {
        background-color: #272d39;
        color: white;
        font-size: 250%;
        font-family: "LEMON MILK";
        text-decoration: none;
    }

    .sidebar a.leave {
        background-color: #ff4655;
        color: darkred;
        font-size: 250%;
        font-family: "LEMON MILK";
        text-decoration: none;
    }

    .sidebar a.active:hover{
        background-color: #45cb85;
        color: green;
        font-size: 250%;
    }

    @media screen and (max-width: 700px) {
        .sidebar {
            width: 100%;
            height: auto;
            position: relative;
        }
        .sidebar a {float: left;}
    }
    @media screen and (max-width: 400px) {
        .sidebar a {
            text-align: center;
            float: none;
        }
    }

</style>
<body>

<div class="sidebar">
    <a class="active" href="/home">Home</a>
    <a class="active" href="/profile">Profile</a>
    <a href="/manageinterest">Interests</a>
    <a class="active" href="/findplayers">Players</a>
    <a class="active" href="/viewmatch">Matches</a>
    <br>
    <br>
    <br>
    <a class="leave" href="/logout">Sign Out</a>
</div>

<div class="content">
    <h1>
        <u>What game interest</u><br>
        <u>would you like to change?</u>
    </h1>
    <#if message??>
        <div class="alert alert-success" style="color: black; font-size: 150%; font-family: 'LEMON MILK';
     background-color: lightblue; text-align: center">
            ${message}
        </div>
    </#if>
    <br>
    <br>
    <br>
    <form action="/updateinterest" role="form" method="post">
        <div class="container">
            <label for="gameName" style="font-size: 200%;font-family: 'LEMON MILK'"> Select Interest:</label>
            <input list="gameName" name="gameName" style="font-size: 150%; background-color: #45cb85; border-color: #45cb85; font-family: 'LEMON MILK'" required/>
            <datalist style="font-size: 150%; background-color: #45cb85; border-color: #45cb85; font-family: 'LEMON MILK'" name="gameName" id="gameName">
                <#list interests as interest>
                    <option value="${interest.game.gameName}">Game:${interest.game.gameName}, Lvl:${interest.lvl}, Rank:${interest.rank.rankName}</option>
                </#list>
            </datalist>
            <br>
            <label for="newLvlId" style="font-size: 200%"> New Lvl:</label>
            <input type="text" min="1" id="newLvlId" name="newLvl" style="font-size: 120%; font-family: 'LEMON MILK'">
            <br>
            <label for="newRank" style="font-size: 200%"> New Rank:</label>
            <input type="text" min="1" id="newRankId" name="newRank" style="font-size: 120%; font-family: 'LEMON MILK'">
            <br>
            <br>
        </div>
        <button type="submit" style="color: darkgreen; font-size: 150%; background-color: #45cb85; border-color: #45cb85;font-family: 'LEMON MILK'">
            Update interest
        </button>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
</body>
</html>