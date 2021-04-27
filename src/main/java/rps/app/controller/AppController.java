package rps.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import rps.app.AppUtils;

import rps.app.DefaultResponse;
import rps.app.gamelogic.GameRunning;
import rps.app.player.PlayersStack;
import rps.app.services.GameService;
import rps.app.gamelogic.Selection;
import rps.app.player.Player;

import java.util.UUID;

@RestController
public class AppController {

    @Autowired
    private final GameService gameService;

    @Autowired
    public AppController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("")
    String getSite() {
        return "Jakob & Yazan";
    }

    @GetMapping(value = "/test")
    public DefaultResponse test() {
        return new DefaultResponse("Hi", "testing");
    }

    @PostMapping(value = "/register/{name}")
    public PlayerResponse registerPlayer(@PathVariable("name") String name) {
        return new PlayerResponse(gameService.registerPlayer(name));
    }

    @GetMapping(value = "/check/{playerid}")
    public PlayerResponse checkPlayer(@PathVariable("playerid") Long playerId) {
        Player player = gameService.findPlayerById(playerId);
        return new PlayerResponse(player);
    }

    @GetMapping(value = "/ready/{playerid}")
    public PlayerResponse readyForPlaying(@PathVariable("playerid") long playerid) {
        Player player = gameService.readyForPlaying(playerid);
        return new PlayerResponse(player);
    }

    @GetMapping(value = "/selection/{playerid}/{selection}")
    public PlayerResponse playing(@PathVariable("playerid") long playerId, @PathVariable("selection") Selection selection) {
        Player player = gameService.findPlayerById(playerId);
        player.move(selection);
        return new PlayerResponse(player);
    }

    // Get token
    // Get rps.app.token
    @GetMapping(value = "auth/{token}")
    public String getToken(@PathVariable("token") String name){
        return AppUtils.createNewId();

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