package ru.tbcarus.topjava.web.vote.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.tbcarus.topjava.model.Vote;
import ru.tbcarus.topjava.to.VoteTo;
import ru.tbcarus.topjava.util.VoteUtils;
import ru.tbcarus.topjava.web.vote.AbstractVoteController;

import java.util.List;

import static ru.tbcarus.topjava.web.SecurityUtil.authUserId;

@RestController
@RequestMapping(value = AdminRestVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestVoteController extends AbstractVoteController {

    public static final String REST_URL = "/rest/admin/users";

    @Override
    @GetMapping("/votes")
    public List<Vote> getAll() { return super.getAll();}

    @Override
    @GetMapping("/{userId}/votes")
    public List<Vote> getAllByUserId(@PathVariable int userId) {
        return super.getAllByUserId(userId);
    }

    @GetMapping("/{userId}/votes/{id}")
    public Vote get(@PathVariable int userId, @PathVariable int id) {
        return super.get(id, userId);
    }

    @DeleteMapping("/{userId}/votes/{id}")
    public void delete(@PathVariable int userId, @PathVariable int id) {
        super.delete(id, userId);
    }
}