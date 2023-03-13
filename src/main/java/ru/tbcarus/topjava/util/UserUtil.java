package ru.tbcarus.topjava.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.tbcarus.topjava.model.Role;
import ru.tbcarus.topjava.model.User;

public class UserUtil {


//    public static User createNewFromTo(UserTo userTo) {
//        return new User(null, userTo.getName(), userTo.getEmail().toLowerCase(), userTo.getPassword(), Role.USER);
//    }
//
//    public static UserTo asTo(User user) {
//        return new UserTo(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getCaloriesPerDay());
//    }
//
//    public static User updateFromTo(User user, UserTo userTo) {
//        user.setName(userTo.getName());
//        user.setEmail(userTo.getEmail().toLowerCase());
//        user.setCaloriesPerDay(userTo.getCaloriesPerDay());
//        user.setPassword(userTo.getPassword());
//        return user;
//    }

    public static User prepareToSave(User user, PasswordEncoder passwordEncoder) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}