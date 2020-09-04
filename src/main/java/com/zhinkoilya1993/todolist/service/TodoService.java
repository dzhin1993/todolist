package com.zhinkoilya1993.todolist.service;

import com.zhinkoilya1993.todolist.model.Task;
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

    public List<Task> getAll(int ownerId) {
        return todoRepository.getAll(ownerId);
    }

    public List<Task> getAllByCompleted(int ownerId, boolean completed) {
        return todoRepository.getAllByCompleted(ownerId, completed);
    }

    public Task get(int ownerId, int todId) {
        return todoRepository.findById(todId)
                .filter(task -> task.getUser().getId() == ownerId)
                .orElse(null);
    }

    @Transactional
    public void saveOrUpdate(int ownerId, Task task) {
        if (!task.isNew() && get(ownerId, task.getId()) == null) {
            return;
        }

        if (task.getStart().isAfter(task.getEnd())) {
            throw new IllegalArgumentException("The start date of the task is after than end of the task");
        }

        task.setUser(userRepository.getOne(ownerId));
        todoRepository.save(task);
    }

    @Transactional
    public void delete(int ownerId, int todoId) {
        todoRepository.delete(todoId, ownerId);
    }

    @Transactional
    public void complete(int ownerId, int todoId, boolean completed) {
        Task task = get(ownerId, todoId);
        task.setCompleted(completed);
    }
}
