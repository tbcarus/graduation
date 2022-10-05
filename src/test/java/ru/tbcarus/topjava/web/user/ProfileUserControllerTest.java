package ru.tbcarus.topjava.web.user;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.tbcarus.topjava.model.Role;
import ru.tbcarus.topjava.model.User;
import ru.tbcarus.topjava.repository.JpaUtil;
import ru.tbcarus.topjava.repository.datajpa.UserRepository;
import ru.tbcarus.topjava.util.exception.NotFoundException;

import java.util.List;

import static ru.tbcarus.topjava.UserTestData.*;

@ContextConfiguration({"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ProfileUserControllerTest {
    private static final Logger log = LoggerFactory.getLogger(ProfileUserControllerTest.class);

    @Autowired
    private AdminUserController controller;
    @Autowired
    private UserRepository repository;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    protected JpaUtil jpaUtil;

    @Before
    public void setup() {
        cacheManager.getCache("users").clear();
        jpaUtil.clear2ndLevelHibernateCache();
    }

    @Test
    public void create() {
        User created = controller.create(getNew());
        int newId = created.getId();
        User newUser = getNew();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(created, newUser);
        USER_MATCHER.assertMatch(controller.get(newId), newUser);
    }

    @Test
    public void createDuplicateEmail() {
        User newUser = new User(null,"Duplicate", "ivan@mail.ru", "duplicate", Role.USER);
        Assert.assertThrows(DataAccessException.class, () -> controller.create(newUser));
    }

    @Test
    public void getAll() {
        List<User> userList = controller.getAll();
        USER_MATCHER.assertMatch(userList, admin, ivan, maria, vasily);
    }

    @Test
    public void get() {
        User adminVerify = controller.get(ADMIN_ID);
        USER_MATCHER.assertMatch(adminVerify, admin);
    }

    @Test
    public void getNotFound() {
        Assert.assertThrows(NotFoundException.class, () -> controller.get(NOT_FOUND));
    }

    @Test
    public void getByEmail() {
        User user = controller.getByMail("maria@mail.ru");
        USER_MATCHER.assertMatch(user, maria);
    }

    @Test
    public void delete() {
        controller.delete(MARIA_ID);
        Assert.assertThrows(NotFoundException.class, () -> controller.get(MARIA_ID));
    }

    @Test
    public void deleteNotFound() {
        Assert.assertThrows(NotFoundException.class, () -> controller.get(NOT_FOUND));
    }

    @Test
    public void update() {
        User updated = getUpdated();
        controller.update(updated, updated.getId());
        USER_MATCHER.assertMatch(controller.get(IVAN_ID), updated);
    }
}