package lab1.meetNGame.model;

import javax.persistence.*;


@Entity
@Table(name = "NOTIFICATIONS")
public class Notification {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private GamerUser gamerUser;

    private String notification;

    public Notification(String notification){
        this.notification = notification;
    }

    public static Notification createNotification(String notification){
        return new Notification(notification);
    }

    public Notification() {

    }

    public Long getId() {
        return id;
    }

    public GamerUser getGamerUser() {
        return gamerUser;
    }

    public String getNotification() {
        return notification;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setGamerUser(GamerUser gamerUser) {
        this.gamerUser = gamerUser;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }
}
