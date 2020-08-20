package com.zhinkoilya1993.todolist.Controller;

import com.zhinkoilya1993.todolist.model.User;
import com.zhinkoilya1993.todolist.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/registration")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public String registration() {
        return "registration";
    }

    @PostMapping
    public String registerUser(HttpServletRequest request, User user) {
        service.saveAndLogin(request, user);
        return "redirect:/todoList";
    }
}
