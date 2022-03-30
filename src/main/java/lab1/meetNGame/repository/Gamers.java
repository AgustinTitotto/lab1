package lab1.meetNGame.repository;

import lab1.meetNGame.model.GamerUser;
import lab1.meetNGame.model.SignUpForm;
import lab1.meetNGame.persistence.UsersTransactions;

import java.util.Optional;

import static lab1.meetNGame.persistence.EntityManagers.currentEntityManager;
import static lab1.meetNGame.persistence.UsersTransactions.tx;

public class Gamers{

    public boolean exists(String email) {
        return findByUserName(email).isPresent();
    }

    public Optional<GamerUser> findByUserName(String userName) {
        return tx(() -> currentEntityManager()
                .createQuery("SELECT u FROM GamerUser u WHERE u.userName LIKE :userName", GamerUser.class)
                .setParameter("userName", userName).getResultList()).stream()
                .findFirst();
    }

    public GamerUser createGamer(SignUpForm form) {
        final GamerUser newGamer = GamerUser.create(form.getUserName(), form.getPassword());

        if (exists(newGamer.getUserName())) throw new IllegalStateException("UserName already exists.");

        return UsersTransactions.persist(newGamer);
    }
}
