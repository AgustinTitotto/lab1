<!DOCTYPE html>
<html lang="en">
<head>
    <title>Welcome!</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body class="text-center" style="background-color: #272d39; background-image: url(/img/Background1.jpg); background-repeat: no-repeat;
 background-position: top; background-size: 95%">

<main style="margin-top: 225px;">
    <#if message??>
        <div class="alert alert-success">
            ${message}
        </div>
    </#if>
    <h1 style="color: white; font-family: 'LEMON MILK'"> <u>Welcome to Meet-n-Game</u> </h1>
    <p style="color: #45cb85; font-size: 160%; font-family: 'LEMON MILK'">Please create a user name and a password</p>
    <form action="/register" role="form" method="post">
        <div class="form-floating" style="margin-left: 400px; margin-right: 400px">
            <input type="text" class="form-control" id="floatingInput" placeholder="User Name" name="userName" required>
            <label for="floatingInput">User Name</label>
        </div>
        <div class="form-floating" style="margin-left: 400px; margin-right: 400px">
            <input type="password" class="form-control" id="floatingPassword" placeholder="Password" name="password" required>
            <label for="floatingPassword">Password</label>
        </div>

        <button class="btn btn-lg btn-success" type="submit" style="width: 570px">Sign up</button>
        <br>
        <p style="color: white">Already logged in?</p>
        <button onclick="document.location='/login'" style="background-color: #45cb85; border-color: #45cb85">Log In</button>
    </form>
</main>

</body>
</html>
