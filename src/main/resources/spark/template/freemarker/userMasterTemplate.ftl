<#macro userMasterTemplate title="Welcome">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${title} | MeetNGame</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<style>

    .navbar{
        padding: 10px;
        background-color: black/*#1e2124*/;
    }
    .nav-link{
        font-family: 'LEMON MILK';
        color: white;
    }

    .nav-link:hover{
        color: #6AA8F5;
    }

    .nav-link-sign-out{
        font-family: 'LEMON MILK';
        text-decoration: none;
        color: white;
    }

    .nav-link-sign-out:hover{
        color: red;
    }

    .background::before{
        content: "";
        position: fixed;
        left: 0;
        right: 0;
        z-index: -1;
        display: block;
        background-image: url(/img/Background3.png);
        background-size:cover;
        width: 100%;
        height: 100%;
        filter: brightness(50%);
    }

    .background {
        position: relative;
    }

    .content{
        padding: 20px;
    }

    h1{
        color: white;
        font-size: 300%;
        text-align: center;
        font-family: "LEMON MILK";
    }

    .profileSubT{
        color: white;
        text-align: center;
        font-family: "LEMON MILK";
    }

    .btn-profile {
        background-color: #6AA8F5; !important;
        color: white;
    }

    .btn-profile:hover {
        background-color: rgb(79, 126, 183);
        color: white;
    }

</style>
<body>

    <nav id="navbar" class="navbar navbar-dark navbar-expand-lg sticky-top">
        <div class="container">
            <a class="navbar-brand me-4" style="color: white; font-family: 'LEMON MILK'; font-size: 160%">MeetNGame</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li>
                        <a class="nav-link" href="/home">Home</a>
                    </li>
                    <li>
                        <a class="nav-link" href="/profile">Profile</a>
                    </li>
                    <li>
                        <a class="nav-link" href="/manageinterest">Interests</a>
                    </li>
                    <li>
                        <a class="nav-link" href="/findplayers">Players</a>
                    </li>
                    <li>
                        <a class="nav-link" href="/viewmatch">Matches</a>
                    </li>
                </ul>
                <div class="d-flex align-items-center">
                    <a class="nav-link-sign-out" href="/logout">Sign Out</a>
                </div>
            </div>
        </div>
    </nav>

    <div class="background">
        <div class="content">
            <#nested />
        </div>
    </div>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous">

</script>
</body>
</html>
</#macro>