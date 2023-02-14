package lab1.meetNGame.UI;

import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;

import java.util.List;

public class DeleteNotificationForm {

    private final String id;
    private final String route;

    public DeleteNotificationForm(String id, String route) {
        this.id = id;
        this.route = route;
    }

    public static DeleteNotificationForm createDeleteNotificationForm(List<String> id, List<String> route){
        return new DeleteNotificationForm(id.get(0), route.get(0));
    }

    public static DeleteNotificationForm createFromBody(String body){
        final MultiMap<String> params = new MultiMap<>();
        UrlEncoded.decodeTo(body, params, "UTF-8");

        return DeleteNotificationForm.createDeleteNotificationForm(
                params.getValues("notificationId"),
                params.getValues("route")
        );
    }

    public String getId() {
        return id;
    }

    public String getRoute() {
        return route;
    }
}
