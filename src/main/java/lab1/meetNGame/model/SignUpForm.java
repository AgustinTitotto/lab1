package lab1.meetNGame.model;

import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;

import java.util.List;

public class SignUpForm {

    private final String userName;
    private final String password;

    public SignUpForm(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public static SignUpForm create(List<String> userName, List<String> password) {
        return new SignUpForm(userName.get(0), password.get(0));
    }

    public static SignUpForm createFromBody(String body) {
        final MultiMap<String> params = new MultiMap<>();
        UrlEncoded.decodeTo(body, params, "UTF-8");

        return SignUpForm.create(
                params.getValues("userName"),
                params.getValues("password")
        );
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

}
