package rps.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


import rps.model.game.Game;
import rps.model.gamelogic.Selection;
import rps.model.utils.AppUtils;
import rps.model.utils.GameDTO;
import rps.services.GameService;

import rps.tokens.Token;
import rps.tokens.TokenService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@AllArgsConstructor
@CrossOrigin
public class AppController {

    private final GameService gameService;

    TokenService tokenService;


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


    // Start Game
    @GetMapping(value = "/games/start")
    public GameDTO startGame(@RequestHeader("token") String tokenId) {
        Token token = tokenService.getTokenById(tokenId);
        return toGameDTO(gameService.startNewGame(token));

    }

    private GameDTO toGameDTO(Game game) {
        return new GameDTO(game.getId(),
                game.getOwner().getName(),
                game.getMove(),
                game.getState(),
                game.getJoiner() != null ? game.getJoiner().getName() : "",
                game.getOpponentMove());
    }


    @GetMapping(value = "/games/join/{gameId}")
    public GameDTO joinGame(@PathVariable("gameId") String gameId,
                            @RequestHeader("token") String tokenId) {
        Token token = tokenService.getTokenById(tokenId);
        return toGameDTO(gameService.joinGame(gameId, token));
    }


    @GetMapping(value = "/games/move/{sign}")
    public GameDTO makeMove(@PathVariable("sign") Selection move,
                            @RequestHeader("token") String tokenId) {
        return  toGameDTO(gameService.makeMove(move,tokenId));
    }

    @GetMapping(value = "/games/status")
    public GameDTO getState(@RequestHeader("token") String tokenId) {
        return toGameDTO(gameService.getState(tokenId));
    }

//
//    @GetMapping(value = "/games/{id}")
//    public GameDTO getState(@PathVariable("gameId") String gameId,
//                            @RequestHeader("token") String tokenId) {
//        return toGameDTO(gameService.getGameInfo(tokenId));
//    }


    @GetMapping(value = "/games")
    public List<GameDTO> getAllGames(@RequestHeader(value="token",required= true) String tokenId ) {
        return  gameService.getAllGames(tokenId).map(game -> toGameDTO(game)).collect(Collectors.toList());
    }






      /* @PostMapping(value = "/user/name")
    public GameDTO setName(@RequestBody String name,
                          @RequestHeader("token") String tokenId)  {
        return toGameDTO(gameService.getName(name,tokenId)) ;
    }
*/


   /*

    // get move
   /* @GetMapping(value = "/games/move/{sign}")
    public GameDTO makeMove(@PathVariable("sign") Selection move,
                            @RequestHeader("token") String tokenId) {
        return new GameDTO(gameService.getMove());
    }*/
//    @GetMapping(value = "/games")
//    public GameDTO getGames() {
//
//        for (Player player : PlayersStack.getInstance().getPlayers()) {
//            System.out.println("du kommer inte in hit!!!!!!!!!!!");
//
////            return new GameDTO(player.getNickName(), player.getMove(), player.getGameState(), player.getOpponentNickName() );
//        }
//        System.out.println(PlayersStack.getInstance());
//
//        return  new GameDTO("jakob", java.util.Optional.of(Selection.PAPER), Game.State.ACTIVE,"yazan");
//        }

//    public Stack<Player> getPlayers(){
//
////        ta fram all games
//
////        kolla om game är open
//
////        lista all open games
//
//        return PlayersStack.getInstance().getPlayers();
//    }

/*    @GetMapping(value = "/games/start")
    public GameBody startGame( @RequestHeader(value = "token",required = false) String tokenId) {
        Token token = tokenService.getTokenById(tokenId);
        return new GameBody(gameService.(token));
    }*/


   /* // set name
    @PostMapping(value = "/user/name")
    public PlayerBody setName(@PathVariable("name") String name) {
        return new PlayerBody(gameService.registerPlayer(name));
    }

 /*


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


//
//    @PostMapping(value = "/register/{name}")
//    public PlayerBody registerPlayer(@PathVariable("name") String name) {
//        return new PlayerBody(gameService.registerPlayer(name));
//    }
//
//    @GetMapping(value = "/check/{playerid}")
//    public PlayerBody checkPlayer(@PathVariable("playerid") Long playerId) {
//        Player player = gameService.findPlayerById(playerId);
//        return new PlayerBody(player);
//    }
//
//    @GetMapping(value = "/ready/{playerid}")
//    public PlayerBody readyForPlaying(@PathVariable("playerid") long playerid) {
//        Player player = gameService.readyForPlaying(playerid);
//        return new PlayerBody(player);
//    }
//
//    @GetMapping(value = "/selection/{playerid}/{selection}")
//    public PlayerBody playing(@PathVariable("playerid") long playerId, @PathVariable("selection") Selection selection) {
//        Player player = gameService.findPlayerById(playerId);
//        player.move(selection);
//        return new PlayerBody(player);
//    }
//
//    @GetMapping(value = "auth/{token}")
//    public String getToken(@PathVariable("token") String token){
//
//        String myRegexp = String.format("-d{20}");
//
//        if(token !=null && token.length() == 20 || token.equals( myRegexp )){
//            return "this token is valid";
//        }else{
//            return "not a valid token";
//        }
//    }

}
