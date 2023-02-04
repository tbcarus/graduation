package ru.tbcarus.topjava.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ru.tbcarus.topjava.model.Restaurant;
import ru.tbcarus.topjava.service.RestaurantService;
import ru.tbcarus.topjava.service.VoteService;
import ru.tbcarus.topjava.web.SecurityUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "ui/admin/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminUIRestaurantController extends AbstractRestaurantController {
    private static final Logger log = LoggerFactory.getLogger(AdminUIRestaurantController.class);

    @Autowired
    private VoteService voteService;
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping()
    public List<Restaurant> getRestaurants() {
        return super.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        return super.get(id);
    }

    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        Restaurant restaurant = restaurantService.get(getId(request));
        model.addAttribute("restaurant", restaurant);
        return "restaurantForm";
    }

    //OK
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    //OK
    @PostMapping()
    public void create(@RequestParam(required = false) String id,
                       @RequestParam String name) {
        Restaurant restaurant = new Restaurant(name);
        if (id.isEmpty()) {
            super.create(restaurant);
        } else {
            int restaurantId = Integer.parseInt(id);
            restaurant.setId(restaurantId);
            super.update(restaurant, restaurantId);
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
