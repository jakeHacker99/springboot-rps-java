package rps.app.gamelogic;

public class GameRunning {
    private String game;
    private Long player;
    private Selection selection;

    public GameRunning(String game, Long player, Selection selection) {
        this.game = game;
        this.player = player;
        this.selection = selection;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public Long getPlayer() {
        return player;
    }

    public void setPlayer(Long player) {
        this.player = player;
    }

    public Selection getSelection() {
        return selection;
    }

    public void setSelection(Selection selection) {
        this.selection = selection;
    }
}
