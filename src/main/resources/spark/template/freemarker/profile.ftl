<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
</head>
<style>
    body{
        background-color: #282e3a;
        background-image: url(/img/Background2.jpg);
        background-position: right;
        background-repeat: no-repeat;
        background-size: 85%;
    }

    h1{
        color: white;
        font-size: 300%;
        text-align: center;
        font-family: 'LEMON MILK';
    }

    .container{
        font-size: 150%;
        color: #45cb85;
        text-align: center;
    }

    p1{
        font-size: 120%;
    }

    .sidebar {
        margin-top: -40px;
        padding: 0;
        width: 200px;
        background-color: #282e3a;
        position: fixed;
        height: 100%;
        overflow: auto;
    }

    .sidebar a {
        display: block;
        color: white;
        padding: 16px;
        font-size: 290%;
        text-decoration: none;
    }

    .sidebar a.active {
        background-color: #45cb85;
        color: green;
        font-size: 290%;
        font-family: "LEMON MILK";
        text-decoration: none;
    }

    .sidebar a.leave {
        background-color: #ff4655;
        color: darkred;
        font-size: 290%;
        font-family: "LEMON MILK";
        text-decoration: none;
    }

    .sidebar a:hover:not(.active) {
        background-color: #45cb85;
        color: green;
        font-size: 290%;
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

    p{
        color: #45cb85;
        text-align: center;
        margin-top: 100px;
        font-family: "LEMON MILK";
        font-size: 200%;
    }

    button{
        background-color: #45cb85;
        border-color: #45cb85;
        font-size: 120%;
        align-content: center;
        margin-left: 600px;
        margin-right: 600px;
    }

    .hpl{
        color: green;
        font-family: "LEMON MILK";
        text-decoration: none;
    }
</style>
<body>
<#if message??>
    <div class="alert alert-success" style="color: black; font-size: 150%; font-family: 'LEMON MILK';
     background-color: lightblue; text-align: center">
        ${message}
    </div>
</#if>

<div class="sidebar">
    <a href="/home">Home</a>
    <a class="active" href="#profile" class="hpl">Profile</a>
    <a a href="/manageinterest">Interests</a>
    <a href="/findplayers">Players</a>
    <a href="/viewmatch" class="hpl">Matches</a>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <a class="leave" href="/logout" class="hpl">Sign Out</a>
</div>

<h1>
    <u>Welcome to your profile</u>
</h1>

    <p>Create a new game description</p>
    <button>
        <a href="/createdescription" class="hpl">Create Description</a>
    </button>
    <p>Update your descriptions</p>
    <button>
        <a href="/updatedescription" class="hpl">Update Description</a>
    </button>
    <p>Delete your descriptions</p>
    <button>
        <a href="/deletedescription" class="hpl">Delete Description</a>
    </button>

</body>
</html>