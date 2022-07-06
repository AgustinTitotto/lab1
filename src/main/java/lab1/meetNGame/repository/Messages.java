package lab1.meetNGame.repository;

import lab1.meetNGame.UI.MessageForm;
import lab1.meetNGame.model.Message;
import lab1.meetNGame.persistence.EntityTransactions;

import java.util.Date;
import java.util.List;

import static lab1.meetNGame.persistence.EntityManagers.currentEntityManager;
import static lab1.meetNGame.persistence.EntityTransactions.tx;

public class Messages {

    public List<Message> getListMessages(String sender, String receiver){
        return tx(() -> currentEntityManager().createQuery("SELECT u FROM Message u WHERE (u.sender " +
                "LIKE:sender or u.sender LIKE:receiver) and (u.receiver LIKE:receiver or u.receiver LIKE:sender)", Message.class)
                .setParameter("sender", sender).setParameter("receiver", receiver).getResultList());
    }

    public void registerMessage(String userName, String receiver, MessageForm message, Date date) {
        Message message1 = Message.createMessage();
        message1.setSender(userName);
        message1.setReceiver(receiver);
        message1.setMessage(message.getMessageContent());
        message1.setDate(date.toString());
        EntityTransactions.persist(message1);
    }
}
