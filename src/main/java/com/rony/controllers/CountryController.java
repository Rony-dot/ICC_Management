package com.rony.controllers;

import com.rony.models.Country;
import com.rony.models.User;
import com.rony.requestDto.CountryReqDto;
import com.rony.services.CountryService;
import com.rony.services.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CountryController {

    Logger logger = LogManager.getLogger(CountryController.class);

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
        model.addAttribute("countries", countryService.CountryRespDtoList());
        return "/countries/show-all";
    }

    @GetMapping("/countries/add")
    public String addCountry(Model model){
        model.addAttribute("country", new CountryReqDto());

        List<User> managingDirectors = userService.allUsers();

        model.addAttribute("managingDirectors",managingDirectors);

        List<User> players = userService.allUsers();
        model.addAttribute("players",players);

        return "/countries/add_country";
    }

//    @PostMapping("/countries/add")
//    public String addCountry(Model model,
//                             @Valid @ModelAttribute CountryReqDto country, BindingResult errors,
//                             @RequestParam("idMD") long idMD,
//                             @RequestParam(value = "playerIds", required = false) long[] playerIds){
//        if(errors.hasErrors()){
//            return "/countries/add_country";
//        }else {
//            System.err.println(idMD+"--------------countries add -> managing director id------------------");
//            countryService.saveCountry(country, idMD, playerIds);
//            return "redirect: /countries/all";
//
//        }
//
//    }

    @PostMapping("/countries/add")
    public String addCountry(Model model,
                            @Valid @ModelAttribute(name = "country") CountryReqDto countryReqDto,
                             BindingResult errors){
        if(errors.hasErrors()){
            return "/countries/add_country";
        }else {
            logger.info("country name : "+countryReqDto.getName());
            logger.info("country Managing director id : "+countryReqDto.getCountryManagerId());
            logger.info("country player id's: "+countryReqDto.getPlayerIds());
            countryService.addCountry(countryReqDto);
            return "redirect: /countries/all";
        }

    }

///*
    @GetMapping("/countries/edit")
    public String edit(Model model, @RequestParam("id") String id){
        Country countryEntity = countryService.getCountryById(id);
        CountryReqDto countryReqDto = new CountryReqDto();
        BeanUtils.copyProperties(countryEntity,countryReqDto);
        countryReqDto.setId(id);
        model.addAttribute("country",countryReqDto);

        List<User> managingDirectors = userService.allUsers();
        model.addAttribute("managingDirectors",managingDirectors);

        List<User> players = userService.allUsers();
        model.addAttribute("players",players);
//        var managingDirectors = userService.allUsers().stream()
//                .filter(u -> u.getRole() == UserRole.TEAM_MANAGER)
//                .collect(Collectors.toList());
//        model.addAttribute("managingDirectors",managingDirectors);

//        var players = userService.allUsers().stream()
//                .filter(u -> u.getRole() == UserRole.PLAYER)
//                .collect(Collectors.toList());
//        model.addAttribute("players",players);
        return "/countries/update_country";
    }

    @PostMapping("/countries/update")
    public String updateCountry(Model model,
                                @ModelAttribute("country") CountryReqDto countryReqDto){
        System.err.println(countryReqDto);
        countryService.updateCountry(countryReqDto);
        return "redirect: /countries/all";
    }

//    @GetMapping("/countries/details")
//    public String showUser(Model model,  @RequestParam("id") long id){
//        var countryEntity = countryService.getCountryById(id);
//        System.out.println(countryEntity+" -----------------------------countryEntity of getMapping details------------------------------");
//        model.addAttribute("country",countryEntity);
//        model.addAttribute("pageTitle","country details");
//        return "countries/details";
//    }

// */

}
