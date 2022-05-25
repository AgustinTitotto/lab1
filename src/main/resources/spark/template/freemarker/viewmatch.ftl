<#-- @ftlvariable name="matches" type="java.util.List<Match>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Find players</title>
</head>
<body>
    <p>View who you match with</p>
    <#list matches as match>
        <option value="${match.user2.userName}">${match.user2.userName}</option>
    </#list>

</body>
</html>