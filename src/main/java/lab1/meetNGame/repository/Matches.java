package lab1.meetNGame.repository;

import lab1.meetNGame.model.GamerUser;
import lab1.meetNGame.model.Like;
import lab1.meetNGame.model.Match;
import lab1.meetNGame.persistence.EntityTransactions;

import java.util.ArrayList;
import java.util.List;

import static lab1.meetNGame.persistence.EntityManagers.currentEntityManager;
import static lab1.meetNGame.persistence.EntityTransactions.tx;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Session;
import javax.mail.Transport;

public class Matches {
    String commonGame = null;
    private String User1 = null;
    String mail1 = null;
    String level1 = null;
    String rank1 = null;
    private String User2 = null;
    String mail2 = null;
    String level2 = null;
    String rank2 = null;
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

    public List<Match> match(GamerUser mainUser, List<GamerUser> userMatches) throws MessagingException {
        List<Like> likedDescriptions = tx(() -> currentEntityManager().createQuery("SELECT u FROM Like u WHERE u.likedUser.gamerUser.userName Like:gamerUser",
                Like.class).setParameter("gamerUser", mainUser.getUserName()).getResultList());
        List<Like> user = tx(() -> currentEntityManager().createQuery("SELECT u FROM Like u WHERE u.mainUser.userName LIKE: userName",
                Like.class).setParameter("userName", mainUser.getUserName()).getResultList());
        List<Match> matches = new ArrayList<>();
        for (int i = 0; i < user.size(); i++) {
            for (int j = 0; j < likedDescriptions.size(); j++) {
                if (user.get(i).getMainUser().getUserName().equals(likedDescriptions.get(j).getLikedUser().getGamerUser().getUserName())
                && user.get(i).getLikedUser().getGame().getGameName().equals(likedDescriptions.get(j).getLikedUser().getGame().getGameName())){
                    if (userMatches.isEmpty()){
                        Match match = Match.createMatch();
                        match.setUser1(user.get(i).getMainUser());
                        match.setUser2(likedDescriptions.get(j).getMainUser());
                        match.setCommonGame(likedDescriptions.get(j).getLikedUser().getGame());
                        EntityTransactions.persist(match);
                        matches.add(match);
                        commonGame = user.get(i).getLikedUser().getGame().getGameName();

                        User1 = user.get(i).getMainUser().getUserName();
                        mail1 = user.get(i).getMainUser().getMail();
                        level1 = likedDescriptions.get(j).getLikedUser().getLvl();
                        rank1 = likedDescriptions.get(j).getLikedUser().getRank().getRankName();

                        User2 = likedDescriptions.get(j).getMainUser().getUserName();
                        mail2 = likedDescriptions.get(j).getMainUser().getMail();
                        level2 = user.get(i).getLikedUser().getLvl();
                        rank2 = user.get(i).getLikedUser().getRank().getRankName();
                    }else{
                        boolean repeated = false;
                        for (GamerUser userMatch : userMatches) {
                            if (likedDescriptions.get(j).getMainUser().getUserName().equals(userMatch.getUserName())) {
                                repeated = true;
                                break;
                            }
                        }
                        if (!repeated){
                            Match match = Match.createMatch();
                            match.setUser1(user.get(i).getMainUser());
                            match.setUser2(likedDescriptions.get(j).getMainUser());
                            match.setCommonGame(likedDescriptions.get(j).getLikedUser().getGame());
                            EntityTransactions.persist(match);
                            matches.add(match);
                            commonGame = user.get(i).getLikedUser().getGame().getGameName();

                            User1 = user.get(i).getMainUser().getUserName();
                            mail1 = user.get(i).getMainUser().getMail();
                            level1 = likedDescriptions.get(j).getLikedUser().getLvl();
                            rank1 = likedDescriptions.get(j).getLikedUser().getRank().getRankName();

                            User2 = likedDescriptions.get(j).getMainUser().getUserName();
                            mail2 = likedDescriptions.get(j).getMainUser().getMail();
                            level2 = user.get(i).getLikedUser().getLvl();
                            rank2 = user.get(i).getLikedUser().getRank().getRankName();
                        }
                    }
                }
            }
        }
        if (User1 != null && User2 != null) {
            Message message1 = new MimeMessage(session1);
            message1.setFrom(new InternetAddress(from));
            message1.setRecipient(Message.RecipientType.TO, new InternetAddress(mail1));
            message1.setSubject("You have a Match");
            message1.setContent("<h1>You just made a match:</h1>" +
                    "<br>" +
                    "<p>User Name: "+User2+"</p>" +
                    "<br>" +
                    "<p>Common Game: "+commonGame+"</p>"+
                    "<br>" +
                    "<p>Level: "+level2+"</p>"+
                    "<br>" +
                    "<p>Rank: "+rank2+"</p>","text/html");
            Message message2 = new MimeMessage(session2);
            message2.setFrom(new InternetAddress(from));
            message2.setRecipient(Message.RecipientType.TO, new InternetAddress(mail2));
            message2.setSubject("You have a Match");
            message2.setContent("<h1>You just made a match:</h1>" +
                    "<br>" +
                    "<p>User Name: "+User1+"</p>" +
                    "<br>" +
                    "<p>Common Game: "+commonGame+"</p>"+
                    "<br>" +
                    "<p>Level: "+level1+"</p>"+
                    "<br>" +
                    "<p>Rank: "+rank1+"</p>","text/html");
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
        return matches;
    }


    public List<GamerUser> showMatches(GamerUser gamer){
        List<GamerUser> finalList = new ArrayList<>();
        List<Match> gamers1 = tx(() -> currentEntityManager().createQuery("SELECT u FROM Match u WHERE u.user1.userName LIKE: userName",
                Match.class).setParameter("userName", gamer.getUserName()).getResultList());
        List<Match> gamers2 = tx(() -> currentEntityManager().createQuery("SELECT u FROM Match u WHERE u.user2.userName LIKE: userName",
                Match.class).setParameter("userName", gamer.getUserName()).getResultList());
        for (int i = 0; i < gamers1.size(); i++) {
            finalList.add(gamers1.get(i).getUser2());
        }
        for (int i = 0; i < gamers2.size(); i++) {
            finalList.add(gamers2.get(i).getUser1());
        }
        return finalList;
    }

    public String getUser1() {
        return User1;
    }

    public String getUser2() {
        return User2;
    }
}
