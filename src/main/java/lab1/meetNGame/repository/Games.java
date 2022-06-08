package lab1.meetNGame.repository;

import lab1.meetNGame.UI.CreateGameForm;
import lab1.meetNGame.model.Game;
import lab1.meetNGame.model.Rank;
import lab1.meetNGame.persistence.EntityTransactions;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

import static lab1.meetNGame.persistence.EntityManagers.currentEntityManager;
import static lab1.meetNGame.persistence.EntityTransactions.tx;

public class Games {

    public boolean gameExists(String gameName){
        return findByGameName(gameName).isPresent();
    }

    public Optional<Game> findByGameName(String gameName) {
        return tx(() -> currentEntityManager().createQuery("SELECT u FROM Game u WHERE u.gameName LIKE: gameName",
                Game.class).setParameter("gameName", gameName).getResultList()).stream().findFirst();
    }

    public Game createGame(CreateGameForm form){
        final Game newGame = Game.createGame(form.getGameName(), form.getCategory(), form.getLvlMax());
      //if (gameExists(newGame.getGameName())) throw new IllegalStateException("Game already exists");
        String[] ranks = form.getRanks().split(",");
        for (int i = 0; i < ranks.length; i++) {
            Rank rank = Rank.createRank(ranks[i]);
            newGame.getRanks().add(rank);
        }
        return EntityTransactions.persist(newGame);
    }

    public Optional<Rank> findRankByName(String rankName){
        return tx(() -> currentEntityManager().createQuery("SELECT u FROM Rank u WHERE u.rankName LIKE: rankName",
                Rank.class).setParameter("rankName", rankName).getResultList()).stream().findFirst();
    }

    public List<Game> allGames(){
        return tx(() -> currentEntityManager().createQuery("SELECT u FROM Game u", Game.class).getResultList());

    }

    public void updateByLvl(String gameName, String newMaxLvl) {
        tx(() -> currentEntityManager().createQuery("UPDATE Game u SET u.lvlMAX = ?1 WHERE u.gameName LIKE: gameName")
                .setParameter("gameName", gameName).setParameter(1, newMaxLvl).executeUpdate());
    }

    public void updateByCategory(String gameName, String category) {
        tx(() -> currentEntityManager().createQuery("UPDATE Game u SET u.category = ?1 WHERE u.gameName LIKE: gameName")
                .setParameter("gameName", gameName).setParameter(1, category).executeUpdate());
    }
}
