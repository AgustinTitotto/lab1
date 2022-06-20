package lab1.meetNGame.repository;

import lab1.meetNGame.model.Game;
import lab1.meetNGame.model.GamerInterest;
import lab1.meetNGame.model.GamerUser;
import lab1.meetNGame.model.Rank;
import lab1.meetNGame.persistence.EntityTransactions;

import java.util.List;
import java.util.Optional;

import static lab1.meetNGame.persistence.EntityManagers.currentEntityManager;
import static lab1.meetNGame.persistence.EntityTransactions.tx;

public class GamerInterests {

    public boolean checkLevel(Game game, String userLvl){
        String a = tx(() -> currentEntityManager().createQuery("SELECT u FROM Game u WHERE u.lvlMAX LIKE: lvlMax",
                Game.class).setParameter("lvlMax", game.getLvlMAX()).getResultList()).stream().findFirst().get().getLvlMAX();
        if (Integer.parseInt(a) >= Integer.parseInt(userLvl)){
            return true;
        }
        else return false;
    }

    public Optional<Rank> checkRank(Game game, String userRank){
        List<Rank> ranks = tx(() -> currentEntityManager().createQuery("SELECT u FROM Game u WHERE u.gameName LIKE: gameName",
                Game.class).setParameter("gameName", game.getGameName()).getResultList()).stream().findFirst().get().getRanks();
        Optional<Rank> rank = Optional.empty();
        for (int i = 0; i < ranks.size(); i++) {
            if (ranks.get(i).getRankName().equals(userRank)){
                rank = Optional.ofNullable(ranks.get(i));
            }
        }
        return rank;
    }

    public GamerInterest createInterest(GamerUser gamer, Game game, Rank rank, String gamerLvl){
        GamerInterest interest = GamerInterest.createInterest(gamerLvl);
        interest.setGame(game);
        interest.setRank(rank);
        interest.setGamerUser(gamer);
        return EntityTransactions.persist(interest);
    }
    public List<GamerInterest> gamersInterest(GamerUser gamerUser) {
        return tx(() -> currentEntityManager().createQuery("SELECT u FROM GamerInterest u WHERE u.gamerUser.userName LIKE:userName", GamerInterest.class)
                .setParameter("userName", gamerUser.getUserName()).getResultList());
    }

    public void deleteInterest(GamerUser gamerUser, String gameName) {
        tx(() -> currentEntityManager().createQuery("DELETE FROM GamerInterest u WHERE u.gamerUser.userName LIKE: userName and u.game.gameName LIKE:gameName")
                .setParameter("userName", gamerUser.getUserName()).setParameter("gameName", gameName).executeUpdate());
    }

    public boolean checkGame(List<GamerInterest> myInterests, String gameName) {
        boolean value = true;
        for (int i = 0; i < myInterests.size(); i++) {
            if (myInterests.get(i).getGame().getGameName().equals(gameName)){
                value = false;
                break;
            }
        }
        return value;
    }
}
