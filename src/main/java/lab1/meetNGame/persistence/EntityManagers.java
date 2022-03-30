package lab1.meetNGame.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class EntityManagers {

    final static private ThreadLocal<EntityManager> emRefMeet = new ThreadLocal<>();

    private static EntityManagerFactory emf;

    public static void setFactory(EntityManagerFactory emf) {
        EntityManagers.emf = emf;
    }

    public static EntityManager currentEntityManager() {
        final EntityManager em = emRefMeet.get();
        if (em == null) {
            emRefMeet.set(emf.createEntityManager());
        }
        return emRefMeet.get();
    }

    public static void close() {
        final EntityManager em = emRefMeet.get();
        if (em != null && em.isOpen()) {
            em.close();
        }
        emRefMeet.remove();
    }
}
