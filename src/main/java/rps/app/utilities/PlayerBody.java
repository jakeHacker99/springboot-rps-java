package rps.app.utilities;


import lombok.Data;
import rps.app.game.Game;
import rps.app.player.Player;
@Data

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


}