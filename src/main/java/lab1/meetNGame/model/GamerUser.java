package lab1.meetNGame.model;


import javax.persistence.*;

@Entity
@Table(name = "GAMER_USERS")
public class GamerUser {

    @Id
    @Column(nullable = false)
    private String userName;

    private String password;

    private boolean isAdmin;

    @Column(length = 1000000000)
    private String image;

    private String mail;

    public GamerUser(){
    }

    public GamerUser(String userName, String password, boolean isAdmin, String image, String mail){
        this.userName = userName;
        this.password = password;
        this.isAdmin = isAdmin;
        this.image = image;
        this.mail = mail;
    }

    public static GamerUser create(String userName, String password, boolean isAdmin, String image, String mail){
        return new GamerUser(userName, password, isAdmin, image, mail);
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
