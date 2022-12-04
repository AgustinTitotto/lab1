<#-- @ftlvariable name="descriptions" type="java.util.List<GamerDescription>" -->
<#-- @ftlvariable name="userNames" type="java.util.List<String>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <title>View Players</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
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
    <br>
    <br>
    <form class="container" action="/findplayers" role="form" method="post">
        <select style="font-size: 150%; background-color: #45cb85; border-color: #45cb85;" name="gamers" id="gamers">
            <#list descriptions as description>
                <option value="${description.gamerUser.userName}, ${description.game.gameName}, ${description.lvl}, ${description.rank.rankName}">
                    ${description.gamerUser.userName} - ${description.game.gameName} - ${description.lvl} - ${description.rank.rankName}</option>
            </#list>
        </select>
        <br>
        <br>
        <button type="submit" style="font-size: 150%; background-color: #45cb85; border-color: #45cb85;font-family: 'LEMON MILK'">
        Like
        </button>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
<!--<script>
    let arr = [<#list userNames as userName>"${userName}", </#list>]
    let desc = [<#list descriptions as description>"${description.game.gameName}", </#list>]
    let a = arr[0]
    document.write(a)
    document.getElementById("user").innerHTML = a;
</script> -->
</html>
<!--  -->
