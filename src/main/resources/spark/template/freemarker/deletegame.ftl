<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Delete game</title>
</head>
<body>
    <form class="container" action="/deletegame" role="form" method="post">
        <select name="gameName" id="gameName">
            <#list games as game>
                <option value="${game.gameName}">${game.gameName}</option>
            </#list>
        </select>
        <input type="submit" value="Submit">
    </form>
</body>
</html>