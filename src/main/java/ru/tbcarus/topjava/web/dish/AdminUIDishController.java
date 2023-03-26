package ru.tbcarus.topjava.web.dish;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.tbcarus.topjava.model.Dish;
import springfox.documentation.annotations.ApiIgnore;

import java.time.LocalDate;
import java.util.List;

@ApiIgnore
@RestController
@RequestMapping(value = "ui/admin/dishes", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminUIDishController extends AbstractDishController {

    @GetMapping("/dishes/by-date")
    public List<Dish> getAllByDate(@RequestParam String date) {
        return super.getAllByDate(LocalDate.parse(date));
    }

    //OK
    @Override
    @GetMapping()
    public List<Dish> getAllByRestaurantId(@RequestParam int restaurantId) {
        return super.getAllByRestaurantId(restaurantId);
    }

    @Override
    @GetMapping("/{id}")
    public Dish get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @Override
    @GetMapping("/{restId}/dishes")
    public List<Dish> getAllByRestaurantIdToday(@PathVariable int restId) {
        return super.getAllByRestaurantIdToday(restId);
    }

    @GetMapping("/{restId}/dishes/by-date")
    public List<Dish> getAllByRestaurantIdAndDate(@PathVariable int restId, @RequestParam String date) {
        return super.getAllByRestaurantIdAndDate(restId, LocalDate.parse(date));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createOrUpdate(@RequestParam(required = false) String id,
                                                 @RequestParam String name,
                                                 @RequestParam int price,
                                                 @RequestParam(required = false) String inputDate,
                                                 @RequestParam(required = false) int restaurantId) {
        LocalDate dishDate = LocalDate.parse(inputDate);
        Dish dish = new Dish(name, price, dishDate);
        if (id.isEmpty()) {
            super.create(dish, restaurantId);
        } else {
            int dishId = Integer.parseInt(id);
            dish.setId(dishId);
            super.update(dish, restaurantId, dishId);
        }
    }

    @Override
    @PutMapping(value = "/{restId}/dishes/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Dish dish, @PathVariable int restId, @PathVariable int id) {
        super.update(dish, restId, id);
    }
}