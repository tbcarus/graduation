package ru.tbcarus.topjava.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.tbcarus.topjava.model.Dish;
import ru.tbcarus.topjava.repository.datajpa.DishRepository;
import ru.tbcarus.topjava.repository.datajpa.RestaurantRepository;
import ru.tbcarus.topjava.util.DateTimeUtil;
import ru.tbcarus.topjava.util.ValidationUtil;

import java.time.LocalDate;
import java.util.List;

@Service
public class DishService {
    private static final Sort SORT_DATE_TIME = Sort.by(Sort.Direction.ASC, "dateTime");

    private final RestaurantRepository restaurantRepository;
    private final DishRepository dishRepository;

    public DishService(RestaurantRepository restaurantRepository, DishRepository dishRepository) {
        this.restaurantRepository = restaurantRepository;
        this.dishRepository = dishRepository;
    }

    public Dish get(int id) {
        return ValidationUtil.checkNotFoundWithId(dishRepository.findById(id).orElse(null), id);
    }

    public Dish getByRestaurant(int id, int restaurantId) {
        return dishRepository.get(id, restaurantId);
    }

    public void delete (int id) {
        ValidationUtil.checkNotFoundWithId(dishRepository.delete(id) != 0, id);
    }

    public List<Dish> getAll() {
        return dishRepository.getAll();
    }

    public List<Dish> getALlByRestaurantId(int restaurantId) {
        return dishRepository.getAllByRestaurantId(restaurantId);
    }

    public List<Dish> getAllByDate(LocalDate date) {
        return dishRepository.getAllByDate(date);
    }

    public List<Dish> getAllByRestaurantIdAndDate(int restaurantId, LocalDate date) {
        return dishRepository.getAllByRestaurantIdAndDate(restaurantId, date);
    }

    public Dish create(Dish dish, int restaurantId) {
        return save(dish, restaurantId);
    }

    public void update(Dish dish, int restaurantId) {
        Dish d = save(dish, restaurantId);
        ValidationUtil.checkNotFoundWithId(d, dish.id());
    }

    private Dish save(Dish dish, int restaurantId) {
        Assert.notNull(dish, "tbca: dish mest be not null");
//        if(dish.isNew()) {
//            return  dishRepository.save(dish);
//        }
        if (!dish.isNew() && dishRepository.findById(dish.getId()).get() == null) {
            return null;
        } else {
            dish.setRestaurant(restaurantRepository.findById(restaurantId).get());
            return dishRepository.save(dish);
        }
    }
}
