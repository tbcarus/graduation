package ru.tbcarus.topjava.web.vote;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;
import ru.tbcarus.topjava.RestaurantTestData;
import ru.tbcarus.topjava.VoteTestData;
import ru.tbcarus.topjava.model.Vote;
import ru.tbcarus.topjava.service.VoteService;
import ru.tbcarus.topjava.util.exception.NotFoundException;
import ru.tbcarus.topjava.web.SecurityUtil;
import ru.tbcarus.topjava.web.vote.rest.AdminRestVoteController;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.tbcarus.topjava.UserTestData.IVAN_ID;
import static ru.tbcarus.topjava.VoteTestData.*;

@SpringJUnitWebConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml",
        "classpath:spring/spring-mvc.xml"
})
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Transactional
public class AdminVoteControllerTest {
    private static final Logger log = LoggerFactory.getLogger(AdminVoteControllerTest.class);

    @Autowired
    private AdminRestVoteController controller;

    @Autowired
    private VoteService service;

    @Test
    public void get() {
        Vote vote = service.get(ivanVote1.getId(), IVAN_ID);
        VOTE_MATCHER.assertMatch(vote, ivanVote1);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(VoteTestData.NOT_FOUND, IVAN_ID));
    }

    @Test
    public void getNotOwn() {
        assertThrows(NotFoundException.class, () -> service.get(mariaVote1.getId(), IVAN_ID));
    }

    @Test
    public void getAll() {
        List<Vote> list = controller.getAll();
        VOTE_MATCHER.assertMatch(list, allVotes);
    }

    @Test
    public void getAllByUserId() {
        List<Vote> list = controller.getAllByUserId(IVAN_ID);
        VOTE_MATCHER.assertMatch(list, ivanVote2, ivanVote1);
    }

    @Test
    public void create() {
        Vote created = controller.create(VoteTestData.getNew(), SecurityUtil.authUserId(), RestaurantTestData.BURGER_KING_ID);
        int newId = created.getId();
        Vote newVote = VoteTestData.getNew();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
        VOTE_MATCHER.assertMatch(controller.get(newId, created.getUser().getId()), newVote);
    }

    @Test
    public void update() {
        Vote updated = VoteTestData.getUpdated();
        service.update(updated, IVAN_ID, RestaurantTestData.MCDONALDS_ID);
        VOTE_MATCHER.assertMatch(controller.get(ivanVote2.getId(), IVAN_ID), updated);
        /*
        * Dish updated = getUpdated();
        controller.update(updated, dish9.getId(), RestaurantTestData.BURGER_KING_ID);
        DISH_MATCHER.assertMatch(controller.get(dish9.getId()), updated);*/
    }

    @Test
    public void delete() {
        service.delete(ivanVote1.getId(), IVAN_ID);
        assertThrows(NotFoundException.class, () -> service.get(ivanVote1.getId(), IVAN_ID));
    }

    @Test
    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(VoteTestData.NOT_FOUND, IVAN_ID));
    }
}