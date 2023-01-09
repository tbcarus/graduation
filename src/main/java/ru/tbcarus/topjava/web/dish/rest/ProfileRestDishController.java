package ru.tbcarus.topjava.web.dish.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tbcarus.topjava.model.Dish;
import ru.tbcarus.topjava.web.dish.AbstractDishController;

import java.util.List;

@RestController
@RequestMapping(value = ProfileRestDishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileRestDishController extends AbstractDishController {

    public static final String REST_URL = "/rest/profile/restaurants";

    @Override
    @GetMapping("/dishes/{id}")
    public Dish get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @GetMapping("/dishes")
    public List<Dish> getAllToday() {
        return super.getAllToday();
    }

    @Override
    @GetMapping("/{restId}/dishes")
    public List<Dish> getAllByRestaurantIdToday(@PathVariable int restId) {
        return super.getAllByRestaurantIdToday(restId);
    }
}
