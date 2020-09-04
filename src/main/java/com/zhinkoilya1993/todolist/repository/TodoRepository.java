package com.zhinkoilya1993.todolist.repository;

import com.zhinkoilya1993.todolist.model.Task;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TodoRepository extends CrudRepository<Task, Integer> {

    @Query("SELECT t FROM Task t WHERE t.user.id=?1 AND t.completed=?2")
    List<Task> getAllByCompleted(int id, boolean completed);

    @Query("SELECT t FROM Task t WHERE t.user.id=?1")
    List<Task> getAll(int userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Task t WHERE t.id=?1 AND t.user.id=?2")
    void delete(int id, int userId);
}
