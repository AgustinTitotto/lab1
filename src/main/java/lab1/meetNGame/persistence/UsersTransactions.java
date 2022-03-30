package lab1.meetNGame.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.function.Function;
import java.util.function.Supplier;

import static lab1.meetNGame.persistence.EntityManagers.currentEntityManager;

public class UsersTransactions {

    private UsersTransactions(){

    }

    public static <R> R persist(R entity) {
        return tx(entityManager -> {
            entityManager.persist(entity);
            return entity;
        });
    }

    public static <R> R tx(Function<EntityManager, R> s) {
        final EntityTransaction tx = currentEntityManager().getTransaction();

        try {
            tx.begin();

            R r = s.apply(currentEntityManager());

            tx.commit();
            return r;
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }

    public static <R> R tx(Supplier<R> s) {
        final EntityTransaction tx = currentEntityManager().getTransaction();

        try {
            tx.begin();

            R r = s.get();

            tx.commit();
            return r;
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }

    public static void tx(Runnable r) {
        final EntityTransaction tx = currentEntityManager().getTransaction();

        try {
            tx.begin();

            r.run();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }
}
