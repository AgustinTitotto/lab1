<!DOCTYPE html>
<html lang="en">
<head>
    <title>Welcome!</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body class="text-center" style="margin-top: 150px; background-color: black">

<main>

    <#if message??>
        <div class="alert alert-success">
            ${message}
        </div>
    </#if>
    <form action="/login" role="form" method="post">
        <h1 style="color: lime";> Welcome to Meet-n-Game </h1>
        <h2 class="h3 mb-3 fw-normal"; style="color: lime";>Sign in</h2>
        <p style="color: lime; font-size: 160%">Please enter your user name and your password</p>

        <div class="form-floating" style="margin-left: 400px; margin-right: 400px">
            <input type="text" class="form-control" id="floatingInput" placeholder="User Name" name="userName" required>
            <label for="floatingInput">User Name</label>
        </div>
        <div class="form-floating" style="margin-left: 400px; margin-right: 400px">
            <input type="password" class="form-control" id="floatingPassword" placeholder="Password" name="password" required>
            <label for="floatingPassword">Password</label>
        </div>

        <button class="btn btn-lg btn-success" type="submit" style="width: 570px">Sign in</button>
        <br>
        <p style="color: lime">You new here?</p>
        <button onclick="document.location='/register'" style="background-color: lime; border-color: lime">Sign Up</button>
    </form>
</main>



</body>
</html>
