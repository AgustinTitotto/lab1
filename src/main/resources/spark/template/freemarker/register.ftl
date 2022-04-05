<!DOCTYPE html>
<html lang="en">
<head>
    <title>Welcome!</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body class="text-center" style="margin-top: 150px">

<main>
    <#if message??>
        <div class="alert alert-success">
            ${message}
        </div>
    </#if>
    <form action="/register" role="form" method="post">
        <h1 class="h3 mb-3 fw-normal">Sign up</h1>

        <div class="form-floating" style="margin-left: 400px; margin-right: 400px">
            <input type="text" class="form-control" id="floatingInput" placeholder="User Name" name="userName" required>
            <label for="floatingInput">User Name</label>
        </div>
        <div class="form-floating" style="margin-left: 400px; margin-right: 400px">
            <input type="password" class="form-control" id="floatingPassword" placeholder="Password" name="password" required>
            <label for="floatingPassword">Password</label>
        </div>

        <button class="btn btn-lg btn-primary" type="submit" style="width: 570px">Sign up</button>
        <br>
        <a href="/login">Sign In</a>
    </form>
</main>

</body>
</html>
