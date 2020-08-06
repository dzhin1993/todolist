package com.zhinkoilya1993.todolist.service;

import com.zhinkoilya1993.todolist.model.Todo;
import com.zhinkoilya1993.todolist.repository.TodoRepository;
import com.zhinkoilya1993.todolist.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public TodoService(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    public List<Todo> getAll(int ownerId) {
        return todoRepository.getAll(ownerId);
    }

    public List<Todo> getAllByCompleted(int ownerId, boolean completed) {
        return todoRepository.getAllByCompleted(ownerId, completed);
    }

    public Todo get(int ownerId, int todId) {
        return todoRepository.findById(todId)
                .filter(todo -> todo.getUser().getId() == ownerId)
                .orElse(null);
    }

    @Transactional
    public void saveOrUpdate(int ownerId, Todo todo) {
        if (!todo.isNew() && get(ownerId, todo.getId()) == null) {
            return;
        }
        todo.setUser(userRepository.getOne(ownerId));
        todoRepository.save(todo);
    }

    @Transactional
    public void delete(int ownerId, int todoId) {
        todoRepository.delete(todoId, ownerId);
    }

    @Transactional
    public void complete(int ownerId, int todoId, boolean completed) {
        Todo todo = get(ownerId, todoId);
        todo.setCompleted(completed);
    }
}
