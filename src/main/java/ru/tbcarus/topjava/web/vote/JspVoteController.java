package ru.tbcarus.topjava.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.tbcarus.topjava.model.Restaurant;
import ru.tbcarus.topjava.model.Vote;
import ru.tbcarus.topjava.service.RestaurantService;
import ru.tbcarus.topjava.service.VoteService;
import ru.tbcarus.topjava.util.VoteUtils;
import ru.tbcarus.topjava.web.SecurityUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static ru.tbcarus.topjava.web.SecurityUtil.authUserId;

@Controller
@RequestMapping("/votes")
public class JspVoteController {
    private static final Logger log = LoggerFactory.getLogger(JspVoteController.class);

    @Autowired
    private VoteService voteService;
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping()
    public String getVotes(Model model) {
        log.info("votes");
        List<Vote> votes = voteService.getAllByUserId(authUserId());
        List<Restaurant> restaurants = restaurantService.getAll();
        model.addAttribute("votesTo", VoteUtils.getTos(votes));
        model.addAttribute("restaurants", restaurants);
        return "votes";
    }

    @PostMapping()
    public String setUser(HttpServletRequest request) {
        String action = request.getParameter("button");
        if ("toVotes".equals(action)) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            log.info("setUser {} in votes", userId);
            SecurityUtil.setAuthUserId(userId);
            return "redirect:votes";
        }
        return "redirect:qwerty";
    }

    @PostMapping("/create-or-update")
    public String createOrUpdate(HttpServletRequest request) {
        LocalDate date = LocalDate.now();
        String voteIdStr = request.getParameter("voteId");
        int voteId = Integer.parseInt(Objects.isNull(voteIdStr) || voteIdStr.isEmpty() ? "0" : voteIdStr);
        String restaurantIdStr = request.getParameter("restaurantId");
        int restaurantId = Integer.parseInt(restaurantIdStr);
        Vote vote = new Vote(date);
        if (StringUtils.hasLength(voteIdStr)) {
            voteService.update(vote, authUserId(), restaurantId);
        } else {
            voteService.create(vote, authUserId(), restaurantId);
        }
        return "redirect:/votes";
    }

    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        voteService.delete(getId(request), authUserId());
        return "redirect:/votes";
    }

    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        Vote vote = voteService.get(getId(request), authUserId());
        model.addAttribute("vote", vote);
        model.addAttribute("restaurants", restaurantService.getAll());
        return "voteForm";
    }

    @GetMapping("/create")
    public String create(HttpServletRequest request, Model model) {
        Vote vote = new Vote(LocalDate.now());
        model.addAttribute("vote", vote);
        model.addAttribute("restaurants", restaurantService.getAll());
        return "voteForm";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
