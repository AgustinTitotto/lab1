package lab1.meetNGame.UI;

import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;

import java.util.List;

public class UpdateDescriptionForm {

    private final String gameName;

    private final String lvl;

    public UpdateDescriptionForm (String gameName, String lvl) {
        this.gameName = gameName;
        this.lvl = lvl;
    }

    public static UpdateDescriptionForm createUpdateForm(List<String> gameName, List<String> lvl){
        return new UpdateDescriptionForm(gameName.get(0), lvl.get(0));
    }

    public static UpdateDescriptionForm createFromBody(String body) {
        final MultiMap<String> params = new MultiMap<>();
        UrlEncoded.decodeTo(body, params, "UTF-8");

        return UpdateDescriptionForm.createUpdateForm(
                params.getValues("gameName"),
                params.getValues("newLvl")
        );
    }

    public String getGameName() {
        return gameName;
    }

    public String getLvl() {
        return lvl;
    }
}
