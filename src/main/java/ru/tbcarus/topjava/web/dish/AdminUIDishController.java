package ru.tbcarus.topjava.web.dish;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.tbcarus.topjava.model.Dish;
import ru.tbcarus.topjava.util.DateTimeUtil;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping(value = "ui/admin/dishes", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminUIDishController extends AbstractDishController {

    @Override
    @GetMapping("/dishes/{id}")
    public Dish get(@PathVariable int id) {
        return super.get(id);
    }

//    @Override
//    @GetMapping()
//    public List<Dish> getAllToday() {
//        return super.getAllToday();
//    }

    @GetMapping("/dishes/by-date")
    public List<Dish> getAllByDate (@RequestParam String date) {
        return super.getAllByDate(LocalDate.parse(date));
    }

    @Override
    @GetMapping()
    public List<Dish> getAllByRestaurantId(@RequestParam int restaurantId) {
        return super.getAllByRestaurantId(restaurantId);
    }

    @Override
    @GetMapping("/{restId}/dishes")
    public List<Dish> getAllByRestaurantIdToday(@PathVariable int restId) {
        return super.getAllByRestaurantIdToday(restId);
    }

    @GetMapping("/{restId}/dishes/by-date")
    public List<Dish> getAllByRestaurantIdAndDate (@PathVariable int restId, @RequestParam String date) {
        return super.getAllByRestaurantIdAndDate(restId, LocalDate.parse(date));
    }

    //OK
    @PostMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestParam String restaurantId,
                       @RequestParam String name,
                       @RequestParam String price,
                       @RequestParam String date) {
        int dishPrice = Integer.parseInt(price);
        LocalDate dishDate;
        try {
            dishDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            dishDate = DateTimeUtil.today().toLocalDate();
        }
        Dish dish = new Dish(name, dishPrice, dishDate);
        super.create(dish, Integer.parseInt(restaurantId));
    }

    @Override
    @DeleteMapping("/{restId}/dishes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @Override
    @PutMapping(value = "/{restId}/dishes/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Dish dish, @PathVariable int restId, @PathVariable int id) {
        super.update(dish, restId, id);
    }
}