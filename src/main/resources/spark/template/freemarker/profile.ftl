<#-- @ftlvariable name="descriptions" type="java.util.List<GamerDescription>" -->
<#-- @ftlvariable name="games" type="java.util.List<Game>" -->
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
<@layout.userMasterTemplate title="Profile">

    <h1>
        <u>Welcome to your profile</u>
    </h1>

    <div class="container mt-5">
        <#if message??>
            <div class="alert alert-success alert-dismissible"  role="alert" style="color: black; font-size: 150%; font-family: 'LEMON MILK'; text-align: center; background-color: lightblue">
                ${message}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </#if>
        <div class="row d-flex justify-content-center">
            <div class="col-xs-12 w-50">
                <div id="media-list" class="row">

                    <div class="d-grid" style="font-family: 'LEMON MILK'">
                        <button type="button" class="btn btn-profile" data-bs-toggle="modal" data-bs-target="#createModal">
                            Add new description
                        </button>

                        <form class="container" action="/createdescription" role="form" method="post">
                            <div class="modal fade" id="createModal" tabindex="-1" aria-labelledby="createModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h2 class="modal-title" id="createModalLabel" style="color: black">Create Description</h2>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <#list games>
                                        <div class="modal-body">
                                                <div class="form-floating pb-2">
                                                    <select class="form-select" name="gameName" id="gameName" style="font-size: 120%; font-family: 'LEMON MILK'">
                                                        <#items as game>
                                                            <option value="${game.gameName}">${game.gameName}</option>
                                                        </#items>
                                                    </select>
                                                    <label for="gameName" style="color: black; font-family: 'LEMON MILK'">Game</label>
                                                </div>
                                                <div class="form-floating pb-2">
                                                    <input class="form-control" type="text" min="1" name="gamerLvl" id="gamerLvl" style="font-size: 120%; font-family: 'LEMON MILK'">
                                                    <label for="gamerLvl" style="color: black; font-family: 'LEMON MILK'">Lvl</label>
                                                </div>
                                                <div class="form-floating">
                                                    <select class="form-select" name="gamerRank" id="gamerRank" style="font-size: 120%; font-family: 'LEMON MILK'">
                                                        <option>

                                                        </option>
                                                    </select>
                                                    <label for="gamerRank" style="color: black; font-family: 'LEMON MILK'">Rank</label>
                                                </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                            <button type="submit" class="btn btn-profile">Create</button>
                                        </div>
                                        <#else>
                                        <div class="modal-body">
                                            <p>You have a description in every game</p>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                        </div>
                                        </#list>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>

                        <#list descriptions>
                            <h4 class="profileSubT">
                                Here are your game descriptions
                            </h4>
                            <#items as description>
                                <div class="row media-body my-3 position-relative" style="color: white; font-family: 'LEMON MILK'">
                                    <div class="col">
                                        <h4>Game: ${description.game.gameName}</h4>
                                        lvl: ${description.lvl}
                                        <br>
                                        rank: ${description.rank.rankName}
                                    </div>
                                    <div class="col">
                                        <div class="manage bottom-0 end-0">
                                            <button type="button" class="btn btn-profile" data-bs-toggle="modal" data-bs-target="#updateModal${description.game.gameName}" onclick="getElements('${description.game.gameName}')">Update</button>
                                            <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal${description.game.gameName}">Delete</button>

                                            <form class="container" action="/updatedescription" role="form" method="post">
                                                <div class="modal fade" id="updateModal${description.game.gameName}" tabindex="-1" aria-labelledby="updateModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog modal-dialog-centered">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h2 class="modal-title" id="updateModalLabel" style="color: black">Update Description</h2>
                                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <input type="hidden" name="gameName" id="gameName" value="${description.game.gameName}">
                                                                <div class="form-floating pb-2">
                                                                    <input class="form-control" type="text" min="1" name="newLvl" id="newLvlId${description.game.gameName}" style="font-size: 120%">
                                                                    <label for="newLvlId${description.game.gameName}" style="color: black">Lvl</label>

                                                                </div>
                                                                <div class="form-floating">
                                                                    <select class="form-select" name="newRank" id="newRankId${description.game.gameName}" style="font-size: 120%">
                                                                        <option>

                                                                        </option>
                                                                    </select>
                                                                    <label for="newRankId${description.game.gameName}" style="color: black;">Rank</label>
                                                                </div>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                                <button type="submit" class="btn btn-profile">Update</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>

                                            <form class="container" action="/deletedescription" role="form" method="post">
                                                <div class="modal fade" id="deleteModal${description.game.gameName}" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog modal-dialog-centered">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h2 class="modal-title" id="deleteModalLabel" style="color: black">Delete Description</h2>
                                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <div style="color: black;">
                                                                    Are you sure you want to delete this description?
                                                                </div>
                                                                <input type="hidden" name="stringValue" id="stringValue" value="${description.game.gameName}, ${description.lvl}, ${description.rank.rankName}">
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                                <button type="submit" class="btn btn-danger">Delete</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>

                                        </div>
                                    </div>
                                </div>
                                <hr style="height: 10px; color: white">
                            </#items>
                            <#else>
                                <h4 class="profileSubT">
                                    You have no descriptions
                                </h4>
                        </#list>
                </div>
            </div>
        </div>
    </div>

</@layout.userMasterTemplate>

<script type="text/javascript">

    let levelMap = new Map();
    let gameRanksMap = new Map()
    let gameName;
    let gameLevel;
    let ranks;
    <#list games as game>
    gameName = '${game.gameName}';
    ranks = [<#list game.ranks as rank>'${rank.rankName}',</#list>]
    gameRanksMap.set(gameName, ranks)
    </#list>

    <#list descriptions as description>
    gameName = '${description.game.gameName}';
    gameLevel = ${description.lvl};
    ranks = [<#list description.game.ranks as rank>'${rank.rankName}',</#list>]
    levelMap.set(gameName,gameLevel);
    gameRanksMap.set(gameName, ranks)
    </#list>

    function getElements (gameName) {
        let level = document.querySelector('#newLvlId' + gameName);
        const levelOutput = levelMap.get(gameName);
        const ranksOutput = gameRanksMap.get(gameName);
        level.value = levelOutput;
        ranks = ranksOutput;
        addOptions("newRank", ranks, gameName);
    }

    function addOptions(domElement, array, gameName) {
        const select = document.getElementById('newRankId' + gameName)
        select.length = 0
        for (const value in array) {
            const option = document.createElement("option");
            option.text = array[value];
            select.add(option);
        }
    }

    const selectedGame = document.getElementById('gameName')
    function onChange() {
        const game = selectedGame.value;
        const ranksOutput = gameRanksMap.get(game);
        addOptions2("const", ranksOutput)
    }
    selectedGame.onchange = onChange;
    onChange();

    function addOptions2(domElement, array) {
        const select = document.getElementById('gamerRank')
        select.length = 0
        for (const value in array) {
            const option = document.createElement("option");
            option.text = array[value];
            select.add(option);
        }
    }

</script>
