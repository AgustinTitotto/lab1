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

    button{
        background-color: #45cb85;
        border-color: #45cb85;
        font-size: 120%;
    }

    button1{
        background-color: #ff4655;
        border-color: #ff4655;
        font-size: 120%;
    }

    .hpl{
        color: green;
        font-family: "LEMON MILK";
        text-decoration: none;
    }

    .hpl1{
        color: darkred;
        font-family: "LEMON MILK";
        text-decoration: none;
    }

    .sidebar {
        margin: 0;
        padding: 0;
        width: 200px;
        position: fixed;
        height: 100%;
        overflow: auto;
    }

    .sidebar a {
        display: block;
        color: white;
        padding: 16px;
        text-decoration: none;
    }

    .sidebar a.active {
        background-color: #45cb85;
        color: green;
        font-family: "LEMON MILK";
        text-decoration: none;
    }

    .sidebar a.leave {
        background-color: #ff4655;
        color: darkred;
        font-family: "LEMON MILK";
        text-decoration: none;
    }

    .sidebar a:hover:not(.active) {
        background-color: #45cb85;
        color: green;
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
    <h1><u>Welcome back</u></h1>

    <#if message??>
        <div class="alert alert-success">
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

    <div class="container">
        <p>View your profile</p>
        <button>
            <a href="/profile" class="hpl">View Profile</a>
        </button>
        <p>Create a new gamer interest</p>
        <button>
            <a href="/manageinterest" class="hpl">Manage interests</a>
        </button>
        <p>Find Players</p>
        <button>
            <a href="/findplayers" class="hpl">Find players</a>
        </button>
        <p>View Matches</p>
        <button>
            <a href="/viewmatch" class="hpl">View Matches</a>
        </button>
        <p>Leave session</p>
        <button1>
            <a href="/logout" class="hpl1">Sign Out</a>
        </button1>
    </div>
</body>

</html>
