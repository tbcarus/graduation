package ru.tbcarus.topjava;

import ru.tbcarus.topjava.model.Role;
import ru.tbcarus.topjava.model.User;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static ru.tbcarus.topjava.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static final MatcherFactory.Matcher<User> USER_MATCHER = MatcherFactory.usingIgnoringFieldsComparator("registered", "roles");

    public static final int ADMIN_ID = START_SEQ;
    public static final int IVAN_ID = START_SEQ + 1;
    public static final int MARIA_ID = START_SEQ + 2;
    public static final int VASILY_AD = START_SEQ + 3;
    public static final int NOT_FOUND = 10;

    public static final User admin = new User(ADMIN_ID, "Admin", "admin@gov.ru", "superAdmin", Role.ADMIN);
    public static final User ivan = new User(IVAN_ID, "Ivan", "ivan@mail.ru", "ivanpass", Role.USER);
    public static final User maria = new User(MARIA_ID, "Maria", "maria@mail.ru", "mariapass", Role.USER);
    public static final User vasily = new User(VASILY_AD, "Vasily", "vasily@mail.ru", "vasilypass", Role.USER);

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", Role.USER);
    }

    public static User getUpdated() {
        User updated = new User(ivan);
        updated.setEmail("update@gmail.com");
        updated.setName("UpdatedName");
        updated.setPassword("newPass");
        updated.setEnabled(false);
        updated.setRoles(Collections.singletonList(Role.ADMIN));
        return updated;
    }

    public static List<User> userList = List.of(admin, ivan, maria, vasily);
}
