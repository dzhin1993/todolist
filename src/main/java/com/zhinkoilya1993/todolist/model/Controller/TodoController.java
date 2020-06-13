package com.zhinkoilya1993.todolist.model.Controller;

import com.zhinkoilya1993.todolist.repository.TodoRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(value = TodoController.URL)
public class TodoController {

    static final String URL = "/todoList";

    private final TodoRepository repository;

    public TodoController(TodoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String getAll(Model model,
                         @PageableDefault(sort = {"dateTime"}, direction = Sort.Direction.ASC) Pageable pageable) {
        model.addAttribute("todoList", repository.findAll(pageable));
        return "todoList";
    }

    @GetMapping("/by-completed")
    public String getAllByCompleted(Model model,
                         @PageableDefault(sort = {"dateTime"}, direction = Sort.Direction.ASC) Pageable pageable,
                                    @RequestParam(required= false, defaultValue = "false") Boolean completed) {
        model.addAttribute("todoList", repository.findAllByCompleted(pageable, completed));
        return "todoList";
    }
}
