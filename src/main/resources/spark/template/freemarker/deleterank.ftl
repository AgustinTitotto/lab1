<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Delete rank</title>
</head>

<body>
    <form class="container" action="/deleterank" role="form" method="post">
        <select style="font-size: 150%; background-color: #45cb85; border-color: #45cb85; font-family: 'LEMON MILK'" name="gameName" id="gameName">
            <#list games as game>
                <option value="${game.gameName}">${game.gameName}</option>
            </#list>
        </select>
        <br>
        <br>
        <label for="newRank" style="font-size: 200%"> Delete Rank:</label>
        <br>
        <input type="text" id="newRank" name="newRank" style="font-size: 120%; font-family: 'LEMON MILK'" required>

        <input style="font-size: 150%; background-color: #45cb85; border-color: #45cb85; font-family: 'LEMON MILK'" type="submit"
               value="Update" name="createRank">
    </form>

</body>
</html>