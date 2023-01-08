package ru.tbcarus.topjava.web.vote.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.tbcarus.topjava.model.Vote;
import ru.tbcarus.topjava.to.VoteTo;
import ru.tbcarus.topjava.util.DateTimeUtil;
import ru.tbcarus.topjava.util.VoteUtils;
import ru.tbcarus.topjava.web.SecurityUtil;
import ru.tbcarus.topjava.web.vote.AbstractVoteController;

import java.net.URI;
import java.util.List;

import static ru.tbcarus.topjava.web.SecurityUtil.authUserId;

@RestController
@RequestMapping(value = ProfileRestVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileRestVoteController extends AbstractVoteController {

    public static final String REST_URL = "/rest/profile/votes";

    @GetMapping("/{id}")
    public Vote get(@PathVariable int id) {
        return super.get(id, authUserId());
    }

    @GetMapping
    public List<VoteTo> getAllTo() {
        return VoteUtils.getTos(super.getAllByUserId(authUserId()));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id, authUserId());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> createRest(@RequestParam int restaurantId) {
        Vote vote = new Vote(DateTimeUtil.getNow().toLocalDate());
        Vote created = super.create(vote, SecurityUtil.authUserId(), restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestParam int id, @RequestParam int restaurantId) {
        Vote vote = new Vote(DateTimeUtil.getNow().toLocalDate());
        super.update(vote, id, SecurityUtil.authUserId(), restaurantId);
    }
}