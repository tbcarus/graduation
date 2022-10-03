package ru.tbcarus.topjava.web.dish;

import org.springframework.stereotype.Controller;
import ru.tbcarus.topjava.model.Dish;
import ru.tbcarus.topjava.util.ValidationUtil;

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

    public void update(Dish dish, int id, int restaurantId) {
        log.info("update {} with restaurantId={}", dish, restaurantId);
        ValidationUtil.assureIdConsistent(dish, id);
        service.update(dish, restaurantId);
    }
}