<#-- @ftlvariable name="descriptions" type="java.util.List<GamerDescription>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
</head>
<style>
    .container{
        color: #45cb85;
        text-align: center;
        margin-top: 100px;
        font-family: "LEMON MILK";
    }
</style>
<body style="background-color: #282e3a; background-image: url(/img/Background2.jpg); background-repeat: no-repeat;
 background-position: center; background-size: 85%">
<h1 style="color: white; font-size: 300%; text-align: center; font-family: 'LEMON MILK'">
    <u>What game do you like to delete?</u>
</h1>
    <form class="container" action="/managedescription" role="form" method="post">
        <select style="font-size: 150%; background-color: #45cb85; border-color: #45cb85;" name="gamers" id="descriptionId">
            <#list descriptions as description>
                <option value="${description.game.gameName}, ${description.lvl}, ${description.rank.rankName}">
                    ${description.game.gameName}, ${description.lvl}, ${description.rank.rankName}</option>
            </#list>
        </select>
        <br>
        <br>
        <input style="font-size: 150%; background-color: #45cb85; border-color: #45cb85;" type="submit" value="Delete">
    </form>
</body>
</html>
