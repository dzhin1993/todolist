package com.zhinkoilya1993.todolist;

import com.zhinkoilya1993.todolist.Controller.ProfileController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@ControllerAdvice(basePackageClasses = ProfileController.class)
public class GlobalExceptionHandler {

    @ExceptionHandler(UserExistException.class)
    public ModelAndView userExistError(UserExistException e) {
        return new ModelAndView("registration", Map.of("error", e.getMessage()));
    }
}
