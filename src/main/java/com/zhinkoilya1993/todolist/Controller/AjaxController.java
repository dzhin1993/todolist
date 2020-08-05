package com.zhinkoilya1993.todolist.Controller;

import com.zhinkoilya1993.todolist.model.Todo;
import com.zhinkoilya1993.todolist.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = AjaxController.URL)
public class AjaxController {

    static final String URL = "/ajax/todoList";

    private final TodoService service;

    public AjaxController(TodoService service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Todo> getAll() {
       return service.getAll();
    }

    @GetMapping("/by-completed")
    public List<Todo> getAllByCompleted(@RequestParam(value = "completed") Boolean completed) {
        return service.getAllByCompleted(completed);
    }

    @GetMapping(value = "/{id}")
    public Todo get(@PathVariable int id) {
        return service.get(id);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

    @PostMapping
    public void saveOrUpdate(Todo todo) {
        service.saveOrUpdate(todo);
    }

    @PostMapping("/complete")
    public void complete(@RequestParam Integer id, @RequestParam(value = "completed") Boolean completed) {
        service.complete(id, completed);
    }
}
