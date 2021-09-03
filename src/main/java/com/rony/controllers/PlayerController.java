package com.rony.controllers;

import com.rony.enums.PlayerExpertise;
import com.rony.enums.PlayerStatus;
import com.rony.models.Player;
import com.rony.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/players/all")
    public String allPlayers(Model model){
        return "players/show-all";
    }

    @GetMapping("/players/add")
    public String addPlayer(Model model){
        model.addAttribute("player",new Player());
        model.addAttribute("playerStatus", PlayerStatus.values());
        model.addAttribute("playerExpertise", PlayerExpertise.values());
        // Later, team objects need to sent from here, filtered by country
        return "players/add_player";
    }

    @PostMapping("/players/add")
    public String addPlayer(Model model, @ModelAttribute("player") Player player, @RequestParam("idTeam") long idTeam){
        System.err.println(idTeam+" id of the team");
//        System.err.println(isCaptain+" is captain of the path variable");
        System.err.println(player.getName()+" name of the player");
        System.err.println(player.getPlayerStatus()+" status of the player");
        System.err.println(player.getExpertise()+" expertise of the player");
        System.err.println(player.isCaptain()+" is captain this player");
        playerService.addPlayer(player, idTeam);
        return "redirect: /players/all";
    }
}
