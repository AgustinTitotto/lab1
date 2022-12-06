<#-- @ftlvariable name="games" type="java.util.List<Game>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Delete game</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body style="background-color: #272d39; background-image: url(/img/Background2.jpg); background-repeat: repeat-y;
 background-position: top; background-size: 80%">
    <h1 style="color: white; font-size: 300%; font-family: 'LEMON MILK'; text-align: center"><u>Create a new rank</u></h1>

    <#if message??>
        <div class="alert alert-success" style="color: black; font-size: 150%; font-family: 'LEMON MILK'; text-align: center;
        background-color: lightblue">
            ${message}
        </div>
    </#if>
    <br>
    <form class="container" style="text-align: center" action="/createrank" role="form" method="post">
        <input list="gameName" name="gameName" style="font-size: 150%; background-color: #45cb85; border-color: #45cb85; font-family: 'LEMON MILK'" required/>
        <datalist style="font-size: 150%; background-color: #45cb85; border-color: #45cb85; font-family: 'LEMON MILK'" name="gameName" id="gameName">
            <#list games as game>
                <option value="${game.gameName}">${game.gameName}</option>
            </#list>
        </datalist>
        <br>
        <br>
        <label for="newRank" style="font-size: 200%; color: #45cb85"> New Rank:</label>
        <br>
        <input type="text" id="newRank" name="newRank" style="font-size: 120%; font-family: 'LEMON MILK'" required>
        <br><br>
        <input style="font-size: 150%; background-color: #45cb85; border-color: #45cb85; font-family: 'LEMON MILK'; text-align: center" type="submit"
               value="Update" name="createRank">
    </form>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>