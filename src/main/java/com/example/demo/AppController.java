package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class AppController {

    @Autowired
    private final Service service;

    @Autowired
    public AppController(Service service) {

        this.service = service;
    }

    /*
     * register -> Join/ nickname -> name
     * registerPlayer -> joinByPlayer
     *  PlayerResponse ->
     *  createPlayer ->
     *
     * */

    @RequestMapping(value = "/join/{name}", method = RequestMethod.POST)
    public PlayerResponse joinByPlayer(@PathVariable("name") String name) {
        return new PlayerResponse(service.createPlayer(name));
    }

    /*
     *  check  -> search
     *  gameService  -> service
     *  checkGame -> searchForPlayer
     *  findPlayerById -> searchPlayerId
     *
     * */
    @RequestMapping(value = "/search/{playerid}", method = RequestMethod.GET)
    public PlayerResponse searchForPlayer(@PathVariable("playerid") Long playerId) {
        Player player = service.searchPlayerId(playerId);
        return new PlayerResponse(player);
    }
    /*
     *
     *
     *
     * */

    @RequestMapping(value = "/ready/{playerid}", method = RequestMethod.GET)
    public PlayerResponse ready(@PathVariable("playerid") long playerid) {
        Player player = service.ready(playerid);
        return new PlayerResponse(player);
    }

    /*
     *  Move  -> Selection
     *
     * */
    @RequestMapping(value = "/Selection/{playerid}/{Selection}", method = RequestMethod.GET)
    public PlayerResponse play(@PathVariable("playerid") long playerId, @PathVariable("Selection") Selection s1) {
        Player player = service.findPlayerById(playerId);
        player.selection(selection);
        return new PlayerResponse(player);
    }

    // RequestMapping for /games/[id] Get
    //  RequestMapping for /games/status Get
    //   RequestMapping for all games list /games

}