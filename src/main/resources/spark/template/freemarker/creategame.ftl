<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Game</title>
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
 background-position: center; background-size: 95%">
        <h1 style="color: white; font-size: 300%; text-align: center; font-family: 'LEMON MILK'">
                <u>Create New Game</u>
        </h1>
        <#if message??>
                <div class="alert alert-success" style="color: black; font-size: 150%; font-family: 'LEMON MILK';
             background-color: lightblue; text-align: center">
                        ${message}
                </div>
        </#if>

        <form class="container" action = "/creategame" role="form" method="post">
                <label for="gameNameId" style="font-size: 200%"> Game Name:</label><br>
                <input type="text" id="gameNameId" name="gameName" style="font-size: 120%; font-family: 'LEMON MILK'" required>
                <br>
                <label for="gameCategoryId" style="font-size: 200%"> Game Category:</label><br>
                <input type="text" id="gameCategoryId" name="gameCategory" style="font-size: 120%; font-family: 'LEMON MILK'" required>
                <br>
                <label for="gameLvlMaxId" style="font-size: 200%"> Game Max Lvl:</label><br>
                <input type="number" min="1" id="gameLvlMaxId" name="gameLvlMax" style="font-size: 120%; font-family: 'LEMON MILK'" required>
                <br>
                <label for="gameRanks" style="font-size: 200%"> Game Rank <i>(insert a(,)between each rank without spaces):</i></label><br>
                <input type="text" id="gameRanks" name="gameRanks" style="font-size: 120%; font-family: 'LEMON MILK'">
                <br>
                <br>
                <button type="submit" style="font-size: 150%; background-color: #45cb85; border-color: #45cb85;">
                    Create game
                </button>
                <!--onclick="createGame()"-->
                <!--poner todos en un div en ves de usar br-->
        </form>
        <button1>
                <a href="/admin" class="container"style="color: darkred; font-family: 'LEMON MILK';text-decoration: none;">Go back</a>
        </button1>

</body>

<script>
function createGame() {
    const gameName = document.getElementById("gameRanks")
    fetch("/game")
        .then(response => response.json())
        .then(data => console.log(data));
}

</script>

</html>