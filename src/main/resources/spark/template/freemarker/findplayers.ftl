<#-- @ftlvariable name="descriptions" type="java.util.List<GamerDescription>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Find players</title>
</head>
<style>
    h1{
        color: white;
        font-size: 300%;
        text-align: center;
    }

    .container{
        font-size: 150%;
        color: #45cb85;
        text-align: center;
    }

    p1{
        font-size: 120%;
    }

</style>
<body style="background-color: #282e3a; background-image: url(/img/Background2.jpg); background-repeat: no-repeat;
background-position: center; background-size: 100%">
<h1 style="color: white; font-size: 300%; text-align: center; font-family: 'LEMON MILK'">
    <u>Meet the people who play like you</u>
</h1>
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
</html>

