package ru.tbcarus.topjava.web.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.tbcarus.topjava.UserTestData;
import ru.tbcarus.topjava.model.User;
import ru.tbcarus.topjava.service.UserService;
import ru.tbcarus.topjava.util.exception.NotFoundException;
import ru.tbcarus.topjava.web.AbstractControllerTest;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.tbcarus.topjava.UserTestData.IVAN_ID;
import static ru.tbcarus.topjava.UserTestData.MARIA_ID;

public class JspUserControllerTest extends AbstractControllerTest {

    @Autowired
    UserService service;

    @Test
    public void root() {
    }

    @Test
    public void getUsers() throws Exception {
        perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/users.jsp"))
                .andExpect(model().attribute("users", UserTestData.userList))
                .andExpect(model().attribute("users", hasSize(4)))
                .andExpect(model().attribute("users", hasItem(
                        allOf(
                                hasProperty("id", is(UserTestData.ADMIN_ID)),
                                hasProperty("name", is(UserTestData.admin.getName()))
                        )
                )));
    }

    @Test
    public void getUser() throws Exception {
        perform(get("/users/" + UserTestData.IVAN_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("user", UserTestData.ivan));
    }

    @Test
    public void delete() throws Exception {
        perform(get("/users/delete?id=" + MARIA_ID))
                .andDo(print());
        assertThrows(NotFoundException.class, () -> service.get(MARIA_ID));
    }

    @Test
    public void update() {
    }

    @Test
    public void create() {
    }

    @Test
    public void createOrUpdate() {
    }

    @Test
    public void setUser() {
    }
}