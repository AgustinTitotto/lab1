package lab1.meetNGame.UI;

import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;

import java.util.List;

public class LogInForm {

    private final String userName;
    private final String password;

    public LogInForm(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public static LogInForm create(List<String> userName, List<String> password) {
        return new LogInForm(userName.get(0), password.get(0));
    }

    public static LogInForm createFromBody(String body) {
        final MultiMap<String> params = new MultiMap<>();
        UrlEncoded.decodeTo(body, params, "UTF-8");

        return LogInForm.create(
                params.getValues("userName"),
                params.getValues("password")
        );
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }
}
