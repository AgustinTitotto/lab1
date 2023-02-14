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
        Notification matchNotification1 = Notification.createNotification("You matched with " + currentUser.getUserName() + " in the game " + matchedGame.getGameName() + "!!!!");
        Notification matchNotification2 = Notification.createNotification("You matched with " + likedGamer.getUserName() + " in the game " + matchedGame.getGameName() + "!!!!");
        matchNotification1.setGamerUser(likedGamer);
        matchNotification2.setGamerUser(currentUser);
        EntityTransactions.persist(matchNotification1);
        EntityTransactions.persist(matchNotification2);
    }


    public List<Notification> getUserNotifications(String userName) {
        return tx(() -> currentEntityManager().createQuery("select u from Notification u where u.gamerUser.userName like: userName", Notification.class)
                .setParameter("userName", userName).getResultList());
    }

    public void deleteNotification(Long notificationID) {
        tx(() -> currentEntityManager().createQuery("delete from Notification u where u.id = ?1").setParameter(1, notificationID).executeUpdate());
    }

    public void registerMessageNotification(GamerUser receiver, GamerUser currentUser) {
        List<Notification> receiverNotifications = tx(() -> currentEntityManager().createQuery("select u from Notification u where u.gamerUser.userName like: userName", Notification.class)
                .setParameter("userName", receiver.getUserName()).getResultList());
        boolean repeatedNotification = false;
        for (Notification notification: receiverNotifications) {
            if (notification.getNotification().equalsIgnoreCase("You have a new message from " + currentUser.getUserName())) {
                repeatedNotification = true;
                break;
            }
        }
        if (!repeatedNotification){
            Notification messageNotification = Notification.createNotification("You have a new message from " + currentUser.getUserName());
            messageNotification.setGamerUser(receiver);
            EntityTransactions.persist(messageNotification);
        }
    }
}
