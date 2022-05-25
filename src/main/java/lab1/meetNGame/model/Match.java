package lab1.meetNGame.model;


import javax.persistence.*;

@Entity
@Table(name = "MATCH")
public class Match {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    GamerUser user1;

    @OneToOne
    GamerUser user2;

    @OneToOne
    Game commonGame;

    public Match(){

    }

    public static Match createMatch(){
        return new Match();
    }

    public void setUser1(GamerUser user1) {
        this.user1 = user1;
    }

    public void setUser2(GamerUser user2) {
        this.user2 = user2;
    }

    public void setCommonGame(Game commonGame) {
        this.commonGame = commonGame;
    }

    public GamerUser getUser1() {
        return user1;
    }

    public GamerUser getUser2() {
        return user2;
    }

    public Game getCommonGame() {
        return commonGame;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
