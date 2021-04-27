package rps.app.controller;

import rps.app.game.Game;
import rps.app.player.Player;

public class PlayerResponse {

    Long playerId;
    String name;
    Player.State state;
    String opponent;
    Game.State gameState;

    PlayerResponse(Player player) {
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
