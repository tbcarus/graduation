package ru.tbcarus.topjava;

import ru.tbcarus.topjava.model.Restaurant;

import static ru.tbcarus.topjava.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "dishes");

    public static final int KFC_ID = START_SEQ + 4;
    public static final int MCDONALDS_ID = START_SEQ + 5;
    public static final int BURGER_KING_ID = START_SEQ + 6;
    public static final int NOT_FOUND = 10;

    public static final Restaurant kfc = new Restaurant(KFC_ID, "KFC");
    public static final Restaurant mcDonalds = new Restaurant(MCDONALDS_ID, "McDonalds");
    public static final Restaurant burgerKing = new Restaurant(BURGER_KING_ID, "Burger King");

    public static Restaurant getNew() {
        return new Restaurant(null, "New");
    }

    public static Restaurant getUpdated() {
        Restaurant updated = new Restaurant(kfc);
        updated.setName("UpdatedName");
        return updated;
    }
}
