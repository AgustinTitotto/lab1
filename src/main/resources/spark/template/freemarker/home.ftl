<#-- @ftlvariable name="myName" type="java.lang.String" -->
<#-- @ftlvariable name="image" type="java.lang.String" -->
<#-- @ftlvariable name="notifications" type="java.util.List<Notification>" -->
<#-- @ftlvariable name="stats" type="lab1.meetNGame.model.Stats" -->
<#import "userMasterTemplate.ftl" as layout />
<@layout.userMasterTemplate title="Home">

    <h1><u>Welcome back</u> <br>
        <u>${myName}</u></h1>

    <#if message??>
        <div class="alert alert-success" style="color: black; font-family: 'LEMON MILK';
         background-color: lightblue; text-align: center; font-size: 150%">
            ${message}
        </div>
    </#if>

    <div class="container d-flex justify-content-center align-items-center" style="height: 500px; font-family: 'LEMON MILK'">
        <div class="row w-100 text-center">
            <div class="col-12 col-md-6 p-2">
                <div class="card">
                    <div class="card-body">
                        <p class="card-text">You are currently playing ${stats.descriptions} games </p>
                    </div>
                </div>
            </div>
            <div class="col-12 col-md-6 p-2">
                <div class="card">
                    <div class="card-body">
                        <p class="card-text">You have liked ${stats.likes} description from other users </p>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
            </div>
            <div class="col-12 col-md-6 p-2">
                <div class="card">
                    <div class="card-body">
                        <p class="card-text">You have successfully matched with ${stats.matches} other players </p>
                    </div>
                </div>
            </div>
        </div>

    </div>

</@layout.userMasterTemplate>

