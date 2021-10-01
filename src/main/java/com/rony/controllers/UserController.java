package com.rony.controllers;

import com.rony.enums.UserRole;
import com.rony.models.User;
import com.rony.requestDto.UserReqDto;
import com.rony.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public String allUsers(Model model){
        model.addAttribute("pageTitle","Show all Users");
        model.addAttribute("users",userService.allUsers());
        return "users/show-all";
    }

    @GetMapping("/add")
    public String addUser_GET(Model model, HttpSession session){
        model.addAttribute("user", new User());
        model.addAttribute("genders",userService.getGenders());
        model.addAttribute("homeTowns",userService.getHomeTowns());
        model.addAttribute("salutations",userService.getSalutations());
        var role = session.getAttribute("role");
        System.err.println(role+" getting role from session in addUser controller ");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // check for a particular role
        boolean hasRoleIcc = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ICC_AUTHORITY"));

        // to get all the roles
        Set<String> roles = authentication.getAuthorities().stream()
                .map(r -> r.getAuthority()).collect(Collectors.toSet());

        if(hasRoleIcc){
            model.addAttribute("roles", Arrays.asList(UserRole.TEAM_MANAGER, UserRole.UMPIRE));
        }else{
            model.addAttribute("roles", UserRole.values());
        }
        return "users/addUser";
    }

    @PostMapping("/add")
    public String addUser_Post(@ModelAttribute(name = "user") @Valid UserReqDto userReqDto,
                               BindingResult errors,
                               Model model){
        String errorMsg = "";
        if(errors.hasErrors()){
            for(ObjectError error: errors.getAllErrors()){
                errorMsg+= error.getDefaultMessage()+"<br>";
            }
//            model.addAttribute("errorMsg", errors.getAllErrors());
            /**
             * returning a page by directory path keeps the previous request
             * but redirecting to a url (new url or same url) creates a new req (so prev req errors are gone)
              */
            return "users/addUser";
        }
        userService.addUser(userReqDto);
        return "redirect: /users/all";
    }

    @GetMapping("/details")
    public String showUser(Model model,  @RequestParam("id") String id){
        var userEntity = userService.getUserById(id);
        System.out.println(userEntity+" -----------------------------userEntity of getMapping details------------------------------");
        model.addAttribute("user",userEntity);
        model.addAttribute("homeTowns",userService.getHomeTowns());
        model.addAttribute("salutations",userService.getSalutations());
        model.addAttribute("genders",userService.getGenders());
        model.addAttribute("pageTitle","User details");
        return "users/details";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam("id") String id){
        var userEntity = userService.getUserById(id);
        System.out.println(userEntity+" --------------------------userEntity of getMapping edit---------------------------------");
        model.addAttribute("user",userEntity);
        model.addAttribute("homeTowns",userService.getHomeTowns());
        model.addAttribute("genders",userService.getGenders());
        model.addAttribute("salutations",userService.getSalutations());
        model.addAttribute("pageTitle","User details");
        return "users/edit";
    }
  /*
    // handling exception of this controller in spring framework
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({NullPointerException.class, NumberFormatException.class})
    public String handlerNullAndNumber(HttpServletRequest request, Model model, Exception e){
        e.printStackTrace();
        model.addAttribute("error",e.getMessage());
        return "error";
    }

    // another approach we can declare different methods for different exceptions of this controller
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value =  ArrayIndexOutOfBoundsException.class)
    public String handlerArrayIndex(HttpServletRequest request, Model model, Exception e){
        e.printStackTrace();
        model.addAttribute("error",e.getMessage());
        return "error";
    }

    // this is for all the exceptions
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value =  Exception.class)
    public String handlerException(HttpServletRequest request, Model model, Exception e){
        e.printStackTrace();
        model.addAttribute("error",e.getMessage());
        return "error";
    }

   */

}
