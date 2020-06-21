package com.zhinkoilya1993.todolist.service;

import com.zhinkoilya1993.todolist.repository.TodoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TodoService {

    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void complete(int id, boolean completed) {
        repository.findById(id).ifPresent(t -> t.setCompleted(completed));
    }
}
