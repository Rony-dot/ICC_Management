package com.rony;

import com.rony.exceptions.ResourceAccessDeniedException;
import com.rony.exceptions.ResourceAlreadyExistsException;
import com.rony.exceptions.ResourceNotFoundException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    @ExceptionHandler(ResourceNotFoundException.class)
    public  String handleNotFound(HttpServletRequest request, Model model,Exception e){
        e.printStackTrace();
        model.addAttribute("error",e.getMessage());
        return "error_404";
    }

    @ResponseStatus(HttpStatus.FORBIDDEN) // 403
    @ExceptionHandler(ResourceAccessDeniedException.class)
    public String accessDenied(HttpServletRequest request, Model model, Exception e){
        e.printStackTrace();
        model.addAttribute("error", e.getMessage());
        return "error_403";
    }

    @ResponseStatus(HttpStatus.CONFLICT) // 409
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public String handlerAlreadyExists(HttpServletRequest request, Model model, Exception e){
        e.printStackTrace();
        model.addAttribute("error",e.getMessage());
        return "error";
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    @ExceptionHandler(RuntimeException.class)
    public String handlerAnyServerError(HttpServletRequest request, Model model, Exception e){
        e.printStackTrace();
        model.addAttribute("error", e.getMessage());
        return "error_500";
    }
}
