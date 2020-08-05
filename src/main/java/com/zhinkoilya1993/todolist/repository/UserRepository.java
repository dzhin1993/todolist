package com.zhinkoilya1993.todolist.repository;

import com.zhinkoilya1993.todolist.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByEmail(String email);
}
