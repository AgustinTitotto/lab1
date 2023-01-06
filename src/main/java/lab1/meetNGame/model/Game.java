package lab1.meetNGame.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "GAMES")
public class Game {

    @Id
    @Column(nullable = false)
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

    public void setRanks(List<Rank> ranks) {
        this.ranks = ranks;
    }

    public String ranksString(){
        StringBuilder result = new StringBuilder();
        if(ranks.size() > 1) {
            result.append(ranks.get(0).getRankName()).append(", ");
            for (int i = 1; i < ranks.size()-1; i++) {
                result.append(ranks.get(i).getRankName()).append(", ");
            }
            result.append(ranks.get(ranks.size()-1).getRankName());
            return result.toString();
        }else if(ranks.size() == 1){
            result.append(ranks.get(0).getRankName());
            return result.toString();
        }else{
            return "No Ranks For This Game";
        }
    }
}
