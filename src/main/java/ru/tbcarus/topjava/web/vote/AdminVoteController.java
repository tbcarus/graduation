package ru.tbcarus.topjava.web.vote;

import org.springframework.stereotype.Controller;
import ru.tbcarus.topjava.model.Vote;

import java.util.List;

import static ru.tbcarus.topjava.web.SecurityUtil.authUserId;

@Controller
public class AdminVoteController extends AbstractVoteController {
    public Vote get(int id) {
        return super.get(id, authUserId());
    }

    public List<Vote> getAll() {
        return super.getAll();
    }

    public List<Vote> getAllByUser(int userId) {
        return super.getAllByUserId(userId);
    }

    public void delete(int id) {
        super.delete(id, authUserId());
    }

    public void update(Vote vote, int id, int restaurantId) {
        super.update(vote, id, authUserId(), restaurantId);
    }
}