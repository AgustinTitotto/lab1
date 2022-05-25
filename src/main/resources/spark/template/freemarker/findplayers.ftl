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
<body style="background-color: #282e3a">

    <form class="container" action="/findplayers" role="form" method="post">
        <select name="gamers" id="gamers">
            <#list descriptions as description>
                <option value="${description.gamerUser.userName}, ${description.game.gameName}, ${description.lvl}, ${description.rank.rankName}">
                    ${description.gamerUser.userName} - ${description.game.gameName} - ${description.lvl} - ${description.rank.rankName}</option>
            </#list>
        </select>
        <br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>

