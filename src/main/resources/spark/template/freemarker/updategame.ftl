<#-- @ftlvariable name="games" type="java.util.List<Game>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update game</title>
</head>
<body>
    <form class="container" action="/updategame" role="form" method="post">
        <select name="gameName" id="gameName">
            <#list games as game>
                <option value="${game.gameName}">${game.gameName}</option>
            </#list>
        </select>
        <br>
        <label for="newCategory" style="font-size: 200%"> New Category:</label>
        <br>
        <input type="text" id="newCategory" name="newCategory" style="font-size: 120%">
        <br>
        <label for="newLvlId" style="font-size: 200%"> New Max Lvl:</label>
        <br>
        <input type="text" min="1" id="newLvlId" name="newMaxLvl" style="font-size: 120%">
        <br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>