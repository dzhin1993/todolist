package com.zhinkoilya1993.todolist;

import com.zhinkoilya1993.todolist.model.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

public class LoggedUser extends org.springframework.security.core.userdetails.User {

    private final User user;

    public LoggedUser(User user) {
        super(user.getEmail(), user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        this.user = user;
    }

    public int getId() {
        return user.getId();
    }
}
