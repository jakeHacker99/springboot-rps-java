package rps.app.controller;

import org.springframework.web.bind.annotation.*;


import rps.app.services.GameService;
import rps.app.gamelogic.Selection;
import rps.app.player.Player;
import rps.app.utilities.AppUtils;
import rps.app.utilities.PlayerBody;


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
    public String getToken(@PathVariable("token") String name){
        return AppUtils.createNewId();

    }

    //  start new game
   /* @GetMapping(value = "/games/start")
    public PlayerResponse startNewGame(){
     return gameService.("HISDA", "new iD");

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


}