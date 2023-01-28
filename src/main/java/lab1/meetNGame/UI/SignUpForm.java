package lab1.meetNGame.UI;

import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;

import java.io.IOException;
import java.util.List;

public class SignUpForm {

    private final String userName;
    private final String password;
    private final String mail;
    private final String image;

    public SignUpForm(String userName, String password, String mail, String image) {
        this.userName = userName;
        this.password = password;
        this.mail = mail;
        this.image = image;
    }

    public static SignUpForm create(String userName, String password, String mail, String image) {
        return new SignUpForm(userName, password, mail, image);
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getImage() {
        return image;
    }

    public String getMail() {
        return mail;
    }
}
