<!DOCTYPE html>
<html lang="en">
<head>
    <title>Welcome!</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body class="text-center" style="background-color: #272d39; background-image: url(/img/Background1.jpg); background-repeat: no-repeat;
 background-position: top; background-size: 95%">

<main style="margin-top: 225px;">
    <#if message??>
        <div class="alert alert-success">
            ${message}
        </div>
    </#if>
    <h1 class="display-1" style="color: antiquewhite; font-family: 'LEMON MILK'"> <u>Welcome to Meet-n-Game</u> </h1>
    <p class="display-6" style="color: #45cb85; font-family: 'LEMON MILK'">Please create a user name and a password</p>
    <form action="/register" role="form" method="post" style="alignment: center; margin-left: 400px; margin-right: 400px; font-size: 30px" enctype="multipart/form-data">
        <input type="text" class="form-control" id="floatingInput" placeholder="User Name" name="userName" style="font-size: 20px; height: 60px" required>
        <br>
        <input type="password" class="form-control" id="floatingPassword" placeholder="Password" name="password" style="font-size: 20px; height: 60px" required>
        <br>
        <input type="email" class="form-control" id="floatingMail" placeholder="Mail" name="mail" style="font-size: 20px; height: 60px" required>
        <br>
        <div class="form-group">
            <input type="file" class="form-control" id="img" accept="image/jpg, image/png" style="font-size: 20px; height: 43px" name="image"/>
        </div>
        <div id="selectedBanner"></div>
        <br>
        <div class="d-grid gap-2 col-5 mx-auto">
            <button class="btn btn-lg btn-success" type="submit" style="font-size: 25px">Sign up</button>
        </div>
        <br>
        <p style="color: white">Already logged in?</p>
        <button class="btn btn-success" onclick="document.location='/login'" style="font-size: 25px">Log In</button>
    </form>
    <br>
</main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script>
    var selDiv = "";
    var storedFiles = [];
    $(document).ready(function () {
        $("#img").on("change", handleFileSelect);
        selDiv = $("#selectedBanner");
    });

    function handleFileSelect(e) {
        var files = e.target.files;
        var filesArr = Array.prototype.slice.call(files);
        filesArr.forEach(function (f) {
            if (!f.type.match("image.*")) {
                return;
            }
            storedFiles.push(f);

            var reader = new FileReader();
            reader.onload = function (e) {
                var html = '<img src="' + e.target.result + "\" data-file='" + f.name + "' class='avatar rounded lg' alt='Category Image' height='200px' width='200px'>";
                selDiv.html(html);
            };
            reader.readAsDataURL(f);
        });
    }
</script>
</body>
</html>
