package lab1.meetNGame.repository;

import lab1.meetNGame.model.GamerUser;
import lab1.meetNGame.model.Like;
import lab1.meetNGame.model.Match;
import lab1.meetNGame.persistence.EntityTransactions;

import java.util.ArrayList;
import java.util.List;

import static lab1.meetNGame.persistence.EntityManagers.currentEntityManager;
import static lab1.meetNGame.persistence.EntityTransactions.tx;

public class Matches {


    public List<Match> match(GamerUser mainUser, List<GamerUser> userMatches){
        List<Like> likedDescriptions = tx(() -> currentEntityManager().createQuery("SELECT u FROM Like u WHERE u.likedUser.gamerUser.userName Like:gamerUser",
                Like.class).setParameter("gamerUser", mainUser.getUserName()).getResultList());
        List<Like> user = tx(() -> currentEntityManager().createQuery("SELECT u FROM Like u WHERE u.mainUser.userName LIKE: userName",
                Like.class).setParameter("userName", mainUser.getUserName()).getResultList());
        List<Match> matches = new ArrayList<>();
        for (Like like : user) {
            for (Like likedDescription : likedDescriptions) {
                if (like.getMainUser().getUserName().equals(likedDescription.getLikedUser().getGamerUser().getUserName())
                        && like.getLikedUser().getGame().getGameName().equals(likedDescription.getLikedUser().getGame().getGameName())) {
                    if (userMatches.isEmpty()) {
                        Match match = Match.createMatch();
                        match.setUser1(like.getMainUser());
                        match.setUser2(likedDescription.getMainUser());
                        match.setCommonGame(likedDescription.getLikedUser().getGame());
                        EntityTransactions.persist(match);
                        matches.add(match);
                    } else {
                        boolean repeated = false;
                        for (GamerUser userMatch : userMatches) {
                            if (likedDescription.getMainUser().getUserName().equals(userMatch.getUserName())) {
                                repeated = true;
                                break;
                            }
                        }
                        if (!repeated) {
                            Match match = Match.createMatch();
                            match.setUser1(like.getMainUser());
                            match.setUser2(likedDescription.getMainUser());
                            match.setCommonGame(likedDescription.getLikedUser().getGame());
                            EntityTransactions.persist(match);
                            matches.add(match);
                        }
                    }
                }
            }
        }
        return matches;
    }


    public List<GamerUser> showMatches(GamerUser gamer){
        List<GamerUser> finalList = new ArrayList<>();
        List<Match> gamers1 = tx(() -> currentEntityManager().createQuery("SELECT u FROM Match u WHERE u.user1.userName LIKE: userName",
                Match.class).setParameter("userName", gamer.getUserName()).getResultList());
        List<Match> gamers2 = tx(() -> currentEntityManager().createQuery("SELECT u FROM Match u WHERE u.user2.userName LIKE: userName",
                Match.class).setParameter("userName", gamer.getUserName()).getResultList());
        for (Match match : gamers1) {
            finalList.add(match.getUser2());
        }
        for (Match match : gamers2) {
            finalList.add(match.getUser1());
        }
        return finalList;
    }
}
