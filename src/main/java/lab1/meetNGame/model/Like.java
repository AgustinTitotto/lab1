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
    private GamerDescription likedUser;

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

    public GamerDescription getLikedUser() {
        return likedUser;
    }

    public void setLikedUser(GamerDescription likedUser) {
        this.likedUser = likedUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
