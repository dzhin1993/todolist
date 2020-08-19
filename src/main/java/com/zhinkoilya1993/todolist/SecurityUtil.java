package com.zhinkoilya1993.todolist;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public class SecurityUtil {

    public static int getUserId() {
        LoggedUser user = getLoggedUser();
        Objects.requireNonNull(user);
        return user.getId();
    }

    public static LoggedUser getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        return (LoggedUser) auth.getPrincipal();
    }
}
