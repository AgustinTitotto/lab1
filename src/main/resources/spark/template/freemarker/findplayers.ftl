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
        background-position: top;
        background-repeat: no-repeat;
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
        text-decoration: none;
    }

    .sidebar a.active {
        background-color: #45cb85;
        color: green;
        font-family: "LEMON MILK";
        text-decoration: none;
    }

    .sidebar a.leave {
        background-color: #ff4655;
        color: darkred;
        font-family: "LEMON MILK";
        text-decoration: none;
    }

    .sidebar a:hover:not(.active) {
        background-color: #45cb85;
        color: green;
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
<h1>
    <u>Meet the people who play like you</u>
</h1>
<div class="sidebar">
    <a href="/home">Home</a>
    <a href="/profile" class="hpl">Profile</a>
    <a a href="/manageinterest">Interests</a>
    <a class="active" href="#findplayers">Players</a>
    <a href="/viewmatch" class="hpl">Matches</a>
    <a class="leave" href="/logout" class="hpl">Sign Out</a>
</div>
<br>
<form class="container" action="/findplayers" role="form" method="post">
    <select style="font-size: 150%; background-color: #45cb85; border-color: #45cb85;" name="gamers" id="gamers">
        <#list descriptions as description>
            <option value="${description.gamerUser.userName}, ${description.game.gameName}, ${description.lvl}, ${description.rank.rankName}">
                ${description.gamerUser.userName} - ${description.game.gameName} - ${description.lvl} - ${description.rank.rankName}</option>
        </#list>
    </select>
    <br>
    <br>
    <input style="font-size: 150%; background-color: #45cb85; border-color: #45cb85;" type="submit" value="Submit">
    <br>
    <br>
    <br>
</form>
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
