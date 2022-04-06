package lab1.meetNGame.repository;

import lab1.meetNGame.model.CreateGameForm;
import lab1.meetNGame.model.Game;
import lab1.meetNGame.persistence.EntityTransactions;

import java.nio.channels.IllegalChannelGroupException;
import java.util.Optional;

import static lab1.meetNGame.persistence.EntityManagers.currentEntityManager;
import static lab1.meetNGame.persistence.EntityTransactions.tx;

public class Games {

    public boolean gameExists(String gameName){
        return findByGameName(gameName).isPresent();
    }

    public Optional<Game> findByGameName(String gameName) {
        return tx(() -> currentEntityManager().createQuery("SELECT u FROM Game u WHERE u.gameName LIKE: gameName",
                Game.class).setParameter("gameName", gameName).getResultList()).stream().findFirst();
    }

    public Game createGame(CreateGameForm form){
        final Game newGame = Game.createGame(form.getGameName(), form.getCategory(), form.getLvlMax());
        if (gameExists(newGame.getGameName())) throw new IllegalStateException("Game already exists");
        return EntityTransactions.persist(newGame);
    }
}
