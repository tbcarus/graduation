package ru.tbcarus.topjava.web.dish;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.tbcarus.topjava.DishTestData;
import ru.tbcarus.topjava.RestaurantTestData;
import ru.tbcarus.topjava.model.Dish;
import ru.tbcarus.topjava.model.Restaurant;
import ru.tbcarus.topjava.service.DishService;
import ru.tbcarus.topjava.util.DateTimeUtil;
import ru.tbcarus.topjava.util.exception.NotFoundException;
import ru.tbcarus.topjava.web.AbstractControllerTest;
import ru.tbcarus.topjava.web.json.JsonUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.tbcarus.topjava.DishTestData.*;
import static ru.tbcarus.topjava.RestaurantTestData.*;

class AdminRestDishControllerTest extends AbstractControllerTest {

    private final static String REST_URL = AdminRestDishController.REST_URL + '/';

    @Autowired
    DishService dishService;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "dishes/" + dish5.id()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(dish5));
    }

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "dishes"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(dishesToday));
    }

    @Test
    void getAllToday() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + kfc.id() + "/dishes"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(dish3, dish2, dish1));
    }

    @Test
    void getAllByDate() throws Exception {
        perform((MockMvcRequestBuilders.get(REST_URL + "dishes/by-date?date=" + ANOTHER_DATE)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(dishesByDate));
    }

    @Test
    void getAllByRestaurantIdToday() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + BURGER_KING_ID + "/dishes"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(bgkDishesToday));
    }

    @Test
    void getAllByRestaurantId() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + BURGER_KING_ID + "/dishes/all"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(bgkDishesAll));
    }

    @Test
    void getAllByRestaurantIdAndDate() throws Exception {
        perform((MockMvcRequestBuilders.get(REST_URL + BURGER_KING_ID + "/dishes/by-date?date=" + ANOTHER_DATE)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(List.of(dish11)));
    }

    @Test
    void createRest() throws Exception {
        Dish newDish = DishTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL + burgerKing.id() + "/dishes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newDish)))
                .andExpect(status().isCreated());

        Dish created = DISH_MATCHER.readFromJson(action);
        int newId = created.id();
        newDish.setId(newId);
        DISH_MATCHER.assertMatch(created, newDish);
        DISH_MATCHER.assertMatch(dishService.get(newId), newDish);
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + KFC_ID + "/dishes/" + dish1.id()))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> dishService.get(dish1.id()));
    }

    @Test
    void deleteNotOwn() {
        //TO DO
    }

    @Test
    void update() throws Exception {
        Dish updated = DishTestData.getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + BURGER_KING_ID + "/dishes/" + updated.id())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        DISH_MATCHER.assertMatch(dishService.get(updated.id()), updated);
    }
}