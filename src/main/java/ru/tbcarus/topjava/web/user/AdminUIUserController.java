package ru.tbcarus.topjava.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.tbcarus.topjava.View;
import ru.tbcarus.topjava.model.Role;
import ru.tbcarus.topjava.model.User;
import ru.tbcarus.topjava.util.exception.IllegalRequestDataException;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@ApiIgnore
@RestController
@RequestMapping(value = "ui/admin/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminUIUserController extends AbstractUserController {

    @Autowired
    private MessageSource messageSource;

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
    public void createOrUpdate(
            @RequestParam(required = false) String enabled,
            @RequestParam(required = false) String userRole,
            @RequestParam(required = false) String adminRole,
            @Validated(View.Web.class) User user) {

        try {
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
        } catch (DataIntegrityViolationException e) {
            throw new IllegalRequestDataException(messageSource.getMessage(EXCEPTION_DUPLICATE_EMAIL, null, LocaleContextHolder.getLocale()));
        }
    }

    @Override
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void enable(@PathVariable int id, @RequestParam boolean enabled) {
        super.enable(id, enabled);
    }
}
