<#-- @ftlvariable name="matches" type="java.util.List<GamerUser>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Find players</title>
</head>
<style>
    body{
        background-color: #282e3a;
        background-image: url(/img/Background2.jpg);
        background-position: top;
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
        margin: 0;
        padding: 0;
        width: 200px;
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

    p{
        color: #45cb85;
        text-align: center;
        margin-top: 100px;
        font-family: "LEMON MILK";
        font-size: 200%;
    }
</style>
<body style="background-color: #282e3a; background-image: url(/img/Background2.jpg);
 background-position: top; background-size: 85%">
<#if message??>
    <div class="alert alert-success" style="margin-left: 625px; margin-right: 600px; color: #45cb85; font-size: 150%; font-family: 'LEMON MILK'; text-align: center">
        ${message}
    </div>
</#if>

<div class="sidebar">
    <a href="/home">Home</a>
    <a href="/profile" class="hpl">Profile</a>
    <a href="/manageinterest">Interests</a>
    <a href="/findplayers">Players</a>
    <a class="active" href="#viewmatch" class="hpl">Matches</a>
    <a class="leave" href="/logout" class="hpl">Sign Out</a>
</div>

<h1 style="color: white; font-size: 300%; text-align: center; font-family: 'LEMON MILK'">
    <u>View who you match with</u>
</h1>

    <p>Your matches are</p>

    <#if matches??>
    <#list matches as match>
        <option style="margin-left: 625px; margin-right: 600px; color: #45cb85; font-size: 150%; font-family: 'LEMON MILK'" value="${match.userName}">${match.userName}</option>
    </#list>
    </#if>
</body>
</html>