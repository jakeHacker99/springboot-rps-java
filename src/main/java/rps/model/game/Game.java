package rps.model.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import lombok.Data;
import lombok.NoArgsConstructor;
import rps.model.gamelogic.Selection;
import rps.tokens.Token;

import javax.persistence.*;
@Data
@Entity
@Table(name = "ourGame")
@NoArgsConstructor
public class Game {

    @Id
    private String id;
    private Selection move;
    private  Selection opponentMove;
    private State state;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    Token owner;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    Token joiner;



    public Game(String id, Token owner) {
        this.id = id;
        this.owner = owner;
        this.state = State.OPEN;

    }





/*
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
*/

    /*public boolean hasAReadyPlayer() {
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

    public String getId() {
        return id;
    }

    public List<GameRunning> getActions() {
        return actions;
    }

    public void setId(String id) {
        this.id = id;
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


    public Selection getOponnentMove(){

        return null;

    }

    public void playerReady(Player player) {
        if (getOpponent(player)
                .map(Player::getState)
                .orElse(Player.State.WAIT)
                .equals(Player.State.READY)) {
            state = State.OPEN;
        }
    }

    public void playerMove(Player player) {
        if (State.OPEN.equals(getState())) {
            setState(State.ACTIVE);
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
    }*/

    public enum State {
         NONE(3), OPEN(2), ACTIVE(1), OVER(0);
        private int value;

        State(int value) {
            this.value = value;
        }
    }


}