<!DOCTYPE html>
<html lang="en">
<head>
    <title>Welcome!</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<style>
    html,body{
        height: 100%;
    }
    .h-custom{
        height: 100%;
    }

</style>
<body style="background-color: #272d39">

    <div class="container-fluid h-custom">
        <div class="row h-100 d-flex justify-content-center align-items-center text-center ">
            <div class="col-md-6 pt-5 my-5 ">
                <div class="mx-5 px-5">
                    <form action="/register" role="form" method="post">
                        <#if message??>
                            <div class="alert alert-success alert-dismissible"  role="alert" style="color: black; font-size: 150%; font-family: 'LEMON MILK'; text-align: center; background-color: lightblue">
                                ${message}
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                        </#if>
                        <h1 style="color: white; font-family: 'LEMON MILK'"> <u>Welcome to Meet-n-Game</u> </h1>
                        <p style="color: #45cb85; font-size: 160%; font-family: 'LEMON MILK'">Please create a username and password</p>
                        <div class="mb-4">
                            <input type="email" class="form-control" id="floatingInput" placeholder="Email" name="mail" style="font-size: 20px; font-family: 'LEMON MILK'" required>
                        </div>
                        <div class="mb-4">
                            <input type="text" class="form-control" id="floatingInput" placeholder="User Name" name="userName" style="font-size: 20px; font-family: 'LEMON MILK'" required>
                        </div>
                        <div class="mb-4">
                            <input type="password" class="form-control" id="floatingPassword" placeholder="Password" name="password" style="font-size: 20px; font-family: 'LEMON MILK'" required>
                        </div>
                        <div class="mb-4">
                            <button class="btn btn-lg btn-success" type="submit" style="font-size: 25px; font-family: 'LEMON MILK'">Sign up</button>
                        </div>
                    </form>
                </div>
                <div class="pt-3">
                    <p style="color: white; font-size: 25px; font-family: 'LEMON MILK'">Already a member?
                    </p>
                    <div>
                        <button class="btn btn-success" onclick="document.location='/login'" style="font-size: 25px; font-family: 'LEMON MILK'">Log In</button>
                    </div>
                </div>
            </div>
            <div class="col-md-6 d-flex flex-column align-items-center justify-content-center text center">
                <div>
                    <img src="/img/OnlyLogo.png" class="img-fluid" alt="Sample Image">
                </div>
                <div>
                    <p style="color: white; font-family: 'LEMON MILK'; font-size: 200%; text-align: center">
                        Join the biggest online gaming community
                    </p>
                </div>
            </div>
        </div>
    </div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
</body>
</html>
