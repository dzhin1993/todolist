package com.zhinkoilya1993.todolist;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

@UtilityClass
public class SecurityUtil {

    public int getUserId() {
        LoggedUser user = getLoggedUser();
        Objects.requireNonNull(user);
        return user.getId();
    }

    public LoggedUser getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        return (LoggedUser) auth.getPrincipal();
    }
}
