package ru.tbcarus.topjava.web;

import org.slf4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.tbcarus.topjava.model.Restaurant;
import ru.tbcarus.topjava.web.restaurant.ProfileRestaurantController;
import ru.tbcarus.topjava.web.vote.ProfileVoteController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class RestaurantServlet extends HttpServlet {
    private static final Logger log = getLogger(RestaurantServlet.class);

    private ClassPathXmlApplicationContext springContext;
    private ProfileRestaurantController restaurantController;
    private ProfileVoteController voteController;

    @Override
    public void init() {
        springContext = new ClassPathXmlApplicationContext(new String[]{"spring/spring-app.xml", "spring/spring-db.xml"}, false);
//       springContext.setConfigLocations("spring/spring-app.xml", "spring/spring-db.xml");
        springContext.getEnvironment();
        springContext.refresh();
        restaurantController = springContext.getBean(ProfileRestaurantController.class);
        voteController = springContext.getBean(ProfileVoteController.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int userId = Integer.parseInt(request.getParameter("userId"));
//        SecurityUtil.setAuthUserId(userId);
        response.sendRedirect("restaurants");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("forward to restaurants");

        List<Restaurant> restaurants = restaurantController.getAll();
        request.setAttribute("restaurants", restaurants);
        request.getRequestDispatcher("/restaurants.jsp").forward(request, response);
    }
}
