package com.zhinkoilya1993.todolist.Controller;

import com.zhinkoilya1993.todolist.model.Todo;
import com.zhinkoilya1993.todolist.model.User;
import com.zhinkoilya1993.todolist.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
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
    public List<Todo> getAll(Authentication auth) {
       return service.getAll(getOwnerId(auth));
    }

    @GetMapping("/by-completed")
    public List<Todo> getAllByCompleted(@RequestParam(value = "completed") Boolean completed, Authentication auth) {
        return service.getAllByCompleted(getOwnerId(auth), completed);
    }

    @GetMapping(value = "/{id}")
    public Todo get(@PathVariable int id, Authentication auth) {
        return service.get(getOwnerId(auth), id);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id, Authentication auth) {
        service.delete(getOwnerId(auth), id);
    }

    @PostMapping
    public void saveOrUpdate(Todo todo, Authentication auth) {
        service.saveOrUpdate(getOwnerId(auth), todo);
    }

    @PostMapping("/complete")
    public void complete(@RequestParam Integer id, @RequestParam(value = "completed") Boolean completed) {
        /*service.complete(id, completed);*/
    }

    private int getOwnerId(Authentication auth) {
       return ((User) auth.getPrincipal()).getId();
    }
}
