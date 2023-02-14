package lab1.meetNGame.repository;

import lab1.meetNGame.model.Stats;

import static lab1.meetNGame.persistence.EntityManagers.currentEntityManager;
import static lab1.meetNGame.persistence.EntityTransactions.tx;

public class StatsRepo {


    public Stats getUserStats(String name) {
        Long gamesPlayed = tx(() -> currentEntityManager().createQuery("select count(u) from GamerDescription u where u.gamerUser.userName like: userName", Long.class)
                .setParameter("userName", name).getSingleResult());
        Long likes = tx(() -> currentEntityManager().createQuery("select count(u) from Like u where u.mainUser.userName like :userName", Long.class)
                .setParameter("userName", name).getSingleResult());
        Long matches = tx(() -> currentEntityManager().createQuery("select count(u) from Match u where u.user1.userName like :userName", Long.class)
                .setParameter("userName", name).getSingleResult()) +
                      tx(() -> currentEntityManager().createQuery("select count(u) from Match u where u.user2.userName like :userName", Long.class)
                .setParameter("userName", name).getSingleResult());
        return new Stats(gamesPlayed, likes, matches);
    }
}
