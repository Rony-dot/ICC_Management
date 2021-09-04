package com.rony.controllers;

import com.rony.enums.EventType;
import com.rony.enums.UserRole;
import com.rony.models.Event;
import com.rony.services.EventService;
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
        model.addAttribute("teams1", teamService.allTeams());
        model.addAttribute("teams2", teamService.allTeams());
        model.addAttribute("umpires", userService.allUsers().stream()
                .filter(u->u.getRole() == UserRole.UMPIRE)
                .collect(Collectors.toList()));

        return "events/add_event";
    }

    @PostMapping("/events/add")
    public String addEvent(Model model, @ModelAttribute Event event, @RequestParam("idTeam1") long idTeam1,
                           @RequestParam("idTeam2") long idTeam2, @RequestParam("umpireIds") long[] umpireIds){

        eventService.saveEvent(event, idTeam1, idTeam2, umpireIds);
        return "redirect: /events/all";
    }
}
