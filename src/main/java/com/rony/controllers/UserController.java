package com.rony.controllers;

import com.rony.models.User;
import com.rony.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserService userService;


    @GetMapping("/all")
    public String allUsers(Model model){
        model.addAttribute("users",userService.getAll());
        return "users/allUsers";
    }

    @GetMapping("/add")
    public String addUser_GET(Model model){
        model.addAttribute("user",new User());
        return "users/addUser";
    }

    @PostMapping("/add")
    public String addUser_Post( @ModelAttribute(name = "user") @Valid User user,  BindingResult bindingResult, Model model){
        String errorMsg = "";
        if(bindingResult.hasErrors()){
//            for(ObjectError error: bindingResult.getAllErrors()){
//                errorMsg+= error+"<br>";
//            }
            System.out.println("--------------------------------------------------------");
            System.out.println("got you!");
            System.out.println("--------------------------------------------------------");
            model.addAttribute("errorMsg", bindingResult.getAllErrors());

            // returning a page by directory path keeps the previous request
            // but redirecting to a url (new url or same url) creates a new req (so prev req errors are gone)
            return "users/addUser";
        }
        userService.addUser(user);
        return "redirect: /users/all";
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
