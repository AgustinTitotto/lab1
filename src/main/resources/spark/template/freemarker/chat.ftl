<#-- @ftlvariable name="messages" type="java.util.List<Message>" -->
<#-- @ftlvariable name="sender" type="java.lang.String" -->
<#-- @ftlvariable name="receiver" type="java.lang.String" -->
<style>

    .form-wrapper {
        font-family: "LEMON MILK";
    }

    .message{
        word-break: break-word;
        border: 2px solid #dedede;
        border-radius: 5px;
    }

    .message p{
        margin: 0;
        padding: 0;
        text-align: right;
        font-family: "LEMON MILK";
        color: white;

    }

    @media (min-width: 576px) {
        .card {
            width: 75%;
        }
    }

</style>
<#import "userMasterTemplate.ftl" as layout />

<@layout.userMasterTemplate title="View match">


        <div class="container d-flex justify-content-center">
                <div class="card" style="height: calc(100vh - 125px); background-color: #1b1f26; border-radius: 1em">
                <div class="card-header">
                    <h1 style="text-align: left">${receiver}</h1>
                </div>
                <div class="card-body overflow-auto" style="position: relative">
                    <#if messages??>
                        <#list messages as message>
                            <#if message.sender == sender>
                                <div class="message mb-3">
                                    <p>${message.message}</p>
                                    <p>${message.date}</p>
                                </div>
                            </#if>
                            <#if message.sender != sender>
                                <div class="message mb-3">
                                    <p style="text-align: left; margin: 0; padding: 0">${message.message}</p>
                                    <p style="color: #aaa; text-align: left; margin: 0; padding: 0">${message.date}</p>
                                </div>
                            </#if>
                        </#list>
                    </#if>
                </div>
                <div class="card-footer d-flex align-items-center">
                    <div class="form-wrapper w-100">
                        <form id="message-form" class="message-form mb-0" action = "/chat/${receiver}" role="form" method="post">
                            <div class="input-group ">
                                <div class="input-group ">
                                    <input type="text" class="form-control" id="message" name="message" placeholder="Send message..." required/>
                                    <button type="submit" class="btn btn-success" style="color:white; width: 15%">Send
                                        <a href="/chat/${receiver}" style="text-decoration: none"></a>
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

</@layout.userMasterTemplate>
