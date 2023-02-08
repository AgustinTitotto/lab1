package lab1.meetNGame.repository;

import lab1.meetNGame.model.Game;
import lab1.meetNGame.model.GamerUser;
import lab1.meetNGame.model.Like;
import lab1.meetNGame.model.Match;
import lab1.meetNGame.persistence.EntityTransactions;

import java.util.ArrayList;
import java.util.List;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Session;
import javax.mail.Transport;

import static lab1.meetNGame.persistence.EntityManagers.currentEntityManager;
import static lab1.meetNGame.persistence.EntityTransactions.tx;

public class Matches {

    String from = "meetNgame@gmail.com";
    String password = "oiozwjnhswsarnws";
    Properties properties = new Properties();
    Properties properties1 = (Properties) properties.put("mail.smtp.auth","true");
    Properties properties2 = (Properties) properties.put("mail.smtp.starttls.enable","true");
    Properties properties3 = (Properties) properties.setProperty("mail.smtp.host", "smtp.gmail.com");
    Properties properties4 = (Properties) properties.setProperty("mail.smtp.port", "587");
    Session session1 = Session.getInstance(properties, new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(from, password);
        }
    });

    Session session2 = Session.getInstance(properties, new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(from, password);
        }
    });


    public Game match(GamerUser currentUser, List<GamerUser> currentUserMatches) throws MessagingException {
        List<Like> likedDescriptions = tx(() -> currentEntityManager().createQuery("SELECT u FROM Like u WHERE u.likedDescription.gamerUser.userName Like:gamerUser",
                Like.class).setParameter("gamerUser", currentUser.getUserName()).getResultList()); //Donde otro likeo a current user
        List<Like> likedByUser = tx(() -> currentEntityManager().createQuery("SELECT u FROM Like u WHERE u.mainUser.userName LIKE: userName",
                Like.class).setParameter("userName", currentUser.getUserName()).getResultList()); // Likeo current user
        List<Match> matches = new ArrayList<>();
        for (Like like : likedByUser) {
            for (Like likedDescription : likedDescriptions) {
                if (like.getMainUser().getUserName().equals(likedDescription.getLikedDescription().getGamerUser().getUserName())
                        && like.getLikedDescription().getGame().getGameName().equals(likedDescription.getLikedDescription().getGame().getGameName())) {
                    if (currentUserMatches.isEmpty()) { // Si no tiene match, agregalo
                        setMailData(likedDescription, like);
                        return generateMatch(matches, like, likedDescription);
                    } else {                            // Fijate que no este repetido
                        boolean repeated = false;
                        for (GamerUser userMatch : currentUserMatches) {
                            if (likedDescription.getMainUser().getUserName().equals(userMatch.getUserName())) {
                                repeated = true;
                                break;
                            }
                        }
                        if (!repeated) {
                            return generateMatch(matches, like, likedDescription);
                        }
                    }
                }
            }
        }
        return null;
    }

    private Game generateMatch(List<Match> matches, Like like, Like likedDescription) {
        Match match = Match.createMatch();
        match.setUser1(like.getMainUser());
        match.setUser2(likedDescription.getMainUser());
        match.setCommonGame(likedDescription.getLikedDescription().getGame());
        EntityTransactions.persist(match);
        matches.add(match);
        return likedDescription.getLikedDescription().getGame();
    }

    private void setMailData(Like likedDescription, Like like) throws MessagingException {
        String commonGame = like.getLikedDescription().getGame().getGameName();

        String User1 = like.getMainUser().getUserName();
        String mail1 = like.getMainUser().getMail();
        String level1 = likedDescription.getLikedDescription().getLvl();
        String rank1 = likedDescription.getLikedDescription().getRank().getRankName();

        String User2 = likedDescription.getMainUser().getUserName();
        String mail2 = likedDescription.getMainUser().getMail();
        String level2 = like.getLikedDescription().getLvl();
        String rank2 = like.getLikedDescription().getRank().getRankName();
        if (User1 != null && User2 != null) {
            Message message1 = new MimeMessage(session1);
            sendMail(commonGame, mail1, User2, level2, rank2, message1);
            Message message2 = new MimeMessage(session2);
            sendMail(commonGame, mail2, User1, level1, rank1, message2);
            try{
                Transport.send(message1);
                System.out.println("Sent message successfully....");
            } catch (MessagingException mex) {
                mex.printStackTrace();
            }
            try{
                Transport.send(message2);
                System.out.println("Sent message successfully....");
            } catch (MessagingException mex) {
                mex.printStackTrace();
            }
        }
    }

    private void sendMail(String commonGame, String mail1, String User2, String level2, String rank2, Message message1) throws MessagingException {
        message1.setFrom(new InternetAddress(from));
        message1.setRecipient(Message.RecipientType.TO, new InternetAddress(mail1));
        message1.setSubject("You have a Match");
        message1.setContent("<h1>You just made a match:</h1>" +
                "<br>" +
                "<p>User Name: "+ User2 +"</p>" +
                "<br>" +
                "<p>Common Game: "+ commonGame +"</p>"+
                "<br>" +
                "<p>Level: "+ level2 +"</p>"+
                "<br>" +
                "<p>Rank: "+ rank2 +"</p>","text/html");
    }


    public List<GamerUser> showMatches(GamerUser gamer){
        List<GamerUser> finalList = new ArrayList<>();
        List<Match> gamers1 = tx(() -> currentEntityManager().createQuery("SELECT u FROM Match u WHERE u.user1.userName LIKE: userName",
                Match.class).setParameter("userName", gamer.getUserName()).getResultList());
        List<Match> gamers2 = tx(() -> currentEntityManager().createQuery("SELECT u FROM Match u WHERE u.user2.userName LIKE: userName",
                Match.class).setParameter("userName", gamer.getUserName()).getResultList());
        for (Match match : gamers1) {
            finalList.add(match.getUser2());
        }
        for (Match match : gamers2) {
            finalList.add(match.getUser1());
        }
        return finalList;
    }
}
