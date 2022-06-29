<#-- @ftlvariable name="descriptions" type="java.util.List<GamerDescription>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
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

    p1{
        font-size: 120%;
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
<!-- <a id="user"></a>-->
<div class="sidebar">
    <a href="/home" class="hpl">Home</a>
    <a href="/profile" class="hpl">Profile</a>
    <a href="/manageinterest" class="hpl">Interests</a>
    <a href="/findplayers" class="hpl">Players</a>
    <a href="/viewmatch" class="hpl">Matches</a>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <a class="leave" href="/logout" class="hpl1">Sign Out</a>
</div>
<h1>
    <u>What dsecription would you</u><br>
    <u>like to delete?</u>
</h1>

    <form class="container" action="/deletedescription" role="form" method="post">
        <select style="font-size: 150%; background-color: #45cb85; border-color: #45cb85; font-family: 'LEMON MILK'" name="gamers" id="descriptionId">
            <#list descriptions as description>
                <option value="${description.game.gameName}, ${description.lvl}, ${description.rank.rankName}">
                    Game: ${description.game.gameName}, Lvl: ${description.lvl}, Rank: ${description.rank.rankName}</option>
            </#list>
        </select>
        <br>
        <br>
        <br>
        <br>
        <input style="font-size: 150%; background-color: #45cb85; border-color: #45cb85; font-family: 'LEMON MILK'" type="submit" value="Delete">
    </form>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
</body>
</html>
