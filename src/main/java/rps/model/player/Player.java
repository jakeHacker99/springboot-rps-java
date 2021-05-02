package rps.model.player;

import java.util.Optional;
import java.util.Random;


import rps.model.game.Game;
import rps.model.utils.Body;
import rps.model.gamelogic.Selection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Player implements Body {

    private String name;
    @Id
    private long   playerId;
    private State  state;
    private Selection selection;
    @OneToOne()
    private Game game;

    public Player() {

    }


    public Player(long playerId) {
        this.playerId = playerId;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }



    public Player(String name) {
        this.state = State.WAIT;
        this.name = name;
        this.playerId = (new Random(System.currentTimeMillis())).nextLong() * name.length();
    }

    public String getNickName() {
        return name;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public void setSelection(Selection selection) {
        this.selection = selection;
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


    public String getName() {
        return name;
    }

    public Selection getSelection() {
        return selection;
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
        return "Player{" + "nickName='" + name + '\'' + ", playerId=" + playerId + ", state=" + state + ", Selection="
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
