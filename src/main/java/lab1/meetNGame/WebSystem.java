package lab1.meetNGame;

import com.google.common.base.Strings;
import lab1.meetNGame.model.*;
import lab1.meetNGame.repository.GamerDescriptions;
import lab1.meetNGame.repository.Gamers;
import lab1.meetNGame.repository.Games;

import java.util.Optional;

public class WebSystem {

    private final Gamers gamers = new Gamers();
    private final Games games = new Games();
    private final GamerDescriptions descriptions = new GamerDescriptions();

    public GamerUser registerGamer(SignUpForm form) {
        if (Strings.isNullOrEmpty(form.getUserName()) || Strings.isNullOrEmpty(form.getPassword()))
            return null;
        return gamers.exists(form.getUserName()) ? null : gamers.createGamer(form);
    }

    public Optional<GamerUser> findUserByUserName(String userName) {
        return gamers.findByUserName(userName);
    }

    public Optional<GamerUser> checkLogin(LogInForm form) {
        return gamers.findByUserName(form.getUserName())
                .filter(foundUser -> validPassword(form, foundUser));
    }

    private boolean validPassword(LogInForm form, GamerUser foundUser) {
        // Super dummy implementation. Zero security
        return form.getPassword().equals(foundUser.getPassword());
    }


    public Game registerGame(CreateGameForm form) {
        return games.gameExists(form.getGameName()) ? null : games.createGame(form);
    }

    public GamerDescription registerGamerDescription(CreateDescriptionForm form){
        Optional<Game> game1 = games.findByGameName(form.getGameName());
        if (game1.isEmpty()){
            return null;
        }
        else {
            boolean lvlCheck = descriptions.checkLevel(game1.get(), form.getLvl());
            if (!lvlCheck){
                return null;
            }
            else {
                Optional<Rank> rankCheck = descriptions.checkRank(game1.get(), form.getRank());
                if (rankCheck.isEmpty()){
                    return null;
                }
                else return descriptions.createDescription(game1.get(), rankCheck.get(), form.getLvl());
            }
        }
    }

}
