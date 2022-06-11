package lab1.meetNGame.UI;

import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;

import java.util.List;

public class DeleteGameForm {

    private final String game;

    public DeleteGameForm(String game) {
        this.game = game;
    }

    public static DeleteGameForm createDeleteForm(List<String> game){
        return new DeleteGameForm(game.get(0));
    }

    public static DeleteGameForm createFromBody(String body){
        final MultiMap<String> params = new MultiMap<>();
        UrlEncoded.decodeTo(body, params, "UTF-8");

        return DeleteGameForm.createDeleteForm(
                params.getValues("gameName")
        );
    }

    public String getGame() {
        return game;
    }
}
