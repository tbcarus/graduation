package ru.tbcarus.topjava.web.dish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.tbcarus.topjava.model.Dish;
import ru.tbcarus.topjava.model.Restaurant;
import ru.tbcarus.topjava.model.Vote;
import ru.tbcarus.topjava.service.DishService;
import ru.tbcarus.topjava.service.RestaurantService;
import ru.tbcarus.topjava.web.SecurityUtil;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@ApiIgnore
@Controller
@RequestMapping("/dishes")
public class JspDishController {
    private static final Logger log = LoggerFactory.getLogger(JspDishController.class);

    @Autowired
    private DishService dishService;
    @Autowired
    private RestaurantService restaurantService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public String getDishes(Model model, HttpServletRequest request) {
        log.info("dishes");
        int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));
//        List<Dish> dishes = dishService.getAllByRestaurantId(restaurantId);
//        model.addAttribute("dishes", dishes);
        Restaurant restaurant = restaurantService.get(restaurantId);
        model.addAttribute("restaurant", restaurant);
//        List<Restaurant> restaurants = restaurantService.getAll();
//        model.addAttribute("restaurants", restaurants);
        return "dishes";
    }

    @PostMapping()
    public String setUser(HttpServletRequest request) {
//        int userId = Integer.parseInt(request.getParameter("userId"));
//        log.info("setUser {} in dishes", userId);
//        SecurityUtil.setAuthUserId(userId);
        return "redirect:dishes?restaurantId="+Integer.parseInt(request.getParameter("restaurantId"));
    }

    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        dishService.delete(getId(request));
        return "redirect:/dishes?restaurantId=" + request.getParameter("restaurantId");
    }

    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        Dish dish = dishService.get(getId(request));
        model.addAttribute("dish", dish);
        return "dishForm";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Dish dish = new Dish();
        dish.setInputDate(LocalDate.now());
        List<Restaurant> restaurants = restaurantService.getAll();
        model.addAttribute("dish", dish);
        model.addAttribute("restaurants", restaurants);
        return "dishForm";
    }

    @PostMapping("/create-or-update")
    public String createOrUpdate(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        String idParam = request.getParameter("id");
        String restaurantIdParam = request.getParameter("restaurantId");
        int id = Integer.parseInt(idParam.isEmpty() ? "0" : idParam);
        int restaurantId = Integer.parseInt(restaurantIdParam);
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        LocalDate inputDate = LocalDate.parse(request.getParameter("date"));
        if (StringUtils.hasLength(idParam)) {
            Dish dish = dishService.get(id);
            dish.setName(name);
            dish.setPrice(price);
            dish.setInputDate(inputDate);
            dishService.update(dish, restaurantId);
        } else {
            Dish dish = new Dish(name, price, inputDate);
//            dish.setRestaurant(restaurantService.get(restaurantId));
            dishService.create(dish, restaurantId);
        }
        return "redirect:/dishes?restaurantId=" + restaurantIdParam;
    }



    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
