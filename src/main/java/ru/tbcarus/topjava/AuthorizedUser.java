package ru.tbcarus.topjava;

import ru.tbcarus.topjava.model.User;

import java.io.Serial;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
    @Serial
    private static final long serialVersionUID = 1L;

    private User user;

    public AuthorizedUser(User user) {
        super(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, user.getRoles());
        setNewUser(user);
    }

    public int getId() {
        return user.id();
    }

    public void setNewUser(User newUser) {
        newUser.setPassword(null);
        user = newUser;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return user.toString();
    }
}