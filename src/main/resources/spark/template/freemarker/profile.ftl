<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
</head>
<style>
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
<body style="background-color: #282e3a; background-image: url(/img/Background2.jpg); background-repeat: no-repeat;
 background-position: top; background-size: 85%">
<h1 style="color: white; font-size: 300%; text-align: center; font-family: 'LEMON MILK'">
    <u>Welcome to your descriptions profile</u>
</h1>
<#if message??>
    <div class="alert alert-success">
        ${message}
    </div>
</#if>

    <p>Create a new game description</p>
    <button>
        <a href="/createdescription" class="hpl">Create Description</a>
    </button>
    <p>View or delete your descriptions</p>
    <button>
        <a href="/managedescription" class="hpl">Manage Description</a>
    </button>

</body>
</html>