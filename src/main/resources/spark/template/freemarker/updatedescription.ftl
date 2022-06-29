<#-- @ftlvariable name="descriptions" type="java.util.List<GamerDescription>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Description</title>
</head>
<style>
    body{
        background-color: #282e3a;
        background-image: url(/img/Background2.jpg);
        background-position: right;
        background-size: 81%;
        background-repeat: no-repeat
    }

    h1{
        color: white;
        font-size: 300%;
        text-align: center;
        font-family: 'LEMON MILK';
        margin-left: 150px;
    }

    .container{
        font-size: 150%;
        color: #45cb85;
        margin-left: 150px;
        text-align: center;
    }

    .sidebar {
        margin-top: -30px;
        margin-left: -15px;
        padding: 0;
        background-color: #282e3a;
        width: 250px;
        position: fixed;
        height: 100%;
        overflow: auto;
    }

    .sidebar a {
        display: block;
        color: white;
        padding: 16px;
        font-size: 295%;
        text-decoration: none;
    }

    .sidebar a.active {
        background-color: #45cb85;
        color: green;
        font-size: 295%;
        font-family: "LEMON MILK";
        text-decoration: none;
    }

    .sidebar a.leave {
        background-color: #ff4655;
        color: darkred;
        font-size: 295%;
        font-family: "LEMON MILK";
        text-decoration: none;
    }

    .sidebar a:hover:not(.active) {
        background-color: #45cb85;
        color: green;
        font-size: 295%;
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

<#if message??>
    <div class="alert alert-success" style="color: black; font-size: 150%; font-family: 'LEMON MILK';
     background-color: lightblue; text-align: center">
        ${message}
    </div>
</#if>

<div class="sidebar">
    <a href="/home">Home</a>
    <a class="active" href="/profile" class="hpl">Profile</a>
    <a href="/manageinterest">Interests</a>
    <a href="/findplayers">Players</a>
    <a href="/viewmatch" class="hpl">Matches</a>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <a class="leave" href="/logout" class="hpl">Sign Out</a>
</div>
<h1>
    <u>What game description</u><br>
    <u>would you like to change?</u>
</h1>
<form class="container" action="/updatedescription" role="form" method="post">
    <select style="font-size: 150%; background-color: #45cb85; border-color: #45cb85; font-family: 'LEMON MILK'" name="gameName" id="gameName">
        <#list descriptions as description>
        <option value="${description.game.gameName}">${description.game.gameName}</option>
    </#list>
    </select>
    <br>
    <br>
    <label for="newLvlId" style="font-size: 200%"> New Lvl:</label>
    <br>
    <input type="text" min="1" id="newLvlId" name="newLvl" style="font-size: 120%; font-family: 'LEMON MILK'" required>
    <br>
    <label for="newRank" style="font-size: 200%"> New Rank:</label>
    <br>
    <input type="text" min="1" id="newRankId" name="newRank" style="font-size: 120%; font-family: 'LEMON MILK'" required>
    <br>
    <br>
    <input style="font-size: 150%; background-color: #45cb85; border-color: #45cb85; font-family: 'LEMON MILK'" type="submit" value="Update">
</form>

<br><br><br><br><br><br>
</body>
</html>