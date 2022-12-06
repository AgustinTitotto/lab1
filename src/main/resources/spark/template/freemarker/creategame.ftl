<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Game</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<style>
    .container{
            color: #45cb85;
        text-align: center;
        margin-top: 100px;
            font-family: "LEMON MILK";
    }
</style>
<body style="background-color: #272d39; background-image: url(/img/Background2.jpg); background-repeat: repeat-y;
 background-position: top; background-size: 80%">
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
                <input type="text" id="gameRanks" name="gameRanks" style="font-size: 120%; font-family: 'LEMON MILK'" required>
                <br>
                <br>
                <button type="submit" style="font-size: 150%; background-color: #45cb85; border-color: #45cb85; font-family: 'LEMON MILK'">
                    Create game
                </button>
                <br>
                <br>
                <button style="font-size: 150%;background-color: #ff4655;border-color: #ff4655">
                        <a href="/admin" style="text-decoration: none; color: black; font-family: 'LEMON MILK'">Go back</a>
                </button>
                <!--onclick="createGame()"-->
                <!--poner todos en un div en ves de usar br-->
        </form>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
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