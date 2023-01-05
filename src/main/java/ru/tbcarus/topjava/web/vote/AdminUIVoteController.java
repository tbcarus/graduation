package ru.tbcarus.topjava.web.vote;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.tbcarus.topjava.model.Vote;
import ru.tbcarus.topjava.to.VoteTo;
import ru.tbcarus.topjava.util.VoteUtils;
import ru.tbcarus.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.util.List;

import static ru.tbcarus.topjava.web.SecurityUtil.authUserId;

@RestController
@RequestMapping(value = "ui/admin/votes", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminUIVoteController extends AbstractVoteController {

    @Override
    @GetMapping("/vote")
    public List<Vote> getAll() { return super.getAll();}

    @GetMapping("/{id}")
    public Vote get(@PathVariable int id) {
        return super.get(id, authUserId());
    }

    @GetMapping()
    public List<VoteTo> getAllTo() {
        return VoteUtils.getTos(super.getAllByUserId(authUserId()));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id, @RequestParam int userId) {
        super.delete(id, userId);
    }

    @PostMapping()
    public void create(@RequestParam int restaurantId) {
        Vote vote = new Vote(LocalDate.now());
        super.create(vote, SecurityUtil.authUserId(), restaurantId);
    }
}