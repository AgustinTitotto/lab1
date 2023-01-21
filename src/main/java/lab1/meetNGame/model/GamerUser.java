package lab1.meetNGame.model;


import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;

import javax.persistence.*;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

@Entity
@Table(name = "GAMER_USERS")
public class GamerUser {

    @Id
    @Column(nullable = false)
    private String userName;

    private String password;

    private String mail;
    private String image;

    private boolean isAdmin;

    public GamerUser(){
    }

    public GamerUser(String userName, String password, String mail, File image, boolean isAdmin) throws IOException {
        this.userName = userName;
        this.password = password;
        this.mail = mail;
        if (image == null){
            this.image = null;
        }else{
            this.image = image.getPath();
        }
        this.isAdmin = isAdmin;
    }

    public static GamerUser create(String userName, String password, String mail, File image, boolean isAdmin) throws IOException {
        return new GamerUser(userName, password, mail, image, isAdmin);
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public String getImage() {
        return image;
    }

    public String getMail() {
        return mail;
    }
}
