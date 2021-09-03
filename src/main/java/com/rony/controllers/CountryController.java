


package com.rony.controllers;

import com.rony.enums.UserRole;
import com.rony.models.Country;
import com.rony.models.User;
import com.rony.services.CountryService;
import com.rony.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CountryController {

    @Autowired
    private CountryService countryService;
    @Autowired
    private UserService userService;

    public CountryController(CountryService countryService, UserService userService) {
        this.countryService = countryService;
        this.userService = userService;
    }

    @GetMapping("/countries/all")
    public String allCountries(Model model){
        model.addAttribute("countries", countryService.allCountries());
        return "/countries/show-all";
    }

    @GetMapping("/countries/add")
    public String addCountry(Model model){
        model.addAttribute("country", new Country());

        var managingDirectors = userService.allUsers().stream()
                .filter(u -> u.getRole() == UserRole.TEAM_MANAGER)
                .collect(Collectors.toList());
        model.addAttribute("managingDirectors",managingDirectors);

        var players = userService.allUsers().stream()
                .filter(u -> u.getRole() == UserRole.PLAYER)
                .collect(Collectors.toList());
        model.addAttribute("players",players);

        return "/countries/add_country";
    }

    @PostMapping("/countries/add")
    public String addCountry(Model model, @ModelAttribute Country country, @RequestParam("idMD") long idMD, @RequestParam("playerIds") long[] playerIds){
        System.err.println(idMD+"--------------countries add -> managing director id------------------");
        countryService.saveCountry(country, idMD, playerIds);
        return "redirect: /countries/all";
    }


    @GetMapping("/countries/edit")
    public String edit(Model model, @RequestParam("id") long id){
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
        return "countries/edit";
    }

    @PostMapping("/countries/update")
    public String updateCountry(Model model, @ModelAttribute("country") Country country, @RequestParam("id") long id, @RequestParam("idMD") long idMD, @RequestParam("playerIds") long[] playerIds){
        System.err.println(idMD+"--------------countries update controller-> managing director id------------------");
        System.err.println(country);
        countryService.updateCountry(country, id, idMD, playerIds);
        return "redirect: /countries/all";
    }

    @GetMapping("/countries/details")
    public String showUser(Model model,  @RequestParam("id") long id){
        var countryEntity = countryService.getCountryById(id);
        System.out.println(countryEntity+" -----------------------------countryEntity of getMapping details------------------------------");
        model.addAttribute("country",countryEntity);
        model.addAttribute("pageTitle","country details");
        return "countries/details";
    }

}
