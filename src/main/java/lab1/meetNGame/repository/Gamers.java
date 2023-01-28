package lab1.meetNGame.repository;

import lab1.meetNGame.model.*;
import lab1.meetNGame.UI.SignUpForm;
import lab1.meetNGame.persistence.EntityTransactions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lab1.meetNGame.persistence.EntityManagers.currentEntityManager;
import static lab1.meetNGame.persistence.EntityTransactions.tx;

public class Gamers{

    public boolean existsName(String userName) {
        return findByUserName(userName).isPresent();
    }

    public boolean existsMail(String mail) {
        return findByMail(mail).isPresent();
    }

    public Optional<GamerUser> findByUserName(String userName) {
        return tx(() -> currentEntityManager()
                .createQuery("SELECT u FROM GamerUser u WHERE u.userName LIKE :userName", GamerUser.class)
                .setParameter("userName", userName).getResultList()).stream()
                .findFirst();
    }

    public Optional<GamerUser> findByMail(String mail) {
        return tx(() -> currentEntityManager()
                .createQuery("SELECT u FROM GamerUser u WHERE u.mail LIKE :mail", GamerUser.class)
                .setParameter("mail", mail).getResultList()).stream()
                .findFirst();
    }

    public GamerUser createGamer(SignUpForm form) throws IOException {
        final GamerUser newGamer = GamerUser.create(form.getUserName(), form.getPassword(), form.getMail(), form.getImage(),false);

        if (existsName(newGamer.getUserName())) throw new IllegalStateException("UserName already exists.");
        if (existsMail(newGamer.getMail())) throw new IllegalStateException("Email address already exists.");

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
