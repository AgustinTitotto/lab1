<#-- @ftlvariable name="messages" type="java.util.List<Message>" -->
<#-- @ftlvariable name="sender" type="java.lang.String" -->
<#-- @ftlvariable name="receiver" type="java.lang.String" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Chat</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
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
        height: 100vh;
        margin-left: 250px;
        background-color: #272d39;
        background-image: url(/img/Background2.jpg);
        background-position: top;
        background-size: contain;
        background-repeat: repeat-y;
        text-align: center;
    }

    .sidebar {
        height: 100%;
        width: 250px;
        position: fixed;
        overflow: auto;
        margin: 0;
        padding: 0;
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
        text-align: center;
        bottom: 0;
        position: fixed;
        width: 100vh;
    }

    .message{
        word-break: break-word;
        border: 2px solid #dedede;
        border-radius: 5px;
    }

    br1 {
        display: block; /* makes it have a width */
        content: ""; /* clears default height */
        margin-top: 10px; /* change this to whatever height you want it */
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
    <a class="leave" href="/logout">Sign Out</a>
</div>
<div class="content">
    <h1>${receiver}</h1>
    <div class="container" style="padding-left: 200px; padding-right: 200px">
        <#if messages??>
            <#list messages as message>
                <#if message.sender == sender>
                    <div class="message">
                        <p style="text-align: right; margin: 0; padding: 0">${message.sender}</p>
                        <p style="text-align: right; margin: 0; padding: 0">${message.message}</p>
                        <p style="color: #aaa; text-align: right; margin: 0; padding: 0">${message.date}</p>
                    </div>
                    <br1>
                </#if>
                <#if message.sender != sender>
                    <div class="message">
                        <p style="text-align: left; margin: 0; padding: 0">${message.sender}</p>
                        <p style="text-align: left; margin: 0; padding: 0">${message.message}</p>
                        <p style="color: #aaa; text-align: left; margin: 0; padding: 0">${message.date}</p>
                    </div>
                    <br1>
                </#if>
            </#list>
        </#if>
        <div class="form-wrapper">
            <form id="message-form" class="message-form" action = "/chat/${receiver}" role="form" method="post">
        <span style="padding: 0 5px; float: left; width: 80%; margin-bottom: 20px;">
            <input style="width: 100%;background-color: #45cb85;border-color: #45cb85;" type="text" name="message" placeholder="Send a message..." class="message" id="message" required/>
        </span>
                <button style="float: right;width: 20%;background-color: #45cb85;border-color: #45cb85;" type="submit" name="send" class="send" id="send">
                    <a href="/chat/${receiver}" style="text-decoration: none; color: black"></a>
                    Send
                </button>
            </form>
        </div>
    </div>
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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>