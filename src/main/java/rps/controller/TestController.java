package rps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import rps.model.player.Player;
import rps.model.utils.DefaultBody;
import rps.repositories.PlayerRepository;

import java.util.List;

@Controller
public class TestController {

    @Autowired
    public PlayerRepository playerRepository;

    @GetMapping(value = "/test")
    public DefaultBody test() {
        return new DefaultBody("Hi", "testing");

    }

    @GetMapping(value =  {" ", "/", "home"})

    public String getHomePage(Model model){
        model.addAttribute("players", playerRepository.findAll());
        return "HomePage";
    }

    @GetMapping("/players")
    public String getPlayers(Model model){
        model.addAttribute("players", playerRepository.findAll());

        return "players";
    }

}

