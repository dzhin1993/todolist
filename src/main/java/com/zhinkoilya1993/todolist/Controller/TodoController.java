package com.zhinkoilya1993.todolist.Controller;

import com.zhinkoilya1993.todolist.model.Todo;
import com.zhinkoilya1993.todolist.repository.TodoRepository;
import com.zhinkoilya1993.todolist.service.TodoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public String getAll(Model model, @RequestParam(required = false) Boolean completed,
                         @PageableDefault(sort = {"dateTime"}, direction = Sort.Direction.ASC) Pageable pageable) {

        Page<Todo> page = repository.findAll(pageable);
        int totalPages = page.getTotalPages();

        model.addAttribute("todoList", page);

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "todoList";
    }

    @GetMapping("/by-completed")
    public String getAllByCompleted(Model model,
                                    @RequestParam(required= false, defaultValue = "false") Boolean completed) {
        model.addAttribute("todoList", repository.findAllByCompleted(completed));
        return "todoList";
    }

    @GetMapping("/new")
    public String initCreationForm(Model model) {
        model.addAttribute("todo", new Todo());
        return "todoForm";
    }

    @GetMapping("/update")
    public String initUpdateForm(Model model, @RequestParam Integer id) {
        model.addAttribute("todo", repository.findById(id));
        return "todoForm";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Integer id) {
        repository.deleteById(id);
        return "redirect:/todoList";
    }

    @PostMapping
    public String saveOrUpdate(@Valid Todo todo, BindingResult result) {
        if(result.hasErrors()) {
            return "todoForm";
        }
        repository.save(todo);
        return "redirect:/todoList";
    }

    @PostMapping("/complete")
    public String enable(@RequestParam Integer id, @RequestParam(value = "completed") Boolean completed) {
        service.complete(id, completed);
        return "redirect:/todoList";
    }
}
