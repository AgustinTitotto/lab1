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
    .nav-link, .nav-link:focus{
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

    .rounded-circle2 {
        border-radius:50%;
        width:50px;
        height:50px;
    }
    
    @media (max-width: 992px) {
        .dropdown-menu-end {
            left: 0 !important;
        }
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
                        <a class="nav-link" href="/interests">Interests</a>
                    </li>
                    <li>
                        <a class="nav-link" href="/findplayers">Players</a>
                    </li>
                    <li>
                        <a class="nav-link" href="/viewmatch">Matches</a>
                    </li>
                </ul>
                <div class="d-flex align-items-center dropdown">
                    <div class="dropdown me-3">
                        <a class="nav-link p" href="#" data-bs-toggle="dropdown" aria-expanded="true">
                            <#if notifications?has_content>
                                <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-envelope-exclamation-fill" viewBox="0 0 16 16">
                                    <path d="M.05 3.555A2 2 0 0 1 2 2h12a2 2 0 0 1 1.95 1.555L8 8.414.05 3.555ZM0 4.697v7.104l5.803-3.558L0 4.697ZM6.761 8.83l-6.57 4.026A2 2 0 0 0 2 14h6.256A4.493 4.493 0 0 1 8 12.5a4.49 4.49 0 0 1 1.606-3.446l-.367-.225L8 9.586l-1.239-.757ZM16 4.697v4.974A4.491 4.491 0 0 0 12.5 8a4.49 4.49 0 0 0-1.965.45l-.338-.207L16 4.697Z"/>
                                    <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7Zm.5-5v1.5a.5.5 0 0 1-1 0V11a.5.5 0 0 1 1 0Zm0 3a.5.5 0 1 1-1 0 .5.5 0 0 1 1 0Z"/>
                                </svg>
                            <#else>
                                <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-envelope-fill" viewBox="0 0 16 16">
                                    <path d="M.05 3.555A2 2 0 0 1 2 2h12a2 2 0 0 1 1.95 1.555L8 8.414.05 3.555ZM0 4.697v7.104l5.803-3.558L0 4.697ZM6.761 8.83l-6.57 4.027A2 2 0 0 0 2 14h12a2 2 0 0 0 1.808-1.144l-6.57-4.027L8 9.586l-1.239-.757Zm3.436-.586L16 11.801V4.697l-5.803 3.546Z"/>
                                </svg>
                            </#if>
                        </a>
                        <ul class="notifications dropdown-menu dropdown-menu-end dropdown-menu-dark" style="background-color: black; font-family: 'LEMON MILK'">
                            <#list notifications>
                                <#items as notification>
                                    <li class="dropdown-item">
                                        <form class="container" action="/deletenotification" role="form" method="post">
                                            <input type="hidden" name="notificationId" id="notificationId" value="${notification.id}">${notification.notification}
                                            <input type="hidden" name="route" id="route${notification?index}">
                                            <button type="submit" class="btn-close btn-close-white position-absolute end-0" aria-label="Close"></button>
                                        </form>
                                    </li>
                                </#items>
                                <#else >
                                    <li class="dropdown-item">
                                        <p class="x">You have no notifications</p>
                                    </li>
                            </#list>
                        </ul>
                    </div>
                    <div class="dropdown ms-3">
                        <a class="nav-link" href="#" data-bs-toggle="dropdown" aria-expanded="true">
                            <img src="data:image/jpeg;base64,${image}" alt="User" class="rounded-circle2">
                        </a>
                        <ul class="dropdown-menu dropdown-menu-dark dropdown-menu-end" style="background-color: black; font-family: 'LEMON MILK'">
                            <li class="dropdown-item">
                                <a class="nav-link" href="/settings">Account settings</a>
                            </li>
                            <li class="dropdown-item">
                                <a class="nav-link-sign-out" href="/logout">Sign Out</a>
                            </li>
                        </ul>
                    </div>
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
<script type="text/javascript">
    let a = window.location.href.substring(window.location.href.lastIndexOf('/') + 1)
    const notifications = [<#list notifications as notification>'${notification?index}',</#list>]
    for (let i = 0; i < notifications.length; i++) {
        document.getElementById("route" + notifications.at(i)).value = a
    }

</script>
</body>
</html>
</#macro>