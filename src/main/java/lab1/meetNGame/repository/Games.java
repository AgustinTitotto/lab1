package lab1.meetNGame.repository;

import lab1.meetNGame.UI.CreateGameForm;
import lab1.meetNGame.model.Game;
import lab1.meetNGame.model.GamerDescription;
import lab1.meetNGame.model.GamerInterest;
import lab1.meetNGame.model.Rank;
import lab1.meetNGame.persistence.EntityTransactions;

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
        List<GamerDescription> gamerDescriptions = tx(() -> currentEntityManager().createQuery("SELECT u FROM GamerDescription  u " +
                "WHERE u.game.gameName LIKE:gameName", GamerDescription.class).setParameter("gameName", gameName).getResultList());
        for (int i = 0; i < gamerDescriptions.size(); i++) {
            if (Integer.parseInt(gamerDescriptions.get(i).getLvl()) > Integer.parseInt(newMaxLvl)){
                String gamer = gamerDescriptions.get(i).getGamerUser().getUserName();
                tx(() -> currentEntityManager().createQuery("UPDATE GamerDescription u SET u.lvl = ?1 WHERE u.gamerUser.userName LIKE:userName AND u.game.gameName LIKE:gameName")
                        .setParameter("userName", gamer).setParameter("gameName", gameName).setParameter(1, newMaxLvl).executeUpdate());
            }
        }
        List<GamerInterest> gamerInterests = tx(() -> currentEntityManager().createQuery("SELECT u FROM GamerInterest  u " +
                "WHERE u.game.gameName LIKE:gameName", GamerInterest.class).setParameter("gameName", gameName).getResultList());
        for (int i = 0; i < gamerInterests.size(); i++) {
            if (Integer.parseInt(gamerInterests.get(i).getLvl()) > Integer.parseInt(newMaxLvl)){
                String gamer = gamerInterests.get(i).getGamerUser().getUserName();
                tx(() -> currentEntityManager().createQuery("UPDATE GamerInterest u SET u.lvl = ?1 WHERE u.gamerUser.userName LIKE:userName")
                        .setParameter("userName", gamer).setParameter(1, newMaxLvl).executeUpdate());
            }
        }
    }

    public void updateByCategory(String gameName, String category) {
        tx(() -> currentEntityManager().createQuery("UPDATE Game u SET u.category = ?1 WHERE u.gameName LIKE: gameName")
                .setParameter("gameName", gameName).setParameter(1, category).executeUpdate());
    }

    public void deleteGame(String game) {
        List<GamerDescription> gamerDescription = tx(() -> currentEntityManager().createQuery("SELECT u FROM GamerDescription u " +
                "WHERE u.game.gameName LIKE:gameName", GamerDescription.class).setParameter("gameName", game).getResultList());
        for (int i = 0; i < gamerDescription.size(); i++) {
            long id = gamerDescription.get(i).getId();
            tx(() -> currentEntityManager().createQuery("DELETE FROM Like u WHERE u.likedUser.id = ?1")
                    .setParameter(1, id).executeUpdate());
        }
        tx(() -> currentEntityManager().createQuery("DELETE FROM GamerDescription u WHERE u.game.gameName LIKE:gameName")
                .setParameter("gameName", game).executeUpdate());
        tx(() -> currentEntityManager().createQuery("DELETE FROM GamerInterest u WHERE u.game.gameName LIKE:gameName")
                .setParameter("gameName", game).executeUpdate());
        tx(() -> currentEntityManager().createQuery("DELETE FROM Match u WHERE u.commonGame.gameName LIKE:gameName")
                .setParameter("gameName", game).executeUpdate());
        Optional<Game> game1 = tx(() -> currentEntityManager().createQuery("SELECT u FROM Game u WHERE u.gameName LIKE:gameName",
                Game.class).setParameter("gameName", game).getResultList().stream().findFirst());
        tx(() -> currentEntityManager().createQuery("DELETE FROM Game u WHERE u.gameName LIKE: gameName")
                .setParameter("gameName", game).executeUpdate());
        List<Rank> ranks = game1.get().getRanks();
        for (int i = 0; i < ranks.size(); i++) {
            String name = ranks.get(i).getRankName();
            tx(() -> currentEntityManager().createQuery("DELETE FROM Rank u WHERE u.rankName LIKE:rankName")
                    .setParameter("rankName", name).executeUpdate());
        }
    }
}
