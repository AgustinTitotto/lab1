package lab1.meetNGame.repository;

import lab1.meetNGame.model.Message;
import lab1.meetNGame.model.Notification;
import lab1.meetNGame.persistence.EntityTransactions;

import java.util.List;

import static lab1.meetNGame.persistence.EntityManagers.currentEntityManager;
import static lab1.meetNGame.persistence.EntityTransactions.tx;

public class Notifications {

    public List<Notification> getListNotifications(String userName) {
        List<Notification> userNotifications = tx(() -> currentEntityManager().createQuery("SELECT u FROM Notification u WHERE (u.receiver LIKE:receiver)", Notification.class)
                .setParameter("receiver", userName).getResultList());
        return userNotifications;
    }

    public void registerNotifications(String sender, String receiver, String message) {
        Notification notification = Notification.createNotification();
        notification.setSender(sender);
        notification.setReceiver(receiver);
        notification.setMessage(message);
        EntityTransactions.persist(notification);
    }

    public void deleteNotifications(String sender, String receiver) {
        tx(() -> currentEntityManager().createQuery("DELETE FROM Notification u WHERE (u.sender LIKE:sender AND u.receiver LIKE:receiver)").setParameter("sender", sender).setParameter("receiver", receiver).executeUpdate());
    }
}
