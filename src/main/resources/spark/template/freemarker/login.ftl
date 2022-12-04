<!DOCTYPE html>
<html lang="en">
<head>
    <title>Welcome!</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body class="text-center" style="margin-top: 225px; background-color: #272d39; background-image: url(/img/Background1.jpg); background-repeat: no-repeat;
 background-position: top; background-size: 95%">

<main>
    <#if message??>
        <div class="alert alert-success">
            ${message}
        </div>
    </#if>
    <h1 class="display-1" style="color: antiquewhite; font-family: 'LEMON MILK'"> <u>Welcome to Meet-n-Game</u> </h1>
    <p class="display-6" style="color: #45cb85; font-family: 'LEMON MILK'">Please enter your user name and your password</p>
    <form action="/login" role="form" method="post" style="alignment: center; margin-left: 400px; margin-right: 400px; font-size: 30px">
        <input type="text" class="form-control" id="floatingInput" placeholder="User Name" name="userName" style="font-size: 20px; height: 60px" required>
        <br>
        <input type="password" class="form-control" id="floatingPassword" placeholder="Password" name="password" style="font-size: 20px; height: 60px" required>
        <br>
        <div class="d-grid gap-2 col-5 mx-auto">
            <button class="btn btn-lg btn-success" type="submit" style="font-size: 25px">Sign in</button>
        </div>
        <br>
        <p style="color: white">You new here?</p>
        <button class="btn btn-success" onclick="document.location='/register'" style="font-size: 25px">Sign Up</button>
    </form>
</main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>
