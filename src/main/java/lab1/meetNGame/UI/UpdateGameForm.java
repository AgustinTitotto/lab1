package lab1.meetNGame.UI;

import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;

import java.util.List;

public class UpdateGameForm {

    private final String gameName;
    private final String category;
    private final String lvlMax;

    public UpdateGameForm(String gameName, String category, String lvlMax){
        this.gameName = gameName;
        this.category = category;
        this.lvlMax = lvlMax;
    }

    public static UpdateGameForm createUpdateForm(List<String> gameName, List<String> category, List<String> lvlMax){
        return new UpdateGameForm(gameName.get(0), category.get(0), lvlMax.get(0));
    }
    public static UpdateGameForm createFromBody(String body) {
        final MultiMap<String> params = new MultiMap<>();
        UrlEncoded.decodeTo(body, params, "UTF-8");

        return UpdateGameForm.createUpdateForm(
                params.getValues("gameName"),
                params.getValues("newCategory"),
                params.getValues("newMaxLvl")
        );
    }

    public String getCategory() {
        return category;
    }

    public String getLvlMax() {
        return lvlMax;
    }

    public String getGameName() {
        return gameName;
    }
}
