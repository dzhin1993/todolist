package com.zhinkoilya1993.todolist.repository;

import com.zhinkoilya1993.todolist.model.Todo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends PagingAndSortingRepository<Todo, Integer> {

    List<Todo> findAllByCompleted(Boolean completed);

    Todo get(int id, int userId);

    @Override
    List<Todo> findAll();
}
