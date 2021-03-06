package com.rony.controllers;

import com.rony.enums.SeriesType;
import com.rony.models.Series;
import com.rony.requestDto.SeriesReqDto;
import com.rony.services.EventService;
import com.rony.services.SeriesService;
import com.rony.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class SeriesController {

    @Autowired
    private SeriesService seriesService;
    @Autowired
    private EventService eventService;
    @Autowired
    private TeamService teamService;

    public SeriesController(EventService eventService, TeamService teamService, SeriesService seriesService) {
        this.eventService = eventService;
        this.teamService = teamService;
        this.seriesService = seriesService;
    }

    @GetMapping("/series/all")
    public String getAll(Model model){
        model.addAttribute("allSeries",seriesService.allSeries());
        return "series/show-all";
    }

    @GetMapping("/series/add")
    public String addSeries(Model model){
        model.addAttribute("series",new SeriesReqDto());
        model.addAttribute("seriesTypes", SeriesType.values());
        model.addAttribute("events",eventService.allEvents());
        model.addAttribute("teams",teamService.allTeams());
        return "series/add_series";
    }

    @PostMapping("/series/add")
    public String addSeries(Model model, @Valid @ModelAttribute SeriesReqDto seriesReqDto,
                            BindingResult errors) {

        if(errors.hasErrors()){
            return "series/add_series";
        }else {
            seriesService.saveSeries(seriesReqDto);
            return "redirect: /series/all";

        }
    }

}
