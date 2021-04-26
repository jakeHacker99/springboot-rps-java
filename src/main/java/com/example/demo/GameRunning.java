package com.example.demo;

public class GameRunning {
    private String game;
    private String player;
    private Selection selection;

    public GameRunning(String game, String player, Selection selection) {
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

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public Selection getSelection() {
        return selection;
    }

    public void setSelection(Selection selection) {
        this.selection = selection;
    }
}
