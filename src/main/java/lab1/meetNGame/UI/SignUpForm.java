package lab1.meetNGame.UI;

import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;

import java.util.List;

public class SignUpForm {

    private final String userName;
    private final String password;
    private final String mail;

    public SignUpForm(String userName, String password, String mail) {
        this.userName = userName;
        this.password = password;
        this.mail = mail;
    }

    public static SignUpForm create(List<String> userName, List<String> password, List<String> mail) {
        return new SignUpForm(userName.get(0), password.get(0), mail.get(0));
    }

    public static SignUpForm createFromBody(String body) {
        final MultiMap<String> params = new MultiMap<>();
        UrlEncoded.decodeTo(body, params, "UTF-8");

        return SignUpForm.create(
                params.getValues("userName"),
                params.getValues("password"),
                params.getValues("mail")
        );
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getMail() {
        return mail;
    }
}
