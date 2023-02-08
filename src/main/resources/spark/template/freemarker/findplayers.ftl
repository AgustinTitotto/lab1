<#-- @ftlvariable name="descriptions" type="java.util.List<GamerDescription>" -->
<#-- @ftlvariable name="userNames" type="java.util.List<String>" -->
<#-- @ftlvariable name="image" type="java.lang.String" -->
<#-- @ftlvariable name="notifications" type="java.util.List<Notification>" -->
<#import "userMasterTemplate.ftl" as layout />
<style>

    #board {
        height: 100%;

    }
    .myCard {
        font-family: "LEMON MILK";
        width: 320px;
        height: 320px;
        position: absolute;
        top: 50%;
        left: 50%;
        border-radius: 1%;
        box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.1);
        background-color: white;
        transform: translateX(-50%) translateY(-50%) scale(0.95);
    }

    .profile-pic {
        border-radius:50%;
        width:200px;
        height:200px;
    }


</style>
<@layout.userMasterTemplate title="FindPlayers">

    <h1>
        <u> Find Players </u>
    </h1>
    <#if message??>
        <div class="toast" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="toast-body">
                ${message}
                <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
        </div>
    </#if>

    <div id="board">

    </div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/hammer.js/2.0.8/hammer.min.js">
</script>
<script>
    /* LikeCarousel (c) 2019 Simone P.M. github.com/simonepm - Licensed MIT */
    let myCounter = 0
    let fullData = [<#list descriptions as description>"${description.gamerUser.userName}, ${description.game.gameName}, ${description.lvl}, ${description.rank.rankName}", </#list>]
    let users = [<#list descriptions as description>"${description.gamerUser.userName}", </#list>]
    let games = [<#list descriptions as description>"${description.game.gameName}", </#list>]
    let levels = [<#list descriptions as description>"${description.lvl}", </#list>]
    let ranks = [<#list descriptions as description>"${description.rank.rankName}", </#list>]
    let images = [<#list descriptions as description>"${description.gamerUser.image}", </#list>]
    class Carousel {
        constructor(element) {
            this.board = element
            // add first two cards programmatically
            this.push()
            // handle gestures
            this.handle()
        }
        handle() {
            // list all cards
            this.cards = this.board.querySelectorAll('.myCard')
            // get top card
            this.topCard = this.cards[this.cards.length - 1]
            // get next card
            this.nextCard = this.cards[this.cards.length - 2]
            // if at least one card is present
            if (this.cards.length > 0) {
                // set default top card position and scale
                this.topCard.style.transform =
                    'translateX(-50%) translateY(-50%) rotate(0deg) rotateY(0deg) scale(1)'
                // destroy previous Hammer instance, if present
                if (this.hammer) this.hammer.destroy()
                // listen for tap and pan gestures on top card
                this.hammer = new Hammer(this.topCard)
                this.hammer.add(new Hammer.Tap())
                this.hammer.add(new Hammer.Pan({
                    position: Hammer.position_ALL,
                    threshold: 0
                }))
                // pass events data to custom callbacks
                this.hammer.on('tap', (e) => {
                    this.onTap(e)
                })
                this.hammer.on('pan', (e) => {
                    this.onPan(e)
                })
            }
        }
        onTap(e) {
            // get finger position on top card
            let propX = (e.center.x - e.target.getBoundingClientRect().left) / e.target.clientWidth
            // get rotation degrees around Y axis (+/- 15) based on finger position
            let rotateY = 15 * (propX < 0.05 ? -1 : 1)
            // enable transform transition
            this.topCard.style.transition = 'transform 100ms ease-out'
            // apply rotation around Y axis
            this.topCard.style.transform =
                'translateX(-50%) translateY(-50%) rotate(0deg) rotateY(' + rotateY + 'deg) scale(1)'
            // wait for transition end
            setTimeout(() => {
                // reset transform properties
                this.topCard.style.transform =
                    'translateX(-50%) translateY(-50%) rotate(0deg) rotateY(0deg) scale(1)'
            }, 100)
        }
        onPan(e) {
            if (!this.isPanning) {
                this.isPanning = true
                // remove transition properties
                this.topCard.style.transition = null
                if (this.nextCard) this.nextCard.style.transition = null
                // get top card coordinates in pixels
                let style = window.getComputedStyle(this.topCard)
                let mx = style.transform.match(/^matrix\((.+)\)$/)
                this.startPosX = mx ? parseFloat(mx[1].split(', ')[4]) : 0
                this.startPosY = mx ? parseFloat(mx[1].split(', ')[5]) : 0
                // get top card bounds
                let bounds = this.topCard.getBoundingClientRect()
                // get finger position on top card, top (1) or bottom (-1)
                this.isDraggingFrom =
                    (e.center.y - bounds.top) > this.topCard.clientHeight / 2 ? -1 : 1
            }
            // get new coordinates
            let posX = e.deltaX + this.startPosX
            let posY = e.deltaY + this.startPosY
            // get ratio between swiped pixels and the axes
            let propX = e.deltaX / this.board.clientWidth
            let propY = e.deltaY / this.board.clientHeight
            // get swipe direction, left (-1) or right (1)
            let dirX = e.deltaX < 0 ? -1 : 1
            // get degrees of rotation, between 0 and +/- 45
            let deg = this.isDraggingFrom * dirX * Math.abs(propX) * 45
            // get scale ratio, between .95 and 1
            let scale = (95 + (5 * Math.abs(propX))) / 100
            // move and rotate top card
            this.topCard.style.transform =
                'translateX(' + posX + 'px) translateY(' + posY + 'px) rotate(' + deg + 'deg) rotateY(0deg) scale(1)'
            // scale up next card
            if (this.nextCard) this.nextCard.style.transform =
                'translateX(-50%) translateY(-50%) rotate(0deg) rotateY(0deg) scale(' + scale + ')'
            if (e.isFinal) {
                this.isPanning = false
                let successful = false
                // set back transition properties
                this.topCard.style.transition = 'transform 200ms ease-out'
                if (this.nextCard) this.nextCard.style.transition = 'transform 100ms linear'
                // check threshold and movement direction
                if (propX > 0.25 && e.direction == Hammer.DIRECTION_RIGHT) {
                    successful = true
                    // get right border position
                    posX = this.board.clientWidth
                } else if (propX < -0.25 && e.direction == Hammer.DIRECTION_LEFT) {
                    successful = true
                    // get left border position
                    posX = -(this.board.clientWidth + this.topCard.clientWidth)
                } else if (propY < -0.25 && e.direction == Hammer.DIRECTION_UP) {
                    successful = true
                    // get top border position
                    posY = -(this.board.clientHeight + this.topCard.clientHeight)
                }
                if (successful) {
                    // throw card in the chosen direction
                    this.topCard.style.transform =
                        'translateX(' + posX + 'px) translateY(' + posY + 'px) rotate(' + deg + 'deg)'
                    if (this.startPosX < posX) {
                        let xhr = new XMLHttpRequest();
                        xhr.open('POST', 'http://localhost:4335/findplayers', true)
                        xhr.setRequestHeader("Content-Type", "application/xhtml+xml; charset=utf-8")
                        const data = fullData[myCounter]
                        xhr.send(data)
                    }
                    myCounter = myCounter + 1
                    // wait transition end
                    setTimeout(() => {
                        // remove swiped card
                        this.board.removeChild(this.topCard)
                        // add new card
                        this.push()
                        // handle gestures on new top card
                        this.handle()
                    }, 200)
                } else {
                    // reset cards position and size
                    this.topCard.style.transform =
                        'translateX(-50%) translateY(-50%) rotate(0deg) rotateY(0deg) scale(1)'
                    if (this.nextCard) this.nextCard.style.transform =
                        'translateX(-50%) translateY(-50%) rotate(0deg) rotateY(0deg) scale(0.95)'
                }
            }
        }
        push() {
            if (fullData.length > myCounter) {
                let card = document.createElement('div')
                let imageDiv = document.createElement('div')
                let textDiv = document.createElement('div')
                let image = document.createElement('img')

                image.src = "data:image/jpeg;base64," + images[myCounter]
                image.alt = "User"
                image.style.maxHeight = "100%"
                image.className = "profile-pic"
                imageDiv.appendChild(image);
                imageDiv.className = "d-flex align-items-center justify-content-center mb-2 "
                imageDiv.style.height = "50%"

                let userName = document.createElement('p')
                let game = document.createElement('p')
                let level = document.createElement('p')
                let rank = document.createElement('p')
                userName.innerText = "Gamer: " + users[myCounter]
                game.innerText = "Game: " + games[myCounter]
                level.innerText = "Level: " + levels[myCounter]
                rank.innerText = "Rank: " + ranks[myCounter]
                textDiv.appendChild(userName)
                textDiv.appendChild(game)
                textDiv.appendChild(level)
                textDiv.appendChild(rank)

                card.classList.add('myCard')
                card.appendChild(imageDiv)
                card.appendChild(textDiv)
                this.board.appendChild(card)
                //this.board.insertBefore(card, this.board.firstChild)
            }
            else {
                let text = document.createElement('div')
                text.className = "d-flex align-items-center justify-content-center"
                text.style.fontFamily = 'LEMON MILK'
                text.innerText = "There are no more matches"
                text.style.color = "white"
                text.style.height = "50%"
                this.board.insertBefore(text, this.board.firstChild)
            }
        }
    }
    let board = document.querySelector('#board')
    let carousel = new Carousel(board)
</script>

</@layout.userMasterTemplate>


<!--
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

    .container{
        font-size: 125%;
        color: #45cb85;
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
</style>
<body>
-->
<!-- <a id="user"></a>-->
<!--
<div class="sidebar">
    <a class="active" href="/home">Home</a>
    <a class="active" href="/profile">Profile</a>
    <a class="active" href="/manageinterest">Interests</a>
    <a href="/findplayers">Players</a>
    <a class="active" href="/viewmatch">Matches</a>
    <br>
    <br>
    <br>
    <a class="leave" href="/logout">Sign Out</a>
</div>

<div class="content">
    <h1>
        <u>Meet the people who would</u><br>
        <u>like to play with you</u>
    </h1>
    <br>
    <br>
    <br>
    <form class="container" action="/findplayers" role="form" method="post">
        <select style="font-size: 150%; background-color: #45cb85; border-color: #45cb85;" name="gamers" id="gamers">
            <#list descriptions as description>
                <option value="${description.gamerUser.userName}, ${description.game.gameName}, ${description.lvl}, ${description.rank.rankName}">
                    ${description.gamerUser.userName} - ${description.game.gameName} - ${description.lvl} - ${description.rank.rankName}</option>
            </#list>
        </select>
        <br>
        <br>
        <button type="submit" style="font-size: 150%; background-color: #45cb85; border-color: #45cb85;font-family: 'LEMON MILK'">
        Like
        </button>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
</body>
-->
<!--<script>
    let arr = [<#list userNames as userName>"${userName}", </#list>]
    let desc = [<#list descriptions as description>"${description.game.gameName}", </#list>]
    let a = arr[0]
    document.write(a)
    document.getElementById("user").innerHTML = a;
</script>
</html>
-->