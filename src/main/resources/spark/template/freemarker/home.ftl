<!DOCTYPE html>
<html lang="en">
<head>
    <title>Welcome to Home!</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<style>
    h1{
        color: white;
        font-size: 300%;
        text-align: center;
        font-family: "Cooper Black"
    }

    .container{
        text-align: center;
        font-size: 150%;
        color: #45cb85;
        font-family: "Cooper Black"
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
        font-family: "Cooper Black";
    }

    .hpl1{
        color: darkred;
        font-family: "Cooper Black";
    }

</style>
<body style="background-color: #282e3a; background-image: url(/img/Background2.jpg); background-repeat: no-repeat;
 background-position: center; background-size: 81%">
<h1><u>Welcome back</u></h1>

<#if message??>
    <div class="alert alert-success">
        ${message}
    </div>
</#if>

<div class="container">
    <p>Create a new game description</p>
    <button>
        <a href="/createdescription" class="hpl">Create description</a>
    </button>
    <p>Create a new gamer interest</p>
    <button>
        <a href="/createinterest" class="hpl">Create interest</a>
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
