package lab1.meetNGame.model;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Rank {

    @Id
    private String rankName;

    public Rank(String rankName){
        this.rankName = rankName;
    }

    public Rank() {

    }
}
