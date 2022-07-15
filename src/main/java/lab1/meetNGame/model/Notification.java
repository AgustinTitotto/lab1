package lab1.meetNGame.model;

import javax.persistence.*;


@Entity
@Table(name = "NOTIFICATION")
public class Notification {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private long id;

    private String sender;

    private String receiver;

    private String message;

    public Notification(){
    }

    public static Notification createNotification(){
        return new Notification();
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
