package lab1.meetNGame.model;

import javax.persistence.*;

@Entity
@Table(name = "GAMERS_DESCRIPTION")
public class GamerDescription {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    GamerUser gamerUser;

    @OneToOne
    private Game game;

    private String lvl;

    @OneToOne(cascade=CascadeType.ALL)
    private Rank rank;

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

    public GamerUser getGamerUser() {
        return gamerUser;
    }

    public Game getGame() {
        return game;
    }

    public String getLvl() {
        return lvl;
    }

    public Rank getRank() {
        return rank;
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
