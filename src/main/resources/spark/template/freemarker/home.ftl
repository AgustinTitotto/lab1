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
    }

    .container{
        font-size: 150%;
        color: #45cb85;
        text-align: left;
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

</style>
<body style="background-color: #282e3a">
<h1><u>Welcome back</u></h1>
<div class="container">
    <p>Create a new game description</p>
    <button>
        <a href="/createdescription" style="color: green">Create description</a>
    </button>
    <p>Create a new gamer interest</p>
    <button>
        <a href="/createinterest" style="color: green">Create interest</a>
    </button>
    <p>Find Players</p>
    <button>
        <a href="/findplayers" style="color: green">Find players</a>
    </button>
    <p>Leave session</p>
    <button1>
        <a href="/logout" style="color: darkred">Sign Out</a>
    </button1>
</div>
</body>

</html>
