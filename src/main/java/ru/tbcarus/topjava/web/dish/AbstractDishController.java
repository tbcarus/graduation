package ru.tbcarus.topjava.web.dish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Dish get(int id) {
        return service.get(id);
    }

    public List<Dish> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public List<Dish> getAllToday() {
        return getAllByDate(DateTimeUtil.today().toLocalDate());
    }

    public List<Dish> getAllByDate(LocalDate date) {
        return service.getAllByDate(date);
    }

    public List<Dish> getAllByRestaurantIdToday(int restaurantId) {
        return getAllByRestaurantIdAndDate(restaurantId, DateTimeUtil.today().toLocalDate());
    }

    public List<Dish> getAllByRestaurantId(int restaurantId){
        return service.getAllByRestaurantId(restaurantId);
    }

    public List<Dish> getAllByRestaurantIdAndDate(int restaurantId, LocalDate date) {
        return service.getAllByRestaurantIdAndDate(restaurantId, date);
    }

    public Dish create(Dish dish, int restId) {
        log.info("create {} in restaurantId {}", dish, restId);
        ValidationUtil.checkNew(dish);
        return service.create(dish, restId);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public void update(Dish dish, int restId, int id) {
        log.info("update {} with id={} in restaurantId {}", dish, id, restId);
        ValidationUtil.assureIdConsistent(dish, id);
        service.update(dish, restId);
    }
}
