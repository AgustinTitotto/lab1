package lab1.meetNGame.UI;

import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;

import java.util.List;

public class RankForm {

    private final String gameName;
    private final String newRank;

    public RankForm(String gameName, String newRank){
        this.gameName = gameName;
        this.newRank = newRank;
    }

    public static RankForm createRankForm(List<String> gameName, List<String> newRank){
        return new RankForm(gameName.get(0), newRank.get(0));
    }

    public static RankForm createFromBody(String body) {
        final MultiMap<String> params = new MultiMap<>();
        UrlEncoded.decodeTo(body, params, "UTF-8");

        return RankForm.createRankForm(
                params.getValues("gameName"),
                params.getValues("newRank")
        );
    }

    public String getGameName() {
        return gameName;
    }

    public String getNewRank() {
        return newRank;
    }
}
