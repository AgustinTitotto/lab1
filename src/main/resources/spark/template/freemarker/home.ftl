<#-- @ftlvariable name="myName" type="String" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Welcome to Home!</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<style>
    body{
        background-color: #282e3a;
        background-image: url(/img/Background2.jpg);
        background-position: center;
        background-size: 81%;
        background-repeat: no-repeat
    }

    h1{
        color: white;
        font-size: 300%;
        text-align: center;
        font-family: "LEMON MILK";
        margin-top: 20px;
    }

    .container{
        text-align: center;
        font-size: 150%;
        color: #45cb85;
        font-family: "LEMON MILK";
        margin-top: 50px;
    }

    .hpl{
        color: green;
        font-family: "LEMON MILK";
        text-decoration: none;
    }

    .sidebar {
        margin: 0;
        padding: 0;
        background-color: #282e3a;
        width: 200px;
        position: fixed;
        height: 100%;
        overflow: auto;
    }

    .sidebar a {
        display: block;
        color: white;
        padding: 16px;
        font-size: 250%;
        text-decoration: none;
    }

    .sidebar a.active {
        background-color: #45cb85;
        color: green;
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

    .sidebar a:hover:not(.active) {
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
<#if message??>
    <div class="alert alert-success" style="text-align: center">
        ${message}
    </div>
</#if>
<div class="sidebar">
    <a class="active" href="#home">Home</a>
    <a href="/profile" class="hpl">Profile</a>
    <a a href="/manageinterest">Interests</a>
    <a href="/findplayers" class="hpl">Players</a>
    <a href="/viewmatch" class="hpl">Matches</a>
    <a class="leave" href="/logout" class="hpl">Sign Out</a>
</div>
    <h1><u>Welcome back</u> <br>
        <u>${myName}</u></h1>

<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>

</body>

</html>
