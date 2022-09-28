package ru.tbcarus.topjava.web.Dish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.tbcarus.topjava.model.Dish;
import ru.tbcarus.topjava.model.Restaurant;
import ru.tbcarus.topjava.service.RestaurantService;
import ru.tbcarus.topjava.util.ValidationUtil;
import ru.tbcarus.topjava.web.restaurant.AbstractRestaurantController;

import java.util.List;

@Controller
public class AdminDishController extends AbstractDishController {

    public Dish create(Dish dish, int restaurantId) {
        log.info("create {} in dish {}", dish, restaurantId);
        ValidationUtil.checkNew(dish);
        return service.create(dish, restaurantId);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public void update(Dish dish, int restaurantId) {
        log.info("update {} with restaurantId={}", dish, restaurantId);
        ValidationUtil.assureIdConsistent(dish, restaurantId);
        service.update(dish, restaurantId);
    }
}