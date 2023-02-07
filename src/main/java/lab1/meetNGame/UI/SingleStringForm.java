package lab1.meetNGame.UI;

import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;

import java.util.List;

public class SingleStringForm {

    private final String description;

    public SingleStringForm(String likedUser) {
        this.description = likedUser;
    }

    public static SingleStringForm createLikeForm(List<String> likedUser){
        return new SingleStringForm(likedUser.get(0));
    }

    public static SingleStringForm createFromBody(String body){
        final MultiMap<String> params = new MultiMap<>();
        UrlEncoded.decodeTo(body, params, "UTF-8");
        return SingleStringForm.createLikeForm(
                params.getValues("stringValue")
        );
    }

    public String getLikedUser() {
        return description;
    }
}
