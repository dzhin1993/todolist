package com.zhinkoilya1993;

import com.zhinkoilya1993.todolist.AuthorizedUser;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

@UtilityClass
public class SecurityUtil {

    public AuthorizedUser get() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        return (AuthorizedUser) auth.getPrincipal();
    }

    public int authUserId() {
        return Objects.requireNonNull(get()).getId();
    }
}
