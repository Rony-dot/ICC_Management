package com.rony.controllers;

import com.rony.enums.EventType;
import com.rony.enums.UserRole;
import com.rony.models.Event;
import com.rony.models.Player;
import com.rony.requestDto.EventReqDto;
import com.rony.responseDto.TeamRespDto;
import com.rony.services.EventService;
import com.rony.services.PlayerService;
import com.rony.services.TeamService;
import com.rony.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class EventController {
    @Autowired
    private EventService eventService;
    @Autowired
    private UserService userService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private PlayerService playerService;

    public EventController(EventService eventService, UserService userService, TeamService teamService) {
        this.eventService = eventService;
        this.userService = userService;
        this.teamService = teamService;
    }

    @GetMapping("/events/all")
    public String getAllEvents(Model model){
        model.addAttribute("events",eventService.allEvents());
        return "events/show-all";
    }

    @GetMapping("/events/add")
    public String addEvent(Model model){
        model.addAttribute("event",new EventReqDto());
        model.addAttribute("eventTypes", EventType.values());

        List<TeamRespDto> teams =  teamService.allTeams();
        model.addAttribute("teams1",teams);
        model.addAttribute("teams2", teams);

        List<Player> players = playerService.allPlayers();
        model.addAttribute("team_1_Captain", players);
        model.addAttribute("team_2_Captain", players);

        model.addAttribute("umpires", userService.allUsers().stream()
                .filter(u -> u.getAuthorities().stream()
                        .findFirst()
                        .get()
                        .getAuthority()
                        .equals("ROLE_UMPIRE"))
                .collect(Collectors.toList()));

        model.addAttribute("new_umpires", userService.allUsers().stream()
                .filter(u -> u.getAuthorities().stream()
                        .findFirst()
                        .get()
                        .getAuthority()
                        .equals("ROLE_USER"))
                .collect(Collectors.toList()));

        return "events/add_event";
    }

    @PostMapping("/events/add")
    public String addEvent(Model model, @Valid @ModelAttribute EventReqDto eventReqDto, BindingResult errors,
                           @RequestParam(value = "newUmpireIds", required = false) String[] newUmpireIds){
        if(errors.hasErrors()){
            return "events/add_event";
        }else {
            eventService.saveEvent(eventReqDto, newUmpireIds);
            return "redirect: /events/all";
        }

    }
}
