<#-- @ftlvariable name="games" type="java.util.List<Game>" -->
<!DOCTYPE html>
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

    .btn-profile {
        background-color: #6AA8F5; !important;
        color: white;
    }

    .btn-profile:hover {
        background-color: rgb(79, 126, 183);
        color: white;
    }

    @media (min-width: 992px) {
        .manage {
            position: absolute;
        }
    }

    .profileSubT{
        color: white;
        text-align: center;
        font-family: "LEMON MILK";
    }


    .background {
        position: relative;
    }
    .background::before {
        content: "";
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background-image: url(/img/Background3.png);
        filter: brightness(50%);
    }

    .container {
        position: relative;
    }

    .modal-footer {
        justify-content: space-between;
    }

</style>
<body class="background">

    <div class="container mt-5 ">
        <h1 class="mb-5">
            <u>Welcome Administrator</u>
        </h1>

        <#if message??>
            <div class="alert alert-success alert-dismissible"  role="alert" style="color: black; font-size: 150%; font-family: 'LEMON MILK'; text-align: center; background-color: lightblue">
                ${message}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </#if>
        <div class="row d-flex justify-content-center">
            <div class="col-xs-12 w-50">
                <div id="media-list" class="row">

                    <div class="d-grid mb-3" style="font-family: 'LEMON MILK'">
                        <button type="button" class="btn btn-profile" data-bs-toggle="modal" data-bs-target="#createModal">
                            Add new Game
                        </button>

                        <form class="container" action="/creategame" role="form" method="post">
                            <div class="modal fade" id="createModal" tabindex="-1" aria-labelledby="createModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h2 class="modal-title" id="createModalLabel" style="color: black">Add Game</h2>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="form-floating pb-2">
                                                <input class="form-control" type="text" min="1" name="gameName" id="gameName" style="font-size: 120%; font-family: 'LEMON MILK'" required>
                                                <label for="gameName" style="color: black; font-family: 'LEMON MILK'">Game Name</label>
                                            </div>
                                            <div class="form-floating pb-2">
                                                <input class="form-control" type="text" min="1" name="gameCategory" id="gameCategory" style="font-size: 120%; font-family: 'LEMON MILK'" required>
                                                <label for="gameCategory" style="color: black; font-family: 'LEMON MILK'">Game Category</label>
                                            </div>
                                            <div class="form-floating pb-2">
                                                <input class="form-control" type="number" min="1" name="gameMaxLvl" id="gameMaxLvl" style="font-size: 120%; font-family: 'LEMON MILK'" required>
                                                <label for="gameMaxLvl" style="color: black; font-family: 'LEMON MILK'">Max Lvl</label>
                                            </div>
                                            <div class="form-floating pb-2">
                                                <input class="form-control" type="text" min="1" name="gameRanks" id="gameRanks" style="font-size: 120%; font-family: 'LEMON MILK'" required>
                                                <label for="gameRanks" style="color: black; font-family: 'LEMON MILK'">Game Ranks</label>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                            <button type="submit" class="btn btn-profile">Add</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>

                    <#list games>
                        <h4 class="profileSubT">
                            Current Games
                        </h4>
                        <#items as game>
                            <div class="row media-body my-3 position-relative" style="color: white; font-family: 'LEMON MILK'">
                                <div class="col-9">
                                    <h4>game: ${game.gameName}</h4>
                                    category: ${game.category}
                                    <br>
                                    max lvl: ${game.lvlMAX}
                                </div>
                                <div class="col">
                                    <div class="manage top-0 end-0">
                                        <button type="button" class="btn btn-profile" data-bs-toggle="modal" data-bs-target="#updateModal${game?index}" style="min-width: 100px">Update</button>
                                        <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal${game?index}" style="min-width: 100px">Delete</button>

                                        <form class="container" action="/updategame" role="form" method="post">
                                            <div class="modal fade" id="updateModal${game?index}" tabindex="-1" aria-labelledby="updateModalLabel" aria-hidden="true">
                                                <div class="modal-dialog modal-dialog-centered">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h2 class="modal-title" id="updateModalLabel" style="color: black">Update Game</h2>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <input type="hidden" name="gameName" id="gameName" value="${game.gameName}">
                                                            <div class="form-floating pb-2">
                                                                <input class="form-control" type="text" min="1" name="newCategory" id="newCategory" style="font-size: 120%">
                                                                <label for="newCategory" style="color: black">Category</label>
                                                            </div>
                                                            <div class="form-floating pb-2">
                                                                <input class="form-control" type="text" min="1" name="newMaxLvl" id="newMaxLvl" style="font-size: 120%">
                                                                <label for="newMaxLvl" style="color: black">Lvl</label>
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

                                        <form class="container" action="/deletegame" role="form" method="post">
                                            <div class="modal fade" id="deleteModal${game?index}" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
                                                <div class="modal-dialog modal-dialog-centered">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h2 class="modal-title" id="deleteModalLabel" style="color: black">Delete Description</h2>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <div style="color: black;">
                                                                Are you sure you want to delete this game?
                                                            </div>
                                                            <input type="hidden" name="gameName" id="gameName" value="${game.gameName}">
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
                                    <div class="manage bottom-0 end-0">
                                        <button type="button" class="btn btn-profile" data-bs-toggle="modal" data-bs-target="#createRank${game?index}" onclick="getElements('${game.gameName}', 'newRankId')" style="min-width: 100px">Add R</button>
                                        <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteRank${game?index}" onclick="getElements('${game.gameName}', 'newRankId2')" style="min-width: 100px">Delete R</button>

                                        <form class="container" action="/createrank" role="form" method="post">
                                            <div class="modal fade" id="createRank${game?index}" tabindex="-1" aria-labelledby="createRankModalLabel" aria-hidden="true">
                                                <div class="modal-dialog modal-dialog-centered">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h2 class="modal-title" id="createRankModalLabel" style="color: black">Add new Rank</h2>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <div class="mb-2" style="color: black;">
                                                                Current Ranks:
                                                            </div>
                                                            <div class="form-floating mb-2">
                                                                <select class="form-select" name="options" id="newRankId${game.gameName}" style="font-size: 120%">
                                                                    <option>

                                                                    </option>
                                                                </select>
                                                                <label for="newRankId${game.gameName}" style="color: black;">Rank</label>
                                                            </div>
                                                            <input type="hidden" name="gameName" id="gameName" value="${game.gameName}">
                                                            <div class="form-floating pb-2">
                                                                <input class="form-control" type="text" min="1" name="newRank" id="newRank" style="font-size: 120%">
                                                                <label for="newCategory" style="color: black">Rank</label>
                                                            </div>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                            <button type="submit" class="btn btn-profile">Add</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>

                                        <form class="container" action="/deleterank" role="form" method="post">
                                            <div class="modal fade" id="deleteRank${game?index}" tabindex="-1" aria-labelledby="deleteRankModalLabel" aria-hidden="true">
                                                <div class="modal-dialog modal-dialog-centered">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h2 class="modal-title" id="deleteRankModalLabel" style="color: black">Delete Rank</h2>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <div class="mb-2" style="color: black;">
                                                                Are you sure you want to delete this rank?
                                                            </div>
                                                            <input type="hidden" name="gameName" id="gameName" value="${game.gameName}">
                                                            <div class="form-floating">
                                                                <select class="form-select" name="newRank" id="newRankId2${game.gameName}" style="font-size: 120%">
                                                                    <option>

                                                                    </option>
                                                                </select>
                                                                <label for="newRankId2${game.gameName}" style="color: black;">Rank</label>
                                                            </div>
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
                    <div class="d-flex justify-content-center" style="font-family: 'LEMON MILK'">
                            <a class="btn btn-danger" href="/logout" role="button">Sign Out</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>

<script type="text/javascript">

    let gameRanksMap = new Map()
    let gameName;
    let ranks;
    <#list games as game>
    gameName = '${game.gameName}';
    ranks = [<#list game.ranks as rank>'${rank.rankName}',</#list>]
    gameRanksMap.set(gameName, ranks)
    </#list>

    function getElements (gameName, id) {
        ranks = gameRanksMap.get(gameName);
        addOptions("newRank", ranks, gameName, id);
    }

    function addOptions(domElement, array, gameName, id) {
        const select = document.getElementById(id + gameName)
        select.length = 0
        for (const value in array) {
            const option = document.createElement("option");
            option.text = array[value];
            select.add(option);
        }
    }


</script>




</body>
</html>