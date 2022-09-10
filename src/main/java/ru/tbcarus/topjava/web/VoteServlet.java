package ru.tbcarus.topjava.web;

import org.slf4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.tbcarus.topjava.model.Vote;
import ru.tbcarus.topjava.util.DateTimeUtil;
import ru.tbcarus.topjava.web.restaurant.ProfileRestaurantController;
import ru.tbcarus.topjava.web.user.ProfileUserController;
import ru.tbcarus.topjava.web.vote.ProfileVoteController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class VoteServlet extends HttpServlet {
    private static final Logger log = getLogger(VoteServlet.class);

    private ClassPathXmlApplicationContext springContext;
    private ProfileVoteController voteController;

    @Override
    public void init() {
        springContext = new ClassPathXmlApplicationContext(new String[]{"spring/spring-app.xml", "spring/spring-db.xml"}, false);
//       springContext.setConfigLocations("spring/spring-app.xml", "spring/spring-db.xml");
        springContext.getEnvironment();
        springContext.refresh();
        voteController = springContext.getBean(ProfileVoteController.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        SecurityUtil.setAuthUserId(userId);
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
                voteController.create(new Vote(DateTimeUtil.today().toLocalDate()), userId, restaurantId);
                response.sendRedirect("/vote");
                return;
            }
        }

        List<Vote> votes = voteController.getAll();
        request.setAttribute("votes", votes);
        request.getRequestDispatcher("/votes.jsp").forward(request, response);
    }
}
