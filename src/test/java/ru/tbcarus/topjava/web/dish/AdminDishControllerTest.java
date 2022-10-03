package ru.tbcarus.topjava.web.dish;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.tbcarus.topjava.RestaurantTestData;
import ru.tbcarus.topjava.model.Dish;
import ru.tbcarus.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

import static ru.tbcarus.topjava.DishTestData.*;


@ContextConfiguration({"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AdminDishControllerTest {
    private static final Logger log = LoggerFactory.getLogger(AdminDishControllerTest.class);

    @Autowired
    private AdminDishController controller;

    @Test
    public void get() {
        Dish dish = controller.get(dish6.getId());
        DISH_MATCHER.assertMatch(dish, dish6);
    }

    @Test
    public void getNotFound() {
        Assert.assertThrows(NotFoundException.class, () -> controller.get(NOT_FOUND));
    }

    @Test
    public void getAll() {
        List<Dish> list = controller.getAll();
        DISH_MATCHER.assertMatch(list, dishes);
    }

    @Test
    public void getAllByRestaurantId() {
        List<Dish> list = controller.getAllByRestaurantId(RestaurantTestData.BURGER_KING_ID);
        DISH_MATCHER.assertMatch(list, dish9, dish10, dish8, dish11);
    }

    @Test
    public void getAllByDate() {
        List<Dish> list = controller.getAllByDate(LocalDate.of(2022, 1, 1));
        DISH_MATCHER.assertMatch(list, dishesByDate);
    }

    @Test
    public void getAllToday() {
        List<Dish> list = controller.getAllToday();
        DISH_MATCHER.assertMatch(list, dishesToday);
    }

    @Test
    public void getAllByRestaurantIdAndDate() {
        List<Dish> list = controller.getAllByRestaurantIdAndDate(RestaurantTestData.BURGER_KING_ID, LocalDate.of(2022, 1, 1));
        DISH_MATCHER.assertMatch(list, dish11);
    }

    @Test
    public void getAllByRestaurantIdToday() {
        List<Dish> list = controller.getAllByRestaurantIdToday(RestaurantTestData.BURGER_KING_ID);
        DISH_MATCHER.assertMatch(list, dish9, dish10, dish8);
    }

    @Test
    public void create() {
        Dish created = controller.create(getNew(), RestaurantTestData.BURGER_KING_ID);
        int newId = created.getId();
        Dish newDish = getNew();
        newDish.setId(newId);
        DISH_MATCHER.assertMatch(created, newDish);
        DISH_MATCHER.assertMatch(controller.get(newId), newDish);
    }

    @Test
    public void createDuplicateDish() {
        Dish dish = new Dish(dish2.getName(), 111, dish2.getInputDate(), dish2.getRestaurant());
        Assert.assertThrows(DataAccessException.class, () -> controller.create(dish, dish.getRestaurant().getId()));
    }

    @Test
    public void delete() {
        controller.delete(dish3.getId());
        Assert.assertThrows(NotFoundException.class, () -> controller.get(dish3.getId()));
    }

    @Test
    public void deleteNotFound() {
        Assert.assertThrows(NotFoundException.class, () -> controller.delete(NOT_FOUND));
    }

    @Test
    public void update() {
        Dish updated = getUpdated();
        controller.update(updated, dish9.getId(), RestaurantTestData.BURGER_KING_ID);
        DISH_MATCHER.assertMatch(controller.get(dish9.getId()), updated);
    }
}