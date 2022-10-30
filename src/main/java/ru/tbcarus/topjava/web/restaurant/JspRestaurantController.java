package ru.tbcarus.topjava.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.tbcarus.topjava.model.Restaurant;
import ru.tbcarus.topjava.model.Vote;
import ru.tbcarus.topjava.service.RestaurantService;
import ru.tbcarus.topjava.service.VoteService;
import ru.tbcarus.topjava.util.VoteUtils;
import ru.tbcarus.topjava.web.SecurityUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static ru.tbcarus.topjava.web.SecurityUtil.authUserId;

@Controller
@RequestMapping("/restaurants")
public class JspRestaurantController {
    private static final Logger log = LoggerFactory.getLogger(JspRestaurantController.class);

    @Autowired
    private VoteService voteService;
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping()
    public String getRestaurants(Model model) {
        log.info("restaurants");
        List<Restaurant> restaurants = restaurantService.getAll();
        model.addAttribute("restaurants", restaurants);
        return "restaurants";
    }

    @PostMapping()
    public String setUser(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        log.info("setUser {} in restaurants", userId);
        SecurityUtil.setAuthUserId(userId);
        return "redirect:restaurants";
    }

    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        restaurantService.delete(getId(request));
        return "redirect:/restaurants";
    }

    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        Restaurant restaurant = restaurantService.get(getId(request));
        model.addAttribute("restaurant", restaurant);
        return "restaurantForm";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Restaurant restaurant = new Restaurant();
        model.addAttribute("restaurant", restaurant);
        return "restaurantForm";
    }

    @PostMapping("/create-or-update")
    public String createOrUpdate(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        int restaurantId = Integer.parseInt(id.isEmpty() ? "0" : id);
        String restaurantName = request.getParameter("name");
        if (StringUtils.hasLength(id)) {
            Restaurant restaurant = restaurantService.get(restaurantId);
            restaurant.setName(restaurantName);
            restaurantService.update(restaurant);
        } else {
            Restaurant restaurant = new Restaurant();
            restaurant.setName(restaurantName);
            restaurantService.create(restaurant);
        }
        return "redirect:/restaurants";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
