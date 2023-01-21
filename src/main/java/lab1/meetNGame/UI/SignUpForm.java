package lab1.meetNGame.UI;

import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
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

    public static SignUpForm create(List<String> userName, List<String> password, List<String> mail, String image) {
        return new SignUpForm(userName.get(0), password.get(0), mail.get(0), image);
    }

    public static SignUpForm createFromBody(String body) throws IOException {
        final MultiMap<String> params = new MultiMap<>();
        UrlEncoded.decodeTo(body, params, "UTF-8");
        return SignUpForm.create(
                    params.getValues("userName"),
                    params.getValues("password"),
                    params.getValues("mail"),
                    String.valueOf(params.getValues("image")).substring(1,String.valueOf(params.getValues("image")).length()-1)
            );
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
