package lab1.meetNGame.repository;


import lab1.meetNGame.model.*;
import lab1.meetNGame.persistence.EntityTransactions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lab1.meetNGame.persistence.EntityManagers.currentEntityManager;
import static lab1.meetNGame.persistence.EntityTransactions.tx;

public class GamerDescriptions {

    public boolean checkGame(List<GamerDescription> myDescriptions, String gameName){
        boolean value = true;
        for (GamerDescription myDescription : myDescriptions) {
            if (myDescription.getGame().getGameName().equals(gameName)) {
                value = false;
                break;
            }
        }
        return value;
    }
    public boolean checkLevel(Game game, String userLvl){
        currentEntityManager().clear();
        String a = tx(() -> currentEntityManager().createQuery("SELECT u FROM Game u WHERE u.lvlMAX LIKE: lvlMax",
                Game.class).setParameter("lvlMax", game.getLvlMAX()).getResultList()).stream().findFirst().get().getLvlMAX();
        return Integer.parseInt(a) >= Integer.parseInt(userLvl);
    }

    public GamerDescription createDescription(GamerUser gamer, Game game, Rank rank, String gamerLvl){
        GamerDescription description = GamerDescription.createDescription(gamerLvl);
        description.setGame(game);
        description.setRank(rank);
        description.setGamerUser(gamer);
        return EntityTransactions.persist(description);
    }

    public GamerDescription getDescriptionByUserNameAndGame(GamerUser gamerUser, Game game) {
        currentEntityManager().clear();
        List<GamerDescription> descriptions = currentEntityManager().createQuery("SELECT u FROM GamerDescription u WHERE u.gamerUser.userName LIKE: userName",
                GamerDescription.class).setParameter("userName", gamerUser.getUserName()).getResultList();
        for (GamerDescription description : descriptions) {
            if (description.getGame().getGameName().equals(game.getGameName())) {
                return description;
            }
        }
        return null;
    }

    public List<GamerDescription> getGamersWithInterest(List<GamerInterest> interestGamers) {
        List<GamerDescription> purgedDescriptions = new ArrayList<>();
        List<GamerDescription> finalDescriptions = new ArrayList<>();
        String userName = interestGamers.get(0).getGamerUser().getUserName();
        currentEntityManager().clear();
        List<GamerDescription> descriptions = tx(() -> currentEntityManager().createQuery("SELECT u FROM GamerDescription u",
                GamerDescription.class).getResultList());
        List<Like> likedGamers = tx(() -> currentEntityManager().createQuery("SELECT u FROM Like u WHERE u.mainUser.userName LIKE: userName",
                Like.class).setParameter("userName", userName).getResultList());
        for (GamerDescription description : descriptions) {
            if (!description.getGamerUser().getUserName().equals(userName)) {
                purgedDescriptions.add(description);
            }
        }
        for (GamerInterest interestGamer : interestGamers) {
            for (GamerDescription purgedDescription : purgedDescriptions) {
                if (interestGamer.getGame().getGameName().equals(purgedDescription.getGame().getGameName())
                        && Integer.parseInt(interestGamer.getLvl()) < Integer.parseInt(purgedDescription.getLvl())
                        && interestGamer.getRank().getRankName().equals(purgedDescription.getRank().getRankName())) {
                    finalDescriptions.add(purgedDescription);
                }
            }
        }
        for (Like likedGamer : likedGamers) {
            for (int j = 0; j < finalDescriptions.size(); j++) {
                if (likedGamer.getLikedUser().getGamerUser().getUserName().equals(finalDescriptions.get(j).getGamerUser().getUserName())) {
                    finalDescriptions.remove(finalDescriptions.get(j));
                }
            }
        }
        return finalDescriptions;
    }

    public List<GamerDescription> getUserDescriptions(GamerUser gamerUser) {
        currentEntityManager().clear();
        return tx(() -> currentEntityManager().createQuery("SELECT u FROM GamerDescription u WHERE u.gamerUser.userName LIKE:userName",
                GamerDescription.class).setParameter("userName", gamerUser.getUserName()).getResultList());
    }

    public void updateByLvl(GamerUser gamerUser, String gameName, String newLevel){
        tx(() -> currentEntityManager().createQuery("UPDATE GamerDescription u SET u.lvl = ?1 WHERE u.game.gameName LIKE: gameName and u.gamerUser.userName LIKE:userName")
                .setParameter("gameName", gameName).setParameter("userName", gamerUser.getUserName()).setParameter(1, newLevel).executeUpdate());
    }

    public boolean checkNewLvl(String gameName, String newLevel){
        currentEntityManager().clear();
        String gameLvl = tx(() -> currentEntityManager().createQuery("SELECT u FROM Game u " +
                "WHERE u.gameName LIKE:gameName", Game.class).setParameter("gameName", gameName).getResultList()).stream().findFirst().get().getLvlMAX();
        return Integer.parseInt(gameLvl) >= Integer.parseInt(newLevel);
    }

    public void deleteDescription(GamerUser gamerUser, String gameName) {
        currentEntityManager().clear();
        Optional<GamerDescription> gamerDescription = tx(() -> currentEntityManager().createQuery("SELECT u FROM GamerDescription u " +
                "WHERE u.game.gameName LIKE:gameName and u.gamerUser.userName LIKE:userName", GamerDescription.class)
                .setParameter("gameName", gameName).setParameter("userName", gamerUser.getUserName()).getResultList().stream().findFirst());
        long id = gamerDescription.get().getId();
        tx(() -> currentEntityManager().createQuery("DELETE FROM Like u WHERE u.likedUser.id = ?1")
                    .setParameter(1, id).executeUpdate());
        tx(() -> currentEntityManager().createQuery("DELETE FROM Match u WHERE (u.user1.userName LIKE:userName OR u.user2.userName LIKE:userName) AND " +
                 "u.commonGame.gameName LIKE:gameName").setParameter("userName", gamerUser.getUserName())
                .setParameter("gameName", gameName).executeUpdate());
        tx(() -> currentEntityManager().createQuery("DELETE FROM GamerDescription u WHERE u.gamerUser.userName LIKE:userName AND u.game.gameName LIKE:gameName")
                .setParameter("userName", gamerUser.getUserName()).setParameter("gameName", gameName).executeUpdate());
    }

    public void updateByRank(GamerUser gamerUser, String gameName, Rank rank) {
        tx(() -> currentEntityManager().createQuery("UPDATE GamerDescription u SET u.rank = ?1 WHERE u.gamerUser.userName LIKE:userName AND " +
                "u.game.gameName LIKE:gameName").setParameter(1, rank).setParameter("gameName", gameName).setParameter("userName", gamerUser.getUserName()).executeUpdate());
    }
}
