package ru.tbcarus.topjava.web.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.tbcarus.topjava.model.Role;
import ru.tbcarus.topjava.model.User;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "ui/admin/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminUIUserController extends AbstractUserController {

    @Override
    @GetMapping
    public List<User> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public User get(@PathVariable int id) {
        return super.get(id);
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
    public ResponseEntity<String> createOrUpdate(
            @RequestParam(required = false) String enabled,
            @RequestParam(required = false) String userRole,
            @RequestParam(required = false) String adminRole,
            @Valid User user,
            BindingResult result) {
        if (result.hasErrors()) {
            if (result.getFieldErrors().size() > 1 || !result.getFieldErrors().get(0).getField().equals("enabled")) {
                String errorFieldsMsg = result.getFieldErrors().stream()
                        .map(fe -> String.format("[%s] %s", fe.getField(), fe.getDefaultMessage()))
                        .collect(Collectors.joining("<br>"));
                return ResponseEntity.unprocessableEntity().body(errorFieldsMsg);
            }
        }
        if (userRole != null) {
            user.setRole(Role.USER);
        }
        if (adminRole != null) {
            user.setRole(Role.ADMIN);
        }
        if (enabled == null) {
            user.setEnabled(false);
        } else {
            user.setEnabled(true);
        }
        if (user.isNew()) {
            super.create(user);
        } else {
            super.update(user, user.getId());
        }
        return ResponseEntity.ok().build();
    }

    @Override
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void enable(@PathVariable int id, @RequestParam boolean enabled) {
        super.enable(id, enabled);
    }
}
