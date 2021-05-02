package rps.model.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import rps.model.utils.Body;
import rps.model.player.Player;
import rps.model.gamelogic.GameRunning;
import rps.model.gamelogic.RulesForGame;
import rps.model.gamelogic.Selection;

import javax.persistence.*;

@Entity
public class Game implements Body {

    @Id
    private String gameId;

    @OneToMany
    private List<Player> players;
    private State state;
    @OneToOne
    private Player winner;
    @OneToMany
    private List<GameRunning> actions = new ArrayList<>();


    public Game(String gameId) {
        this.gameId = gameId;
        this.state = State.WAIT;
    }

    public Game() {

    }


    public void makeWinnerOfGame(Player winner) {
        setState(State.OVER);
        this.winner = winner;
    }

    public void updatePlayAction(GameRunning playRunning) {
        actions.add(playRunning);
    }

    public boolean hasOtherPlayerPlayed() {
        return actions.size() == 1;
    }

    public boolean hasAReadyPlayer() {
        for (Player player : this.getPlayers()) {
            if (Player.State.READY.equals(player.getState())) {
                return true;
            }

        }
        return false;
    }

    public Long evaluate() {
        Selection result = evaluateMoves();
        if (result == null || Selection.Draw.equals(result)) {
            return null;
        }
        for (GameRunning action : actions) {
            if (result.equals(action.getSelection())) {
                return action.getPlayer();
            }

        }
        return null;
    }


    private Selection evaluateMoves() {
        if (actions.size() == 2) {
            GameRunning player1 = actions.get(0);
            GameRunning player2 = actions.get(1);
            RulesForGame rules = new RulesForGame();
            return rules.result(player1.getSelection(), player2.getSelection());
        }
        return null;
    }

    public String getGameId() {
        return gameId;
    }

    public List<GameRunning> getActions() {
        return actions;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    @Override
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public String getOpponentNickName(Player player) {
        return getOpponent(player)
                .map(Player::getNickName)
                .orElse("");
    }


    public Optional<Player> getOpponent(Player player) {
        if (players.size() < 2) {
            return Optional.empty();
        }
        return Optional.of(players.get(0).equals(player) ? players.get(1) : players.get(0));
    }

    public void playerReady(Player player) {
        if (getOpponent(player)
                .map(Player::getState)
                .orElse(Player.State.WAIT)
                .equals(Player.State.READY)) {
            state = State.READY;
        }
    }

    public void playerMove(Player player) {
        if (State.READY.equals(getState())) {
            setState(State.INPROGRESS);
        }

        getOpponent(player).ifPresent(opponent -> opponent.getMove().ifPresent(move -> {
            evaluateMove(player, opponent).ifPresent(this::makeWinnerOfGame);
            opponent.updateState();
        }));

    }

    private Optional<Player> evaluateMove(Player playerOne, Player playerTwo) {
        Selection selectionPlayerOne = playerOne.getMove().get();
        Selection selectionPlayerTow = playerTwo.getMove().get();
        RulesForGame rules = new RulesForGame();
        Selection result = rules.result(selectionPlayerOne, selectionPlayerTow);
        if (result.equals(Selection.Draw)) {
            return Optional.empty();
        }
        return Optional.of(result.equals(selectionPlayerOne) ? playerOne : playerTwo);
    }

    public Optional<Player> getWinner() {
        return Optional.ofNullable(winner);
    }

    public enum State {
        WAIT(3), READY(2), INPROGRESS(1), OVER(0);
        private int value;

        State(int value) {
            this.value = value;
        }
    }


}