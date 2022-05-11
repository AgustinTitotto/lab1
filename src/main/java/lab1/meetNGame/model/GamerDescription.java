package lab1.meetNGame.model;

import javax.persistence.*;

@Entity
@Table(name = "GAMERS_DESCRIPTION")
public class GamerDescription {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    private String lvl;

    @OneToOne
    private Game game;

    @OneToOne
    private Rank rank;

    @ManyToOne
    GamerUser gamerUser;

    public GamerDescription(){
    }

    public GamerDescription(String lvl){
        this.lvl = lvl;
    }

    public static GamerDescription createDescription(String lvl){
        return new GamerDescription(lvl);
    }

    public Long getId() {
        return id;
    }

    public String getLvl() {
        return lvl;
    }

    public Game getGame() {
        return game;
    }

    public Rank getRank() {
        return rank;
    }

    public GamerUser getGamerUser() {
        return gamerUser;
    }

    public void setGamerUser(GamerUser gamerUser) {
        this.gamerUser = gamerUser;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }
}
