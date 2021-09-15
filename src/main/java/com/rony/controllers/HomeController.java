package com.rony.controllers;

import com.rony.config.HibernateConfig;
import com.rony.config.Initializer;
import com.rony.enums.UserRole;
import com.rony.models.User;
import com.rony.services.CountryService;
import com.rony.services.RoleService;
import com.rony.services.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.geom.QuadCurve2D;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @Autowired
    private HibernateConfig hibernateConfig;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;

    @Autowired
    private CountryService countryService;

    private static final Logger logger = LogManager.getLogger(HomeController.class);

    public HomeController(UserService userService, HibernateConfig hibernateConfig, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userService = userService;
        this.hibernateConfig = hibernateConfig;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String home(Model model, HttpServletRequest request){
        System.err.println("------------------------------------------- ");
        System.err.println("initializer invoking started () ");

        logger.info("initilizer called");
        Initializer initializer = new Initializer(roleService, userService, passwordEncoder, hibernateConfig, countryService);
        logger.info("initilizer finished");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var role = authentication.getAuthorities().stream()
                .findFirst().get().getAuthority();
        logger.info("your role is --------> " + role);

        // check for a particular role
        boolean whichRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ICC_AUTHORITY"));

        // to get all the roles
        Set<String> roles = authentication.getAuthorities().stream()
                .map(r -> r.getAuthority()).collect(Collectors.toSet());

        for(String r : roles){
            logger.info("role from for looop : " + r);
        }


        
        return "index";
    }

//    @GetMapping({"/hello",""})
//    public String home(Model model){
//        return "index";
//    }

    @GetMapping("/success")
    public String success(Model model, HttpServletRequest request){
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var role = authentication.getAuthorities().stream()
                .findFirst().get().getAuthority();
        var cm = (User) authentication.getPrincipal();
        var country = countryService.allCountries().stream()
                .filter(u -> u.getManagingDirector().getId() == cm.getId())
                .findFirst()
                .get();
        var cid = country.getId();
        HttpSession session = request.getSession();

        session.setAttribute("cid", cid);
        model.addAttribute("msg",role );
        return "successPage";
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model){
        System.out.println("you tried to login bro ?");
        String errorMessge = null;
        if(error != null) {
            errorMessge = "Username or Password is incorrect !!";
        }
        model.addAttribute("errorMessge", errorMessge);
        return "login";
    }

    @GetMapping("/logout")
    public String logout(Model model, HttpSession session){
        System.err.println("logging out?");
        session.invalidate();
        System.err.println("logged out success");
        return "login";
    }

    @GetMapping("/register")
    public String registrationForm(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("genders",userService.getGenders());
        model.addAttribute("homeTowns",userService.getHomeTowns());
        model.addAttribute("salutations",userService.getSalutations());
        return "registration";
    }

    @PostMapping("/register")
    public String processRegistration(Model model, @ModelAttribute(name = "user") User user){
        user.setUserRole(roleService.findByRoleName("ROLE_USER"));
        userService.addUser(user);
        return "redirect:/login";
    }

//    @PostMapping("/login")
//    public String login(Model model, @RequestParam("email") String email,
//                          @RequestParam("password") String password,
//                        HttpServletRequest request){
//       try{
//           if(!userService.validateUser(email,password)) {
//               System.err.println("invalid user");
//               model.addAttribute("msg","invalid email/password");
//               return login(model);
//           }
//       }catch (Exception e){
//           System.err.println("exception of login post in HomeController");
//           return login(model);
//       }
//        model.addAttribute("msg","logged in success!");
//        System.err.println("valid user");
//        System.out.println("logged in bro ! ");
//        model.addAttribute("msg","logged in bro ! ");
//        HttpSession session = request.getSession();
//        session.setAttribute("email", email);
//        var user = userService.getUserByEmail(email);
//        session.setAttribute("role",user.getUserRole());
//        System.err.println("role setting : "+user.getUserRole());
//        return home(model,session);
//    }


}
