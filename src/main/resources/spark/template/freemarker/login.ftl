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
<body style="background-color: #272d39;">
    <div class="container-fluid h-custom py-5">
        <div class="row d-flex justify-content-center h-100 ">
            <div class="col-12 col-md-6 col-lg-4 d-flex align-items-center">
                <div class="card bg-dark" style="border-radius: 2rem">
                    <div class="card-body p-5 text-center">
                        <div class="mb-md-5 mt-md-5 pb-5">
                            <form action="/login" role="form" method="post">
                                <#if message??>
                                    <div class="alert alert-success alert-dismissible"  role="alert" style="color: black; font-size: 150%; font-family: 'LEMON MILK'; text-align: center; background-color: lightblue">
                                        ${message}
                                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                    </div>
                                </#if>
                                <h1 class="mb-2" style="color: antiquewhite; font-family: 'LEMON MILK'"> <u>Log In</u> </h1>
                                <p class="mb-5" style="color: #45cb85; font-size: 160%; font-family: 'LEMON MILK'">Please enter your username and password</p>
                                <div class="mb-5">
                                    <input type="text" class="form-control" id="floatingInput" placeholder="User Name" name="userName" style="font-size: 20px; font-family: 'LEMON MILK'" required>
                                </div>
                                <div class="mb-5">
                                    <input type="password" class="form-control" id="floatingPassword" placeholder="Password" name="password" style="font-size: 20px; font-family: 'LEMON MILK'" required>
                                </div>
                                <div class="mb-5">
                                    <button class="btn btn-success" type="submit" style="font-size: 25px; font-family: 'LEMON MILK'">Log in</button>
                                </div>

                            </form>
                        </div>
                        <div>
                            <p style="color: antiquewhite; font-size: 25px; font-family: 'LEMON MILK'">Not a member?
                                <button class="btn btn-success" onclick="document.location='/register'" style="font-size: 25px; font-family: 'LEMON MILK'">Sign Up</button>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--
-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
</body>
</html>
<!--
    #image-background{
             background-image: url(/img/Background2.jpg);
             background-position: center center;
             background-repeat: no-repeat;
             background-attachment: fixed;
             background-size: cover;
             height: 100vh;
         }
     <div id="image-background" class="bg-image">
        Antes de todo
     </div>
    -->