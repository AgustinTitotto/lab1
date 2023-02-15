package lab1.meetNGame.UI;

import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;

import java.util.List;

public class DeleteNotificationForm {

    private final String id;


    public DeleteNotificationForm(String id) {
        this.id = id;

    }

    public static DeleteNotificationForm createDeleteNotificationForm(List<String> id){
        return new DeleteNotificationForm(id.get(0));
    }

    public static DeleteNotificationForm createFromBody(String body){
        final MultiMap<String> params = new MultiMap<>();
        UrlEncoded.decodeTo(body, params, "UTF-8");

        return DeleteNotificationForm.createDeleteNotificationForm(
                params.getValues("notificationId")
        );
    }

    public String getId() {
        return id;
    }

}
