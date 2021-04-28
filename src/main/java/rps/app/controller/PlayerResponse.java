package rps.app.controller;


import lombok.Data;
import rps.app.game.Game;
import rps.app.player.Player;
@Data

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


}
