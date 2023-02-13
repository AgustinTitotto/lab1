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
        String[] ranks = form.getRanks().split(",");
        for (String s : ranks) {
            Rank rank = Rank.createRank(s);
            newGame.getRanks().add(rank);
        }
        return EntityTransactions.persist(newGame);
    }

    public Rank findRankByName(String rankName, String game){
        Rank rank = new Rank();
        Optional<Game> game1 = findByGameName(game);
        List<Rank> ranks = game1.get().getRanks();
        for (Rank value : ranks) {
            if (value.getRankName().equals(rankName))
                rank = value;
        }
        return rank;
    }

    public List<Game> allGames(){
        return tx(() -> currentEntityManager().createQuery("SELECT u FROM Game u", Game.class).getResultList());

    }

    public void updateByLvl(String gameName, String newMaxLvl) {
        tx(() -> currentEntityManager().createQuery("UPDATE Game u SET u.lvlMAX = ?1 WHERE u.gameName LIKE: gameName")
                .setParameter("gameName", gameName).setParameter(1, newMaxLvl).executeUpdate());
        List<GamerDescription> gamerDescriptions = tx(() -> currentEntityManager().createQuery("SELECT u FROM GamerDescription  u " +
                "WHERE u.game.gameName LIKE:gameName", GamerDescription.class).setParameter("gameName", gameName).getResultList());
        for (GamerDescription gamerDescription : gamerDescriptions) {
            if (Integer.parseInt(gamerDescription.getLvl()) > Integer.parseInt(newMaxLvl)) {
                String gamer = gamerDescription.getGamerUser().getUserName();
                tx(() -> currentEntityManager().createQuery("UPDATE GamerDescription u SET u.lvl = ?1 WHERE u.gamerUser.userName LIKE:userName AND u.game.gameName LIKE:gameName")
                        .setParameter("userName", gamer).setParameter("gameName", gameName).setParameter(1, newMaxLvl).executeUpdate());
            }
        }
        List<GamerInterest> gamerInterests = tx(() -> currentEntityManager().createQuery("SELECT u FROM GamerInterest  u " +
                "WHERE u.game.gameName LIKE:gameName", GamerInterest.class).setParameter("gameName", gameName).getResultList());
        for (GamerInterest gamerInterest : gamerInterests) {
            if (Integer.parseInt(gamerInterest.getLvl()) > Integer.parseInt(newMaxLvl)) {
                String gamer = gamerInterest.getGamerUser().getUserName();
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
        for (GamerDescription description : gamerDescription) {
            long id = description.getId();
            tx(() -> currentEntityManager().createQuery("DELETE FROM Like u WHERE u.likedDescription.id = ?1")
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
        for (Rank rank : ranks) {
            String name = rank.getRankName(); //Borra los rangos con mismo nombre y mismo Id
            long id = rank.getRankId();
            tx(() -> currentEntityManager().createQuery("DELETE FROM Rank u WHERE u.rankName LIKE:rankName and u.rankId = ?1")
                    .setParameter("rankName", name).setParameter(1, id).executeUpdate());
        }
    }

    public Rank createNewRank(String gameName, String newRank) {
        Rank rank = new Rank();
        Optional<Game> game1 = findByGameName(gameName);
        List<Rank> ranks = game1.get().getRanks();
        for (Rank value : ranks) {
            String name = value.getRankName();
            if (name.equals(newRank)) {
                rank = null;
                break;
            }
        }
        if (rank != null){
            rank = Rank.createRank(newRank);
            game1.get().getRanks().add(rank);
            return EntityTransactions.persist(rank);
        }
        else{
            return null;
        }

    }

    public Rank deleteRank(String gameName, String newRank) {
        Rank rank = null;
        Optional<Game> game1 = findByGameName(gameName);
        List<Rank> ranks = game1.get().getRanks();
        for (Rank value : ranks) {
            String name = value.getRankName();
            if (name.equals(newRank)) {
                rank = value;
                break;
            }
        }
        if (rank != null){
            long id = rank.getRankId();
            List<GamerDescription> descriptions = tx(() -> currentEntityManager().createQuery("SELECT u FROM GamerDescription u " +
                    "WHERE u.rank.rankId = ?1", GamerDescription.class).setParameter(1, id).getResultList());
            for (GamerDescription description : descriptions) {
                long gamerDesId = description.getId();
                String userName = description.getGamerUser().getUserName();
                tx(() -> currentEntityManager().createQuery("DELETE FROM Match u WHERE u.user1.userName LIKE:userName or u.user2.userName LIKE:userName")
                        .setParameter("userName", userName).executeUpdate());
                tx(() -> currentEntityManager().createQuery("DELETE FROM Like u WHERE u.likedUser.id = ?1")
                        .setParameter(1, gamerDesId).executeUpdate());
            }
            tx(() -> currentEntityManager().createQuery("DELETE FROM GamerInterest u WHERE u.rank.rankId = ?1")
                    .setParameter(1, id).executeUpdate());
            tx(() -> currentEntityManager().createQuery("DELETE FROM GamerDescription u WHERE u.rank.rankId = ?1")
                    .setParameter(1, id).executeUpdate());
            ranks.remove(rank); //List<Rank> con los rangos del juego, menos el que borre
            game1.get().setRanks(ranks);
            EntityTransactions.persist(game1.get());
            tx(() -> currentEntityManager().createQuery("DELETE FROM Rank u WHERE u.rankId = ?1")
                    .setParameter(1, id).executeUpdate());
            return rank;
        }
        else return null;
    }

    public Optional<Rank> checkRank(Game game, String userRank){
        List<Rank> ranks = tx(() -> currentEntityManager().createQuery("SELECT u FROM Game u WHERE u.gameName LIKE: gameName",
                Game.class).setParameter("gameName", game.getGameName()).getResultList()).stream().findFirst().get().getRanks();
        Optional<Rank> rank = Optional.empty();
        for (Rank value : ranks) {
            if (value.getRankName().equals(userRank)) {
                rank = Optional.of(value);
            }
        }
        return rank;
    }
}
