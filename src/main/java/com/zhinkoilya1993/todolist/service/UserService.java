package com.zhinkoilya1993.todolist.service;

import com.zhinkoilya1993.todolist.LoggedUser;
import com.zhinkoilya1993.todolist.UserExistException;
import com.zhinkoilya1993.todolist.model.User;
import com.zhinkoilya1993.todolist.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findByEmail(email);
        if (user != null) {
            return new LoggedUser(user);
        }
        throw new UsernameNotFoundException("User '" + email + "' not found");
    }

    private void save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        if (repository.findByEmail(user.getEmail()) != null) {
            throw new UserExistException("User with this email is already exist");
        }
        repository.save(user);
    }

    @SneakyThrows
    public void saveAndLogin(HttpServletRequest request, User user) {
        String password = user.getPassword();
        save(user);
        request.login(user.getEmail(), password);
    }
}
