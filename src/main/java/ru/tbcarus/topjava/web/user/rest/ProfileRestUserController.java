package ru.tbcarus.topjava.web.user.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.tbcarus.topjava.model.User;
import ru.tbcarus.topjava.web.user.AbstractUserController;

import static ru.tbcarus.topjava.web.SecurityUtil.authUserId;

@RestController
@RequestMapping(value = ProfileRestUserController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileRestUserController extends AbstractUserController {

    public static final String REST_URL = "/rest/profile";

    @GetMapping
    public User get() {
        return super.get(authUserId());
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete() {
        super.delete(authUserId());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody User user) {
        super.update(user, authUserId());
    }

    @GetMapping("/text")
    public String testUTF() {
        return "Русский текст";
    }
}
