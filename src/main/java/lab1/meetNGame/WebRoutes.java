package lab1.meetNGame;

import lab1.meetNGame.UI.*;
import lab1.meetNGame.model.*;
import spark.*;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.*;

import static spark.Spark.*;

public class WebRoutes {

    private static final String GAMER_SESSION_ID = "gamer";

    /**
     * TEMPLATES
     **/
    public static final String SIGN_UP_TEMPLATE = "register.ftl";
    public static final String LOGIN_TEMPLATE = "login.ftl";
    public static final String HOME_TEMPLATE = "home.ftl";
    public static final String ADMIN_HOME_TEMPLATE = "adminhome.ftl";
    public static final String CREATE_GAME_TEMPLATE = "creategame.ftl";
    public static final String CREATE_DESCRIPTION_TEMPLATE = "createdescription.ftl";
    public static final String CREATE_INTEREST_TEMPLATE = "createinterest.ftl";
    public static final String FIND_PLAYERS_TEMPLATE = "findplayers.ftl";
    public static final String VIEW_MATCH_TEMPLATE = "viewmatch.ftl";
    public static final String UPDATE_GAME_TEMPLATE = "updategame.ftl";
    public static final String DELETE_GAME_TEMPLATE = "deletegame.ftl";
    public static final String PROFILE_TEMPLATE = "profile.ftl";
    public static final String UPDATE_DESCRIPTION_TEMPLATE = "updatedescription.ftl";
    public static final String DELETE_DESCRIPTION_TEMPLATE = "deletedescription.ftl";
    public static final String MANAGE_INTEREST_TEMPLATE = "manageinterest.ftl";
    public static final String UPDATE_INTEREST_TEMPLATE = "updateinterest.ftl";
    public static final String DELETE_INTEREST_TEMPLATE = "deleteinterest.ftl";
    public static final String MANAGE_RANK_TEMPLATE = "/manageranks.ftl";
    public static final String CREATE_RANK_TEMPLATE = "createrank.ftl";
    public static final String DELETE_RANK_TEMPLATE = "deleterank.ftl";
    public static final String VIEW_PLAYER_PROFILE = "viewplayerprofile.ftl";


    /**
     * ROUTES
     **/
    //public static final String STATUS_ROUTE = "/status";
    public static final String HOME_ROUTE = "/home";
    public static final String REGISTER_ROUTE = "/register";
    public static final String LOGIN_ROUTE = "/login";
    public static final String LOGOUT_ROUTE = "/logout";
    public static final String ADMIN_HOME_ROUTE = "/admin";
    public static final String CREATE_GAME_ROUTE = "/creategame";
    public static final String CREATE_DESCRIPTION_ROUTE = "/createdescription";
    public static final String CREATE_INTEREST_ROUTE = "/createinterest";
    public static final String FIND_PLAYERS_ROUTE = "/findplayers";
    public static final String VIEW_MATCH_ROUTE = "/viewmatch";
    public static final String UPDATE_GAME_ROUTE = "/updategame";
    public static final String DELETE_GAME_ROUTE = "/deletegame";
    public static final String PROFILE_ROUTE = "/profile";
    public static final String UPDATE_DESCRIPTION_ROUTE = "/updatedescription";
    public static final String DELETE_DESCRIPTION_ROUTE = "/deletedescription";
    public static final String MANAGE_INTEREST_ROUTE = "/manageinterest";
    public static final String UPDATE_INTEREST_ROUTE = "/updateinterest";
    public static final String DELETE_INTEREST_ROUTE = "/deleteinterest";
    public static final String MANAGE_RANK_ROUTE = "/manageranks";
    public static final String CREATE_RANK_ROUTE = "/createrank";
    public static final String DELETE_RANK_ROUTE = "/deleterank";
    public static final String VIEW_PLAYER_ROUTE = "/viewplayerprofile";

    final static private WebSystem system = new WebSystem();

    public void startRoutes() {
        webroutes();
    }

    private void webroutes() {
        get(REGISTER_ROUTE, (req, res) -> {
            final Optional<GamerUser> authenticatedGamerUser = getAuthenticatedGamerUser(req);
            if (authenticatedGamerUser.isEmpty()) {
                return render(SIGN_UP_TEMPLATE);
            }
            else if(authenticatedGamerUser.get().isAdmin()){ //si el usuario ya esta logeado, lleva a la pagina correspondiente
                res.redirect(ADMIN_HOME_ROUTE);
                return halt();
            }
            else{
                res.redirect(HOME_ROUTE);
                return halt();
            }
        });
        post(REGISTER_ROUTE, (req, res) -> {
            final Optional<GamerUser> authenticatedGamerUser = getAuthenticatedGamerUser(req);
            if (authenticatedGamerUser.isEmpty()){
                final SignUpForm form = SignUpForm.createFromBody(req.body());
                final GamerUser gamer = system.registerGamer(form);
                if (gamer != null) {
                    res.redirect("/login?ok");
                    return halt();
                } else {
                    final Map<String, Object> model = Map.of("message", "UserName already exists");
                    return render(model, SIGN_UP_TEMPLATE);
                }
            }
            else if(authenticatedGamerUser.get().isAdmin()){ //si el usuario ya esta logeado, lleva a la pagina correspondiente
                res.redirect(ADMIN_HOME_ROUTE);
                return halt();
            }
            else{
                res.redirect(HOME_ROUTE);
                return halt();
            }
        });
        get(LOGIN_ROUTE, (req, res) -> {
            final Optional<GamerUser> authenticatedGamerUser = getAuthenticatedGamerUser(req);
            if (authenticatedGamerUser.isEmpty()) {
                final Map<String, Object> model = new HashMap<>();

                if (req.queryParams("ok") != null) model.put("message", "User created");

                return render(model, LOGIN_TEMPLATE);
            }
            else if(authenticatedGamerUser.get().isAdmin()){ //si el usuario ya esta logeado, lleva a la pagina correspondiente
                res.redirect(ADMIN_HOME_ROUTE);
                return halt();
            }
            else{
                res.redirect(HOME_ROUTE);
                return halt();
            }
        });

        post(LOGIN_ROUTE, (req, res) ->{ //chequeamos que no haya un usuario ya iniciado, vemos si es admin o no.
            final Optional<GamerUser> authenticatedGamerUser = getAuthenticatedGamerUser(req);
            if (authenticatedGamerUser.isEmpty()){
                LogInForm logInForm = LogInForm.createFromBody(req.body());
                final Optional<GamerUser> validUser = system.checkLogin(logInForm);
                if (validUser.isPresent()) {
                    setAuthenticatedGamerUser(req, validUser.get());
                    if (!validUser.get().isAdmin()) {
                        res.redirect(HOME_ROUTE);
                    } else {
                        res.redirect(ADMIN_HOME_ROUTE);
                    }
                    return halt();
                } else {
                    final Map<String, Object> model = new HashMap<>();
                    model.put("message", "Invalid user or password");
                    return render(model, LOGIN_TEMPLATE);
                }
            } /*else {
                res.redirect(LOGIN_ROUTE);      //Esto vuelve a hacer el get de Login, lleva a home o adminHome.
                return halt();
            }*/
            else if(authenticatedGamerUser.get().isAdmin()){ //si el usuario ya esta logeado, lleva a la pagina correspondiente
                res.redirect(ADMIN_HOME_ROUTE);
                return halt();
            }
            else{
                res.redirect(HOME_ROUTE);
                return halt();
            }
        });

        get(LOGOUT_ROUTE, (req, res) -> {
            clearAuthenticatedGamerUser(req);
            res.redirect(LOGIN_ROUTE);
            return halt();
        });

        authenticatedGet(HOME_ROUTE, (req, res) -> {
            final Optional<GamerUser> authenticatedGamerUser = getAuthenticatedGamerUser(req);
            if (!authenticatedGamerUser.get().isAdmin()) { //Revisa que no sea admin
                String name = authenticatedGamerUser.get().getUserName();
                final Map<String, Object> model = new HashMap<>();
                model.put("myName", name);
                if(system.getMessage() != null) {
                    model.put("message", system.getMessage());
                    system.setMessage(null);
                }
                if (req.queryParams("noGames") != null) model.put("message", "No games created yet");
                if (req.queryParams("liked") != null) model.put("message", "Liked user");
                if (req.queryParams("updatedInterest") != null) model.put("message", "Interest updated");
                if (req.queryParams("updatedDescription") != null) model.put("message", "Description updated");


                return render(model, HOME_TEMPLATE);
            }else{
                final Map<String, Object> model = new HashMap<>();
                model.put("message", "User is Admin");
                return render(model, ADMIN_HOME_TEMPLATE);
            }
        });

        authenticatedGet(ADMIN_HOME_ROUTE, (req, res) -> {
            final Optional<GamerUser> authenticatedGamerUser = getAuthenticatedGamerUser(req);
            if (authenticatedGamerUser.get().isAdmin()){ //Revisa que no sea un gamer
                final Map<String, Object> model = new HashMap<>();

                if (req.queryParams("gameOk") != null) model.put("message", "Game created");
                if (req.queryParams("updatedGame") != null) model.put("message", "Game updated");
                if (req.queryParams("deletedGame") != null) model.put("message", "Game deleted");
                if (req.queryParams("updatedRanks") != null) model.put("message", "Ranks updated");
                if (req.queryParams("deletedRanks") != null) model.put("message", "Rank deleted");


                return render(model, ADMIN_HOME_TEMPLATE);
            }
            else {
                final Map<String, Object> model = new HashMap<>();
                String name = authenticatedGamerUser.get().getUserName();
                model.put("message", "User is not Admin");
                model.put("myName", name);
                return render(model, HOME_TEMPLATE);
            }
        });

        authenticatedGet(CREATE_GAME_ROUTE, (req, res) -> {
            final Optional<GamerUser> authenticatedGamerUser = getAuthenticatedGamerUser(req);
            if (authenticatedGamerUser.get().isAdmin()){    //Revisa que no sea un gamer
                return render(CREATE_GAME_TEMPLATE);
            }
            else {                                          //Lleva a la pagina del gamer
                final Map<String, Object> model = new HashMap<>();
                String name = authenticatedGamerUser.get().getUserName();
                model.put("message", "User is not Admin");
                model.put("myName", name);
                return render(model, HOME_TEMPLATE);
            }
        });

        authenticatedPost(CREATE_GAME_ROUTE, (req, res) -> {
            final Optional<GamerUser> authenticatedGamerUser = getAuthenticatedGamerUser(req);
            if (authenticatedGamerUser.get().isAdmin()){    //Revisa que no sea un gamer
                CreateGameForm gameForm = CreateGameForm.createFromBody(req.body());
                final Game validGame = system.registerGame(gameForm);
                if (validGame != null){                     //Si el juego no existe lo crea
                    res.redirect("/admin?gameOk");
                    return halt();
                }
                else {                                      //Mensaje por si el juego ya existe
                    final Map<String, Object> model = new HashMap<>();
                    model.put("message", "GameName already exists");
                    return render(model, CREATE_GAME_TEMPLATE);
                }
            }
            else {                                          //Lleva a la pagina del gamer
                final Map<String, Object> model = new HashMap<>();
                String name = authenticatedGamerUser.get().getUserName();
                model.put("message", "User is not Admin");
                model.put("myName", name);
                return render(model, HOME_TEMPLATE);
            }
        });

        getGames(UPDATE_GAME_ROUTE, UPDATE_GAME_TEMPLATE);

        authenticatedPost(UPDATE_GAME_ROUTE, (req, res) -> {
            final Optional<GamerUser> authenticatedGamerUser = getAuthenticatedGamerUser(req);
            if (authenticatedGamerUser.get().isAdmin()){            //Revisa que no sea un gamer
                UpdateGameForm updateGameForm = UpdateGameForm.createFromBody(req.body());
                if (!system.gameExists(updateGameForm.getGameName())){ //Revisa que el juego exista
                    res.redirect("/updategame?notGame");
                    return halt();
                }
                else {
                    if (updateGameForm.getGameName().equals("")){   //Revisa que elija un juego
                        res.redirect("/deletegame?noGameSelected");
                        return halt();
                    }
                    else if (updateGameForm.getCategory().equals("") && updateGameForm.getLvlMax().equals("")){ //Revisa que por lo menos un atributo este lleno
                        res.redirect("/updategame?notOk");
                        return halt();
                    }
                    else if (updateGameForm.getCategory().equals("")){  //Actualiza por nivel
                        system.updateGameLvl(updateGameForm.getGameName(), updateGameForm.getLvlMax());
                        res.redirect("/admin?updatedGame");
                        return halt();
                    }
                    else if ((updateGameForm.getLvlMax().equals(""))){  //Actualiza por categoria
                        system.updateGameCategory(updateGameForm.getGameName(), updateGameForm.getCategory());
                        res.redirect("/admin?updatedGame");
                        return halt();
                    }
                    else {                                              //Actualiza por nivel y categoria
                        system.updateGameLvl(updateGameForm.getGameName(), updateGameForm.getLvlMax());
                        system.updateGameCategory(updateGameForm.getGameName(), updateGameForm.getCategory());
                        res.redirect("/admin?updatedGame");
                        return halt();
                    }
                }
            }
            else {                                                  //Lleva a la pagina del gamer
                final Map<String, Object> model = new HashMap<>();
                String name = authenticatedGamerUser.get().getUserName();
                model.put("message", "User is not Admin");
                model.put("myName", name);
                return render(model, HOME_TEMPLATE);
            }
        });

        getGames(DELETE_GAME_ROUTE, DELETE_GAME_TEMPLATE);

        authenticatedPost(DELETE_GAME_ROUTE, (req, res) -> {
            final Optional<GamerUser> authenticatedGamerUser = getAuthenticatedGamerUser(req);
            if (authenticatedGamerUser.get().isAdmin()) {   //Revisa que no sea un gamer
                DeleteGameForm deleteGameForm = DeleteGameForm.createFromBody(req.body());
                if (!system.gameExists(deleteGameForm.getGame())){ //Revisa que el juego exista
                    res.redirect("/updategame?notGame");
                    return halt();
                }
                else {
                    if (deleteGameForm.getGame().equals("")){   //Revisa que elija un juego
                        res.redirect("/deletegame?noGameSelected");
                    }
                    else {
                        system.deleteGame(deleteGameForm.getGame());
                        res.redirect("/admin?deletedGame");
                    }
                    return halt();
                }
            }
            else {                                           //Lleva a la pagina del gamer
                final Map<String, Object> model = new HashMap<>();
                String name = authenticatedGamerUser.get().getUserName();
                model.put("message", "User is not Admin");
                model.put("myName", name);
                return render(model, HOME_TEMPLATE);
            }
        });

        authenticatedGet(MANAGE_RANK_ROUTE, (req, res) -> {
            final Optional<GamerUser> authenticatedGamerUser = getAuthenticatedGamerUser(req);
            if(authenticatedGamerUser.get().isAdmin()){     //Se fija que no sea un gamer
                return render(MANAGE_RANK_TEMPLATE);
            }
            else{                                           //Lleva a la pagina del gamer
                final Map<String, Object> model = new HashMap<>();
                String name = authenticatedGamerUser.get().getUserName();
                model.put("message", "User is not Admin");
                model.put("myName", name);
                return render(model, HOME_TEMPLATE);
            }
        });

        getGames(CREATE_RANK_ROUTE, CREATE_RANK_TEMPLATE);

        authenticatedPost(CREATE_RANK_ROUTE, (req, res) -> {
            final Optional<GamerUser> authenticatedGamerUser = getAuthenticatedGamerUser(req);
            if(authenticatedGamerUser.get().isAdmin()){     //Se fija que no sea un gamer
                RankForm rankForm = RankForm.createFromBody(req.body());
                Rank newRank = system.registerRank(rankForm.getGameName(), rankForm.getNewRank());
                if (newRank != null){                       //Se fija que el rango no exista. Deberia fijarse que el juego existe
                    res.redirect("/admin?updatedRanks");
                    return halt();
                }
                else {                                      //Vuelve al createrank con mensaje
                    res.redirect("/createrank?rankexists");
                    return halt();
                }
            }
            else{                                           //Lleva a la pagina del gamer
                final Map<String, Object> model = new HashMap<>();
                String name = authenticatedGamerUser.get().getUserName();
                model.put("message", "User is not Admin");
                model.put("myName", name);
                return render(model, HOME_TEMPLATE);
            }
        });

        getGames(DELETE_RANK_ROUTE, DELETE_RANK_TEMPLATE);

        authenticatedPost(DELETE_RANK_ROUTE, (req, res) -> {
            final Optional<GamerUser> authenticatedGamerUser = getAuthenticatedGamerUser(req);
            if(authenticatedGamerUser.get().isAdmin()){     //Se fija que no sea un gamer
                RankForm rankForm = RankForm.createFromBody(req.body());
                Rank newRank = system.deleteRank(rankForm.getGameName(), rankForm.getNewRank());
                if (newRank != null){                       //Se fija que el rango exista
                    res.redirect("/admin?deletedRanks");
                    return halt();
                }
                else {                                      //Vuelve al deleterank con mensaje
                    res.redirect("/deleterank?rankNotExists");
                    return halt();
                }
            }
            else{                                           //Lleva a la pagina del gamer
                final Map<String, Object> model = new HashMap<>();
                String name = authenticatedGamerUser.get().getUserName();
                model.put("message", "User is not Admin");
                model.put("myName", name);
                return render(model, HOME_TEMPLATE);
            }
        });

        authenticatedGet(PROFILE_ROUTE, (req, res) -> {
            final Optional<GamerUser> authenticatedGamerUser = getAuthenticatedGamerUser(req);
            if (!authenticatedGamerUser.get().isAdmin()) {  //Revisa que no sea admin
                return render(PROFILE_TEMPLATE);
            }else{                                          //Lleva a la pagina del admin
                final Map<String, Object> model = new HashMap<>();
                model.put("message", "User is Admin");
                return render(model, ADMIN_HOME_TEMPLATE);
            }
        });

        authenticatedGet(CREATE_DESCRIPTION_ROUTE, (req, res) -> {
            final Optional<GamerUser> authenticatedGamerUser = getAuthenticatedGamerUser(req);
            if (!authenticatedGamerUser.get().isAdmin()){   //Revisa que no sea admin
                List<Game> games = system.getGames();
                if (!games.isEmpty()){
                    final Map<String, Object> model = new HashMap<>();
                    model.put("games", games);
                    return new FreeMarkerEngine().render(new ModelAndView(model, CREATE_DESCRIPTION_TEMPLATE));
                }
                else {
                    res.redirect("/home?noGames");
                    return halt();
                }
            }
            else {                                          //Lleva a la pagina del admin
                final Map<String, Object> model = new HashMap<>();
                model.put("message", "User is Admin");
                return render(model, ADMIN_HOME_TEMPLATE);
            }
        });

        authenticatedPost(CREATE_DESCRIPTION_ROUTE, (req, res) -> {
            final Optional<GamerUser> authenticatedGamerUser = getAuthenticatedGamerUser(req);
            if (!authenticatedGamerUser.get().isAdmin()){
                CreateDescriptionForm descriptionForm = CreateDescriptionForm.createFromBody(req.body());
                List<GamerDescription> myDescriptions = system.getUserDescriptions(authenticatedGamerUser.get());
                GamerDescription description = system.registerGamerDescription(authenticatedGamerUser.get(), myDescriptions, descriptionForm);
                if (description != null){
                    res.redirect("/home?ok");
                    return halt();
                }
                else {
                    List<Game> games = system.getGames();
                    final String message = system.getMessage();
                    final Map<String, Object> model = Map.of("message", message, "games", games);
                    system.setMessage(null);
                    return render(model, CREATE_DESCRIPTION_TEMPLATE);
                }
            }
            else {                                          //Lleva a la pagina del admin
                final Map<String, Object> model = new HashMap<>();
                model.put("message", "User is Admin");
                return render(model, ADMIN_HOME_TEMPLATE);
            }
        });

        authenticatedGet(UPDATE_DESCRIPTION_ROUTE, (req, res) -> {
            final Optional<GamerUser> authenticatedGamerUser = getAuthenticatedGamerUser(req);
            if (!authenticatedGamerUser.get().isAdmin()) {
                List<GamerDescription> gamerDescriptions = system.getUserDescriptions(authenticatedGamerUser.get());
                if(gamerDescriptions.size() != 0) {
                    final Map<String, Object> model = new HashMap<>();
                    model.put("descriptions", gamerDescriptions);
                    return new FreeMarkerEngine().render(new ModelAndView(model, UPDATE_DESCRIPTION_TEMPLATE));
                }else{
                    final Map<String, Object> model = Map.of("message", "You don't have descriptions");
                    return render(model, PROFILE_TEMPLATE);
                }
            } else {
                final Map<String, Object> model = new HashMap<>();
                model.put("message", "User is Admin");
                return render(model, ADMIN_HOME_TEMPLATE);
            }
        });

        authenticatedPost(UPDATE_DESCRIPTION_ROUTE, (req, res) -> {
            final Optional<GamerUser> authenticatedGamerUser = getAuthenticatedGamerUser(req);
            if (!authenticatedGamerUser.get().isAdmin()) {
                UpdateDescriptionForm updateDescriptionForm = UpdateDescriptionForm.createFromBody(req.body());
                if (updateDescriptionForm.getLvl().equals("") && updateDescriptionForm.getRank().equals("")) {
                    final Map<String, Object> model = Map.of("message", "You have to complete new lvl or rank");
                    return render(model, PROFILE_TEMPLATE);
                }else if (!updateDescriptionForm.getLvl().equals("") && updateDescriptionForm.getRank().equals("")){
                    if (system.checkNewLevelDescription(updateDescriptionForm.getGameName(), updateDescriptionForm.getLvl())) {
                        system.updateDescriptionLvl(authenticatedGamerUser.get(), updateDescriptionForm.getGameName(), updateDescriptionForm.getLvl());
                        res.redirect("/home?descriptionUpdated");
                        return halt();
                    }
                    else{
                        List<GamerDescription> gamerDescriptions = system.getUserDescriptions(authenticatedGamerUser.get());
                        final Map<String, Object> model = Map.of("message", system.getMessage(), "descriptions", gamerDescriptions);
                        system.setMessage(null);
                        return render(model, UPDATE_DESCRIPTION_TEMPLATE);
                    }
                }
                else if (updateDescriptionForm.getLvl().equals("") && !updateDescriptionForm.getRank().equals("")){
                    Rank newRank = system.getRank(updateDescriptionForm.getRank(), updateDescriptionForm.getGameName());
                    if (newRank.getRankId() != null){
                        system.updateDescriptionRank(authenticatedGamerUser.get(), updateDescriptionForm.getGameName(),newRank);
                        res.redirect("/home?updatedDescription");
                        return halt();
                    }
                    else {
                        List<GamerDescription> gamerDescriptions = system.getUserDescriptions(authenticatedGamerUser.get());
                        final Map<String, Object> model = Map.of("message","Rank doesnt exist or does not belong to game", "descriptions", gamerDescriptions);
                        system.setMessage(null);
                        return render(model, UPDATE_DESCRIPTION_TEMPLATE);
                    }
                }
                else {
                    Rank newRank = system.getRank(updateDescriptionForm.getRank(), updateDescriptionForm.getGameName());
                    if (system.checkNewLevelDescription(updateDescriptionForm.getGameName(), updateDescriptionForm.getLvl()) && (newRank != null)) {
                        system.updateDescriptionLvl(authenticatedGamerUser.get(), updateDescriptionForm.getGameName(), updateDescriptionForm.getLvl());
                        system.updateDescriptionRank(authenticatedGamerUser.get(), updateDescriptionForm.getGameName(),newRank);
                        res.redirect("/home?updatedDescription");
                        return halt();
                    }
                    else {
                        List<GamerDescription> gamerDescriptions = system.getUserDescriptions(authenticatedGamerUser.get());
                        final Map<String, Object> model = Map.of("message","Rank doesnt exist/Lvl is higher than lvlMax", "descriptions", gamerDescriptions);
                        system.setMessage(null);
                        return render(model, UPDATE_DESCRIPTION_TEMPLATE);
                    }
                }
            } else {
                final Map<String, Object> model = new HashMap<>();
                model.put("message", "User is Admin");
                return render(model, ADMIN_HOME_TEMPLATE);
            }
        });

        authenticatedGet(DELETE_DESCRIPTION_ROUTE, (req, res) -> {
            final Optional<GamerUser> authenticatedGamerUser = getAuthenticatedGamerUser(req);
            if (!authenticatedGamerUser.get().isAdmin()){
                List<GamerDescription> gamerDescriptions = system.getUserDescriptions(authenticatedGamerUser.get());
                if (!gamerDescriptions.isEmpty()){
                    final Map<String, Object> model = new HashMap<>();
                    model.put("descriptions", gamerDescriptions);
                    return new FreeMarkerEngine().render(new ModelAndView(model, DELETE_DESCRIPTION_TEMPLATE));
                }
                else {
                    final Map<String, Object> model = Map.of("message", "You dont have descriptions");
                    return render(model, PROFILE_TEMPLATE);
                }
            }
            else {
                final Map<String, Object> model = new HashMap<>();
                model.put("message", "User is Admin");
                return render(model, ADMIN_HOME_TEMPLATE);
            }
        });

        authenticatedPost(DELETE_DESCRIPTION_ROUTE, (req, res) -> {
            final Optional<GamerUser> authenticatedGamerUser = getAuthenticatedGamerUser(req);
            LikeForm description = LikeForm.createFromBody(req.body()); //uso LikeForm pq es igual
            system.deleteDescription(description.getLikedUser(), authenticatedGamerUser.get());
            res.redirect("/home?ok");
            return halt();
        });

        authenticatedGet(MANAGE_INTEREST_ROUTE, (req, res) -> {
            final Optional<GamerUser> authenticatedGamerUser = getAuthenticatedGamerUser(req);
            if (!authenticatedGamerUser.get().isAdmin()){
                return render(MANAGE_INTEREST_TEMPLATE);
            }
            else {
                final Map<String, Object> model = new HashMap<>();
                model.put("message", "User is Admin");
                return render(model, ADMIN_HOME_TEMPLATE);
            }
        });

        authenticatedGet(CREATE_INTEREST_ROUTE, (req, res) -> {
            final Optional<GamerUser> authenticatedGamerUser = getAuthenticatedGamerUser(req);
            if (!authenticatedGamerUser.get().isAdmin()){
                List<Game> games = system.getGames();
                if (!games.isEmpty()){
                    final Map<String, Object> model = new HashMap<>();
                    model.put("games", games);
                    return new FreeMarkerEngine().render(new ModelAndView(model, CREATE_INTEREST_TEMPLATE));
                }
                else {
                    res.redirect("/home?noGames");
                    return halt();
                }
            }
            else {
                final Map<String, Object> model = new HashMap<>();
                model.put("message", "User is Admin");
                return render(model, ADMIN_HOME_TEMPLATE);
            }
        });

        authenticatedPost(CREATE_INTEREST_ROUTE, (req, res) -> {
            CreateInterestForm interestForm = CreateInterestForm.createFromBody(req.body());
            GamerUser gamer = getAuthenticatedGamerUser(req).get();
            List<GamerInterest> myInterests = system.getGamerInterest(gamer);
            GamerInterest interest = system.registerGamerInterest(gamer, myInterests, interestForm);
            if (interest != null){
                res.redirect("/home?ok");
                return halt();
            }
            else {
                final String message = system.getMessage();
                List<Game> games = system.getGames();
                final Map<String, Object> model = Map.of("message", message, "games", games);
                system.setMessage(null);
                return render(model, CREATE_INTEREST_TEMPLATE);
            }
        });

        authenticatedGet(UPDATE_INTEREST_ROUTE, (req, res) -> {
            final Optional<GamerUser> authenticatedGamerUser = getAuthenticatedGamerUser(req);
            if (!authenticatedGamerUser.get().isAdmin()) {
                List<GamerInterest> gamerInterests = system.getGamerInterest(authenticatedGamerUser.get());
                if(gamerInterests.size() != 0) {
                    final Map<String, Object> model = new HashMap<>();
                    model.put("interests", gamerInterests);
                    return new FreeMarkerEngine().render(new ModelAndView(model, UPDATE_INTEREST_TEMPLATE));
                }else{
                    final Map<String, Object> model = Map.of("message", "You don't have interests");
                    return render(model, MANAGE_INTEREST_TEMPLATE);
                }
            } else {
                final Map<String, Object> model = new HashMap<>();
                model.put("message", "User is Admin");
                return render(model, ADMIN_HOME_TEMPLATE);
            }
        });

        authenticatedPost(UPDATE_INTEREST_ROUTE, (req, res) -> {
            final Optional<GamerUser> authenticatedGamerUser = getAuthenticatedGamerUser(req);
            if (!authenticatedGamerUser.get().isAdmin()) {
                UpdateInterestForm updateInterestForm = UpdateInterestForm.createFromBody(req.body());
                if (!system.gameExists(updateInterestForm.getGameName())){
                    final Map<String, Object> model = Map.of("message", "Game does not exist");
                    return render(model, MANAGE_INTEREST_TEMPLATE);
                }
                else {
                    if (updateInterestForm.getLvl().equals("") && updateInterestForm.getRank().equals("")) {
                        final Map<String, Object> model = Map.of("message", "You have to complete new lvl or rank");
                        return render(model, MANAGE_INTEREST_TEMPLATE);
                    }else if (!updateInterestForm.getLvl().equals("") && updateInterestForm.getRank().equals("")){
                        if (system.checkNewLevelInterest(updateInterestForm.getGameName(), updateInterestForm.getLvl())) {
                            system.updateInterestLvl(authenticatedGamerUser.get(), updateInterestForm.getGameName(), updateInterestForm.getLvl());
                            res.redirect("/home?updatedInterest");
                            return halt();
                        }
                        else{
                            List<GamerInterest> gamerInterests = system.getGamerInterest(authenticatedGamerUser.get());
                            final Map<String, Object> model = Map.of("message", system.getMessage(),"interests", gamerInterests);
                            system.setMessage(null);
                            return render(model, UPDATE_INTEREST_TEMPLATE);
                        }
                    }
                    else if (updateInterestForm.getLvl().equals("") && !updateInterestForm.getRank().equals("")){
                        Rank newRank = system.getRank(updateInterestForm.getRank(), updateInterestForm.getGameName());
                        if (newRank.getRankId() != null){
                            system.updateInterestRank(authenticatedGamerUser.get(), updateInterestForm.getGameName(),newRank);
                            res.redirect("/home?updatedInterest");
                            return halt();
                        }
                        else {
                            List<GamerInterest> gamerInterests = system.getGamerInterest(authenticatedGamerUser.get());
                            final Map<String, Object> model = Map.of("message", "Rank doesnt exist or does not belong to game","interests", gamerInterests);
                            system.setMessage(null);
                            return render(model, UPDATE_INTEREST_TEMPLATE);
                        }
                    }
                    else {
                        Rank newRank = system.getRank(updateInterestForm.getRank(), updateInterestForm.getGameName());
                        if (system.checkNewLevelInterest(updateInterestForm.getGameName(), updateInterestForm.getLvl()) && (newRank != null)) {
                            system.updateInterestLvl(authenticatedGamerUser.get(), updateInterestForm.getGameName(), updateInterestForm.getLvl());
                            system.updateInterestRank(authenticatedGamerUser.get(), updateInterestForm.getGameName(),newRank);
                            res.redirect("/home?updatedInterest");
                            return halt();
                        }
                        else {
                            List<GamerInterest> gamerInterests = system.getGamerInterest(authenticatedGamerUser.get());
                            final Map<String, Object> model = Map.of("message", "Rank doesnt exist/Lvl is higher than lvlMax","interests", gamerInterests);
                            return render(model, UPDATE_INTEREST_TEMPLATE);
                        }
                    }
                }
            } else {
                final Map<String, Object> model = new HashMap<>();
                model.put("message", "User is Admin");
                return render(model, ADMIN_HOME_TEMPLATE);
            }
        });

        authenticatedGet(DELETE_INTEREST_ROUTE, (req, res) -> {
            final Optional<GamerUser> authenticatedGamerUser = getAuthenticatedGamerUser(req);
            if (!authenticatedGamerUser.get().isAdmin()){
                List<GamerInterest> interests = system.getGamerInterest(authenticatedGamerUser.get());
                if (!interests.isEmpty()){
                    final Map<String, Object> model = new HashMap<>();
                    model.put("interests", interests);
                    return new FreeMarkerEngine().render(new ModelAndView(model, DELETE_INTEREST_TEMPLATE));
                }
                else {
                    final Map<String, Object> model = Map.of("message", "You dont have interests");
                    return render(model, MANAGE_INTEREST_TEMPLATE);
                }
            }
            else {
                res.redirect(ADMIN_HOME_ROUTE);
                return halt();
            }
        });

        authenticatedPost(DELETE_INTEREST_ROUTE, (req, res) -> {
            final Optional<GamerUser> authenticatedGamerUser = getAuthenticatedGamerUser(req);
            LikeForm description = LikeForm.createFromBody(req.body()); //uso LikeForm pq es igual
            system.deleteInterest(description.getLikedUser(), authenticatedGamerUser.get());
            res.redirect("/home?ok");
            return halt();
        });
        authenticatedGet(FIND_PLAYERS_ROUTE, (req, res) -> {
            final Optional<GamerUser> authenticatedGamerUser = getAuthenticatedGamerUser(req);
                if (!authenticatedGamerUser.get().isAdmin()) {
                    GamerUser gamerUser = authenticatedGamerUser.get();
                    List<GamerDescription> descriptions = system.getInterestPlayers(gamerUser);
                    if (descriptions !=null && descriptions.size() != 0){
                        List<String> userNames = userNameQuoted(descriptions);
                        final Map<String, Object> model = new HashMap<>();
                        model.put("descriptions", descriptions);
                        model.put("userNames", userNames);
                        return new FreeMarkerEngine().render(new ModelAndView(model, FIND_PLAYERS_TEMPLATE));
                    }
                    else{
                        system.setMessage("You have no candidates");
                        res.redirect("/home?ok");
                        return halt();
                    }
                }
                else {
                    res.redirect(ADMIN_HOME_ROUTE);
                    return halt();
                }
        });

        authenticatedPost(FIND_PLAYERS_ROUTE, (req, res) -> {
            LikeForm likedUser = LikeForm.createFromBody(req.body());
            GamerUser gamer = getAuthenticatedGamerUser(req).get();
            Like like = system.registerLike(likedUser, gamer);
            system.createMatch(gamer);
            if (like != null){
                res.redirect("/home?liked");
                return halt();
            }
            else {
                final Map<String, Object> model = Map.of("message", "Select a User");
                return render(model, FIND_PLAYERS_TEMPLATE);
            }
        });

        authenticatedGet(VIEW_MATCH_ROUTE, (req, res) -> {
            final Optional<GamerUser> currentUser = getAuthenticatedGamerUser(req);
            if (!currentUser.get().isAdmin()){
                List<GamerUser> matches = system.showMatch(currentUser.get());
                if(!matches.isEmpty()){
                    final Map<String, Object> model = new HashMap<>();
                    model.put("matches", matches);
                    return new FreeMarkerEngine().render(new ModelAndView(model, VIEW_MATCH_TEMPLATE));
                }
                else {
                    system.setMessage("You have no matches");
                    res.redirect("/home?ok");
                    return halt();
                }
            }
            else {
                final Map<String, Object> model = new HashMap<>();
                model.put("message", "User is Admin");
                return render(model, ADMIN_HOME_TEMPLATE);
            }
        });

        authenticatedPost(VIEW_MATCH_ROUTE, (req, res) -> {
            final Optional<GamerUser> currentUser = getAuthenticatedGamerUser(req);
            if (!currentUser.get().isAdmin()){
                LikeForm player = LikeForm.createFromBody(req.body());
                Optional<GamerUser> gamerUser = system.findUserByUserName(player.getLikedUser());
                List<GamerUser> matches = system.showMatch(currentUser.get());
                if (gamerUser.isPresent()){
                    List<GamerDescription> descriptions = system.getUserDescriptions(gamerUser.get());
                    final Map<String, Object> model = new HashMap<>();
                    model.put("matches", matches);
                    model.put("descriptions", descriptions);
                    return new FreeMarkerEngine().render(new ModelAndView(model, VIEW_MATCH_TEMPLATE));
                }
                else {
                    system.setMessage("You have to select a player");
                    res.redirect("/home?ok");
                    return halt();
                }
            }
            else {
                final Map<String, Object> model = new HashMap<>();
                model.put("message", "User is Admin");
                return render(model, ADMIN_HOME_TEMPLATE);
            }
        });
    }

    private void getGames(String gameRoute, String gameTemplate) {
        authenticatedGet(gameRoute, (req, res) -> {
            final Optional<GamerUser> authenticatedGamerUser = getAuthenticatedGamerUser(req);
            if (authenticatedGamerUser.get().isAdmin()) {       //Se fija que no sea un gamer
                List<Game> games = system.getGames();
                final Map<String, Object> model = new HashMap<>();
                if(!games.isEmpty()) {                          //Se fija que haya juegos creados
                    model.put("games", games);
                    if (req.queryParams("notOk") != null) model.put("message", "Fill at least one attribute to update");
                    if (req.queryParams("noGameSelected") != null) model.put("message", "Choose a game to delete");
                    if (req.queryParams("notGame") != null) model.put("message", "The game you chose does not exist");
                    if (req.queryParams("rankexists") != null) model.put("message", "Rank name already exist");
                    if (req.queryParams("rankNotExists") != null) model.put("message", "Rank name does not exist");
                    return new FreeMarkerEngine().render(new ModelAndView(model, gameTemplate));
                }else{                                          //Mensaje y lleva al createGame
                    model.put("message", "There are no games to update");
                    return render(model, CREATE_GAME_TEMPLATE);
                }
            } else {                                            //Lleva a la pagina del gamer
                final Map<String, Object> model = new HashMap<>();
                String name = authenticatedGamerUser.get().getUserName();
                model.put("message", "User is not Admin");
                model.put("myName", name);
                return render(model, HOME_TEMPLATE);
            }
        });
    }

    private void authenticatedGet(String route, Route o) {
        get(route, (request, response) -> {
            final Optional<GamerUser> authenticatedGamerUser = getAuthenticatedGamerUser(request);

            if (authenticatedGamerUser.isPresent()) {
                return o.handle(request, response);
            } else {
                response.redirect(LOGIN_ROUTE);
                return halt();
            }
        });
    }

    private void authenticatedPost(String route, Route o) {
        post(route, (request, response) -> {
            final Optional<GamerUser> authenticatedGamerUser = getAuthenticatedGamerUser(request);
            if (authenticatedGamerUser.isPresent()) {
                return o.handle(request, response);
            } else {
                response.redirect(LOGIN_ROUTE);
                return halt();
            }
        });
    }


    private void clearAuthenticatedGamerUser(Request req) {
        req.session().invalidate();
    }

    private void setAuthenticatedGamerUser(Request req, GamerUser gamerUser) {
        Session session = req.session(true);
        session.attribute(GAMER_SESSION_ID, gamerUser.getUserName());
    }

    private Optional<GamerUser> getAuthenticatedGamerUser(Request req) {
        final String userName = req.session().attribute(GAMER_SESSION_ID);
        return Optional.ofNullable(userName).flatMap(system::findUserByUserName);
    }

    private Object render(String template) {
        return new FreeMarkerEngine().render(new ModelAndView(Collections.emptyMap(), template));
    }

    private Object render(Map<String, Object> model, String signUptemplate) {
        return new FreeMarkerEngine().render(new ModelAndView(model, signUptemplate));
    }

    private List<String> userNameQuoted(List<GamerDescription> descriptions){
        List<String> list = new ArrayList<>();
        for (GamerDescription description : descriptions) {
            list.add(description.getGamerUser().getUserName());
        }
        //list.stream().collect(Collectors.joining("','", "'", "'"));
        return list;
    }

}
