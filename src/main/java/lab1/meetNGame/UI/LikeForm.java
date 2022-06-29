package lab1.meetNGame.UI;

import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;

import java.util.List;

public class LikeForm {

    private final String description;

    public LikeForm(String likedUser) {
        this.description = likedUser;
    }

    public static LikeForm createLikeForm(List<String> likedUser){
        return new LikeForm(likedUser.get(0));
    }

    public static LikeForm createFromBody(String body){
        final MultiMap<String> params = new MultiMap<>();
        UrlEncoded.decodeTo(body, params, "UTF-8");
        return LikeForm.createLikeForm(
                params.getValues("gamers")
        );
    }

    public String getLikedUser() {
        return description;
    }
}
