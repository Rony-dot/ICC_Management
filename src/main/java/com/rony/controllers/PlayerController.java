package com.rony.controllers;

import com.rony.enums.PlayerExpertise;
import com.rony.enums.PlayerStatus;
import com.rony.enums.UserRole;
import com.rony.models.Player;
import com.rony.models.User;
import com.rony.services.CountryService;
import com.rony.services.PlayerService;
import com.rony.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
public class PlayerController {
    @Autowired
    private PlayerService playerService;
    @Autowired
    private UserService userService;
    @Autowired
    private CountryService countryService;

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
    public String addPlayer(Model model, HttpSession session){

        var cid = (long) session.getAttribute("cid");
        System.out.println("1. country id is "+cid);
        model.addAttribute("cid", cid);
        model.addAttribute("player",new Player());
        model.addAttribute("playerStatus", PlayerStatus.values());
        model.addAttribute("playerExpertise", PlayerExpertise.values());
        model.addAttribute("player_users", userService.allUsers());


        // Later, team objects need to sent from here, filtered by country
        return "players/add_player";
    }

    @PostMapping("/players/add")
    public String addPlayer(Model model, @Valid @ModelAttribute("player") Player player, BindingResult errors,
                            // TODO team id need to be added here
                            //  @RequestParam("idTeam") long idTeam,
                            @RequestParam("cid") long cid,
                            @RequestParam("userId") long userId){

        if(errors.hasErrors()){
            return "players/add_player";
        }else {

            System.err.println(player.getPlayerStatus()+" status of the player");
            System.err.println(player.getExpertise()+" expertise of the player");
            System.out.println("2. country id is "+cid);
            playerService.addPlayer(player,userId, cid);

            return "redirect: /players/all";
        }
    }
}
