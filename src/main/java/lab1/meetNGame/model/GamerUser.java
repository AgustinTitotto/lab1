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

    public GamerUser(){
    }

    public GamerUser(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    public static GamerUser create(String userName, String password){
        return new GamerUser(userName, password);
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
