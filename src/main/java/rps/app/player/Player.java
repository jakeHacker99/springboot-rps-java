package rps.app.player;

import java.util.Optional;
import java.util.Random;


import rps.app.Response;
import rps.app.gamelogic.Selection;
import rps.app.game.Game;



public class Player implements Response {



    private String nickName;

    private long   playerId;
    private State  state;
    private Selection selection;
    private Game game;



    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }



    public Player(String nickName) {
        this.state = State.WAIT;
        this.nickName = nickName;
        this.playerId = (new Random(System.currentTimeMillis())).nextLong() * nickName.length();
    }

    public String getNickName() {
        return nickName;
    }

    public long getPlayerId() {
        return playerId;
    }

    @Override public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getOpponentNickName() {
        if (game == null) {
            return "";
        }
        return game.getOpponentNickName(this);
    }

    public void ready() {
        state = State.READY;
        if (game != null) {
            game.playerReady(this);
        }
    }

    public Game.State getGameState() {
        return game == null ? null : game.getState() ;
    }

    public void move(Selection selection) {
        if (game == null)
            throw new RuntimeException("No game!");
        this.selection = selection;
        game.playerMove(this);
        updateState();
    }

    public boolean hasGame() {
        return game != null;
    }

    public Optional<Selection> getMove() {
        return Optional.ofNullable(selection);
    }

    public void updateState() {
        if (game == null){
            return;
        }
        game.getWinner().ifPresent(player -> this.state = player.equals(this) ? State.WIN : State.LOSE);
    }

    @Override
    public String toString() {
        return "Player{" + "nickName='" + nickName + '\'' + ", playerId=" + playerId + ", state=" + state + ", Selection="
                + selection + ", game=" + game + '}';
    }

    public enum State {
        WAIT(0), READY(1), PLAYING(2), WIN(3), LOSE(4);
        private int value;

        private State(int value) {
            this.value = value;
        }

        @Override public String toString() {
            switch (this.value) {
                case 0:
                    return "WAIT";
                case 1:
                    return "READY";
                case 2:
                    return "PLAYING";
                case 3:
                    return "WIN";
                case 4:
                    return "LOSE";
            }
            return "IDLE";
        }
    }


}
