package lab1.meetNGame.repository;

import lab1.meetNGame.model.Game;
import lab1.meetNGame.model.GamerUser;
import lab1.meetNGame.model.Notification;
import lab1.meetNGame.persistence.EntityTransactions;

import java.util.List;

import static lab1.meetNGame.persistence.EntityManagers.currentEntityManager;
import static lab1.meetNGame.persistence.EntityTransactions.tx;


public class Notifications {

    public void registerMatchNotification(GamerUser likedGamer, GamerUser currentUser, Game matchedGame) {
        Notification matchNotification = Notification.createNotification("You matched with " + currentUser.getUserName() + " in the game " + matchedGame.getGameName() + "!!!!");
        matchNotification.setGamerUser(likedGamer);
        EntityTransactions.persist(matchNotification);
    }


    public List<Notification> getUserNotifications(String userName) {
        return tx(() -> currentEntityManager().createQuery("select u from Notification u where u.gamerUser.userName like: userName", Notification.class)
                .setParameter("userName", userName).getResultList());
    }

    public void deleteNotification(Long notificationID) {
        tx(() -> currentEntityManager().createQuery("delete from Notification u where u.id = ?1").setParameter(1, notificationID).executeUpdate());
    }

}
