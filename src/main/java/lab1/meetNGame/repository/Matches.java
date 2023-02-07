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


    public boolean match(GamerUser currentUser, List<GamerUser> currentUserMatches){
        List<Like> likedDescriptions = tx(() -> currentEntityManager().createQuery("SELECT u FROM Like u WHERE u.likedDescription.gamerUser.userName Like:gamerUser",
                Like.class).setParameter("gamerUser", currentUser.getUserName()).getResultList()); //Donde otro likeo a current user
        List<Like> likedByUser = tx(() -> currentEntityManager().createQuery("SELECT u FROM Like u WHERE u.mainUser.userName LIKE: userName",
                Like.class).setParameter("userName", currentUser.getUserName()).getResultList()); // Likeo current user
        List<Match> matches = new ArrayList<>();
        for (Like like : likedByUser) {
            for (Like likedDescription : likedDescriptions) {
                if (like.getMainUser().getUserName().equals(likedDescription.getLikedDescription().getGamerUser().getUserName())
                        && like.getLikedDescription().getGame().getGameName().equals(likedDescription.getLikedDescription().getGame().getGameName())) {
                    if (currentUserMatches.isEmpty()) { // Si no tiene match, agregalo
                        return generateMatch(matches, like, likedDescription);
                    } else {                            // Fijate que no este repetido
                        boolean repeated = false;
                        for (GamerUser userMatch : currentUserMatches) {
                            if (likedDescription.getMainUser().getUserName().equals(userMatch.getUserName())) {
                                repeated = true;
                                break;
                            }
                        }
                        if (!repeated) {
                            return generateMatch(matches, like, likedDescription);
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean generateMatch(List<Match> matches, Like like, Like likedDescription) {
        Match match = Match.createMatch();
        match.setUser1(like.getMainUser());
        match.setUser2(likedDescription.getMainUser());
        match.setCommonGame(likedDescription.getLikedDescription().getGame());
        EntityTransactions.persist(match);
        matches.add(match);
        return true;
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
