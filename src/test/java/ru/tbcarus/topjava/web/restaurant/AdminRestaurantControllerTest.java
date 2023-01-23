package ru.tbcarus.topjava.web.restaurant;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import ru.tbcarus.topjava.model.Restaurant;
import ru.tbcarus.topjava.util.exception.NotFoundException;
import ru.tbcarus.topjava.web.restaurant.rest.AdminRestRestaurantController;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.tbcarus.topjava.RestaurantTestData.*;

@SpringJUnitWebConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml",
        "classpath:spring/spring-mvc.xml"
})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
//@Transactional
public class AdminRestaurantControllerTest {
    private static final Logger log = LoggerFactory.getLogger(AdminRestaurantControllerTest.class);

    @Autowired
    private AdminRestRestaurantController controller;

    @Test
    public void create() {
        Restaurant created = controller.create(getNew());
        int newId = created.getId();
        Restaurant newRestaurant = getNew();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(controller.get(newId), newRestaurant);
    }

    @Test
    public void createDuplicateName() {
        Restaurant restaurant = new Restaurant(null, "KFC");
        assertThrows(DataAccessException.class, () -> controller.create(restaurant));
    }

    @Test
    public void getAll() {
        List<Restaurant> list = controller.getAll();
        RESTAURANT_MATCHER.assertMatch(list, burgerKing, kfc, mcDonalds);
    }

    @Test
    public void get() {
        Restaurant restaurant = controller.get(kfc.getId());
        RESTAURANT_MATCHER.assertMatch(restaurant, kfc);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> controller.get(NOT_FOUND));
    }

    @Test
    public void delete() {
        controller.delete(mcDonalds.getId());
        assertThrows(NotFoundException.class, () -> controller.get(mcDonalds.getId()));
    }

    @Test
    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> controller.delete(NOT_FOUND));
    }

    @Test
    public void update() {
        Restaurant updated = getUpdated();
        controller.update(updated, updated.getId());
        RESTAURANT_MATCHER.assertMatch(controller.get(KFC_ID), updated);
    }
}