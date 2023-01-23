package ru.tbcarus.topjava.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.tbcarus.topjava.model.Restaurant;
import ru.tbcarus.topjava.model.Vote;
import ru.tbcarus.topjava.repository.datajpa.RestaurantRepository;
import ru.tbcarus.topjava.repository.datajpa.UserRepository;
import ru.tbcarus.topjava.repository.datajpa.VoteRepository;
import ru.tbcarus.topjava.util.DateTimeUtil;
import ru.tbcarus.topjava.util.ValidationUtil;
import ru.tbcarus.topjava.util.VoteUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
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
        Vote vote = voteRepository.findById(id).orElse(null);
        if (vote != null && vote.getUser().getId() != userId) {
            vote = null;
        }
        return ValidationUtil.checkNotFoundWithId(vote, id);
    }

    public Vote getToday(int userId) {
        return voteRepository.getByDateAndUserId(DateTimeUtil.today().toLocalDate(), userId);
    }

    public List<Vote> getAllToday() {
        return voteRepository.getAllByDate(DateTimeUtil.today().toLocalDate());
    }

    public void delete(int id, int userId) {
        ValidationUtil.checkNotFoundWithId(voteRepository.delete(id, userId) != 0, id);
    }

    public List<Vote> getAll() {
        return voteRepository.getAll();
    }

    public Restaurant getWinnerToday() {
        List<Vote> votes = voteRepository.getAllByDate(DateTimeUtil.today().toLocalDate());
        List<Restaurant> restaurants = restaurantRepository.getAll();
        if (votes.size() == 0) {
            return null;
        }
        List<Integer> votesCounter = new ArrayList<>();
        int currentVotesCount = 0;
        String currentRestaurantName = votes.get(0).getRestaurant().getName();

        for (Vote v : votes) {
            if (!currentRestaurantName.equals(v.getRestaurant().getName())) {
                votesCounter.add(currentVotesCount);
                currentRestaurantName = v.getRestaurant().getName();
                currentVotesCount = 0;
            }
            currentVotesCount++;
        }
        votesCounter.add(currentVotesCount);

        int max = Collections.max(votesCounter);
        int maxIndex = votesCounter.indexOf(max);
        votesCounter.remove(maxIndex);
        Restaurant winnerRestaurant = restaurants.remove(maxIndex);
        if (votesCounter.contains(max)) {
            return null;
        }
        return winnerRestaurant;
    }


    public List<Vote> getAllByUserId(int userId) {
        return voteRepository.getAllByUserId(userId);
    }

    public Vote create(Vote vote, int userId, int restaurantId) {
        if (voteRepository.getByDateAndUserId(LocalDate.now(), userId) == null) {
            vote.setUser(userRepository.findById(userId).get());
            vote.setRestaurant(restaurantRepository.findById(restaurantId).get());
            return voteRepository.save(vote);
        } else {
            update(vote, userId, restaurantId);
        }
        return null;

//        return save(vote, userId, restaurantId);
    }

    public void update(Vote vote, int userId, int restaurantId) {
        Assert.notNull(vote, "vote must be not null");
        Vote currentVote = voteRepository.getByDateAndUserId(vote.getDate(), userId);
        voteRepository.update(restaurantRepository.findById(restaurantId).get(), currentVote.id());

//        Vote v = save(vote, userId, restaurantId);
//        ValidationUtil.checkNotFoundWithId(v, vote.id());
    }

    private Vote save(Vote vote, int userId, int restaurantId) {
        Assert.notNull(vote, "tbca: vote mest be not null");
        if (!vote.isNew() && voteRepository.findById(vote.getId()).get() == null) {
            return null;
        }
        Vote currentVote = voteRepository.getByDateAndUserId(vote.getDate(), userId);
        if (currentVote != null && LocalTime.now().isBefore(VoteUtils.timeForRevote)) {
            voteRepository.update(restaurantRepository.findById(restaurantId).get(), currentVote.id());
            return null;
        }
        vote.setUser(userRepository.findById(userId).get());
        vote.setRestaurant(restaurantRepository.findById(restaurantId).get());
        return voteRepository.save(vote);
    }
}
