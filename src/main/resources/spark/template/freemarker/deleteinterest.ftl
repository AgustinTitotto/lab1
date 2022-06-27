<#-- @ftlvariable name="interests" type="java.util.List<GamerDescription>" -->
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
        background-repeat: no-repeat;
        background-size: 85%;
    }

    h1{
        color: white;
        font-size: 300%;
        text-align: center;
        font-family: 'LEMON MILK';
    }

    .container{
        font-size: 150%;
        color: #45cb85;
        text-align: center;
    }

    p1{
        font-size: 120%;
    }

    .sidebar {
        margin-top: -40px;
        padding: 0;
        width: 205px;
        background-color: #282e3a;
        position: fixed;
        height: 100%;
        overflow: auto;
    }

    .sidebar a {
        display: block;
        color: white;
        padding: 16px;
        font-size: 290%;
        text-decoration: none;
    }

    .sidebar a.active {
        background-color: #45cb85;
        color: green;
        font-size: 290%;
        font-family: "LEMON MILK";
        text-decoration: none;
    }

    .sidebar a.leave {
        background-color: #ff4655;
        color: darkred;
        font-size: 290%;
        font-family: "LEMON MILK";
        text-decoration: none;
    }

    .sidebar a:hover:not(.active) {
        background-color: #45cb85;
        color: green;
        font-size: 290%;
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

    .container{
        color: #45cb85;
        text-align: center;
        margin-top: 100px;
        font-family: "LEMON MILK";
    }
</style>
<body>
<div class="sidebar">
    <a href="/home">Home</a>
    <a href="/profile" class="hpl">Profile</a>
    <a class="active" href="/manageinterest">Interests</a>
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
    <u>What interest would you</u><br>
    <u>like to delete?</u>
</h1>

    <form class="container" action="/deleteinterest" role="form" method="post">
        <select style="font-size: 150%; background-color: #45cb85; border-color: #45cb85; font-family: 'LEMON MILK'" name="gamers" id="interestId">
            <#list interests as interest>
                <option value="${interest.game.gameName}, ${interest.lvl}, ${interest.rank.rankName}">
                    ${interest.game.gameName}, ${interest.lvl}, ${interest.rank.rankName}</option>
            </#list>
        </select>
        <br>
        <br>
        <input style="font-size: 150%; background-color: #45cb85; border-color: #45cb85; font-family: 'LEMON MILK'" type="submit" value="Delete">
    </form>
<br><br><br><br><br><br><br><br><br><br><br><br>
</body>
</html>
