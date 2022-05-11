<#-- @ftlvariable name="gamers" type="java.util.List<GamerUser>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Find players</title>
</head>
<body>

    <select name="gamers" id="gamers">
        <#list gamers as gamerUser>
            <option value="${gamerUser.userName}">${gamerUser.userName}</option>
        </#list>
    </select>

</body>
</html>
