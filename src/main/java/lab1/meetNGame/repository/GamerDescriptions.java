package lab1.meetNGame.repository;


import lab1.meetNGame.model.*;
import lab1.meetNGame.persistence.EntityTransactions;

import java.util.List;
import java.util.Optional;

import static lab1.meetNGame.persistence.EntityManagers.currentEntityManager;
import static lab1.meetNGame.persistence.EntityTransactions.tx;

public class GamerDescriptions {

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

    public GamerDescription createDescription(GamerUser gamer, Game game, Rank rank, String gamerLvl){
        GamerDescription description = GamerDescription.createDescription(gamerLvl);
        description.setGame(game);
        description.setRank(rank);
        description.setGamerUser(gamer);
        return EntityTransactions.persist(description);
    }
}
