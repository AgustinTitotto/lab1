package lab1.meetNGame;

import com.google.common.base.Strings;
import lab1.meetNGame.model.GamerUser;
import lab1.meetNGame.model.LogInForm;
import lab1.meetNGame.model.SignUpForm;
import lab1.meetNGame.repository.Gamers;

import java.util.Optional;

public class WebSystem {

    private Gamers gamers = new Gamers();

    public GamerUser registerGamer(SignUpForm form) {
        if (Strings.isNullOrEmpty(form.getUserName()) || Strings.isNullOrEmpty(form.getPassword()))
            return null;
        return gamers.exists(form.getUserName()) ? null : gamers.createGamer(form);
    }

    public Optional<GamerUser> findUserByUserName(String userName) {
        return gamers.findByUserName(userName);
    }

    public Optional<GamerUser> checkLogin(LogInForm form) {
        return gamers.findByUserName(form.getUserName())
                .filter(foundUser -> validPassword(form, foundUser));
    }

    private boolean validPassword(LogInForm form, GamerUser foundUser) {
        // Super dummy implementation. Zero security
        return form.getPassword().equals(foundUser.getPassword());
    }
}
