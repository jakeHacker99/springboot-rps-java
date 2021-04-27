package rps.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import rps.app.DefaultResponse;
import rps.app.services.GameService;
import rps.app.gamelogic.Selection;
import rps.app.player.Player;

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
        return new DefaultResponse("Hi", "Testing");
    }

    @PostMapping(value = "/register/{nickname}")
    public PlayerResponse registerPlayer(@PathVariable("nickname") String nickname) {
        return new PlayerResponse(gameService.createPlayer(nickname));
    }

    @GetMapping(value = "/check/{playerid}")
    public PlayerResponse checkGame(@PathVariable("playerid") Long playerId) {
        Player player = gameService.findPlayerById(playerId);
        return new PlayerResponse(player);
    }

    @GetMapping(value = "/ready/{playerid}")
    public PlayerResponse ready(@PathVariable("playerid") long playerid) {
        Player player = gameService.ready(playerid);
        return new PlayerResponse(player);
    }

    @GetMapping(value = "/selection/{playerid}/{selection}")
    public PlayerResponse playing(@PathVariable("playerid") long playerId, @PathVariable("selection") Selection selection) {
        Player player = gameService.findPlayerById(playerId);
        player.move(selection);
        return new PlayerResponse(player);
    }


    /*
     * we add other request mapping here later
     *
     * */


}