package ru.tbcarus.topjava.web;

import org.slf4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import ru.tbcarus.topjava.model.Vote;
import ru.tbcarus.topjava.util.DateTimeUtil;
import ru.tbcarus.topjava.util.VoteUtils;
import ru.tbcarus.topjava.web.restaurant.ProfileRestaurantController;
import ru.tbcarus.topjava.web.user.ProfileUserController;
import ru.tbcarus.topjava.web.vote.ProfileVoteController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class VoteServlet extends HttpServlet {
    private static final Logger log = getLogger(VoteServlet.class);

    private ProfileVoteController voteController;
    private ProfileRestaurantController restaurantController;

    @Override
    public void init() {
        WebApplicationContext springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        voteController = springContext.getBean(ProfileVoteController.class);
        restaurantController = springContext.getBean(ProfileRestaurantController.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
//        int userId = Integer.parseInt(request.getParameter("id"));
//        SecurityUtil.setAuthUserId(userId);
        LocalDate date = LocalDate.now();
        int restaurantId = Integer.parseInt(Objects.requireNonNullElse(request.getParameter("restaurant"), "0"));
        Vote vote = new Vote(date);
        if (StringUtils.hasLength(request.getParameter("id"))) {
            voteController.update(vote, getId(request), restaurantId);
        } else {
            voteController.create(vote, restaurantId);
        }
        response.sendRedirect("votes");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("forward to votes");
        String action = request.getParameter("action");
        switch (action == null ? "all" : action) {
            case "vote": {
                int userId = SecurityUtil.authUserId();
                int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));
                voteController.create(new Vote(DateTimeUtil.today().toLocalDate()), restaurantId);
                response.sendRedirect("/vote");
                return;
            }
            case "create", "update": {
                Vote vote = "create".equals(action) ? new Vote(LocalDate.now()) : voteController.get(getId(request));
                request.setAttribute("vote", vote);
                request.setAttribute("restaurants", restaurantController.getAll());
                request.getRequestDispatcher("/voteForm.jsp").forward(request, response);
                return;
            }
            case "delete": {
                voteController.delete(getId(request));
            }
        }

        List<Vote> votes = voteController.getAll();
        request.setAttribute("votes", votes);
        request.setAttribute("votesTo", VoteUtils.getTos(votes));
        request.getRequestDispatcher("/votes.jsp").forward(request, response);
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
