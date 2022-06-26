<#-- @ftlvariable name="descriptions" type="java.util.List<GamerDescription>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Description</title>
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
 background-position: top; background-size: 95%">
<h1 style="color: white; font-size: 300%; text-align: center; font-family: 'LEMON MILK'">
    <u>What game description would you </u><br>
    <u>like to change?</u>
</h1>
<form class="container" action="/updatedescription" role="form" method="post">
    <select style="font-size: 150%; background-color: #45cb85; border-color: #45cb85; font-family: 'LEMON MILK'" name="gameName" id="gameName">
        <#list descriptions as description>
        <option value="${game.gameName}">${game.gameName}</option>
    </#list>
    </select>
    <br>
    <br>
    <label for="newLvlId" style="font-size: 200%"> New Max Lvl:</label>
    <br>
    <input type="text" min="1" id="newLvlId" name="newMaxLvl" style="font-size: 120%; font-family: 'LEMON MILK'">
    <br>
    <br>
    <input style="font-size: 150%; background-color: #45cb85; border-color: #45cb85; font-family: 'LEMON MILK'" type="submit" value="Update">
</form>
</body>
</html>