package lab1.meetNGame.repository;

import lab1.meetNGame.model.GamerDescription;
import lab1.meetNGame.model.GamerUser;
import lab1.meetNGame.model.Like;
import lab1.meetNGame.persistence.EntityTransactions;


public class Likes {

    public Like createLike(GamerUser gamer, GamerDescription likedDescription) {
        final Like newLike = Like.createLike();
        newLike.setMainUser(gamer);
        newLike.setLikedUser(likedDescription);
        return EntityTransactions.persist(newLike);
    }

}
