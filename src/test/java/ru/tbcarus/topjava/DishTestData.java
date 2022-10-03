package ru.tbcarus.topjava;

import ru.tbcarus.topjava.model.Dish;

import java.time.LocalDate;
import java.util.List;

import static ru.tbcarus.topjava.model.AbstractBaseEntity.START_SEQ;

public class DishTestData {
    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER = MatcherFactory.usingIgnoringFieldsComparator("restaurant");

    public static final int KFC1 = START_SEQ + 7;
    public static final int KFC2 = START_SEQ + 8;
    public static final int KFC3 = START_SEQ + 9;
    public static final int KFC4 = START_SEQ + 18;

    public static final int MCD1 = START_SEQ + 10;
    public static final int MCD2 = START_SEQ + 11;
    public static final int MCD3 = START_SEQ + 12;
    public static final int MCD4 = START_SEQ + 13;

    public static final int BGK1 = START_SEQ + 14;
    public static final int BGK2 = START_SEQ + 15;
    public static final int BGK3 = START_SEQ + 16;
    public static final int BGK4 = START_SEQ + 17;

    public static final int NOT_FOUND = 100;

    public static final Dish dish1 = new Dish(KFC1, "Бёдра", 7, LocalDate.now(), RestaurantTestData.kfc);
    public static final Dish dish2 = new Dish(KFC2, "Крылья", 5, LocalDate.now(), RestaurantTestData.kfc);
    public static final Dish dish3 = new Dish(KFC3, "Пепси", 3, LocalDate.now(), RestaurantTestData.kfc);
    public static final Dish dish12 = new Dish(KFC4, "Блюдо на другую дату KFC", 12, LocalDate.of(2022, 1, 1), RestaurantTestData.kfc);

    public static final Dish dish4 = new Dish(MCD1, "Роял", 8, LocalDate.now(), RestaurantTestData.mcDonalds);
    public static final Dish dish5 = new Dish(MCD2, "Чизбургер", 4, LocalDate.now(), RestaurantTestData.mcDonalds);
    public static final Dish dish6 = new Dish(MCD3, "БигМак", 5, LocalDate.now(), RestaurantTestData.mcDonalds);
    public static final Dish dish7 = new Dish(MCD4, "Кола", 3, LocalDate.now(), RestaurantTestData.mcDonalds);

    public static final Dish dish8 = new Dish(BGK1, "Воппер", 8, LocalDate.now(), RestaurantTestData.burgerKing);
    public static final Dish dish9 = new Dish(BGK2, "Пиво", 7, LocalDate.now(), RestaurantTestData.burgerKing);
    public static final Dish dish10 = new Dish(BGK3, "Меринда", 4, LocalDate.now(), RestaurantTestData.burgerKing);
    public static final Dish dish11 = new Dish(BGK4, "Блюдо на другую дату BGK", 10, LocalDate.of(2022, 1, 1), RestaurantTestData.burgerKing);

    public static Dish getNew() {
        return new Dish(null, "New", 11, LocalDate.now(), RestaurantTestData.burgerKing);
    }

    public static Dish getUpdated() {
        Dish updated = new Dish(dish9);
        updated.setName("UpdatedName");
        return updated;
    }

    public static final List<Dish> dishes = List.of(
            dish9, dish10, dish8, dish11,
            dish3, dish2, dish12, dish1,
            dish5, dish4, dish7, dish6);

    public static final List<Dish> dishesByDate = List.of(dish11, dish12);

    public static final List<Dish> dishesToday = List.of(
            dish9, dish10, dish8,
            dish3, dish2, dish1,
            dish5, dish4, dish7, dish6);
}
