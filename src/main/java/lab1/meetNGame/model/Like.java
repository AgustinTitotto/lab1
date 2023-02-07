package lab1.meetNGame.model;

import javax.persistence.*;

@Entity
@Table(name = "LIKES")
public class Like {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    private GamerUser mainUser;

    @OneToOne
    private GamerDescription likedDescription;

    public Like(){
    }

    public static Like createLike(){
        return new Like();
    }

    public GamerUser getMainUser() {
        return mainUser;
    }

    public void setMainUser(GamerUser mainUser) {
        this.mainUser = mainUser;
    }

    public GamerDescription getLikedDescription() {
        return likedDescription;
    }

    public void setLikedUser(GamerDescription likedUser) {
        this.likedDescription = likedUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
