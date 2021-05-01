package rps.bootstrap;

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
        player2.setPlayerId(3);
        player2.setName("yazan");
        player2.setSelection(Selection.ROCK);

        player2.setState(Player.State.READY);
        playerRepository.save(player2);





    }
}



