package rps.app.game;

import java.util.HashMap;
import java.util.Map;

public class GameHistory {

    private static GameHistory instance = null;
    private static Map<String, Game> activeSessions;

    private GameHistory() {
        this.activeSessions = new HashMap<String, Game>();
    }

    public static GameHistory getInstance() {
        if (instance == null) {
            instance = new GameHistory();
        }
        return instance;
    }

    public static boolean gameExists(String gamesessionid) {
        return (fetch(gamesessionid) != null);
    }

    public static Game fetch(String gamesessionid) {
        return activeSessions.get(gamesessionid);
    }

    public void add(Game newGame) {
        activeSessions.put(newGame.getGameId(), newGame);
    }

    public void delete(Game game) {
        activeSessions.remove(game.getGameId());
    }
}
