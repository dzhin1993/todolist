package com.zhinkoilya1993.todolist.model.Controller;

import com.zhinkoilya1993.todolist.model.Todo;
import com.zhinkoilya1993.todolist.repository.TodoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


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

        Page<Todo> page = repository.findAll(pageable);
        int totalPages = page.getTotalPages() - 1;

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
    public String saveOrUpdate(Todo todo) {
        repository.save(todo);
        return "redirect:/todoList";
    }
}
