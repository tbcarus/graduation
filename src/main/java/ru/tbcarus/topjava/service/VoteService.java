package ru.tbcarus.topjava.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.tbcarus.topjava.model.Vote;
import ru.tbcarus.topjava.repository.datajpa.RestaurantRepository;
import ru.tbcarus.topjava.repository.datajpa.UserRepository;
import ru.tbcarus.topjava.repository.datajpa.VoteRepository;
import ru.tbcarus.topjava.util.DateTimeUtil;
import ru.tbcarus.topjava.util.ValidationUtil;

import java.time.LocalTime;
import java.util.List;

@Service
public class VoteService {
    private static final Sort SORT_DATE_TIME = Sort.by(Sort.Direction.ASC, "dateTime");

    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    public VoteService(VoteRepository voteRepository, UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public Vote get(int id, int userId) {
        return ValidationUtil.checkNotFoundWithId(voteRepository.findById(id).orElse(null), id);
    }

    public void delete(int id, int userId) {
        ValidationUtil.checkNotFoundWithId(voteRepository.delete(id, userId) != 0, id);
    }

    public List<Vote> getAll() {
        return voteRepository.getAll();
    }

    public List<Vote> getAllByUserId(int userId) {
        return voteRepository.getAllByUserId(userId);
    }

    public Vote create(Vote vote, int userId, int restaurantId) {
        return save(vote, userId, restaurantId);
    }

    public void update(Vote vote, int userId, int restaurantId) {
        Vote v = save(vote, userId, restaurantId);
        ValidationUtil.checkNotFoundWithId(v, vote.id());
    }

    private Vote save(Vote vote, int userId, int restaurantId) {
        Assert.notNull(vote, "tbca: vote mest be not null");
        if (!vote.isNew() && voteRepository.findById(vote.getId()).get() == null) {
            return null;
        }
        Vote currentVote = voteRepository.getByDate(vote.getDate());
        if (currentVote != null && LocalTime.now().isBefore(DateTimeUtil.timeForRevote)) {
            voteRepository.update(restaurantRepository.findById(restaurantId).get(), currentVote.id());
            return null;
        }
        vote.setUser(userRepository.findById(userId).get());
        vote.setRestaurant(restaurantRepository.findById(restaurantId).get());
        return voteRepository.save(vote);
    }
}
