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
                    match.setUser2(likedDescriptions.get(j).getLikedUser().getGamerUser());
                    match.setCommonGame(likedDescriptions.get(j).getLikedUser().getGame());
                    EntityTransactions.persist(match);
                    matches.add(match);
                }
            }
        }
        return matches;
    }
}
