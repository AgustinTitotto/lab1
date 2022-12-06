<#-- @ftlvariable name="matches" type="java.util.List<GamerUser>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <title>View Matches</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
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
        height: 100vh;
        margin-left: 250px;
        background-color: #272d39;
        background-image: url(/img/Background2.jpg);
        background-position: top;
        background-size: contain;
        background-repeat: repeat-y;
        text-align: center;
    }

    .container {
        font-size: 150%;
        color: #45cb85;
        text-align: center;
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

    .container{
        font-size: 150%;
        color: #45cb85;
        text-align: center;
        font-family: "LEMON MILK";
    }

</style>
<body>
<!-- <a id="user"></a>-->
<div class="sidebar">
    <a class="active" href="/home">Home</a>
    <a class="active" href="/profile">Profile</a>
    <a class="active" href="/manageinterest">Interests</a>
    <a class="active" href="/findplayers">Players</a>
    <a href="/viewmatch">Matches</a>
    <br>
    <br>
    <a class="leave" href="/logout">Sign Out</a>
</div>

<div class="content">
    <h1>
        <u>View who you match with</u>
    </h1>
    <br>
    <br>
    <br>
    <p class="container">Your matches are</p>
    <div class="container" style="padding-left: 200px; padding-right: 200px">
        <#if matches??>
            <#list matches as match>
                <hr/>
                <div class="match">
                    <div style="text-align: left; float: left">
                        ${match.userName}
                    </div>
                    <div style="text-align: right; float: right;">
                        <button style="background-color: #45cb85;border-color: #45cb85;">
                            <a href="/viewmatch/${match.userName}" style="text-decoration: none; color: black">View Profile</a>
                        </button>
                        <button style="background-color: #45cb85;border-color: #45cb85;">
                            <a href="/chat/${match.userName}"style="text-decoration: none; color: black">Chat with Player</a>
                        </button>
                    </div>
                </div>
                <br>
                <#else>
                    <hr/>
                    <div class="well">
                        There're no matches so far.
                    </div>
                </#list>
        </#if>
        <br>
        <br>
        <#if descriptions??>
        <select style="font-size: 150%; background-color: #45cb85; border-color: #45cb85; font-family: 'LEMON MILK'" name="descriptions" id="descriptions">
            <#list descriptions as description>
                <option value="${description.game.gameName}, ${description.lvl}, ${description.rank.rankName}">
                    Game: ${description.game.gameName}, Lvl: ${description.lvl}, Rank: ${description.rank.rankName}</option>
            </#list>
            </#if>
    </div>

    <!--
    <form action="/viewmatch" role="form" method="post">
        <div class="container">
            <select style="font-size: 125%; background-color: #45cb85; border-color: #45cb85; font-family: 'LEMON MILK'" name="gamers" id="gamers">
                <#if matches??>
                    <#list matches as match>
                        <option style="color: #45cb85; font-size: 125%; font-family: 'LEMON MILK'"
                                value="${match.userName}">${match.userName}</option>
                    </#list>
                </#if>
            </select>
        </div>
        <br>
        <br>
        <button type="submit" style="color: darkgreen; font-size: 150%; background-color: #45cb85; border-color: #45cb85;font-family: 'LEMON MILK'">
            Click to view player profile
        </button>
        <br>
        <br>
        <#if descriptions??>
        <select style="font-size: 150%; background-color: #45cb85; border-color: #45cb85; font-family: 'LEMON MILK'" name="descriptions" id="descriptions">
        <#list descriptions as description>
            <option value="${description.game.gameName}, ${description.lvl}, ${description.rank.rankName}">
                Game: ${description.game.gameName}, Lvl: ${description.lvl}, Rank: ${description.rank.rankName}</option>
        </#list>
        </#if>
    </form>
    -->
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>