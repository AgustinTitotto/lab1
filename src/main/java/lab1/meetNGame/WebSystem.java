package lab1.meetNGame;

import com.google.common.base.Strings;
import lab1.meetNGame.UI.*;
import lab1.meetNGame.model.*;
import lab1.meetNGame.repository.*;

import java.util.List;
import java.util.Optional;

public class WebSystem {

    private final Gamers gamers = new Gamers();
    private final Games games = new Games();
    private final GamerDescriptions descriptions = new GamerDescriptions();
    private final GamerInterests interests = new GamerInterests();
    private final Likes likes = new Likes();
    private final Matches matches = new Matches();

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

    public GamerDescription registerGamerDescription(GamerUser gamer, CreateDescriptionForm form){
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
                else return descriptions.createDescription(gamer, game1.get(), rankCheck.get(), form.getLvl());
            }
        }
    }

    public GamerInterest registerGamerInterest(GamerUser gamer, CreateInterestForm form){
        Optional<Game> game1 = games.findByGameName(form.getGameName());
        if (game1.isEmpty()){
            return null;
        }
        else {
            boolean lvlCheck = interests.checkLevel(game1.get(), form.getLvl());
            if (!lvlCheck){
                return null;
            }
            else {
                Optional<Rank> rankCheck = interests.checkRank(game1.get(), form.getRank());
                if (rankCheck.isEmpty()){
                    return null;
                }
                else return interests.createInterest(gamer, game1.get(), rankCheck.get(), form.getLvl());
            }
        }
    }

    public List<GamerDescription> getInterestPlayers(GamerUser gamerUser) {
        List<GamerInterest> interestGamers = interests.gamersInterest(gamerUser);
        if (interestGamers.size() != 0){
            return descriptions.getGamersWithInterest(interestGamers);
        }
        else return null;
    }

    public Like registerLike(LikeForm likedUser, GamerUser gamer) {
        String[] description = likedUser.getLikedUser().split(", ");
        Optional<GamerUser> likedGamer = gamers.findByUserName(description[0]);
        Optional<Game> likedGame = games.findByGameName(description[1]);
        GamerDescription likedDescription = descriptions.getDescriptionByUserNameAndGame(likedGamer.get(), likedGame.get());
        return likes.createLike(gamer, likedDescription);
    }

    public List<Match> createMatch(GamerUser currentUser) {
        return matches.match(currentUser);
    }

    public List<GamerUser> showMatch(GamerUser gamerUser) {
        return matches.showMatches(gamerUser);
    }

    public List<Game> getGames() {
        return games.allGames();
    }

    public void updateGameLvl(String gameName, String newMaxLvl) {
        games.updateByLvl(gameName, newMaxLvl);
    }

    public void updateGameCategory(String gameName, String category) {
        games.updateByCategory(gameName, category);
    }

    public void deleteGame(String game) {
        games.deleteGame(game);
    }

    public List<GamerDescription> getUserDescriptions(GamerUser gamerUser) {
        return descriptions.getUserDescriptions(gamerUser);
    }

    public void deleteDescription(String gamerDescription, GamerUser gamerUser) {
        String[] description = gamerDescription.split(", ");
        String gameName = description[0];
        descriptions.deleteDescription(gamerUser, gameName);
    }

    public List<GamerInterest> getGamerInterest(GamerUser gamerUser) {
        return interests.gamersInterest(gamerUser);
    }

    public void deleteInterest(String gamerInterest, GamerUser gamerUser) {
        String[] interest = gamerInterest.split(", ");
        String gameName = interest[0];
        interests.deleteInterest(gamerUser, gameName);
    }
}
