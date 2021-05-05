package rps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import rps.model.game.Game;
import rps.model.utils.AppUtils;
import rps.model.utils.DefaultBody;
import rps.model.utils.GameBody;
import rps.services.GameService;
import rps.model.gamelogic.Selection;
import rps.model.player.Player;
import rps.model.utils.PlayerBody;
import rps.tokens.Token;
import rps.tokens.TokenService;

import java.util.UUID;


@RestController
public class AppController {
    @Autowired
    private final GameService gameService;

    TokenService tokenService;

    public AppController(GameService gameService) {
        this.gameService = gameService;
    }

    //  Get token
    @GetMapping(value = "/auth/token")
    public String getToken() {
        return AppUtils.createNewId();

    }

    // set name
//    @PostMapping(value = "/user/{name}")
//    public PlayerBody setName(@PathVariable("name") String name,
//                              @RequestHeader(value = "token",required = false) String tokenId) {
//        Token token = tokenService.getTokenById(tokenId);
//        System.out.println("token is" + token);
//        return new PlayerBody(gameService.registerPlayerById(tokenId));
//    }

    @GetMapping(value = "/games")
    public GameBody getGames() {
        return  new GameBody("jakob", Selection.PAPER, Game.State.ACTIVE,"yazan", Selection.SCISSORS);
    }


   /* // set name
    @PostMapping(value = "/user/name")
    public PlayerBody setName(@PathVariable("name") String name) {
        return new PlayerBody(gameService.registerPlayer(name));
    }

    // Start Game
    @GetMapping(value = "/games/start")
    public PlayerBody startGame(@PathVariable("name") String name) {
        return new PlayerBody(gameService.registerPlayer(name));
    }

    // joinGame

    @GetMapping(value = "/games/start")
    public PlayerBody joinGame(@PathVariable("name") String name) {
        return new PlayerBody(gameService.registerPlayer(name));
    }


    // Game state



    // Game info

    @GetMapping(value = "/games/{id}")
    public PlayerBody gameInfo(@PathVariable("id") String name) {
        return new PlayerBody(gameService.registerPlayer(name));
    }

    // Make move

    @GetMapping(value = "/games/move/{sign}")
    public PlayerBody makeMove(@PathVariable("name") String name) {
        return new PlayerBody(gameService.registerPlayer(name));
    }
*/




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