package lab1.meetNGame;

import lab1.meetNGame.model.*;
import lab1.meetNGame.persistence.DataBase;
import lab1.meetNGame.persistence.EntityManagers;
import lab1.meetNGame.persistence.EntityTransactions;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.port;
import static spark.Spark.staticFiles;

public class WebApp {

    private final WebRoutes webRoutes = new WebRoutes();
    private final DataBase dataserver = new DataBase();

    public void start(){
        //startDatabase();
        startWebServer();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("monolithic-db");
        EntityManagers.setFactory(entityManagerFactory);
        initialData();
    }

    private void initialData() {
        GamerUser admin = new GamerUser("meetngame", "meetngame123", true);
        GamerUser gamer1 = new GamerUser("gamer1", "123", false);
        EntityTransactions.persist(admin);
        EntityTransactions.persist(gamer1);

        final GamerUser gamer2 = GamerUser.create("gamer2", "456", false);
        final GamerUser gamer3 = GamerUser.create("gamer3", "789", false);
        EntityTransactions.persist(gamer2);
        EntityTransactions.persist(gamer3);

        final Game game1 = Game.createGame("Hola", "Shooter", "15");
        final Game game2 = Game.createGame("Yupi", "MBO", "30");
        EntityTransactions.persist(game1);
        EntityTransactions.persist(game2);

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
        EntityTransactions.persist(rank1);
        EntityTransactions.persist(rank2);
        EntityTransactions.persist(rank3);
        EntityTransactions.persist(rank4);

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
        EntityTransactions.persist(description1);
        EntityTransactions.persist(description12);
        EntityTransactions.persist(description2);
        EntityTransactions.persist(description3);
        EntityTransactions.persist(description4);

        final GamerInterest interest1 = GamerInterest.createInterest("20");
        interest1.setGamerUser(gamer1);
        interest1.setGame(game2);
        interest1.setRank(rank4);
        final GamerInterest interest2 = GamerInterest.createInterest("10");
        interest2.setGamerUser(gamer3);
        interest2.setGame(game2);
        interest2.setRank(rank3);
        EntityTransactions.persist(interest1);
        EntityTransactions.persist(interest2);

        final Like like1 = Like.createLike();
        final Like like3 = Like.createLike();
        like1.setMainUser(gamer1);
        like1.setLikedUser(description4);
        like3.setMainUser(gamer3);
        like3.setLikedUser(description1);
        EntityTransactions.persist(like1);
        EntityTransactions.persist(like3);

        final Match match = Match.createMatch();
        match.setUser1(gamer1);
        match.setUser2(gamer3);
        match.setCommonGame(game1);
        EntityTransactions.persist(match);
    }

    private void startDatabase() {
        dataserver.startDatabaseServer();
    }

    private void startWebServer() {
        staticFiles.location("public");
        port(4335);
        webRoutes.startRoutes();
    }

    /*private void stopDatabase() {
        dataserver.stopDataBaseServer();
    }

    private void stopWebServer() {
        Spark.stop();
    }*/

    /*
    final GamerUser gamer2 = GamerUser.create("gamer2", "456", false);
    final GamerUser gamer3 = GamerUser.create("gamer3", "789", false);
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


     */
}
