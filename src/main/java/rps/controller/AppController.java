package rps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import rps.services.GameService;
import rps.model.gamelogic.Selection;
import rps.model.player.Player;
import rps.model.utils.PlayerBody;


@RestController
public class AppController {
    @Autowired
    private final GameService gameService;

    public AppController(GameService gameService) {
        this.gameService = gameService;
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

    @GetMapping(value = "auth/{token}")
    public String getToken(@PathVariable("token") String token){

        String myRegexp = String.format("-d{20}");

        if(token !=null && token.length() == 20 || token.equals( myRegexp )){
            return "this token is valid";
        }else{
            return "not a valid token";
        }
    }
}