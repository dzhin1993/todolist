package com.zhinkoilya1993.todolist.service;

import com.zhinkoilya1993.todolist.model.Todo;
import com.zhinkoilya1993.todolist.repository.TodoRepository;
import com.zhinkoilya1993.todolist.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.zhinkoilya1993.SecurityUtil.authUserId;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public TodoService(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    public List<Todo> getAll() {
        return todoRepository.getAll(authUserId());
    }

    public List<Todo> getAllByCompleted(boolean completed) {
        return todoRepository.getAllByCompleted(authUserId(), completed);
    }

    public Todo get(int id) {
        int userId = authUserId();
        return todoRepository.findById(id)
                .filter(todo -> todo.getUser().getId() == userId)
                .orElse(null);
    }

    @Transactional
    public void saveOrUpdate(Todo todo) {
        int userId = authUserId();
        if (!todo.isNew() && get(todo.getId()) == null) {
            return;
        }
        todo.setUser(userRepository.getOne(userId));
        todoRepository.save(todo);
    }

    @Transactional
    public void delete(int id) {
        int userId = authUserId();
        todoRepository.delete(id, userId);
    }

    @Transactional
    public void complete(int id, boolean completed) {
        todoRepository.findById(id).ifPresent(t -> t.setCompleted(completed));
    }
}
