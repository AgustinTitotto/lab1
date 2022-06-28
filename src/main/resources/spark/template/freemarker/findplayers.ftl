<#-- @ftlvariable name="descriptions" type="java.util.List<GamerDescription>" -->
<#-- @ftlvariable name="userNames" type="java.util.List<String>" -->
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
        background-position: right;
        background-repeat: no-repeat;
        background-size: 85%;
    }

    h1{
        color: white;
        font-size: 300%;
        text-align: center;
        font-family: 'LEMON MILK';
        margin-left: 120px;
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
        width: 200px;
        background-color: #282e3a;
        position: fixed;
        height: 100%;
        overflow: auto;
    }

    .sidebar a {
        display: block;
        color: white;
        padding: 16px;
        font-size: 250%;
        text-decoration: none;
        font-family: "LEMON MILK";
    }

    .sidebar a.active {
        background-color: #45cb85;
        color: green;
        font-size: 250%;
        font-family: "LEMON MILK";
        text-decoration: none;
    }

    .sidebar a.leave {
        background-color: #ff4655;
        color: darkred;
        font-size: 250%;
        alignment: bottom;
        font-family: "LEMON MILK";
        text-decoration: none;
    }

    .sidebar a:hover:not(.active) {
        background-color: #45cb85;
        color: green;
        font-size: 250%;
        font-family: "LEMON MILK";
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
    <a class="leave" href="/logout" class="hpl1">Sign Out</a>
</div>
<h1>
    <u>Meet the people who would</u><br>
    <u>like to play with you</u>
</h1>

<br>
<form class="container" action="/findplayers" role="form" method="post">
    <select style="margin-left: 120px; font-size: 150%; background-color: #45cb85; border-color: #45cb85;" name="gamers" id="gamers">
        <#list descriptions as description>
            <option value="${description.gamerUser.userName}, ${description.game.gameName}, ${description.lvl}, ${description.rank.rankName}">
                ${description.gamerUser.userName} - ${description.game.gameName} - ${description.lvl} - ${description.rank.rankName}</option>
        </#list>
    </select>
    <br>
    <br>
    <input style="margin-left: 120px; font-size: 150%; background-color: #45cb85; border-color: #45cb85;" type="submit" value="Like">
    <br>
    <br>
    <br>
</form>
<br><br><br><br><br><br><br><br><br><br><br><br><br>
</body>
<!--<script>
    let arr = [<#list userNames as userName>"${userName}", </#list>]
    let desc = [<#list descriptions as description>"${description.game.gameName}", </#list>]
    let a = arr[0]
    document.write(a)
    document.getElementById("user").innerHTML = a;
</script> -->
</html>
<!--  -->
