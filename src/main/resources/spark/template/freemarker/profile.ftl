<#-- @ftlvariable name="descriptions" type="java.util.List<GamerDescription>" -->
<#-- @ftlvariable name="games" type="java.util.List<Game>" -->
<style>
</style>
<#import "userMasterTemplate.ftl" as layout />
<@layout.userMasterTemplate title="Profile">

    <h1>
        <u>Welcome to your profile</u>
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
                                                <div class="form-floating">
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
                                        <div class="position-absolute bottom-0 end-0">
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


<!--<!DOCTYPE html>
<html lang="en">
<head>
    <title>Welcome!</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<style>

    h1{
        color: white;
        font-size: 300%;
        text-align: center;
        font-family: "LEMON MILK";
    }

    .content {
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

    p{
        color: #45cb85;
        text-align: center;
        font-family: "LEMON MILK";
        font-size: 200%;
    }

    button{
        background-color: #45cb85;
        border-color: #45cb85;
        font-size: 170%;
    }

    .hpl{
        color: darkgreen;
        font-family: "LEMON MILK";
        text-decoration: none;
    }
</style>
<body>
<div class="sidebar">
    <a class="active" href="/home">Home</a>
    <a href="/profile">Profile</a>
    <a class="active" href="/manageinterest">Interests</a>
    <a class="active" href="/findplayers">Players</a>
    <a class="active" href="/viewmatch">Matches</a>
    <br>
    <br>
    <br>
    <a class="leave" href="/logout">Sign Out</a>
</div>

<div class="content">
    <h1>
        <u>Welcome to your profile</u>
    </h1>
    <br>
    <#if message??>
        <div class="alert alert-success" style="color: black; font-size: 150%; font-family: 'LEMON MILK';
         background-color: lightblue; text-align: center;">
            ${message}
        </div>
    </#if>
    <br>
    <br>
    <p>Create a new game description</p>
    <button>
        <a href="/createdescription" class="hpl">Create Description</a>
    </button>
    <br>
    <br>
    <p>Update your descriptions</p>
    <button>
        <a href="/updatedescription" class="hpl">Update Description</a>
    </button>
    <br>
    <br>
    <p>Delete your descriptions</p>
    <button>
        <a href="/deletedescription" class="hpl">Delete Description</a>
    </button>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
</body>
</html>