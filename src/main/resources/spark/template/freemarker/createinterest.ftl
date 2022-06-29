<!DOCTYPE html>
<html lang="en">
<head>
    <title>Create Interests</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<style>

    h1{
        color: white;
        font-size: 300%;
        text-align: center;
        font-family: "LEMON MILK";
    }

    .content {
        padding: 20px 20px;
        height: 768px;
        margin-left: 250px;
        background-color: #272d39;
        background-image: url(/img/Background2.jpg);
        background-position: top;
        background-size: contain;
        background-repeat: repeat-y;
        text-align: center;
    }

    .container {
        font-size: 125%;
        color: #45cb85;
        text-align: left;
        padding-left: 100px;
    }

    .sidebar {
        height: 100%;
        width: 250px;
        position: fixed;
        z-index: 1;
        top: 0;
        left: 0;
        overflow-x: hidden;
        padding-top: 20px;
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
<div class="sidebar">
    <a class="active" href="/home">Home</a>
    <a class="active" href="/profile">Profile</a>
    <a href="/manageinterest">Interests</a>
    <a class="active" href="/findplayers">Players</a>
    <a class="active" href="/viewmatch">Matches</a>
    <br>
    <br>
    <br>
    <a class="leave" href="/logout">Sign Out</a>
</div>

<div class="content">
    <h1>
        <u>Create An Interest</u>
    </h1>
    <#if message??>
        <div class="alert alert-success" style="color: black; font-size: 150%; font-family: 'LEMON MILK';
         background-color: lightblue; text-align: center">
            ${message}
        </div>
    </#if>
    <p style="color: #45cb85; font-size: 250%; text-align: center; font-family: 'LEMON MILK'">What do you seek in a player?</p>
    <br>
    <br>
    <br>
    <form action = "/createinterest" role="form" method="post">
        <div class="container">
            <label for="gameName" style="font-size: 200%;font-family: 'LEMON MILK'"> Select Game:</label>
            <input list="gameName" name="gameName" style="font-size: 150%; background-color: #45cb85; border-color: #45cb85; font-family: 'LEMON MILK'" required/>
            <datalist style="font-size: 150%; background-color: #45cb85; border-color: #45cb85; font-family: 'LEMON MILK'" name="gameName" id="gameName">
                <#list games as game>
                    <option value="${game.gameName}">${game.gameName}</option>
                </#list>
            </datalist>
            <br>
            <label for="gamerLvl" style="font-size: 200%;font-family: 'LEMON MILK'"> Select lvl:</label>
            <input type="number" min="1" id="gamerLvl" name="gamerLvl" style="font-size: 120%" required>
            <br>
            <label for="gamerRank" style="font-size: 200%;font-family: 'LEMON MILK'"> Select Rank:</label>
            <input type="text" min="1" id="gamerRank" name="gamerRank" style="font-size: 120%" required>
            <br>
            <br>
        </div>
        <button type="submit" style="color: darkgreen; font-size: 150%; background-color: #45cb85; border-color: #45cb85;font-family: 'LEMON MILK'">
            Create interest
        </button>
    </form>
</div>

</body>
</html>