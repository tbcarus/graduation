package ru.tbcarus.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.tbcarus.topjava.model.Role;
import ru.tbcarus.topjava.model.User;
import ru.tbcarus.topjava.web.user.rest.AdminRestUserController;

import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 automatic resource management (ARM)
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/inmemory.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestUserController adminUserController = appCtx.getBean(AdminRestUserController.class);
            adminUserController.create(new User(null, "userName", "email@mail.ru", "password", Role.ADMIN));
            System.out.println();

//            MealRestController mealController = appCtx.getBean(MealRestController.class);
//            List<MealTo> filteredMealsWithExcess =
//                    mealController.getBetween(
//                            LocalDate.of(2020, Month.JANUARY, 30), LocalTime.of(7, 0),
//                            LocalDate.of(2020, Month.JANUARY, 31), LocalTime.of(11, 0));
//            filteredMealsWithExcess.forEach(System.out::println);
//            System.out.println();
//            System.out.println(mealController.getBetween(null, null, null, null));
        }
    }
}
