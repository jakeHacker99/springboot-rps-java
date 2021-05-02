package rps.dto;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import rps.model.game.Game;
import rps.model.gamelogic.Selection;
import rps.model.player.Player;
import rps.repositories.GameRepository;
import rps.repositories.PlayerRepository;

@Component
public class Data implements ApplicationListener<ContextRefreshedEvent> {

    private GameRepository gameRepository;


    private PlayerRepository playerRepository;


    public Data(GameRepository gameRepository, PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();

    }
    private void initData(){

        Game game1 = new Game();
        game1.setGameId("2");
        game1.setState(Game.State.INPROGRESS);

        Game game2 = new Game();
        game2.setGameId("1");
        game2.setState(Game.State.INPROGRESS);
        game2.getWinner().toString();


        Game game3= new Game();
        game3.setGameId("4");
        game3.setState(Game.State.WAIT);

        Game game4 = new Game();
        game4.setGameId("3");
        game4.setState(Game.State.WAIT);


        Game game5 = new Game();
        game5.setGameId("6");
        game5.setState(Game.State.INPROGRESS);

        Game game6 = new Game();
        game6.setGameId("5");
        game6.setState(Game.State.OVER);

        Game game7 = new Game();
        game7.setGameId("8");
        game7.setState(Game.State.OVER);

        Game game8 = new Game();
        game8.setGameId("7");
        game8.setState(Game.State.WAIT);

        Game game9 = new Game();
        game9.setGameId("10");
        game9.setState(Game.State.OVER);
        game9.getWinner();
        Player p1 = new Player();

        Game game10 = new Game();
        game10.setGameId("9");
        game10.setState(Game.State.OVER);




        gameRepository.save(game1);
        gameRepository.save(game2);
        gameRepository.save(game3);
        gameRepository.save(game4);
        gameRepository.save(game5);
        gameRepository.save(game6);
        gameRepository.save(game7);
        gameRepository.save(game8);
        gameRepository.save(game9);
        gameRepository.save(game10);



        Player player1 = new Player();

        player1.setPlayerId(1);
        player1.setName("jake");
        player1.setSelection(Selection.PAPER);
        player1.setState(Player.State.READY);
        player1.setGame(game1);


        playerRepository.save(player1);

        Player player2 = new Player();
        player2.setPlayerId(2);
        player2.setName("yazan");
        player2.setSelection(Selection.ROCK);

        player2.setState(Player.State.READY);
        player2.setGame(game2);

        playerRepository.save(player2);


        Player player3 = new Player();
        player3.setPlayerId(3);
        player3.setName("adam");
        player3.setSelection(Selection.SCISSORS);
        player3.setState(Player.State.WAIT);
        player3.setGame(game3);

        playerRepository.save(player3);

        Player player11 = new Player();
        player11.setPlayerId(4);
        player11.setName("william");
        player11.setSelection(Selection.ROCK);

        player11.setState(Player.State.READY);
        player11.setGame(game4);



        playerRepository.save(player11);

        Player player4 = new Player();
        player4.setPlayerId(5);
        player4.setName("gabriella");
        player4.setSelection(Selection.PAPER);
        player4.setState(Player.State.PLAYING);
        player4.setGame(game5);


        playerRepository.save(player4);

        Player player5 = new Player();
        player5.setPlayerId(6);
        player5.setName("christian");
        player5.setSelection(Selection.ROCK);

        player5.setState(Player.State.LOSE);
        player5.setGame(game6);

        playerRepository.save(player5);

        Player player6 = new Player();
        player6.setPlayerId(7);
        player6.setName("gabriella");
        player6.setSelection(Selection.ROCK);
        player6.setState(Player.State.PLAYING);
        player6.setGame(game7);


        playerRepository.save(player6);

        Player player7 = new Player();
        player7.setPlayerId(8);
        player7.setName("caroline");
        player7.setSelection(Selection.ROCK);
        player7.setState(Player.State.LOSE);

        player7.setGame(game8);


        playerRepository.save(player7);

        Player player8 = new Player();
        player8.setPlayerId(9);
        player8.setName("eyuel");
        player8.setSelection(Selection.ROCK);
        player8.setState(Player.State.WAIT);
        player8.setGame(game9);


        playerRepository.save(player8);

        Player player9 = new Player();
        player9.setPlayerId(10);
        player9.setName("brindah");
        player9.setSelection(Selection.SCISSORS);
        player9.setState(Player.State.WIN);

        player9.setGame(game10);

        playerRepository.save(player9);

    }

    public GameRepository getGameRepository() {
        return gameRepository;
    }

    public PlayerRepository getPlayerRepository() {
        return playerRepository;
    }



}



