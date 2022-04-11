package lab1.meetNGame.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "GAMES")
public class Game {

    @Id
    private String gameName;

    private String category;

    private String lvlMAX;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Rank> ranks = new ArrayList<>();

    public Game(){
    }

    public Game(String gameName, String category, String lvlMAX){
        this.gameName = gameName;
        this.category = category;
        this.lvlMAX = lvlMAX;
    }

    public static Game createGame(String gameName, String category, String lvlMAX){
        return new Game(gameName, category, lvlMAX);
    }

    public String getGameName() {
        return gameName;
    }

    public String getCategory() {
        return category;
    }

    public String getLvlMAX() {
        return lvlMAX;
    }

    public List<Rank> getRanks() {
        return ranks;
    }
}
