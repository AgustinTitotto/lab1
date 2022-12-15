<#-- @ftlvariable name="descriptions" type="java.util.List<GamerDescription>" -->
<#-- @ftlvariable name="userNames" type="java.util.List<String>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <title>View Players</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<style>

    h1{
        color: white;
        font-size: 300%;
        text-align: center;
        font-family: "LEMON MILK";
    }

    p{
        font-size: 120%;
        text-align: center;
        font-family: "LEMON MILK";
    }

    .content {
        padding: 20px 20px;
        height: 100vh;
        margin-left: 250px;
        background-color: #272d39;
        background-image: url(/img/Background2.jpg);
        background-position: top;
        background-size: contain;
        background-repeat: repeat-y;
        text-align: center;
    }

    .container{
        font-size: 125%;
        color: #45cb85;
        font-family: "LEMON MILK";
    }

    .sidebar {
        height: 100%;
        width: 250px;
        position: fixed;
        overflow: auto;
        margin: 0;
        padding: 0;
        background-color: #272d39;
    }

    .sidebar a {
        padding: 16px;
        background-color: #45cb85;
        font-family: "LEMON MILK";
        color: green;
        display: block;
        font-size: 250%;
        text-decoration: none;
    }

    .sidebar a.active {
        background-color: #272d39;
        color: white;
        font-size: 250%;
        font-family: "LEMON MILK";
        text-decoration: none;
    }

    .sidebar a.leave {
        background-color: #ff4655;
        color: darkred;
        font-size: 250%;
        font-family: "LEMON MILK";
        text-decoration: none;
    }

    .sidebar a.active:hover{
        background-color: #45cb85;
        color: green;
        font-size: 250%;
    }

    @media screen and (max-width: 700px) {
        .sidebar {
            width: 100%;
            height: auto;
            position: relative;
        }
        .sidebar a {float: left;}
    }
    @media screen and (max-width: 400px) {
        .sidebar a {
            text-align: center;
            float: none;
        }
    }
</style>
<body>
<!-- <a id="user"></a>-->
<div class="sidebar">
    <a class="active" href="/home">Home</a>
    <a class="active" href="/profile">Profile</a>
    <a class="active" href="/manageinterest">Interests</a>
    <a href="/findplayers">Players</a>
    <a class="active" href="/viewmatch">Matches</a>
    <br>
    <br>
    <a class="leave" href="/logout">Sign Out</a>
</div>

<div class="content">
    <h1>
        <u>Meet the people who would</u><br>
        <u>like to play with you</u>
    </h1>
    <br>
    <form class="container" action="/findplayers" role="form" method="post">
        <br>
        <div id="carouselExampleControls" class="carousel slide">
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <h1 name="userName" id="userName"></h1>
                    <p>Game:
                        <span name="gameName" id="gameName"></span>
                    </p>
                    <p>Level:
                        <span name="level" id="level"></span>
                    </p>
                    <p>Rank:
                        <span name="rankName" id="rankName"></span>
                    </p>
                </div>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev" name="backButton" id="backButton">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next" name="nextButton" id="nextButton">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
            </button>
        </div>
        <br>
        <br>
        <input name="gamers" id="gamers" readonly>
        <br>
        <br>
        <button type="submit" style="font-size: 150%; background-color: #45cb85; border-color: #45cb85;font-family: 'LEMON MILK'">
        Like
        </button>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
<script type="text/javascript">
    let counter = 0;
    let name = new Map;
    let game = new Map;
    let level = new Map;
    let rank = new Map;
    const displayName = document.querySelector('#userName');
    const displayGame = document.querySelector('#gameName');
    const displayLevel = document.querySelector('#level');
    const displayRank = document.querySelector('#rankName');
    const input = document.querySelector('#gamers');
    <#list descriptions as description>
        userName = '${description.gamerUser.userName}';
        gameName = '${description.game.gameName}';
        gameLevel = ${description.lvl};
        gameRank = '${description.rank.rankName}';
        name.set(counter,userName);
        game.set(counter,gameName);
        level.set(counter,gameLevel);
        rank.set(counter,gameRank);
        counter += 1;
    </#list>
    let newCounter = 0;
    const next = document.querySelector('#nextButton');
    const back = document.querySelector('#backButton');
    next.addEventListener('click', () =>{
        newCounter += 1;
        if (newCounter >= counter){
            newCounter = newCounter - counter;
        }
        const nameOutput = name.get(newCounter);
        const gameOutput = game.get(newCounter);
        const levelOutput = level.get(newCounter);
        const rankOutput = rank.get(newCounter);
        displayName.textContent = nameOutput;
        displayGame.textContent = gameOutput;
        displayLevel.textContent = levelOutput;
        displayRank.textContent = rankOutput;
        input.value = ""+nameOutput+", "+gameOutput+", "+levelOutput+", "+rankOutput;
    })
    back.addEventListener('click', () =>{
        newCounter -= 1;
        if (newCounter < 0){
            newCounter = counter + newCounter;
        }
        const nameOutput = name.get(newCounter);
        const gameOutput = game.get(newCounter);
        const levelOutput = level.get(newCounter);
        const rankOutput = rank.get(newCounter);
        displayName.textContent = nameOutput;
        displayGame.textContent = gameOutput;
        displayLevel.textContent = levelOutput;
        displayRank.textContent = rankOutput;
        input.value = ""+nameOutput+", "+gameOutput+", "+levelOutput+", "+rankOutput;
    })
    displayName.textContent = name.get(0);
    displayGame.textContent = game.get(0);
    displayLevel.textContent = level.get(0);
    displayRank.textContent = rank.get(0);
    input.value = ""+name.get(0)+", "+game.get(0)+", "+level.get(0)+", "+rank.get(0);
</script>
</html>
<!--  -->
