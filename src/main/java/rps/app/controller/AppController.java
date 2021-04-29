package rps.app.controller;

import org.springframework.web.bind.annotation.*;

import rps.app.utilities.AppUtils;

import rps.app.utilities.DefaultBody;
import rps.app.utilities.PlayerBody;
import rps.app.game.Game;
import rps.app.player.PlayersStack;
import rps.app.services.GameService;
import rps.app.gamelogic.Selection;
import rps.app.player.Player;



@RestController

public class AppController {

    private final GameService gameService;

    public AppController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("")
    String getSite() {
        return "Jakob & Yazan";
    }

    @GetMapping(value = "/test")
    public DefaultBody test() {
        return new DefaultBody("Hi", "testing");
    }

    @PostMapping(value = "/register/{name}")
    public PlayerBody registerPlayer(@PathVariable("name") String name) {
        return new PlayerBody(gameService.registerPlayer(name));
    }

    @GetMapping(value = "/check/{playerid}")
    public PlayerBody checkPlayer(@PathVariable("playerid") Long playerId) {
        Player player = gameService.findPlayerById(playerId);
        return new PlayerBody(player);
    }

    @GetMapping(value = "/ready/{playerid}")
    public PlayerBody readyForPlaying(@PathVariable("playerid") long playerid) {
        Player player = gameService.readyForPlaying(playerid);
        return new PlayerBody(player);
    }

    @GetMapping(value = "/selection/{playerid}/{selection}")
    public PlayerBody playing(@PathVariable("playerid") long playerId, @PathVariable("selection") Selection selection) {
        Player player = gameService.findPlayerById(playerId);
        player.move(selection);
        return new PlayerBody(player);
    }

    // Get token
    // Get rps.app.token
    @GetMapping(value = "auth/{token}")
    public String getToken(@PathVariable("token") String name) {
        return AppUtils.createNewId();
    }


    @GetMapping(value = "/games/start")
    public Game startNewGame() {


        return (Game) gameService.spawnGame(PlayersStack.getInstance());


    }

    //  start new game
    /*@GetMapping(value = "/games/start")
    public GameRunning startGame(){
        gameService.spawnNewGame();
        return new

    }*/


    // game status
    /*
     * @GetMapping(value = "/games/status")
     *
     *
     * */

    // Game List
    /*
     *  @GetMapping(value = "/games")
     * */

    // Game info
    /*
     * @GetMapping(value = "/games/{id}")
     * */





    /*
     * we add other request mapping here later
     *
     * */


}