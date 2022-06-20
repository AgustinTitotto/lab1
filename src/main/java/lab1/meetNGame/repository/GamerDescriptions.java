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
        for (int i = 0; i < myDescriptions.size(); i++) {
            if (myDescriptions.get(i).getGame().getGameName().equals(gameName)){
                value = false;
                break;
            }
        }
        return value;
    }
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

    public GamerDescription getDescriptionByUserNameAndGame(GamerUser gamerUser, Game game) {
        List<GamerDescription> descriptions = currentEntityManager().createQuery("SELECT u FROM GamerDescription u WHERE u.gamerUser.userName LIKE: userName",
                GamerDescription.class).setParameter("userName", gamerUser.getUserName()).getResultList();
        for (int i = 0; i < descriptions.size(); i++) {
            if (descriptions.get(i).getGame().getGameName().equals(game.getGameName())){
                return descriptions.get(i);
            }
        }
        return null;
    }

    public List<GamerDescription> getGamersWithInterest(List<GamerInterest> interestGamers) {
        List<GamerDescription> finalDescriptions = new ArrayList<>();
        String userName = interestGamers.get(0).getGamerUser().getUserName();
        List<GamerDescription> descriptions = tx(() -> currentEntityManager().createQuery("SELECT u FROM GamerDescription u",
                GamerDescription.class).getResultList());
        List<Like> likedGamers = tx(() -> currentEntityManager().createQuery("SELECT u FROM Like u WHERE u.mainUser.userName LIKE: userName",
                Like.class).setParameter("userName", userName).getResultList());
        for (int i = 0; i < descriptions.size(); i++) {
            if (descriptions.get(i).getGamerUser().getUserName().equals(userName)){
                descriptions.remove(descriptions.get(i));
            }
        }
        for (int i = 0; i < interestGamers.size(); i++) {
            for (int j = 0; j < descriptions.size(); j++) {
                if (interestGamers.get(i).getGame().getGameName().equals(descriptions.get(j).getGame().getGameName())
                        && Integer.parseInt(interestGamers.get(i).getLvl()) < Integer.parseInt(descriptions.get(j).getLvl())
                        && interestGamers.get(i).getRank().getRankName().equals(descriptions.get(j).getRank().getRankName())){
                    finalDescriptions.add(descriptions.get(j));
                }
            }
        }
        for (int i = 0; i < likedGamers.size(); i++) {
            for (int j = 0; j < finalDescriptions.size(); j++) {
                if (likedGamers.get(i).getLikedUser().getGamerUser().getUserName().equals(finalDescriptions.get(j).getGamerUser().getUserName())) {
                    finalDescriptions.remove(finalDescriptions.get(j));
                }
            }
        }
        return finalDescriptions;
    }

    public List<GamerDescription> getUserDescriptions(GamerUser gamerUser) {
        return tx(() -> currentEntityManager().createQuery("SELECT u FROM GamerDescription u WHERE u.gamerUser.userName LIKE:userName",
                GamerDescription.class).setParameter("userName", gamerUser.getUserName()).getResultList());
    }

    public void deleteDescription(GamerUser gamerUser, String gameName) {
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
}
