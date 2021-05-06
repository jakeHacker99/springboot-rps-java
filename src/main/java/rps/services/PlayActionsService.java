/*
package rps.services;

import org.springframework.stereotype.Service;
import rps.model.utils.DefaultBody;
import rps.model.utils.Body;
import rps.model.game.Game;
import rps.model.game.GameHistory;
import rps.model.gamelogic.GameRunning;
import rps.model.gamelogic.Selection;
import rps.model.player.Player;

@Service
public class PlayActionsService {

    private static final String PLAY_ROCK = "ROCK";
    private static final String PLAY_PAPER = "PAPER";
    private static final String PLAY_SCISSORS = "SCISSORS";

    public Body readyPlayer(String gamesessionid, long playerId) {
        if (playerAndGameValid(gamesessionid, playerId)) {
            Game game = getGame(gamesessionid);
            if (game.hasAReadyPlayer()) {
                return readyGameAndPlayer(game, playerId);
            } else {
                return readyPlayer(game, playerId);
            }
        }
        return new DefaultBody("Game or Player Not Found", "INVALID");
    }

    public Body makeAMove(String gamesessionid, long playerId, String move) {
        return makeAMove(getGame(gamesessionid), playerId, move);
    }

    public Body makeAMove(Game game, long playerId, String move) {
        if (isPlayable(game)) {
            if (Game.State.OPEN.equals(game.getState())) {
                changeGameStatus(game, Game.State.ACTIVE);
            }
            GameRunning playAction = new GameRunning(game.getId(), playerId, getMove(move));
            changePlayerState(getPlayer(game,playerId), Player.State.PLAYING);

            if (game.hasOtherPlayerPlayed()) {
                game.updatePlayAction(playAction);
                Body winner = evaluateGame(game);
                if (winner == null) {
                    new DefaultBody("Improper Response", "INVALID");
                }
                changeGameStatus(game, Game.State.OVER);
                if ("TIE".equals(winner.getState()))
                {
                    return new DefaultBody("Its a TIE", "TIE");
                }
                winner = getPlayer(game, Long.parseLong(winner.getState()));
                changePlayerStatuses(game, (Player) winner);
                return winner;
            } else {
                game.updatePlayAction(playAction);
                return game;
            }
        }
        return new DefaultBody("The Other Player is Not Yet Ready", "INVALID");
    }


    private void changePlayerStatuses(Game game, Player winningPlayer) {
        changePlayerState(winningPlayer, Player.State.WIN);
        changePlayerState(getOtherPlayer(game,winningPlayer.getPlayerId()), Player.State.LOSE);
    }

    private boolean isPlayable(Game game) {
        return game.getState().equals(Game.State.OPEN) || game.getState().equals(Game.State.ACTIVE);
    }

    private Selection getMove(String move) {
        if (PLAY_ROCK.equals(move)) {
            return Selection.ROCK;
        } else if (PLAY_PAPER.equals(move)) {
            return Selection.PAPER;
        } else if (PLAY_SCISSORS.equals(move)){
            return Selection.SCISSORS;
        }
        return null;
    }

    private void changeGameStatus(Game game, Game.State state) {
        game.setState(state);
    }

    private void changePlayerState(Player player, Player.State state) {
        player.setState(state);
    }

    private Result evaluateGame(Game game) {
        long winnerId = game.evaluate();
        if (winnerId == 0L) {
            return new Result("TIE");
        }
        return new Result(String.valueOf(winnerId));
    }

    public class Result implements Body {
        private String state;

        public Result(String state) {
            this.state = state;
        }

        @Override
        public String getState() { return state;}
    }

    private Body readyPlayer(Game game, long player) {
        Player playerX = getPlayer(game, player);
        playerX.setState(Player.State.READY);
        return playerX;
    }

    private Body readyGameAndPlayer(Game game, long player) {
        Player playerX = getPlayer(game, player);
        playerX.setState(Player.State.READY);
        game.setState(Game.State.OPEN);
        return game;
    }

    private Game getGame(String gamesessionid) {
        return GameHistory.getInstance().fetch(gamesessionid);
    }

    private Player getPlayer(Game game, long playerId) {
        for (Player player : game.getPlayers()) {
            if (player.getPlayerId() == playerId) {
                return player;
            }
        }
        return null;
    }

    private Player getOtherPlayer(Game game, long playerId) {
        for (Player player : game.getPlayers()) {
            if (player.getPlayerId() != playerId) {
                return player;
            }
        }
        return null;
    }

    private boolean playerAndGameValid(String gamesessionid, long playerId) {
        if (GameHistory.gameExists(gamesessionid)) {
            Game currentGame = GameHistory.getInstance().fetch(gamesessionid);
            for (Player player : currentGame.getPlayers()) {
                if (player.getPlayerId() == playerId) {
                    return true;
                }
            }
        }
        return false;
    }

}
*/
