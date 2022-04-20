package lab1.meetNGame.model;

import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;

import java.util.List;

public class CreateDescriptionForm {

    private final String gameName;
    private final String lvl;
    private final String rank;

    public CreateDescriptionForm(String gameName, String lvl, String rank){
        this.gameName = gameName;
        this.lvl = lvl;
        this.rank = rank;
    }

    private static CreateDescriptionForm createDescriptionForm(List<String> gameName, List<String> gamerLvl, List<String> gamerRank) {
        return new CreateDescriptionForm(gameName.get(0), gamerLvl.get(0), gamerRank.get(0));
    }

    public static CreateDescriptionForm createFromBody(String body){
        final MultiMap<String> params = new MultiMap<>();
        UrlEncoded.decodeTo(body, params, "UTF-8");

        return CreateDescriptionForm.createDescriptionForm(
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
