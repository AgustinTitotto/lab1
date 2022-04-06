package lab1.meetNGame.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GAMES")
public class Game {

    @Id
    private String gameName;

    private String category;

    private String lvlMAX;

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
}
