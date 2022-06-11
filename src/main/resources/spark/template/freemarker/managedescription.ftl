<#-- @ftlvariable name="descriptions" type="java.util.List<GamerDescription>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
</head>
<body>
    <form class="container" action="/managedescription" role="form" method="post">
        <select name="gamers" id="descriptionId">
            <#list descriptions as description>
                <option value="${description.game.gameName}, ${description.lvl}, ${description.rank.rankName}">
                    ${description.game.gameName}, ${description.lvl}, ${description.rank.rankName}</option>
            </#list>
        </select>
        <input type="submit" value="Submit">
    </form>
</body>
</html>
