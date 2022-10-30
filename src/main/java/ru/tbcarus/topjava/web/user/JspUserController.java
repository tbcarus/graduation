package ru.tbcarus.topjava.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.tbcarus.topjava.model.Restaurant;
import ru.tbcarus.topjava.model.Role;
import ru.tbcarus.topjava.model.User;
import ru.tbcarus.topjava.service.RestaurantService;
import ru.tbcarus.topjava.service.UserService;
import ru.tbcarus.topjava.web.SecurityUtil;

import javax.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class JspUserController {
    private static final Logger log = LoggerFactory.getLogger(JspUserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/")
    public String root(Model model) {
        log.info("root");
        List<User> users = userService.getAll();
        List<Restaurant> restaurants = restaurantService.getAll();
        model.addAttribute("users", users);
        model.addAttribute("restaurants", restaurants);
        return "index";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        log.info("users");
        model.addAttribute("user", userService.get(SecurityUtil.authUserId()));
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @GetMapping("/users/{id}")
    public String getUser(@PathVariable("id") String id, Model model) {
        User user = userService.get(Integer.parseInt(id));
        model.addAttribute("user", user);
        return "userInfo";
    }

    @GetMapping("/users/delete")
    public String delete(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("id"));
        log.info("delete user {}", userId);
        userService.delete(userId);
        return "redirect:/users";
    }

    @GetMapping("/users/update")
    public String update(HttpServletRequest request, Model model) {
        User user = userService.get(Integer.parseInt(request.getParameter("id")));
        model.addAttribute("user", user);
        return "userForm";
    }

    @GetMapping("/users/create")
    public String create(HttpServletRequest request, Model model) {
        User user = new User(LocalDateTime.now());
        model.addAttribute("user", user);
        return "userForm";
    }

    @PostMapping("/users/create-or-update")
    public String createOrUpdate(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        int userId = Integer.parseInt(id.isEmpty() ? "0" : id);
        String name = request.getParameter("name");
        String eMail = request.getParameter("email");
        boolean enabled = request.getParameter("enabled") != null;
        LocalDateTime registered = LocalDateTime.now();
        String password = request.getParameter("password");
        Set<Role> roles = new HashSet<>();
        if (request.getParameter("userRole") != null) {
            roles.add(Role.USER);
        }
        if (request.getParameter("adminRole") != null) {
            roles.add(Role.ADMIN);
        }

        if (StringUtils.hasLength(request.getParameter("id"))) {
            User user = userService.get(userId);
            user.setName(name);
            user.setEnabled(enabled);
            user.setPassword(password);
            user.setRoles(roles);
            userService.update(user);

        } else {
            User user = new User(null, name, eMail, password, enabled, registered, roles);
            userService.create(user);
        }
        return "redirect:/users";
    }

    @PostMapping("/users")
    public String setUser(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        log.info("setUser {}", userId);
        SecurityUtil.setAuthUserId(userId);
        return "redirect:users";
    }
}
