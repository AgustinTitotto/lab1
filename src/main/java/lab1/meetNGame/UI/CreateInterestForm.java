package lab1.meetNGame.UI;

import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;

import java.util.List;

public class CreateInterestForm {
    private final String gameName;
    private final String lvl;
    private final String rank;

    public CreateInterestForm(String gameName, String lvl, String rank){
        this.gameName = gameName;
        this.lvl = lvl;
        this.rank = rank;
    }

    private static CreateInterestForm createInterestForm(List<String> gameName, List<String> gamerLvl, List<String> gamerRank) {
        return new CreateInterestForm(gameName.get(0), gamerLvl.get(0), gamerRank.get(0));
    }

    public static CreateInterestForm createFromBody(String body){
        final MultiMap<String> params = new MultiMap<>();
        UrlEncoded.decodeTo(body, params, "UTF-8");

        return CreateInterestForm.createInterestForm(
                params.getValues("gameName"),
                params.getValues("gamerLvl"),
                params.get("gamerRank")
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
