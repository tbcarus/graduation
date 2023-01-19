package ru.tbcarus.topjava.web.vote;

import org.springframework.http.HttpStatus;
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
    public List<Vote> getAll() {
        return super.getAll();
    }

    @GetMapping("/{id}")
    public Vote get(@PathVariable int id) {
        return super.get(id, authUserId());
    }

    @GetMapping("/today")
    public List<Vote> getToday() {
        return super.getToday(SecurityUtil.authUserId());
    }

    //OK
    @GetMapping()
    public List<VoteTo> getAllTo() {
        return VoteUtils.getTos(super.getAllByUserId(authUserId()));
    }

    //OK
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id, authUserId());
    }

    //OK
    @PostMapping()
    public void create(@RequestParam(required = false) String id, @RequestParam int restaurantId) {
        Vote vote = new Vote(LocalDate.now());
        if (!id.isEmpty()) {
            super.update(vote, Integer.parseInt(id), SecurityUtil.authUserId(), restaurantId);
        } else {
            super.create(vote, SecurityUtil.authUserId(), restaurantId);
        }
    }
}