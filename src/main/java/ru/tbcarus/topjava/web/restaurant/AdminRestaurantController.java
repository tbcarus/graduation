package ru.tbcarus.topjava.web.restaurant;

import org.springframework.stereotype.Controller;
import ru.tbcarus.topjava.model.Restaurant;
import ru.tbcarus.topjava.model.User;
import ru.tbcarus.topjava.web.user.AbstractUserController;

import java.util.List;

@Controller
public class AdminRestaurantController extends AbstractRestaurantController {

    @Override
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @Override
    public Restaurant get(int id) {
        return super.get(id);
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        return super.create(restaurant);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public void update(Restaurant restaurant, int id) {
        super.update(restaurant, id);
    }
}