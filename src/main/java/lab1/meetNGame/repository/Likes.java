package lab1.meetNGame.repository;

import lab1.meetNGame.model.GamerDescription;
import lab1.meetNGame.model.GamerUser;
import lab1.meetNGame.model.Like;
import lab1.meetNGame.persistence.EntityTransactions;

import java.util.List;

import static lab1.meetNGame.persistence.EntityManagers.currentEntityManager;
import static lab1.meetNGame.persistence.EntityTransactions.tx;

public class Likes {

    public Like createLike(GamerUser gamer, GamerDescription likedDescription) {
        final Like newLike = Like.createLike();
        newLike.setMainUser(gamer);
        newLike.setLikedUser(likedDescription);
        return EntityTransactions.persist(newLike);
    }

}
