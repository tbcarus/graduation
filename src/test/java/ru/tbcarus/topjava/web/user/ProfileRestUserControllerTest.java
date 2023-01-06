package ru.tbcarus.topjava.web.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.tbcarus.topjava.model.User;
import ru.tbcarus.topjava.service.UserService;
import ru.tbcarus.topjava.web.AbstractControllerTest;
import ru.tbcarus.topjava.web.SecurityUtil;
import ru.tbcarus.topjava.web.json.JsonUtil;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.tbcarus.topjava.UserTestData.*;
import static ru.tbcarus.topjava.web.user.rest.ProfileRestUserController.REST_URL;

//@SpringJUnitWebConfig(locations = {
//        "classpath:spring/spring-app.xml",
//        "classpath:spring/spring-db.xml",
//        "classpath:spring/spring-mvc.xml"
//})
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ProfileRestUserControllerTest extends AbstractControllerTest {

    @Autowired
    UserService userService;

    @Test
    public void get() throws Exception {
        SecurityUtil.setAuthUserId(ADMIN_ID);
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHER.contentJson(admin));
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL))
                .andExpect(status().isNoContent());
        USER_MATCHER.assertMatch(userService.getAll(), ivan, maria, vasily);
    }

    @Test
    void update() throws Exception {
        SecurityUtil.setAuthUserId(IVAN_ID);
        User updated = getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());

        USER_MATCHER.assertMatch(userService.get(IVAN_ID), updated);
    }
}