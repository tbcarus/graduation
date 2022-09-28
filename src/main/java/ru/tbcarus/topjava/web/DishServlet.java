package ru.tbcarus.topjava.web;

import org.slf4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.tbcarus.topjava.model.Dish;
import ru.tbcarus.topjava.model.Restaurant;
import ru.tbcarus.topjava.web.Dish.AdminDishController;
import ru.tbcarus.topjava.web.Dish.ProfileDishController;
import ru.tbcarus.topjava.web.restaurant.ProfileRestaurantController;
import ru.tbcarus.topjava.web.vote.ProfileVoteController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class DishServlet extends HttpServlet {
    private static final Logger log = getLogger(DishServlet.class);

    private ClassPathXmlApplicationContext springContext;
    private ProfileRestaurantController restaurantController;
    private ProfileVoteController voteController;
    private ProfileDishController dishController;

    @Override
    public void init() {
        springContext = new ClassPathXmlApplicationContext(new String[]{"spring/spring-app.xml", "spring/spring-db.xml"}, false);
//       springContext.setConfigLocations("spring/spring-app.xml", "spring/spring-db.xml");
        springContext.getEnvironment();
        springContext.refresh();
        restaurantController = springContext.getBean(ProfileRestaurantController.class);
        voteController = springContext.getBean(ProfileVoteController.class);
        dishController = springContext.getBean(ProfileDishController.class);
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
//        response.sendRedirect("dishes");
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("forward to dishes");

        int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));
        List<Dish> dishes = dishController.getAllByRestaurantIdToday(restaurantId);
        request.setAttribute("dishes", dishes);
        Restaurant restaurant = restaurantController.get(restaurantId);
        request.setAttribute("restaurant", restaurant);
        request.getRequestDispatcher("/dishes.jsp").forward(request, response);
    }
}
