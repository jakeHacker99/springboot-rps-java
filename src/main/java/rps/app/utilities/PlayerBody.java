package rps.app.utilities;

import rps.app.game.Game;
import rps.app.player.Player;

public class PlayerBody {

    Long playerId;
    String name;
    Player.State state;
    String opponent;
    Game.State gameState;

    public PlayerBody(Player player) {
        this.playerId = player.getPlayerId();
        this.name = player.getNickName();
        this.state = player.getState();
        this.opponent = player.getOpponentNickName();
        this.gameState = player.getGameState();
    }


    public Long getPlayerId() {
        return playerId;
    }

    public String getNickName() {
        return name;
    }

    public Player.State getState() {
        return state;
    }

    public String getOpponent() {
        return opponent;
    }

    public Game.State getGameState() {
        return gameState;
    }
}
