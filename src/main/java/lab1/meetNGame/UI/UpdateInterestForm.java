package lab1.meetNGame.UI;

import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;

import java.util.List;

public class UpdateInterestForm {

    private final String gameName;
    private final String lvl;
    private final String rank;

    public UpdateInterestForm (String gameName, String lvl, String rank) {
        this.gameName = gameName;
        this.lvl = lvl;
        this.rank = rank;
    }

    public static UpdateInterestForm createUpdateForm(List<String> gameName, List<String> lvl, List<String> ranks){
        return new UpdateInterestForm(gameName.get(0), lvl.get(0), ranks.get(0));
    }

    public static UpdateInterestForm createFromBody(String body) {
        final MultiMap<String> params = new MultiMap<>();
        UrlEncoded.decodeTo(body, params, "UTF-8");

        return UpdateInterestForm.createUpdateForm(
                params.getValues("gameName"),
                params.getValues("newLvl"),
                params.getValues("newRank")
        );
    }

    public String getGameName() {
        return gameName;
    }

    public String getLvl() {
        return lvl;
    }

    public String getRank() {
        return rank;
    }
}
