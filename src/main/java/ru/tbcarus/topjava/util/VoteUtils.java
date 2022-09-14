package ru.tbcarus.topjava.util;

import ru.tbcarus.topjava.model.Vote;
import ru.tbcarus.topjava.to.VoteTo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.util.List;

public class VoteUtils {

    public static final LocalTime timeForRevote = LocalTime.of(23, 59);

    public static List<VoteTo> getTos(List<Vote> votes) {
        return votes
                .stream()
                .map(vote -> createTo(vote, LocalTime.now().isBefore(timeForRevote)))
                .toList();
    }

    private static VoteTo createTo(Vote vote, boolean canRevote) {
        return new VoteTo(vote.getId(),
                vote.getDate(),
                vote.getUser(),
                vote.getRestaurant(),
                canRevote);
    }
}
