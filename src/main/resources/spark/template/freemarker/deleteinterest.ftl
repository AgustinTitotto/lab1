<#-- @ftlvariable name="interests" type="java.util.List<GamerDescription>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
</head>
<body>
    <form class="container" action="/deleteinterest" role="form" method="post">
        <select name="gamers" id="interestId">
            <#list interests as interest>
                <option value="${interest.game.gameName}, ${interest.lvl}, ${interest.rank.rankName}">
                    ${interest.game.gameName}, ${interest.lvl}, ${interest.rank.rankName}</option>
            </#list>
        </select>
        <input type="submit" value="Submit">
    </form>
</body>
</html>
