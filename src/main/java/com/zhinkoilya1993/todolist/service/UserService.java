package com.zhinkoilya1993.todolist.service;

import com.zhinkoilya1993.todolist.AuthorizedUser;
import com.zhinkoilya1993.todolist.model.User;
import com.zhinkoilya1993.todolist.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findByEmail(email);
        if (user != null) {
            return new AuthorizedUser(user);
        }
        throw new UsernameNotFoundException("User '" + email + "' not found");
    }
}
