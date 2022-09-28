package ru.tbcarus.topjava.web.Dish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.tbcarus.topjava.model.Dish;
import ru.tbcarus.topjava.service.DishService;
import ru.tbcarus.topjava.util.DateTimeUtil;
import ru.tbcarus.topjava.util.ValidationUtil;

import java.time.LocalDate;
import java.util.List;

public abstract class AbstractDishController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected DishService service;

    public List<Dish> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public List<Dish> getAllByRestaurantId(int restaurantId){
        return service.getALlByRestaurantId(restaurantId);
    }

    public List<Dish> getAllByDate(LocalDate date) {
        return service.getAllByDate(date);
    }

    public List<Dish> getAllToday() {
        return getAllByDate(DateTimeUtil.today().toLocalDate());
    }

    public List<Dish> getAllByRestaurantIdAndDate(int restaurantId, LocalDate date) {
        return service.getAllByRestaurantIdAndDate(restaurantId, date);
    }

    public List<Dish> getAllByRestaurantIdToday(int restaurantId) {
        return getAllByRestaurantIdAndDate(restaurantId, DateTimeUtil.today().toLocalDate());
    }
}
