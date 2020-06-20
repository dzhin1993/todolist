package com.zhinkoilya1993.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class TodoListApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(TodoListApplication.class, args);
    }
}
