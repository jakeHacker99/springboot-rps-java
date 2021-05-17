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

    public GameDTO(String id, String name, Selection move, Game.State game, String opponentName, Selection opponentMove) {
        this.id = id;
        this.name = name;
        this.move = move;
        this.game = game;
        this.opponentName = opponentName;
        this.opponentMove = opponentMove;
    }


    public String createGameId() {
        return UUID.randomUUID().toString();
    }

}
