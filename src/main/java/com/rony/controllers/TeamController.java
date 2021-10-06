package com.rony.controllers;

import com.rony.enums.PlayerStatus;
import com.rony.enums.UserRole;
import com.rony.models.Country;
import com.rony.models.Player;
import com.rony.models.Team;
import com.rony.models.User;
import com.rony.requestDto.TeamReqDto;
import com.rony.services.CountryService;
import com.rony.services.PlayerService;
import com.rony.services.TeamService;
import com.rony.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TeamController {

    @Autowired
    private TeamService teamService;
    @Autowired
    private UserService userService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private PlayerService playerService;

    public TeamController(TeamService teamService, UserService userService,  CountryService countryService, PlayerService playerService) {
        this.teamService = teamService;
        this.userService = userService;
        this.countryService = countryService;
        this.playerService = playerService;
    }

    @GetMapping("/teams/all")
    public String allTeams(Model model){
        model.addAttribute("teams", teamService.allTeams());
        return "/teams/show-all";
    }

    @GetMapping("/teams/add")
    public String addTeam(Model model){
        model.addAttribute("team", new TeamReqDto());

        List<User> coaches = userService.allUsers();
//                .stream()
//                .filter(u -> u.getAuthorities().stream()
//                        .findFirst().get().getAuthority().equals("ROLE_COACH"))
//                .collect(Collectors.toList());
        model.addAttribute("coaches",coaches);

        List<Player> players = playerService.allPlayers().stream()
                .filter(player -> player.getPlayerStatus() == PlayerStatus.ACTIVE)
                .collect(Collectors.toList());
        model.addAttribute("players",players);

//        model.addAttribute("countries",countryService.allCountries());

        return "/teams/add_team";
    }

    @PostMapping("/teams/add")
    public String addTeam(Model model, @Valid @ModelAttribute TeamReqDto teamReqDto,
                          BindingResult errors,
                          HttpSession session){

        if(errors.hasErrors()){
            return "/teams/add_team";
        }else {
            // TODO need to check if it works or not,
            //  send cid from getMapping() then map automatically from the addTeam.jsp form

            String cid = String.valueOf(session.getAttribute("cid"));
            teamService.saveTeam(teamReqDto, cid);

            return "redirect: /teams/all";

        }
    }

/*
    @GetMapping("/teams/edit")
    public String edit(Model model, @RequestParam("id") String id){
        var countryEntity = countryService.getCountryById(id);
        System.out.println(countryEntity+" --------------------------countryEntity of getMapping edit---------------------------------");
        model.addAttribute("id",id);
        model.addAttribute("country",countryEntity);
        var managingDirectors = userService.allUsers().stream()
                .filter(u -> u.getRole() == UserRole.TEAM_MANAGER)
                .collect(Collectors.toList());
        model.addAttribute("managingDirectors",managingDirectors);

        var players = userService.allUsers().stream()
                .filter(u -> u.getRole() == UserRole.PLAYER)
                .collect(Collectors.toList());
        model.addAttribute("players",players);
        return "teams/edit";
    }

    @PostMapping("/teams/update")
    public String updateTeam(Model model, @ModelAttribute("country") CountryReqDto country, @RequestParam("id") long id, @RequestParam("idMD") long idMD, @RequestParam("playerIds") long[] playerIds){
        System.err.println(idMD+"--------------countries update controller-> managing director id------------------");
        System.err.println(country);
        countryService.updateCountry(country, id, idMD, playerIds);
        return "redirect: /teams/all";
    }

    @GetMapping("/teams/details")
    public String showTeam(Model model,  @RequestParam("id") long id){
        var countryEntity = countryService.getCountryById(id);
        System.out.println(countryEntity+" -----------------------------countryEntity of getMapping details------------------------------");
        model.addAttribute("country",countryEntity);
        model.addAttribute("pageTitle","country details");
        return "teams/details";
    }

 */

}
