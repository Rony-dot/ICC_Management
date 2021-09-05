package com.rony.controllers;

import com.rony.enums.PlayerExpertise;
import com.rony.enums.PlayerStatus;
import com.rony.enums.UserRole;
import com.rony.models.Player;
import com.rony.services.PlayerService;
import com.rony.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.stream.Collectors;

@Controller
public class PlayerController {
    @Autowired
    private PlayerService playerService;
    @Autowired
    private UserService userService;

    public PlayerController(PlayerService playerService, UserService userService) {
        this.playerService = playerService;
        this.userService = userService;
    }

    @GetMapping("/players/all")
    public String allPlayers(Model model){
        model.addAttribute("players",playerService.allPlayers());
        playerService.allPlayers().stream()
                .forEach(p-> System.err.println(p.toString()));
        return "players/show-all";
    }

    @GetMapping("/players/add")
    public String addPlayer(Model model){
        model.addAttribute("player",new Player());
        model.addAttribute("playerStatus", PlayerStatus.values());
        model.addAttribute("playerExpertise", PlayerExpertise.values());
        model.addAttribute("player_users", userService.allUsers().stream()
                .filter(user -> user.getRole() == UserRole.PLAYER)
                .collect(Collectors.toList()));
        // Later, team objects need to sent from here, filtered by country
        return "players/add_player";
    }

    @PostMapping("/players/add")
    public String addPlayer(Model model, @ModelAttribute("player") Player player,
                // TODO team id need to be added here
                            //  @RequestParam("idTeam") long idTeam,
                            @RequestParam(value = "isCap", required = false) boolean isCap,
                            @RequestParam("userId") long userId){
//        System.err.println(idTeam+" id of the team");
        System.err.println(isCap+" is captain of the path variable");
//        System.err.println(player.getName()+" name of the player");
        System.err.println(player.getPlayerStatus()+" status of the player");
        System.err.println(player.getExpertise()+" expertise of the player");
        /**
         * TODO dummy team id is used for a while
         */
//        long idTeam = -100;
//        playerService.addPlayer(player, idTeam);
        player.setCaptain(isCap);
        playerService.addPlayer(player,userId);

        return "redirect: /players/all";
    }
}
