<#-- @ftlvariable name="games" type="java.util.List<Game>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Delete game</title>
</head>
<body style="background-color: #272d39; background-image: url(/img/Background2.jpg); background-repeat: no-repeat;
 background-position: center; background-size: 85%">
    <h1 style="color: white; font-size: 300%; font-family: 'LEMON MILK'; text-align: center"><u>Create a new rank</u></h1>
    <form class="container" style="text-align: center" action="/createrank" role="form" method="post">
        <input list="gameName" name="gameName" style="font-size: 150%; background-color: #45cb85; border-color: #45cb85; font-family: 'LEMON MILK'" required/>
        <datalist style="font-size: 150%; background-color: #45cb85; border-color: #45cb85; font-family: 'LEMON MILK'" name="gameName" id="gameName">
            <#list games as game>
                <option value="${game.gameName}">${game.gameName}</option>
            </#list>
        </datalist>
        <br>
        <br>
        <label for="newRank" style="font-size: 200%; color: #45cb85"> New Rank:</label>
        <br>
        <input type="text" id="newRank" name="newRank" style="font-size: 120%; font-family: 'LEMON MILK'" required>
        <br><br>
        <input style="font-size: 150%; background-color: #45cb85; border-color: #45cb85; font-family: 'LEMON MILK'; text-align: center" type="submit"
               value="Update" name="createRank">
    </form>

</body>
</html>