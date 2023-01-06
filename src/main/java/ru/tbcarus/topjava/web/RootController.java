package ru.tbcarus.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.tbcarus.topjava.model.Restaurant;
import ru.tbcarus.topjava.model.User;
import ru.tbcarus.topjava.service.RestaurantService;
import ru.tbcarus.topjava.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class RootController {
    private static final Logger log = LoggerFactory.getLogger(RootController.class);

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
        model.addAttribute("activeUser", userService.get(SecurityUtil.authUserId()));
        return "index";
    }

    @PostMapping("/users")
    public String setUserToUsers(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        log.info("setUser {}", userId);
        SecurityUtil.setAuthUserId(userId);
        return "redirect:users";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        log.info("users");
        model.addAttribute("user", userService.get(SecurityUtil.authUserId()));
        model.addAttribute("users", userService.getAll());
        return "users";
    }
}
