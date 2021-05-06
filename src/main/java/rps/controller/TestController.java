//package rps.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import rps.model.utils.DefaultBody;
//import rps.repositories.GameRepository;
//import rps.repositories.PlayerRepository;
//
//
//@Controller
//public class TestController {
//
//    @Autowired
//    public PlayerRepository playerRepository;
//
//    @Autowired
//    public GameRepository gameRepository;
//
//
//    @GetMapping(value =  {" ", "/", "home"})
//
//    public String getHomePage(Model model){
//        model.addAttribute("players", playerRepository.findAll());
//        model.addAttribute("games", gameRepository.findAll());
//
//        return "HomePage";
//    }
//
//    @GetMapping("/players")
//    public String getPlayers(Model model){
//        model.addAttribute("players", playerRepository.findAll());
//
//        return "players";
//    }
//
//}
//
