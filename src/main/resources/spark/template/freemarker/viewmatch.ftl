<#-- @ftlvariable name="matches" type="java.util.List<GamerUser>" -->
<#-- @ftlvariable name="image" type="java.lang.String" -->
<#-- @ftlvariable name="notifications" type="java.util.List<Notification>" -->
<style>

    @media (min-width: 768px) {
        .manage {
            position: absolute;
        }
    }
</style>
<#import "userMasterTemplate.ftl" as layout />
<@layout.userMasterTemplate title="View match">

    <h1 class="pb-5">
        <u>Here are your matches</u>
    </h1>

    <#if message??>
        <div class="alert alert-success" style="color: black; font-size: 150%; font-family: 'LEMON MILK';
         background-color: lightblue; text-align: center;">
            ${message}
        </div>
    </#if>

    <div class="container mt-5">
        <div class="row d-flex justify-content-center">
            <div class="col-xs-12 w-50">
                <div id="media-list" class="row">
                        <#list matches>
                            <#items as match>
                                <div class="row media-body my-3 position-relative" style="color: white; font-family: 'LEMON MILK'">
                                    <div class="col">
                                        <h4>Gamer: ${match.value0.userName}</h4>
                                        Game: ${match.value1.gameName}
                                    </div>
                                    <div class="col">
                                        <div class="manage bottom-0 end-0">
                                            <a href="/viewplayerprofile/${match.value0.userName}" style="text-decoration: none;">
                                                <button type="button" class="btn btn-profile" >Profile</button>
                                            </a>
                                            <a href="/chat/${match.value0.userName}" style="text-decoration: none;">
                                                <button type="button" class="btn btn-profile" >Chat </button>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                <hr style="height: 10px; color: white">
                            </#items>
                            <#else >
                                <h4 class="profileSubT">
                                    You have no matches
                                </h4>
                        </#list>
                </div>
            </div>
        </div>
    </div>


</@layout.userMasterTemplate>

