package rps.model.gamelogic;

import lombok.Data;

import javax.persistence.*;


@Data
public class GameRunning {

    private String game;
    private Long player;
    private Selection selection;

    public GameRunning(String game, Long player, Selection selection) {
        this.game = game;
        this.player = player;
        this.selection = selection;
    }

    public GameRunning() {

    }
}
