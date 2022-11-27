package ru.tbcarus.topjava.web.restaurant;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tbcarus.topjava.model.Restaurant;
import ru.tbcarus.topjava.model.User;
import ru.tbcarus.topjava.web.user.ProfileRestUserController;

import java.util.List;

import static ru.tbcarus.topjava.web.SecurityUtil.authUserId;

@RestController
@RequestMapping(value = ProfileRestRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileRestRestaurantController extends AbstractRestaurantController {

    public static final String REST_URL = "/rest/profile/restaurants";

    @Override
    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @GetMapping
    public List<Restaurant> getAll() {
        return super.getAll();
    }
}
