<#-- @ftlvariable name="myName" type="java.lang.String" -->
<#-- @ftlvariable name="image" type="java.lang.String" -->
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

</@layout.userMasterTemplate>

