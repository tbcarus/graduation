package ru.tbcarus.topjava.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.tbcarus.topjava.VoteTestData;
import ru.tbcarus.topjava.model.Vote;
import ru.tbcarus.topjava.service.VoteService;
import ru.tbcarus.topjava.util.exception.NotFoundException;
import ru.tbcarus.topjava.web.AbstractControllerTest;
import ru.tbcarus.topjava.web.SecurityUtil;
import ru.tbcarus.topjava.web.json.JsonUtil;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.tbcarus.topjava.UserTestData.admin;
import static ru.tbcarus.topjava.UserTestData.ivan;
import static ru.tbcarus.topjava.VoteTestData.*;
import static ru.tbcarus.topjava.web.SecurityUtil.authUserId;
import static ru.tbcarus.topjava.web.vote.rest.ProfileRestVoteController.REST_URL;

class ProfileRestVoteControllerTest extends AbstractControllerTest {

    @Autowired
    VoteService voteService;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/" + ADMIN_VOTE1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(adminVote1));
    }

    @Test
    void delete() throws Exception {
        SecurityUtil.setAuthUserId(ivan.id());
        perform(MockMvcRequestBuilders.delete(REST_URL + "/" + ivanVote2.id()))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> voteService.get(ivanVote2.id(), authUserId()));
    }

    @Test
    void createRest() throws Exception {
        SecurityUtil.setAuthUserId(admin.id());
        Vote newVote = VoteTestData.getNew();
        ResultActions actions = perform(MockMvcRequestBuilders.post(
                REST_URL + "/?restaurantId=" + newVote.getRestaurant().id())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newVote)))
                .andExpect(status().isCreated());
        Vote created = VOTE_MATCHER.readFromJson(actions);
        int newId = created.id();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
        VOTE_MATCHER.assertMatch(voteService.get(newId, authUserId()), newVote);
    }

    @Test
    void update() throws Exception {
        SecurityUtil.setAuthUserId(ivan.id());
        Vote updated = VoteTestData.getUpdated();
        perform(MockMvcRequestBuilders.put(
                REST_URL + "/?id=" + updated.id() +"&restaurantId=" + updated.getRestaurant().id())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        VOTE_MATCHER.assertMatch(voteService.get(updated.id(), authUserId()), updated);
    }
}