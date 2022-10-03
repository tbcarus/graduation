package ru.tbcarus.topjava;

import ru.tbcarus.topjava.MatcherFactory;
import ru.tbcarus.topjava.RestaurantTestData;
import ru.tbcarus.topjava.model.Dish;
import ru.tbcarus.topjava.model.Vote;

import java.time.LocalDate;
import java.util.List;

import static ru.tbcarus.topjava.model.AbstractBaseEntity.START_SEQ;

public class VoteTestData {
    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator("user", "restaurant");

    public static final int ADMIN_VOTE1_ID = START_SEQ + 19;
    public static final int IVAN_VOTE1_ID = START_SEQ + 20;
    public static final int MARIA_VOTE1_ID = START_SEQ + 21;
    public static final int VASILY_VOTE1_ID = START_SEQ + 22;
    public static final int IVAN_VOTE2_ID = START_SEQ + 23;
    public static final int MARIA_VOTE2_ID = START_SEQ + 24;
    public static final int VASILY_VOTE2_ID = START_SEQ + 25;

    public static final int NOT_FOUND = 100;

    public static final Vote adminVote1 = new Vote(ADMIN_VOTE1_ID, LocalDate.of(2022, 1, 1), UserTestData.admin, RestaurantTestData.mcDonalds);
    public static final Vote ivanVote1 = new Vote(IVAN_VOTE1_ID, LocalDate.of(2022, 1, 1), UserTestData.ivan, RestaurantTestData.kfc);
    public static final Vote mariaVote1 = new Vote(MARIA_VOTE1_ID, LocalDate.of(2022, 1, 1), UserTestData.maria, RestaurantTestData.burgerKing);
    public static final Vote vasilyVote1 = new Vote(VASILY_VOTE1_ID, LocalDate.of(2022, 1, 1), UserTestData.vasily, RestaurantTestData.mcDonalds);
    public static final Vote ivanVote2 = new Vote(IVAN_VOTE2_ID, LocalDate.now(), UserTestData.ivan, RestaurantTestData.mcDonalds);
    public static final Vote mariaVote2 = new Vote(MARIA_VOTE2_ID, LocalDate.now(), UserTestData.maria, RestaurantTestData.kfc);
    public static final Vote vasilyVote2 = new Vote(VASILY_VOTE2_ID, LocalDate.now(), UserTestData.vasily, RestaurantTestData.burgerKing);

    public static Vote getNew() {
        return new Vote(LocalDate.now(), UserTestData.admin, RestaurantTestData.burgerKing);
    }

    public static Vote getUpdated() {
        Vote updated = new Vote(ivanVote2);
        updated.setRestaurant(RestaurantTestData.burgerKing);
        return updated;
    }

    public static final List<Vote> allVotes = List.of(
            vasilyVote1, mariaVote1, ivanVote1, adminVote1,
            vasilyVote2, mariaVote2, ivanVote2);


}
