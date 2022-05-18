<#-- @ftlvariable name="descriptions" type="java.util.List<GamerDescription>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Find players</title>
</head>
<style>
    h1{
        color: white;
        font-size: 300%;
        text-align: center;
    }

    .container{
        font-size: 150%;
        color: #45cb85;
        text-align: center;
    }

    p1{
        font-size: 120%;
    }

</style>
<body style="background-color: #282e3a">
<h1><u>Meet players who match your interests</u></h1>

<div class="container">
    <p1><u>User Name</u></p1>
    <p>${descriptions[0].gamerUser.userName}</p>
    <p1><u>Game</u></p1>
    <p>${descriptions[0].game.gameName}</p>
    <p1><u>Level</u></p1>
    <p>${descriptions[0].lvl}</p>
    <p1><u>Rank</u></p1>
    <p>${descriptions[0].rank.rankName}</p>
</div>

</body>
</html>

