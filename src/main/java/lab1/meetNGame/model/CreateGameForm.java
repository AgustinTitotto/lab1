package lab1.meetNGame.model;

import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;

import java.util.List;

public class CreateGameForm {

    private final String gameName;
    private final String category;
    private final String lvlMax;
    private final String ranks;

    public CreateGameForm(String gameName, String category, String lvlMax, String ranks) {
        this.gameName = gameName;
        this.category = category;
        this.lvlMax = lvlMax;
        this.ranks = ranks;
    }

    public static CreateGameForm createGameForm(List<String> gameName, List<String> category, List<String> lvlMax, List<String> ranks) {
        return new CreateGameForm(gameName.get(0), category.get(0), lvlMax.get(0), ranks.get(0));
    }

    public static CreateGameForm createFromBody(String body) {
        final MultiMap<String> params = new MultiMap<>();
        UrlEncoded.decodeTo(body, params, "UTF-8");

        return CreateGameForm.createGameForm(
                params.getValues("gameName"),
                params.getValues("gameCategory"),
                params.getValues("gameLvlMax"),
                params.getValues("gameRanks")
        );
    }

    public String getGameName() {
        return gameName;
    }

    public String getCategory() {
        return category;
    }

    public String getLvlMax() {
        return lvlMax;
    }

    public String getRanks() {
        return ranks;
    }
}
