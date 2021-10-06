package com.rony;

import com.rony.exceptions.InternalServerException;
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
import java.util.Arrays;
import java.util.Optional;


@ControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    @ExceptionHandler(ResourceNotFoundException.class)
    public  String handleNotFound(HttpServletRequest request, Model model,Exception e){
        e.printStackTrace();
        model.addAttribute("error",e.getMessage());
        logger.info("global exception => error 404 occurred");
        return "/errors/error_404";
    }

    @ResponseStatus(HttpStatus.FORBIDDEN) // 403
    @ExceptionHandler(ResourceAccessDeniedException.class)
    public String accessDenied(HttpServletRequest request, Model model, Exception e){
        e.printStackTrace();
        model.addAttribute("error", e.getMessage());
        logger.info("global exception => error 403 occurred");
        return "/errors/error_403";
    }

    @ResponseStatus(HttpStatus.CONFLICT) // 409
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public String handlerAlreadyExists(HttpServletRequest request, Model model, Exception e){
        e.printStackTrace();
        model.addAttribute("error",e.getMessage());
        logger.info("global exception => error occurred");
        return "error";
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    @ExceptionHandler({InternalServerException.class, NumberFormatException.class,
            NullPointerException.class, Exception.class})
    public String handlerAnyServerError(HttpServletRequest request, Model model, Exception e){
        e.printStackTrace();
        model.addAttribute("error", e.getMessage());
        printStackTrace(e);
//        logger.error("error : {} ", class2.get();
        logger.info("global exception => error 500 occurred");
        return "/errors/error_500";
    }

    private void printStackTrace(Exception ex) {
        StackTraceElement[] trace = ex.getStackTrace();
        StringBuilder traceLines = new StringBuilder();
        traceLines.append("Caused By: ").append(ex.fillInStackTrace()).append("\n");
        Arrays.stream(trace).filter(f -> f.getClassName().contains("com.rony"))
                .forEach(traceElement -> traceLines.append("\tat ").append(traceElement).append("\n"));
        logger.error(traceLines);
    }



}
