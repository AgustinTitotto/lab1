<#-- @ftlvariable name="matches" type="java.util.List<GamerUser>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Find players</title>
</head>
<style>
    p{
        color: #45cb85;
        text-align: center;
        margin-top: 100px;
        font-family: "LEMON MILK";
        font-size: 200%;
    }
</style>
<body style="background-color: #282e3a; background-image: url(/img/Background2.jpg); background-repeat: no-repeat;
 background-position: center; background-size: 85%">
<h1 style="color: white; font-size: 300%; text-align: center; font-family: 'LEMON MILK'">
    <u>See who do you match</u>
</h1>
    <p>View who you match with</p>
    <#list matches as match>
        <option style="margin-left: 625px; margin-right: 600px; color: #45cb85; font-size: 150%" value="${match.userName}">${match.userName}</option>
    </#list>

</body>
</html>