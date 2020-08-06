package com.zhinkoilya1993.todolist.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping("/todoList")
    public String getMeals() {
        return "todoList";
    }

    @GetMapping("/login")
    public String logIn() {
        return "login";
    }
}
