package lab1.meetNGame;


import lab1.meetNGame.model.*;
import spark.*;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

        post(LOGIN_ROUTE, (req, res) ->{
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
                res.redirect(LOGIN_ROUTE);
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
                    model.put("message", "User is no Admin");
                    return render(model, ADMIN_HOME_TEMPLATE);
                }
            }
            res.redirect(LOGIN_ROUTE);
            return halt();
        });

        post(CREATE_GAME_ROUTE, (req, res) -> {
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
