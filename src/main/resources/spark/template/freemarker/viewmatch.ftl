<#-- @ftlvariable name="matches" type="java.util.List<GamerUser>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Find players</title>
</head>
<body>
    <p>View who you match with</p>
    <#list matches as match>
        <option value="${match.userName}">${match.userName}</option>
    </#list>

</body>
</html>