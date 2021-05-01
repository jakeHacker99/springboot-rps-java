package rps.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import rps.model.utils.Body;
import rps.model.game.Game;
import rps.model.game.GameHistory;
import rps.model.player.Player;
import rps.model.utils.AppUtils;
import rps.model.player.PlayersStack;

@Service
public class GameService {

    private Map<Long, Player> players = new HashMap<>();
    private List<Game> games = new ArrayList<>();

    public Body spawnGame(PlayersStack availablePlayers) {
        if (isEnoughPlayersToSpawnGame(availablePlayers)) {
            return spawnNewGame(availablePlayers);
        } else {
            return defaultResponse(availablePlayers);
        }
    }

    private Body defaultResponse(PlayersStack availablePlayers) {
        List<Player> pickedPlayers = new ArrayList<Player>();
        pickedPlayers.addAll(availablePlayers.getPlayers());
        return availablePlayers.getPlayers().peek();
    }

    private Game spawnNewGame(PlayersStack availablePlayers) {
        List<Player> pickedPlayers = new ArrayList<Player>();
        pickedPlayers.add(availablePlayers.pop());
        pickedPlayers.add(availablePlayers.pop());

        Game newGame = new Game(AppUtils.createNewId());
        newGame.setPlayers(pickedPlayers);
        newGame.setState(Game.State.WAIT);
        pickedPlayers.get(0).setGame(newGame);
        pickedPlayers.get(1).setGame(newGame);
        GameHistory.getInstance().add(newGame);
        return newGame;
    }

    public Game startNewGame(Player playerOne, Player playerTwo) {
        List<Player> pickedPlayers = new ArrayList<>();
        pickedPlayers.add(playerOne);
        pickedPlayers.add(playerTwo);

        Game newGame = new Game(AppUtils.createNewId());
        games.add(newGame);
        newGame.setPlayers(pickedPlayers);
        newGame.setState(Game.State.READY);
        playerOne.setGame(newGame);
        playerTwo.setGame(newGame);
        GameHistory.getInstance().add(newGame);
        return newGame;
    }


    public boolean isEnoughPlayersToSpawnGame(PlayersStack playersStack) {
        return playersStack.size() > 1;
    }

    public Player findPlayerById(Long playerId) {
        return players.get(playerId);
    }

  /*  public Player findPlayerByName(String nickName) {

        if(players.get(name) == null){
            return this.players.get(name);
        }
        return players.get(name); }*/


    public Player registerPlayer(String nickname) {
        Player newPlayer = new Player(nickname);
        players.put(newPlayer.getPlayerId(), newPlayer);
        return newPlayer;
    }

    public Player readyForPlaying(long playerid) {
        Player player = findPlayerById(playerid);
        player.ready();
        if (!player.hasGame()) {

            Optional<Player> oppnent = players.values().stream()
                    .filter(player1 -> !player1.equals(player))
                    .filter(o -> o.getState().equals(Player.State.READY))
                    .findFirst();
            oppnent.ifPresent(o ->startNewGame(player, o));
        }
        return player;
    }

}
