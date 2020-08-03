package com.zhinkoilya1993.todolist.Controller;

import com.zhinkoilya1993.todolist.model.Todo;
import com.zhinkoilya1993.todolist.repository.TodoRepository;
import com.zhinkoilya1993.todolist.service.TodoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


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

    @GetMapping
    public String getAllByCompleted(Model model, @RequestParam(required = false) Boolean completed,
                                    @PageableDefault(sort = {"dateTime"}, direction = Sort.Direction.ASC) Pageable pageable) {

        Page<Todo> page = completed != null
                ? repository.findAllByCompleted(completed, pageable)
                : repository.findAll(pageable);

        model.addAttribute("todoList", page);

        int totalPages = page.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "todoList";
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
