package rps.model.utils;

import lombok.Value;
import rps.model.game.Game;
import rps.model.gamelogic.Selection;

import java.util.UUID;

@Value
public class GameBody  {

    private String id;
    private String name;
    private Selection move;
    private Game.State game;
    private String opponentName;
    private Selection opponentMove;

    public GameBody( String name, Selection move, Game.State game, String opponentName, Selection opponentMove) {
        this.id =createGameId();
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
