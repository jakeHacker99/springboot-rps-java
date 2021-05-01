package rps.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import rps.model.gamelogic.Selection;
import rps.model.player.Player;
import rps.repositories.GameRepository;
import rps.repositories.PlayerRepository;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private GameRepository gameRepository;


    private PlayerRepository playerRepository;


    public DevBootstrap( GameRepository gameRepository, PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();

    }
    private void initData(){

        Player player1 = new Player();
        player1.setPlayerId(1);
        player1.setName("jake");
        player1.setSelection(Selection.PAPER);
        player1.setState(Player.State.READY);

        playerRepository.save(player1);

        Player player2 = new Player();
        player2.setPlayerId(2);
        player2.setName("yazan");
        player2.setSelection(Selection.ROCK);

        player2.setState(Player.State.READY);
        playerRepository.save(player2);


        Player player3 = new Player();
        player3.setPlayerId(3);
        player3.setName("adam");
        player3.setSelection(Selection.SCISSORS);
        player3.setState(Player.State.WAIT);

        playerRepository.save(player3);

        Player player11 = new Player();
        player11.setPlayerId(4);
        player11.setName("william");
        player11.setSelection(Selection.ROCK);

        player11.setState(Player.State.READY);
        playerRepository.save(player11);

        Player player4 = new Player();
        player4.setPlayerId(5);
        player4.setName("gabriella");
        player4.setSelection(Selection.PAPER);
        player4.setState(Player.State.PLAYING);

        playerRepository.save(player4);

        Player player5 = new Player();
        player5.setPlayerId(6);
        player5.setName("christian");
        player5.setSelection(Selection.ROCK);

        player5.setState(Player.State.LOSE);
        playerRepository.save(player5);

        Player player6 = new Player();
        player6.setPlayerId(5);
        player6.setName("gabriella");
        player6.setSelection(Selection.ROCK);
        player6.setState(Player.State.PLAYING);

        playerRepository.save(player6);

        Player player7 = new Player();
        player7.setPlayerId(7);
        player7.setName("caroline");
        player7.setSelection(Selection.ROCK);

        player7.setState(Player.State.LOSE);
        playerRepository.save(player7);

        Player player8 = new Player();
        player8.setPlayerId(8);
        player8.setName("eyuel");
        player8.setSelection(Selection.ROCK);
        player8.setState(Player.State.WAIT);

        playerRepository.save(player8);

        Player player9 = new Player();
        player9.setPlayerId(9);
        player9.setName("brindah");
        player9.setSelection(Selection.SCISSORS);

        player9.setState(Player.State.WIN);
        playerRepository.save(player9);

        Player player10 = new Player();
        player10.setPlayerId(10);
        player10.setName("agnes");
        player10.setSelection(Selection.PAPER);

        player10.setState(Player.State.WIN);
        playerRepository.save(player10);










    }

    public GameRepository getGameRepository() {
        return gameRepository;
    }

    public PlayerRepository getPlayerRepository() {
        return playerRepository;
    }



}



