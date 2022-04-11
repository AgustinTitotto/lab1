package lab1.meetNGame.model;


import javax.persistence.*;

@Entity
@Table(name = "RANKS")
public class Rank {

    @Id
    @GeneratedValue
    private Long rankId;

    private String rankName;

    public Rank() {
    }

    public Rank(String rankName){
        this.rankName = rankName;
    }

    public static Rank createRank(String rankName){
        return new Rank(rankName);
    }

    public Long getRankId() {
        return rankId;
    }

    public String getRankName() {
        return rankName;
    }
}
