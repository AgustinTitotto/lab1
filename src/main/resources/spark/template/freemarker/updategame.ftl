<#-- @ftlvariable name="games" type="java.util.List<Game>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update game</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<style>
    .container{
        color: #45cb85;
        text-align: center;
        margin-top: 100px;
        font-family: "LEMON MILK";
    }
</style>
<body style="background-color: #282e3a; background-image: url(/img/Background2.jpg); background-repeat: repeat-y;
 background-position: top; background-size: 80%">
    <h1 style="color: white; font-size: 300%; text-align: center; font-family: 'LEMON MILK'">
        <u>What game would you </u><br>
        <u>like to change?</u>
    </h1>
    <#if message??>
        <div class="alert alert-success" style="color: black; font-size: 150%; font-family: 'LEMON MILK';
             background-color: lightblue; text-align: center">
            ${message}
        </div>
    </#if>
    <form class="container" action="/updategame" role="form" method="post">
        <select style="font-size: 150%; background-color: #45cb85; border-color: #45cb85; font-family: 'LEMON MILK'" name="gameName" id="gameName">
            <#list games as game>
                <option value="${game.gameName}">${game.gameName}</option>
            </#list>
        </select>
        <br>
        <br>
        <p>Your current Category is:
            <span name="previousCategory" id="previousCategory"></span>
        </p>
        <label for="newCategory" style="font-size: 200%"> New Category:</label>
        <br>
        <input type="text" id="newCategory" name="newCategory" style="font-size: 120%; font-family: 'LEMON MILK'">
        <br>
        <br>
        <p>Your current Max Level is:
            <span name="previousLvl" id="previousLvl"></span>
        </p>
        <label for="newLvlId" style="font-size: 200%"> New Max Lvl:</label>
        <br>
        <input type="text" min="1" id="newLvlId" name="newMaxLvl" style="font-size: 120%; font-family: 'LEMON MILK'">
        <br>
        <br>
        <input style="font-size: 150%; background-color: #45cb85; border-color: #45cb85; font-family: 'LEMON MILK'" type="submit" value="Update">
    </form>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <script type="text/javascript">
        const selectElement = document.querySelector('#gameName');
        let level = document.querySelector('#previousLvl');
        let category = document.querySelector('#previousCategory');
        selectElement.addEventListener('change', () =>{
            let levelMap = new Map();
            let categoryMap = new Map();
            let gameName;
            let gameLevel;
            let gameCategory;
            <#list games as game>
            gameName = '${game.gameName}';
            gameLevel = ${game.lvlMAX};
            gameCategory = '${game.category}';
            levelMap.set(gameName,gameLevel);
            categoryMap.set(gameName,gameCategory);
            </#list>
            const levelOutput = levelMap.get(selectElement.value);
            const gameOutput = categoryMap.get(selectElement.value);
            level.textContent = levelOutput;
            category.textContent = gameOutput;
        });
        selectElement.addEventListener('click', () =>{
            let levelMap = new Map();
            let categoryMap = new Map();
            let gameName;
            let gameLevel;
            let gameCategory;
            <#list games as game>
            gameName = '${game.gameName}';
            gameLevel = ${game.lvlMAX};
            gameCategory = '${game.category}';
            levelMap.set(gameName,gameLevel);
            categoryMap.set(gameName,gameCategory);
            </#list>
            const levelOutput = levelMap.get(selectElement.value);
            const gameOutput = categoryMap.get(selectElement.value);
            level.textContent = levelOutput;
            category.textContent = gameOutput;
        })
    </script>
</body>
</html>