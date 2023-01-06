package ru.tbcarus.topjava.web.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tbcarus.topjava.model.Role;
import ru.tbcarus.topjava.model.User;

import java.util.List;

@RestController
@RequestMapping(value = "ui/admin/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminUIUserController extends AbstractUserController {

    @Override
    @GetMapping
    public List<User> getAll() {
        return super.getAll();
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    //OK
    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestParam String id,
                       @RequestParam String name,
                       @RequestParam String email,
                       @RequestParam String password,
                       @RequestParam(required = false) String userRole,
                       @RequestParam(required = false) String adminRole) {
        User user = new User(null, name, email, password);
        if (userRole != null) {
            user.setRole(Role.USER);
        }
        if (adminRole != null) {
            user.setRole(Role.ADMIN);
        }
        if (id.isEmpty()) {
            super.create(user);
        } else {
            int userId = Integer.parseInt(id);
            user.setId(userId);
            super.update(user, userId);

        }

    }
}
