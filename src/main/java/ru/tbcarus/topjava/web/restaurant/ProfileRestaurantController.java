package ru.tbcarus.topjava.web.restaurant;

import org.springframework.stereotype.Controller;
import ru.tbcarus.topjava.model.Restaurant;
import ru.tbcarus.topjava.model.User;
import ru.tbcarus.topjava.web.SecurityUtil;
import ru.tbcarus.topjava.web.user.AbstractUserController;

import java.util.List;

@Controller
public class ProfileRestaurantController extends AbstractRestaurantController {
    public List<Restaurant> getAll(){
        return super.getAll();
    }

    public Restaurant get(int restaurantId) {
        return super.get(restaurantId);
    }
}
