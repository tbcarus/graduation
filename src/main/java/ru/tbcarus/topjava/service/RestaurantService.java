package ru.tbcarus.topjava.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.tbcarus.topjava.model.Restaurant;
import ru.tbcarus.topjava.repository.datajpa.DishRepository;
import ru.tbcarus.topjava.repository.datajpa.RestaurantRepository;
import ru.tbcarus.topjava.util.ValidationUtil;

import java.util.List;

@Service
public class RestaurantService {
    private static final Sort SORT_DATE_TIME = Sort.by(Sort.Direction.ASC, "dateTime");

    private final RestaurantRepository restaurantRepository;
    private final DishRepository dishRepository;

    public RestaurantService(RestaurantRepository restaurantRepository, DishRepository dishRepository) {
        this.restaurantRepository = restaurantRepository;
        this.dishRepository = dishRepository;
    }

    public Restaurant get(int id) {
        return ValidationUtil.checkNotFoundWithId(restaurantRepository.findById(id).orElse(null), id);
    }

    public void delete(int id) {
        ValidationUtil.checkNotFoundWithId(restaurantRepository.delete(id) != 0, id);
    }

    public List<Restaurant> getAll() {
        return restaurantRepository.getAll();
    }

    public Restaurant create(Restaurant restaurant) {
        return save(restaurant);
    }

    public void update(Restaurant restaurant) {
        Restaurant r = save(restaurant);
        ValidationUtil.checkNotFoundWithId(r, restaurant.id());
    }

    private Restaurant save(Restaurant restaurant) {
        Assert.notNull(restaurant, "tbca: reataurant mest be not null");
        if (!restaurant.isNew() && restaurantRepository.findById(restaurant.getId()).get() == null) {
            return null;
        } else {
            restaurant.setDishes(dishRepository.getAll(restaurant.getId()));
            return restaurantRepository.save(restaurant);
        }
    }
}
