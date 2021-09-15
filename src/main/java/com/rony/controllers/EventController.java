package com.rony.controllers;

import com.rony.enums.EventType;
import com.rony.enums.UserRole;
import com.rony.models.Event;
import com.rony.services.EventService;
import com.rony.services.PlayerService;
import com.rony.services.TeamService;
import com.rony.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
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
        model.addAttribute("event",new Event());
        model.addAttribute("eventTypes", EventType.values());

        var teams =  teamService.allTeams();
        model.addAttribute("teams1",teams);
        model.addAttribute("teams2", teams);

        var players = playerService.allPlayers();
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
    public String addEvent(Model model, @ModelAttribute Event event,
                           @RequestParam("idTeam1") long idTeam1,
                           @RequestParam("idTeam1Captain") long idTeam1Cap,
                           @RequestParam("idTeam2") long idTeam2,
                           @RequestParam("idTeam2Captain") long idTeam2Cap,
                           @RequestParam(value = "umpireIds", required = false) long[] umpireIds,
                           @RequestParam(value = "newUmpireIds", required = false) long[] newUmpireIds){

        eventService.saveEvent(event, idTeam1, idTeam1Cap, idTeam2, idTeam2Cap, umpireIds, newUmpireIds);
        return "redirect: /events/all";
    }
}
