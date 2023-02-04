package meetNGame;

import lab1.meetNGame.model.*;
import lab1.meetNGame.persistence.DataBase;
import lab1.meetNGame.repository.Gamers;
import org.junit.*;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static lab1.meetNGame.persistence.EntityManagers.currentEntityManager;
import static lab1.meetNGame.persistence.EntityTransactions.tx;

public class GamerUserTest {


    private final static DataBase database = new DataBase();

    @BeforeClass
    public static void beforeSuite() {
        database.startDatabaseServer();
    }

    @AfterClass
    public static void afterSuite() {
        database.stopDataBaseServer();
    }

    /*@Before
    public void beforeTest() {
        EntityManagerFactory sessionFactory = Persistence.createEntityManagerFactory("monolithic-db");
        EntityManagers.setFactory(sessionFactory);
    }

    @After
    public void close() {
        sessionFactory.close();
    }*/

    @Test
    public void getUsers() throws IOException {
        BufferedImage bImage = ImageIO.read(new File("./src/main/resources/public/img/DefaultImage.png"));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "png", bos);
        byte[] data = bos.toByteArray();

        EntityManagerFactory sessionFactory = Persistence.createEntityManagerFactory("monolithic-db");
        final EntityManager entityManager = sessionFactory.createEntityManager();
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        final GamerUser gamer1 = GamerUser.create("gamer1", "123", false, new String(Base64.getEncoder().encode(data)));
        final GamerUser gamer2 = GamerUser.create("gamer2", "456", false, new String(Base64.getEncoder().encode(data)));
        final GamerUser gamer3 = GamerUser.create("gamer3", "789", false, new String(Base64.getEncoder().encode(data)));

        entityManager.persist(gamer1);
        entityManager.persist(gamer2);
        entityManager.persist(gamer3);

        List<GamerUser> gamers1 = new ArrayList<>();
        gamers1.add(gamer2);
        gamers1.add(gamer3);

        List<GamerUser> gamers2 = entityManager.createQuery("SELECT u FROM GamerUser u",
                                  GamerUser.class).getResultList();

        gamers2.remove(gamer1);

        Assert.assertEquals(2, gamers2.size());
        Assert.assertEquals(gamers1.get(0).getUserName(), gamers2.get(0).getUserName());

        transaction.commit();
        entityManager.close();
    }

    @Test
    public void checkInterest() throws IOException {
        BufferedImage bImage = ImageIO.read(new File("./src/main/resources/public/img/DefaultImage.png"));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "png", bos);
        byte[] data = bos.toByteArray();

        EntityManagerFactory sessionFactory = Persistence.createEntityManagerFactory("monolithic-db");
        final EntityManager entityManager = sessionFactory.createEntityManager();
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        final GamerUser gamer1 = GamerUser.create("gamer1", "123", false, new String(Base64.getEncoder().encode(data)));
        final GamerUser gamer2 = GamerUser.create("gamer2", "456", false, new String(Base64.getEncoder().encode(data)));
        final GamerUser gamer3 = GamerUser.create("gamer3", "789", false, new String(Base64.getEncoder().encode(data)));
        entityManager.persist(gamer1);
        entityManager.persist(gamer2);
        entityManager.persist(gamer3);

        final Game game1 = Game.createGame("Hola", "Shooter", "15");
        final Game game2 = Game.createGame("Yupi", "MBO", "30");
        entityManager.persist(game1);
        entityManager.persist(game2);

        final Rank rank1 = Rank.createRank("Hola1");
        final Rank rank2 = Rank.createRank("Hola2");
        final Rank rank3 = Rank.createRank("Yupi1");
        final Rank rank4 = Rank.createRank("Yupi2");
        List<Rank> list1 = new ArrayList<>();
        List<Rank> list2 = new ArrayList<>();
        list1.add(rank1);
        list1.add(rank2);
        list2.add(rank3);
        list2.add(rank4);
        game1.setRanks(list1);
        game2.setRanks(list2);
        entityManager.persist(rank1);
        entityManager.persist(rank2);
        entityManager.persist(rank3);
        entityManager.persist(rank4);

        final GamerDescription description1 = GamerDescription.createDescription("10"); //gamer1
        final GamerDescription description12 = GamerDescription.createDescription("15"); //gamer1
        final GamerDescription description2 = GamerDescription.createDescription("2"); //gamer2
        final GamerDescription description3 = GamerDescription.createDescription("22"); //gamer2
        final GamerDescription description4 = GamerDescription.createDescription("25"); //gamer3
        description1.setGamerUser(gamer1);
        description1.setGame(game1);
        description1.setRank(rank2);
        description12.setGamerUser(gamer1);
        description12.setGame(game2);
        description12.setRank(rank3);

        description2.setGamerUser(gamer2);
        description2.setGame(game1);
        description2.setRank(rank1);
        description3.setGamerUser(gamer2);
        description3.setGame(game2);
        description3.setRank(rank4);

        description4.setGamerUser(gamer3);
        description4.setGame(game2);
        description4.setRank(rank4);
        entityManager.persist(description1);
        entityManager.persist(description12);
        entityManager.persist(description2);
        entityManager.persist(description3);
        entityManager.persist(description4);

        final GamerInterest interest1 = GamerInterest.createInterest("15");
        final GamerInterest interest2 = GamerInterest.createInterest("20");
        interest1.setGamerUser(gamer1);
        interest1.setGame(game1);
        interest1.setRank(rank2);
        interest2.setGamerUser(gamer1);
        interest2.setGame(game2);
        interest2.setRank(rank4);

        entityManager.persist(interest1);
        entityManager.persist(interest2);
        List<GamerInterest> interests = new ArrayList<>();
        interests.add(interest1);
        interests.add(interest2);

        List<GamerUser> gamers = new ArrayList<>();
        String userName = gamer1.getUserName();
        List<GamerDescription> descriptions = entityManager.createQuery("SELECT u FROM GamerDescription u",
                GamerDescription.class).getResultList();
        for (int i = 0; i < descriptions.size(); i++) {
            if (descriptions.get(i).getGamerUser().getUserName().equals(userName)){
                descriptions.remove(descriptions.get(i));
            }
        }
        for (int i = 0; i < interests.size(); i++) {
            for (int j = 0; j < descriptions.size(); j++) {
                if (interests.get(i).getGame().getGameName().equals(descriptions.get(j).getGame().getGameName())
                        && Integer.parseInt(interests.get(i).getLvl()) < Integer.parseInt(descriptions.get(j).getLvl())
                        && interests.get(i).getRank().getRankName().equals(descriptions.get(j).getRank().getRankName())){
                    gamers.add(descriptions.get(j).getGamerUser());
                }
            }
        }

        Assert.assertEquals(2, gamers.size());

        transaction.commit();
        entityManager.close();
    }
}