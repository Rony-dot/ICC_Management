package com.rony.controllers;

import com.rony.models.Country;
import com.rony.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping("/countries/all")
    public String allCountries(Model model){
        model.addAttribute("countries", countryService.getAll());
        return "/countries/all_country";
    }

    @GetMapping("/countries/add")
    public String addCountry(Model model){
        model.addAttribute("country", new Country());
        return "/countries/add_country";
    }
    @PostMapping("/countries/add")
    public String addCountry(Model model, @ModelAttribute Country country){
        countryService.saveCountry(country);
        return "redirect: /countries/all";
    }
}
