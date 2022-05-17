<#-- @ftlvariable name="descriptions" type="java.util.List<GamerDescription>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Find players</title>
</head>

<body>

    <#list descriptions as description>
        <p>${description.gamerUser.userName}
    </#list>

    <#list descriptions>
        <table>
            <#items as description>
            <tr class="${description.gamerUser.userName}Row">
                <td>${description?counter}
                <td>${description.gamerUser.userName}
            </#items>
        </table>
    </#list>

    <#if >
        <#assign description = descriptions[0]>
        <p>${description.gamerUser.userName}</p>
    </#if>


</body>
</html>
