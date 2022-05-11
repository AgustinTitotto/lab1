package lab1.meetNGame.repository;

import lab1.meetNGame.model.GamerDescription;
import lab1.meetNGame.model.GamerInterest;
import lab1.meetNGame.model.GamerUser;
import lab1.meetNGame.UI.SignUpForm;
import lab1.meetNGame.model.Rank;
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

    public List<GamerUser> getGamersWithInterest(List<GamerInterest> interestGamers) {
        List<GamerUser> gamers = new ArrayList<>();
        String userName = interestGamers.get(0).getGamerUser().getUserName();
        List<GamerDescription> descriptions = tx(() -> currentEntityManager().createQuery("SELECT u FROM GamerDescription u",
                GamerDescription.class).getResultList());
        for (int i = 0; i < descriptions.size(); i++) {
            if (descriptions.get(i).getGamerUser().getUserName().equals(userName)){
                descriptions.remove(descriptions.get(i));
            }
        }
        for (int i = 0; i < interestGamers.size(); i++) {
            for (int j = 0; j < descriptions.size(); j++) {
                if (interestGamers.get(i).getGame().getGameName().equals(descriptions.get(j).getGame().getGameName())
                        && Integer.parseInt(interestGamers.get(i).getLvl()) < Integer.parseInt(descriptions.get(j).getLvl())
                        && interestGamers.get(i).getRank().getRankName().equals(descriptions.get(j).getRank().getRankName())){
                    gamers.add(descriptions.get(j).getGamerUser());
                }
            }
        }
        return gamers;
    }
}
