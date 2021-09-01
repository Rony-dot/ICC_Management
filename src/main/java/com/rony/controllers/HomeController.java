package com.rony.controllers;

import com.rony.config.HibernateConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private HibernateConfig hibernateConfig;

    @GetMapping("/")
    public String home(){
        hibernateConfig.getSession();
        return "index";
    }
}
