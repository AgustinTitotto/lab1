<#-- @ftlvariable name="messages" type="java.util.List<Message>" -->
<#-- @ftlvariable name="sender" type="java.lang.String" -->
<#-- @ftlvariable name="receiver" type="java.lang.String" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Chat</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<style>

    h1{
        color: white;
        font-size: 300%;
        text-align: center;
        font-family: "LEMON MILK";
    }

    .content {
        top: 0;
        padding: 20px 20px;
        height: 768px;
        margin-left: 250px;
        background-color: #272d39;
        background-image: url(/img/Background2.jpg);
        background-position: top;
        background-size: contain;
        background-repeat: repeat-y;
        text-align: center;
    }

    .container {
        font-size: 150%;
        color: #45cb85;
        text-align: center;
        font-family: "LEMON MILK";
    }

    .sidebar {
        height: 100%;
        width: 250px;
        position: fixed;
        z-index: 1;
        top: 0;
        left: 0;
        overflow-x: hidden;
        padding-top: 20px;
        background-color: #272d39;
    }

    .sidebar a {
        padding: 16px;
        background-color: #45cb85;
        font-family: "LEMON MILK";
        color: green;
        display: block;
        font-size: 250%;
        text-decoration: none;
    }

    .sidebar a.active {
        background-color: #272d39;
        color: white;
        font-size: 250%;
        font-family: "LEMON MILK";
        text-decoration: none;
    }

    .sidebar a.leave {
        background-color: #ff4655;
        color: darkred;
        font-size: 250%;
        font-family: "LEMON MILK";
        text-decoration: none;
    }

    .sidebar a.active:hover{
        background-color: #45cb85;
        color: green;
        font-size: 250%;
    }

    @media screen and (max-width: 700px) {
        .sidebar {
            width: 100%;
            height: auto;
            position: relative;
        }
        .sidebar a {float: left;}
    }
    @media screen and (max-width: 400px) {
        .sidebar a {
            text-align: center;
            float: none;
        }
    }

    .container{
        font-size: 150%;
        color: #45cb85;
        text-align: center;
        font-family: "LEMON MILK";
    }

    .form-wrapper {
        height: 100%;
        margin-left: 250px;
        background-color: #272d39;
        text-align: center;
        overflow: hidden;
    }

</style>
<body>
<!-- <a id="user"></a>-->
<div class="sidebar">
    <a class="active" href="/home">Home</a>
    <a class="active" href="/profile">Profile</a>
    <a class="active" href="/manageinterest">Interests</a>
    <a class="active" href="/findplayers">Players</a>
    <a href="/viewmatch">Matches</a>
    <br>
    <br>
    <br>
    <a class="leave" href="/logout">Sign Out</a>
</div>
<div class="content">
    <h1>${receiver}</h1>
    <div class="container" style="padding-left: 200px; padding-right: 200px">
        <#if messages??>
            <#list messages as message>
                <#if message.sender == sender>
                    <div class="message" style="float: right">
                        ${message.message}
                    </div>
                    <br>
                </#if>
                <#if message.sender != sender>
                    <div class="message" style="float: left">
                        ${message.message}
                    </div>
                    <br>
                </#if>
            </#list>
        </#if>
    </div>
</div>
<div class="form-wrapper">
    <form id="message-form" class="message-form" action = "/chat/${receiver}" role="form" method="post">
        <span style="display: block; overflow: hidden; padding: 0 5px; float: left; width: 80%">
            <input style="width: 100%; box-sizing: border-box; background-color: #45cb85;border-color: #45cb85; font-size: 150%" type="text" name="message" placeholder="Send a message..." class="message" id="message" />
        </span>
        <button style="float: right; box-sizing: border-box; width: 20%;background-color: #45cb85;border-color: #45cb85; font-size: 150%;" type="submit" name="send" class="send" id="send">
            <a href="/chat/${receiver}" style="text-decoration: none; color: black">Send</a>
            Send
        </button>
    </form>
</div>
<!--
<div class="content2" style="float: left">
    <form action = "/chat:${sender}" role="form" method="post">
        <input type="text" id="message" name="message" style="font-size: 150%" required>
    </form>
</div>
<div style="float:right;">
    <button style="background-color: #45cb85;border-color: #45cb85; float: right; font-size: 150%; width: 100px">
        <a href="/chat/:${receiver}" style="text-decoration: none; color: black">Send</a>
    </button>
</div>
-->
</body>
</html>