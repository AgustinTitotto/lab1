package lab1.meetNGame.model;

import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;

import java.util.List;

public class CreateGameForm {

    private final String gameName;
    private final String category;
    private final String lvlMax;

    public CreateGameForm(String gameName, String category, String lvlMax) {
        this.gameName = gameName;
        this.category = category;
        this.lvlMax = lvlMax;
    }

    public static CreateGameForm createGameForm(List<String> gameName, List<String> category, List<String> lvlMax) {
        return new CreateGameForm(gameName.get(0), category.get(0), lvlMax.get(0));
    }

    public static CreateGameForm createFromBody(String body) {
        final MultiMap<String> params = new MultiMap<>();
        UrlEncoded.decodeTo(body, params, "UTF-8");

        return CreateGameForm.createGameForm(
                params.getValues("gameName"),
                params.getValues("gameCategory"),
                params.getValues("gameLvlMax")
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
}
