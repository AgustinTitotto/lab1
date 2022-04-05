package lab1.meetNGame.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GAMER_USERS")
public class GamerUser {

    @Id
    private String userName;

    private String password;

    private boolean isAdmin;

    public GamerUser(){
    }

    public GamerUser(String userName, String password, boolean isAdmin){
        this.userName = userName;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public static GamerUser create(String userName, String password, boolean isAdmin){
        return new GamerUser(userName, password, isAdmin);
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
}
