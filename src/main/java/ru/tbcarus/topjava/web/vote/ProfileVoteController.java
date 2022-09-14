package ru.tbcarus.topjava.web.vote;

import org.springframework.stereotype.Controller;
import ru.tbcarus.topjava.model.Vote;
import ru.tbcarus.topjava.to.VoteTo;
import ru.tbcarus.topjava.util.ValidationUtil;
import ru.tbcarus.topjava.util.VoteUtils;

import java.util.List;

import static ru.tbcarus.topjava.web.SecurityUtil.authUserId;

@Controller
public class ProfileVoteController extends AbstractVoteController {
    public Vote get(int id) {
        return super.get(id, authUserId());
    }

    public List<Vote> getAll() {
        return super.getAllByUserId(authUserId());
    }

    public List<VoteTo> getAllTo() {
        return VoteUtils.getTos(getAll());
    }

    public void delete(int id) {
        super.delete(id, authUserId());
    }

//    public void update(Vote vote, int id, int restaurantId) {
//        ValidationUtil.assureIdConsistent(vote, id);
//        super.update(vote, id, restaurantId);
//    }
}