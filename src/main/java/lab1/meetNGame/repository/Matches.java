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


    public List<Match> match(GamerUser mainUser){
        List<Like> likedDescriptions = tx(() -> currentEntityManager().createQuery("SELECT u FROM Like u WHERE u.likedUser.gamerUser.userName Like:gamerUser",
                Like.class).setParameter("gamerUser", mainUser.getUserName()).getResultList());
        List<Like> user = tx(() -> currentEntityManager().createQuery("SELECT u FROM Like u WHERE u.mainUser.userName LIKE: userName",
                Like.class).setParameter("userName", mainUser.getUserName()).getResultList());
        List<Match> matches = new ArrayList<>();
        for (int i = 0; i < user.size(); i++) {
            for (int j = 0; j < likedDescriptions.size(); j++) {
                if (user.get(i).getMainUser().getUserName().equals(likedDescriptions.get(j).getLikedUser().getGamerUser().getUserName())
                && user.get(i).getLikedUser().getGame().getGameName().equals(likedDescriptions.get(j).getLikedUser().getGame().getGameName())){
                    Match match = Match.createMatch();
                    match.setUser1(user.get(i).getMainUser());
                    match.setUser2(likedDescriptions.get(j).getMainUser());
                    match.setCommonGame(likedDescriptions.get(j).getLikedUser().getGame());
                    EntityTransactions.persist(match);
                    matches.add(match);
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
        for (int i = 0; i < gamers1.size(); i++) {
            finalList.add(gamers1.get(i).getUser2());
        }
        for (int i = 0; i < gamers2.size(); i++) {
            finalList.add(gamers2.get(i).getUser1());
        }
        return finalList;
    }
}
