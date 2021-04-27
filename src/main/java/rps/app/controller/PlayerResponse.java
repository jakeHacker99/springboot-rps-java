package rps.app.controller;

import rps.app.game.Game;
import rps.app.player.Player;

public class PlayerResponse {

    Long playerId;
    String nickName;
    Player.State state;
    String opponent;
    Game.State gameState;

    PlayerResponse(Player player) {
        this.playerId = player.getPlayerId();
        this.nickName = player.getNickName();
        this.state = player.getState();
        this.opponent = player.getOpponentNickName();
        this.gameState = player.getGameState();
    }

    public Long getPlayerId() {
        return playerId;
    }

    public String getNickName() {
        return nickName;
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
