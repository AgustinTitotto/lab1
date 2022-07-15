package lab1.meetNGame.repository;

import lab1.meetNGame.model.*;
import lab1.meetNGame.UI.SignUpForm;
import lab1.meetNGame.persistence.EntityTransactions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lab1.meetNGame.persistence.EntityManagers.currentEntityManager;
import static lab1.meetNGame.persistence.EntityTransactions.tx;

public class Gamers{

    public boolean exists(String userName) {
        return findByUserName(userName).isPresent();
    }

    public Optional<GamerUser> findByUserName(String userName) {
        return tx(() -> currentEntityManager()
                .createQuery("SELECT u FROM GamerUser u WHERE u.userName LIKE :userName", GamerUser.class)
                .setParameter("userName", userName).getResultList()).stream()
                .findFirst();
    }

    public GamerUser createGamer(SignUpForm form) {
        final GamerUser newGamer = GamerUser.create(form.getUserName(), form.getPassword(), false);

        if (exists(newGamer.getUserName())) throw new IllegalStateException("UserName already exists.");

        return EntityTransactions.persist(newGamer);
    }

    public List<GamerUser> gamerList() {
        List<GamerUser> allGamers = tx(() -> currentEntityManager().createQuery("SELECT u FROM GamerUser u", GamerUser.class).getResultList());
        for (int i = 0; i < allGamers.size(); i++) {
            if (allGamers.get(i).isAdmin()){
                allGamers.remove(i);
            }
        }
        return allGamers;
    }
}
