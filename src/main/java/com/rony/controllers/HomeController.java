package com.rony.controllers;

import com.rony.config.HibernateConfig;
import com.rony.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private HibernateConfig hibernateConfig;
    @Autowired
    private UserService userService;

    public HomeController(UserService userService, HibernateConfig hibernateConfig) {
        this.userService = userService;
        this.hibernateConfig = hibernateConfig;
    }

    @GetMapping("/")
    public String home(Model model, HttpSession session){
//        hibernateConfig.getSession();
        System.err.println("session getting role : "+session.getAttribute("role"));
        return "index";
    }

//    @GetMapping({"/hello",""})
//    public String home(Model model){
//        return "index";
//    }

    @GetMapping("/login")
    public String login(Model model){
        System.out.println("you tried to login bro ?");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(Model model, HttpSession session){
        System.err.println("logging out?");
        session.invalidate();
        System.err.println("logged out success");
        return "login";
    }

    @PostMapping("/login")
    public String login(Model model, @RequestParam("email") String email, @RequestParam("password") String password,
                        HttpServletRequest request){
       try{
           if(!userService.validateUser(email,password)) {
               System.err.println("invalid user");
               model.addAttribute("msg","invalid email/password");
               return login(model);
           }
       }catch (Exception e){
           System.err.println("exception of login post in HomeController");
           return login(model);
       }
        model.addAttribute("msg","logged in success!");
        System.err.println("valid user");
        System.out.println("logged in bro ! ");
        model.addAttribute("msg","logged in bro ! ");
        HttpSession session = request.getSession();
        session.setAttribute("email", email);
        var user = userService.getUserByEmail(email);
        session.setAttribute("role",user.getRole());
        System.err.println("role setting : "+user.getRole());
        return home(model,session);
    }


}
