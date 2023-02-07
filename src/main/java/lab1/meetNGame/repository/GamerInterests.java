package lab1.meetNGame.repository;

import lab1.meetNGame.model.Game;
import lab1.meetNGame.model.GamerInterest;
import lab1.meetNGame.model.GamerUser;
import lab1.meetNGame.model.Rank;
import lab1.meetNGame.persistence.EntityTransactions;

import java.util.List;

import static lab1.meetNGame.persistence.EntityManagers.currentEntityManager;
import static lab1.meetNGame.persistence.EntityTransactions.tx;

public class GamerInterests {

    public boolean checkLevel(Game game, String userLvl){
        String a = tx(() -> currentEntityManager().createQuery("SELECT u FROM Game u WHERE u.lvlMAX LIKE: lvlMax",
                Game.class).setParameter("lvlMax", game.getLvlMAX()).getResultList()).stream().findFirst().get().getLvlMAX();
        return Integer.parseInt(a) >= Integer.parseInt(userLvl);
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

    public void updateByLvl(GamerUser gamerUser, String gameName, String newLevel){
        tx(() -> currentEntityManager().createQuery("UPDATE GamerInterest u SET u.lvl = ?1 WHERE u.game.gameName LIKE: gameName and u.gamerUser.userName LIKE:userName")
                .setParameter("gameName", gameName).setParameter("userName", gamerUser.getUserName()).setParameter(1, newLevel).executeUpdate());
    }

    public boolean checkNewLvl(String gameName, String newLevel){
        String gameLvl = tx(() -> currentEntityManager().createQuery("SELECT u FROM Game u " +
                "WHERE u.gameName LIKE:gameName", Game.class).setParameter("gameName", gameName).getResultList()).stream().findFirst().get().getLvlMAX();
        return Integer.parseInt(gameLvl) >= Integer.parseInt(newLevel);
    }

    public void updateByRank(GamerUser gamerUser, String gameName, Rank rank) {
        tx(() -> currentEntityManager().createQuery("UPDATE GamerInterest u SET u.rank = ?1 WHERE u.gamerUser.userName LIKE:userName AND " +
                "u.game.gameName LIKE:gameName").setParameter(1, rank).setParameter("gameName", gameName).setParameter("userName", gamerUser.getUserName()).executeUpdate());
    }

    public void deleteInterest(GamerUser gamerUser, String gameName) {
        tx(() -> currentEntityManager().createQuery("DELETE FROM GamerInterest u WHERE u.gamerUser.userName LIKE: userName and u.game.gameName LIKE:gameName")
                .setParameter("userName", gamerUser.getUserName()).setParameter("gameName", gameName).executeUpdate());
    }

    public boolean checkGame(List<GamerInterest> myInterests, String gameName) {
        boolean value = true;
        for (GamerInterest myInterest : myInterests) {
            if (myInterest.getGame().getGameName().equals(gameName)) {
                value = false;
                break;
            }
        }
        return value;
    }
}
