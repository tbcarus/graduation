package ru.tbcarus.topjava.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.tbcarus.topjava.model.Vote;
import ru.tbcarus.topjava.service.VoteService;
import ru.tbcarus.topjava.util.ValidationUtil;
import ru.tbcarus.topjava.web.SecurityUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class AbstractVoteController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService service;

    public List<Vote> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public List<Vote> getAllByUserId(int userId) {
        log.info("getAll for user {}", userId);
        return service.getAllByUserId(userId);
    }

    public List<Vote> getToday(int userId) {
        log.info("get vote today by user {}", userId);
        return Collections.singletonList(service.getToday(userId));
    }

    public Vote get(int id, int userId) {
        log.info("get {}", id);
        return service.get(id, userId);
    }

    public Vote create(Vote vote, int userId, int restaurantId) {
        log.info("create {}", vote);
        ValidationUtil.checkNew(vote);
        return service.create(vote, userId, restaurantId);
    }

    public void update(Vote vote, int id, int userId, int restaurantId) {
        log.info("update {} with id={}", vote, id);
        ValidationUtil.assureIdConsistent(vote, id);
        service.update(vote, userId, restaurantId);
    }

    public void delete(int id, int userId) {
        log.info("delete {}", id);
        service.delete(id, userId);
    }
}
