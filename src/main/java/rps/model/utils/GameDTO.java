package rps.model.utils;

import lombok.Value;
import rps.model.game.Game;
import rps.model.gamelogic.Selection;

import java.util.UUID;

@Value
public class GameDTO {

    private String id;
    private String name;
    private Selection move;
    private Game.State game;
    private String opponentName;
   private Selection opponentMove;
   private String winner;

    public GameDTO(String id, String name, Selection move, Game.State game, String opponentName, Selection opponentMove, String winner) {
        this.id = id;
        this.name = name;
        this.move = move;
        this.game = game;
        this.opponentName = opponentName;
        this.opponentMove = opponentMove;
        this.winner = winner;
    }

    public String createGameId() {
        return UUID.randomUUID().toString();
    }

}
