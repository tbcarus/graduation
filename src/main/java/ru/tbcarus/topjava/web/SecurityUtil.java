package ru.tbcarus.topjava.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.tbcarus.topjava.AuthorizedUser;
import ru.tbcarus.topjava.model.AbstractBaseEntity;

import static java.util.Objects.requireNonNull;

public class SecurityUtil {

    private static int id = AbstractBaseEntity.START_SEQ;

    private SecurityUtil() {
    }

    public static AuthorizedUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof AuthorizedUser) ? (AuthorizedUser) principal : null;
    }

    public static AuthorizedUser get() {
        return requireNonNull(safeGet(), "No authorized user found");
    }

    public static int authUserId() {
        return get().getUser().id();
    }

    public static void setAuthUserId(int id) {
        SecurityUtil.id = id;
    }

}