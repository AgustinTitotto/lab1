<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Delete rank</title>
</head>

<body style="background-color: #272d39; background-image: url(/img/Background2.jpg); background-repeat: no-repeat;
 background-position: center; background-size: 85%">
<h1 style="color: white; font-size: 300%; font-family: 'LEMON MILK'; text-align: center"><u>Delete a rank</u></h1>
    <form class="container" style="text-align: center" action="/deleterank" role="form" method="post">
        <select style="font-size: 150%; background-color: #45cb85; border-color: #45cb85; font-family: 'LEMON MILK'" name="gameName" id="gameName">
            <#list games as game>
                <option value="${game.gameName}">${game.gameName}</option>
            </#list>
        </select>
        <br>
        <br>
        <label for="newRank" style="font-size: 200%; color: #45cb85"> Delete Rank:</label>
        <br>
        <input type="text" id="newRank" name="newRank" style="font-size: 120%; font-family: 'LEMON MILK'" required>
        <br><br>
        <input style="font-size: 150%; background-color: #45cb85; border-color: #45cb85; font-family: 'LEMON MILK'" type="submit"
               value="Delete" name="createRank">
    </form>

</body>
</html>