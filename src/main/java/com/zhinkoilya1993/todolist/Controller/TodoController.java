package com.zhinkoilya1993.todolist.Controller;

import com.zhinkoilya1993.todolist.model.Todo;
import com.zhinkoilya1993.todolist.repository.TodoRepository;
import com.zhinkoilya1993.todolist.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping(value = TodoController.URL)
public class TodoController {

    static final String URL = "/todoList";

    private final TodoRepository repository;
    private final TodoService service;

    public TodoController(TodoRepository repository, TodoService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping()
    public String init() {
        return "todoList";
    }

    @GetMapping(value = "/all" ,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Todo> getAll() {
       return (List<Todo>) repository.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public Todo get(@PathVariable int id) {
        return repository.findById(id).get();
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        repository.deleteById(id);
    }

    @PostMapping
    public void saveOrUpdate(@Valid Todo todo, BindingResult result) {
        repository.save(todo);
    }

    @PostMapping("/complete")
    public String enable(@RequestParam Integer id, @RequestParam(value = "completed") Boolean completed) {
        service.complete(id, completed);
        return "redirect:/todoList";
    }
}
