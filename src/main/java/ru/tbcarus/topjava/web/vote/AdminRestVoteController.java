package ru.tbcarus.topjava.web.vote;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.tbcarus.topjava.model.Vote;
import ru.tbcarus.topjava.to.VoteTo;
import ru.tbcarus.topjava.util.VoteUtils;

import java.util.List;

import static ru.tbcarus.topjava.web.SecurityUtil.authUserId;

@RestController
@RequestMapping(value = AdminRestVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestVoteController extends AbstractVoteController {

    public static final String REST_URL = "/rest/admin/votes";

    @Override
    @GetMapping()
    public List<Vote> getAll() { return super.getAll();}

    @GetMapping("/{id}")
    public Vote get(@PathVariable int id) {
        return super.get(id, authUserId());
    }

    @GetMapping
    public List<VoteTo> getAllTo() {
        return VoteUtils.getTos(super.getAllByUserId(authUserId()));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id, @RequestParam int userId) {
        super.delete(id, userId);
    }
}