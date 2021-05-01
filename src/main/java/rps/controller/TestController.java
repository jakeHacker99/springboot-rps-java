package rps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import rps.model.utilities.DefaultBody;
import rps.repositories.PlayerRepository;

@Controller
public class TestController {

    public PlayerRepository playerRepository;

    @GetMapping(value = "/test")
    public DefaultBody test() {
        return new DefaultBody("Hi", "testing");

    }
   /* @GetMapping(value =  {" ", "/", "home"})
    public String getHomePage(Model model){
        return "HomePage";
    }

    @GetMapping("/players")
    public String getPlayers(Model model){
        model.addAttribute("player", playerRepository.findAll());

        return "player";
    }*/

}

