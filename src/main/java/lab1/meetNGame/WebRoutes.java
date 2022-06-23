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
    public static final String ADMIN_HOME_TEMPLATE = "adminhome.html";
    public static final String CREATE_GAME_TEMPLATE = "creategame.html";
    public static final String CREATE_DESCRIPTION_TEMPLATE = "createdescription.html";
    public static final String CREATE_INTEREST_TEMPLATE = "createinterest.html";
    public static final String FIND_PLAYERS_TEMPLATE = "findplayers.ftl";
    public static final String VIEW_MATCH_TEMPLATE = "viewmatch.ftl";
    public static final String UPDATE_GAME_TEMPLATE = "updategame.ftl";
    public static final String DELETE_GAME_TEMPLATE = "deletegame.ftl";
    public static final String PROFILE_TEMPLATE = "profile.ftl";
    public static final String UPDATE_DESCRIPTION_TEMPLATE = "updatedescription.ftl";
    public static final String DELETE_DESCRIPTION_TEMPLATE = "deletedescription.ftl";
    public static final String MANAGE_INTEREST_TEMPLATE = "manageinterest.ftl";
    public static final String DELETE_INTEREST_TEMPLATE = "deleteinterest.ftl";

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
    public static final String DELETE_INTEREST_ROUTE = "/deleteinterest";

    final static private WebSystem system = new WebSystem();

    public void startRoutes() {
        webroutes();
    }

    private void webroutes() {
        get(REGISTER_ROUTE, (req, res) -> render(SIGN_UP_TEMPLATE));
        post(REGISTER_ROUTE, (req, res) -> {
            final SignUpForm form = SignUpForm.createFromBody(req.body());

            final GamerUser gamer = system.registerGamer(form);

            if (gamer != null) {
                res.redirect("/login?ok");
                return halt();
            } else {
                final Map<String, Object> model = Map.of("message", "UserName already exists");
                return render(model, SIGN_UP_TEMPLATE);
            }
        });
        get(LOGIN_ROUTE, (req, res) -> {
            final Optional<GamerUser> authenticatedGamerUser = getAuthenticatedGamerUser(req);

            if (authenticatedGamerUser.isEmpty()) {
                final Map<String, Object> model = new HashMap<>();

                if (req.queryParams("ok") != null) model.put("message", "User created");

                return render(model, LOGIN_TEMPLATE);
            }

            res.redirect(HOME_ROUTE);
            return halt();
        });

        post(LOGIN_ROUTE, (req, res) ->{ //chequeamos que no haya un usuario ya iniciado, vemos si es admin o no.
            final Optional<GamerUser> authenticatedGamerUser = getAuthenticatedGamerUser(req);

            if (authenticatedGamerUser.isEmpty()){
                LogInForm logInForm = LogInForm.createFromBody(req.body());
                final Optional<GamerUser> validUser = system.checkLogin(logInForm);

                if (validUser.isPresent()) {
                    setAuthenticatedGamerUser(req, validUser.get());
                    if (validUser.get().isAdmin()){
                        res.redirect(ADMIN_HOME_ROUTE);
                        return halt();
                    }
                    else {
                        res.redirect(HOME_ROUTE);
                        return halt();
                    }
                } else {
                    final Map<String, Object> model = new HashMap<>();
                    model.put("message", "Invalid user or password");
                    return render(model, LOGIN_TEMPLATE);
                }
            } else {
                res.redirect(LOGIN_ROUTE);      //revisar esto, no deberia llevar al home ?
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
            if (!authenticatedGamerUser.get().isAdmin()) {
                String name = authenticatedGamerUser.get().getUserName();
                final Map<String, Object> model = new HashMap<>();
                model.put("myName", name);
                return render(model, HOME_TEMPLATE);
            }else{
                final Map<String, Object> model = new HashMap<>();
                model.put("message", "User is Admin");
                return render(model, ADMIN_HOME_TEMPLATE);
            }
        });

        authenticatedGet(ADMIN_HOME_ROUTE, (req, res) -> render(ADMIN_HOME_TEMPLATE));

        authenticatedGet(CREATE_GAME_ROUTE, (req, res) -> render(CREATE_GAME_TEMPLATE));

        authenticatedGet(CREATE_GAME_ROUTE, (req, res) -> {
            final Optional<GamerUser> authenticatedGamerUser = getAuthenticatedGamerUser(req);
            if (authenticatedGamerUser.get().isAdmin()) {
                return render(CREATE_GAME_TEMPLATE);
            } else {
                final Map<String, Object> model = new HashMap<>();
                model.put("message", "User is not Admin");
                return render(model, HOME_TEMPLATE);
            }
        });

        post(CREATE_GAME_ROUTE, (req, res) -> { //deberia chequear que es admin. Creamos Juegos con rangos, falta modificacion y borrar. Con base de datos lo completamos.
            CreateGameForm gameForm = CreateGameForm.createFromBody(req.body());
            final Game validGame = system.registerGame(gameForm);
            if (validGame != null){
                res.redirect("/admin?ok");
                return halt();
            }
            else {
                final Map<String, Object> model = Map.of("message", "Game already exists");
                return render(model, CREATE_GAME_TEMPLATE);
            }
        });

        authenticatedGet(UPDATE_GAME_ROUTE, (req, res) -> {
            final Optional<GamerUser> authenticatedGamerUser = getAuthenticatedGamerUser(req);
            if (authenticatedGamerUser.get().isAdmin()) {
                List<Game> games = system.getGames();
                if(games.size() != 0) {
                    final Map<String, Object> model = new HashMap<>();
                    model.put("games", games);
                    return new FreeMarkerEngine().render(new ModelAndView(model, UPDATE_GAME_TEMPLATE));
                }else{
                    final Map<String, Object> model = Map.of("message", "You don't have games");
                    return render(model, ADMIN_HOME_TEMPLATE);
                }
            } else {
                final Map<String, Object> model = new HashMap<>();
                model.put("message", "User is not Admin");
                return render(model, HOME_TEMPLATE);
            }
        });

        post(UPDATE_GAME_ROUTE, (req, res) -> {
            UpdateGameForm updateGameForm = UpdateGameForm.createFromBody(req.body());
             if (updateGameForm.getGameName() != null && (updateGameForm.getCategory() != null || updateGameForm.getLvlMax() != null)){
                 if(updateGameForm.getCategory().equals("")){
                     if (updateGameForm.getLvlMax().equals("")) {
                         res.redirect("/updategame?ok");
                         return halt();
                     }else{
                         system.updateGameLvl(updateGameForm.getGameName(), updateGameForm.getLvlMax());
                         res.redirect("/admin?ok");
                         return halt();
                     }
                }
                else if (updateGameForm.getLvlMax().equals("")){
                    system.updateGameCategory(updateGameForm.getGameName(), updateGameForm.getCategory());
                    res.redirect("/admin?ok");
                    return halt();
                }
                else {
                    system.updateGameLvl(updateGameForm.getGameName(), updateGameForm.getLvlMax());
                    system.updateGameCategory(updateGameForm.getGameName(), updateGameForm.getCategory());
                    res.redirect("/admin?ok");
                    return halt();
                }
            }
            else {
                final Map<String, Object> model = Map.of("message", "Select an attribute to update");
                return render(model, ADMIN_HOME_TEMPLATE);
            }
        });

        authenticatedGet(DELETE_GAME_ROUTE, (req, res) -> {
            final Optional<GamerUser> authenticatedGamerUser = getAuthenticatedGamerUser(req);
            if (authenticatedGamerUser.get().isAdmin()){
                List<Game> games = system.getGames();
                if(games.size() != 0) {
                    final Map<String, Object> model = new HashMap<>();
                    model.put("games", games);
                    return new FreeMarkerEngine().render(new ModelAndView(model, DELETE_GAME_TEMPLATE));
                }else{
                    final Map<String, Object> model = Map.of("message", "You don't have games");
                    return render(model, ADMIN_HOME_TEMPLATE);
                }
            }
            else {
                final Map<String, Object> model = new HashMap<>();
                model.put("message", "User is not Admin");
                return render(model, HOME_TEMPLATE);
            }
        });

        post(DELETE_GAME_ROUTE, (req, res) -> {
            DeleteGameForm deleteGameForm = DeleteGameForm.createFromBody(req.body());
            system.deleteGame(deleteGameForm.getGame());
            res.redirect("/admin?ok");
            return halt();
        });

        authenticatedGet(PROFILE_ROUTE, (req, res) -> render(PROFILE_TEMPLATE));

        get(CREATE_DESCRIPTION_ROUTE, (req, res) -> {
            final Optional<GamerUser> authenticatedGamerUser = getAuthenticatedGamerUser(req);
            if (authenticatedGamerUser.isPresent()) {
                if (!authenticatedGamerUser.get().isAdmin()){
                    return render(CREATE_DESCRIPTION_TEMPLATE);
                }
                else {
                    res.redirect(ADMIN_HOME_ROUTE);
                    return halt();
                }
            } else {
                res.redirect(LOGIN_ROUTE);
                return halt();
            }
        });

        authenticatedPost(CREATE_DESCRIPTION_ROUTE, (req, res) -> {
            CreateDescriptionForm descriptionForm = CreateDescriptionForm.createFromBody(req.body());
            GamerUser gamer = getAuthenticatedGamerUser(req).get();
            List<GamerDescription> myDescriptions = system.getUserDescriptions(gamer);
            GamerDescription description = system.registerGamerDescription(gamer, myDescriptions, descriptionForm);
            if (description != null){
                res.redirect("/home?ok");
                return halt();
            }
            else {
                final String message = system.getErrorMessage();
                final Map<String, Object> model = Map.of("message", message);
                return render(model, CREATE_DESCRIPTION_TEMPLATE);
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

        post(UPDATE_DESCRIPTION_ROUTE, (req, res) -> {
            UpdateDescriptionForm updateDescriptionForm = UpdateDescriptionForm.createFromBody(req.body());
            final GamerUser user = getAuthenticatedGamerUser(req).get();
            if (updateDescriptionForm.getGameName() != null && (updateDescriptionForm.getLvl() != null)){
                if (updateDescriptionForm.getLvl().equals("")) {
                    res.redirect("/updatedescription?ok");
                    return halt();
                }else{
                    if (system.checkNewLevel(updateDescriptionForm.getGameName(), updateDescriptionForm.getLvl())){
                        system.updateDescriptionLvl(user, updateDescriptionForm.getGameName(), updateDescriptionForm.getLvl());
                        res.redirect("/home?ok");
                        return halt();
                    }else{
                        final Map<String, Object> model = Map.of("message", system.getErrorMessage());
                        return render(model, UPDATE_DESCRIPTION_TEMPLATE);
                    }
                }
            }
            else {
                final Map<String, Object> model = Map.of("message", "Select an attribute to update");
                return render(model, UPDATE_DESCRIPTION_TEMPLATE);
            }
        });

        authenticatedGet(DELETE_DESCRIPTION_ROUTE, (req, res) -> {
            final Optional<GamerUser> authenticatedGamerUser = getAuthenticatedGamerUser(req);
            if (!authenticatedGamerUser.get().isAdmin()){
                List<GamerDescription> gamerDescriptions = system.getUserDescriptions(authenticatedGamerUser.get());
                if (gamerDescriptions.size() != 0){
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
                res.redirect(ADMIN_HOME_ROUTE);
                return halt();
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
                res.redirect(ADMIN_HOME_ROUTE);
                return halt();
            }
        });

        get(CREATE_INTEREST_ROUTE, (req, res) -> {
            final Optional<GamerUser> authenticatedGamerUser = getAuthenticatedGamerUser(req);
            if (authenticatedGamerUser.isPresent()) {
                if (!authenticatedGamerUser.get().isAdmin()){
                    return render(CREATE_INTEREST_TEMPLATE);
                }
                else {
                    res.redirect(ADMIN_HOME_ROUTE);
                    return halt();
                }
            } else {
                res.redirect(LOGIN_ROUTE);
                return halt();
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
                final String message = system.getErrorMessage();
                final Map<String, Object> model = Map.of("message", message);
                return render(model, CREATE_INTEREST_TEMPLATE);
            }
        });

        authenticatedGet(DELETE_INTEREST_ROUTE, (req, res) -> {
            final Optional<GamerUser> authenticatedGamerUser = getAuthenticatedGamerUser(req);
            if (!authenticatedGamerUser.get().isAdmin()){
                List<GamerInterest> interests = system.getGamerInterest(authenticatedGamerUser.get());
                if (interests.size() != 0){
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
                    if (descriptions != null){
                        List<String> userNames = userNameQuoted(descriptions);
                        final Map<String, Object> model = new HashMap<>();
                        model.put("descriptions", descriptions);
                        model.put("userNames", userNames);
                        return new FreeMarkerEngine().render(new ModelAndView(model, FIND_PLAYERS_TEMPLATE));
                    }
                    else{
                        final Map<String, Object> model = Map.of("message", "You dont have interests");
                        return render(model, HOME_TEMPLATE);
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
                res.redirect("/home?ok");
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
                if(matches.size() != 0){
                    final Map<String, Object> model = new HashMap<>();
                    model.put("matches", matches);
                    return new FreeMarkerEngine().render(new ModelAndView(model, VIEW_MATCH_TEMPLATE));
                }
                else {
                    final Map<String, Object> model = Map.of("message", "You have no matches yet");
                    return render(model, HOME_TEMPLATE);
                }
            }
            else {
                final Map<String, Object> model = new HashMap<>();
                model.put("message", "User is Admin");
                return render(model, ADMIN_HOME_TEMPLATE);
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
        for (int i = 0; i < descriptions.size(); i++) {
            list.add(descriptions.get(i).getGamerUser().getUserName());
        }
        //list.stream().collect(Collectors.joining("','", "'", "'"));
        return list;
    }

}
