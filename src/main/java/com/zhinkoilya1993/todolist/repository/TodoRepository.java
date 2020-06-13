package com.zhinkoilya1993.todolist.repository;

import com.zhinkoilya1993.todolist.model.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends PagingAndSortingRepository<Todo, Integer> {

    Page<Todo> findAll(Pageable pageable);

    Page<Todo> findAllByCompleted(Pageable pageable, Boolean completed);
}
