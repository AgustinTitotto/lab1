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
        // get(LOGIN_ROUTE, (req, res) -> render(LOGIN_TEMPLATE));
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

        authenticatedGet(HOME_ROUTE, (req, res) -> render(HOME_TEMPLATE));
        authenticatedGet(ADMIN_HOME_ROUTE, (req, res) -> render(ADMIN_HOME_TEMPLATE));

        get(CREATE_GAME_ROUTE, (req, res) -> render(CREATE_GAME_TEMPLATE));
        post(ADMIN_HOME_ROUTE, (req, res) -> {
            final Optional<GamerUser> authenticatedGamerUser = getAuthenticatedGamerUser(req);
            if (authenticatedGamerUser.isPresent()) {
                if (authenticatedGamerUser.get().isAdmin()) {
                    res.redirect(CREATE_GAME_ROUTE);
                    return halt();
                } else {
                    final Map<String, Object> model = new HashMap<>();
                    model.put("message", "User is not Admin");
                    return render(model, HOME_TEMPLATE);
                }
            }
            else {
                res.redirect(LOGIN_ROUTE);
                return halt();
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
            GamerDescription description = system.registerGamerDescription(gamer, descriptionForm);
            if (description != null){
                res.redirect("/home?ok");
                return halt();
            }
            else {
                final Map<String, Object> model = Map.of("message", "One or more parameter are wrong");
                return render(model, CREATE_DESCRIPTION_TEMPLATE);
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
            GamerInterest interest = system.registerGamerInterest(gamer, interestForm);
            if (interest != null){
                res.redirect("/home?ok");
                return halt();
            }
            else {
                final Map<String, Object> model = Map.of("message", "One or more parameter are wrong");
                return render(model, CREATE_INTEREST_TEMPLATE);
            }
        });

        get(FIND_PLAYERS_ROUTE, (req, res) -> {
            final Optional<GamerUser> authenticatedGamerUser = getAuthenticatedGamerUser(req);
            if (authenticatedGamerUser.isPresent()) {
                if (!authenticatedGamerUser.get().isAdmin()){
                    GamerUser gamerUser = authenticatedGamerUser.get();
                    List<GamerDescription> descriptions = system.getInterestPlayers(gamerUser);
                    final Map<String, Object> model = new HashMap<>();
                    model.put("descriptions", descriptions);
                    return new FreeMarkerEngine().render(new ModelAndView(model, FIND_PLAYERS_TEMPLATE));
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

        authenticatedPost(FIND_PLAYERS_ROUTE, (req, res) -> {
            LikeForm likedUser = LikeForm.createFromBody(req.body());
            GamerUser gamer = getAuthenticatedGamerUser(req).get();
            Like like = system.registerLike(likedUser, gamer);
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
                List<Match> matches = system.getMatches(currentUser.get());
                final Map<String, Object> model = new HashMap<>();
                model.put("matches", matches);
                return new FreeMarkerEngine().render(new ModelAndView(model, VIEW_MATCH_TEMPLATE));
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

}
