package ru.tbcarus.topjava.web.json;

import org.junit.jupiter.api.Test;
import ru.tbcarus.topjava.model.Vote;

import java.util.List;

import static ru.tbcarus.topjava.VoteTestData.*;

class JsonUtilTest {

    @Test
    void readWriteValue() {
        String json = JsonUtil.writeValue(adminVote1);
        System.out.println(json);
        Vote vote = JsonUtil.readValue(json, Vote.class);
        VOTE_MATCHER.assertMatch(vote, adminVote1);
    }

    @Test
    void readWriteValues() {
        String json = JsonUtil.writeValue(allVotes);
        System.out.println(json);
        List<Vote> votes = JsonUtil.readValues(json, Vote.class);
        VOTE_MATCHER.assertMatch(votes, allVotes);
    }
}