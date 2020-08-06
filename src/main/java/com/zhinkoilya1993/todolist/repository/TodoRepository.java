package com.zhinkoilya1993.todolist.repository;

import com.zhinkoilya1993.todolist.model.Todo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Integer> {

    @Query("SELECT t FROM Todo t WHERE t.user.id=?1 AND t.completed=?2")
    List<Todo> getAllByCompleted(int id, boolean completed);

    @Query("SELECT t FROM Todo t WHERE t.user.id=?1")
    List<Todo> getAll(int userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Todo t WHERE t.id=?1 AND t.user.id=?2")
    void delete(int id, int userId);
}
