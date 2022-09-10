package ru.tbcarus.topjava.web.user;

import org.springframework.stereotype.Controller;
import ru.tbcarus.topjava.model.User;
import ru.tbcarus.topjava.web.SecurityUtil;

@Controller
public class ProfileUserController extends AbstractUserController {
    public User get() {
        return super.get(SecurityUtil.authUserId());
    }

    public void delete() {
        super.delete(SecurityUtil.authUserId());
    }

    public void update(User user) {
        super.update(user, SecurityUtil.authUserId());
    }
}
