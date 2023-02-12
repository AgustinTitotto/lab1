<#-- @ftlvariable name="descriptions" type="java.util.List<GamerDescription>" -->
<#-- @ftlvariable name="image" type="java.lang.String" -->
<#-- @ftlvariable name="notifications" type="java.util.List<Notification>" -->
<#-- @ftlvariable name="stats" type="lab1.meetNGame.model.Stats" -->
<#-- @ftlvariable name="username" type="java.lang.String" -->
<#import "userMasterTemplate.ftl" as layout />

<@layout.userMasterTemplate title="ViewPlayerProfile">

    <h1><u>${username} profile</u></h1>

    <#if message??>
        <div class="alert alert-success" style="color: black; font-family: 'LEMON MILK';
         background-color: lightblue; text-align: center; font-size: 150%">
            ${message}
        </div>
    </#if>

    <div class="container d-flex justify-content-center align-items-center" style="height: 500px; font-family: 'LEMON MILK'">
        <div class="row w-100 text-center">
            <div class="col-12 col-md-6 p-2">
                <div class="card">
                    <div class="card-body">
                        <p class="card-text">${username} is currently playing ${stats.descriptions} games </p>
                    </div>
                </div>
            </div>
            <div class="col-12 col-md-6 p-2">
                <div class="card">
                    <div class="card-body">
                        <p class="card-text">${username} has liked ${stats.likes} description from other users </p>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
            </div>
            <div class="col-12 col-md-6 p-2">
                <div class="card">
                    <div class="card-body">
                        <p class="card-text">${username} has successfully matched with ${stats.matches} other players </p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="container text-center">
        <#list descriptions>
            <h4 class="profileSubT pb-3">
                Here are ${username} game descriptions
            </h4>
            <#items as description>
                <div class="row media-body my-3 position-relative" style="color: white; font-family: 'LEMON MILK'">
                    <div class="col">
                        <h4>Game: ${description.game.gameName}</h4>
                        lvl: ${description.lvl}
                        <br>
                        rank: ${description.rank.rankName}
                    </div>
                </div>
                <hr style="height: 10px; color: white">
            </#items>
        <#else>
            <h4 class="profileSubT">
                You have no descriptions
            </h4>
        </#list>

    </div>

</@layout.userMasterTemplate>



<!--<!DOCTYPE html>
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

    .container {
        font-size: 150%;
        color: #45cb85;
        text-align: center;
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
    <a class="active" href="/manageinterest">Interests</a>
    <a class="active" href="/findplayers">Players</a>
    <a href="/viewmatch">Matches</a>
    <br>
    <br>
    <br>
    <a class="leave" href="/logout">Sign Out</a>
</div>

<div class="content">
        <#list descriptions as description>
            <option value="${description.game.gameName}, ${description.lvl}, ${description.rank.rankName}">
                Game: ${description.game.gameName}, Lvl: ${description.lvl}, Rank: ${description.rank.rankName}</option>
        </#list>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
</body>
</html>
-->