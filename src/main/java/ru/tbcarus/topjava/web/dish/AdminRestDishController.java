package ru.tbcarus.topjava.web.dish;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.tbcarus.topjava.model.Dish;
import ru.tbcarus.topjava.model.Restaurant;
import ru.tbcarus.topjava.util.ValidationUtil;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = AdminRestDishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestDishController extends AbstractDishController {

    public static final String REST_URL = "/rest/admin/restaurants";

    @Override
    @GetMapping("/dishes/{id}")
    public Dish get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @GetMapping("/dishes")
    public List<Dish> getAllToday() {
        return super.getAllToday();
    }

    @Override
    @GetMapping("/dishes/{localDate}")
    public List<Dish> getAllByDate (@PathVariable LocalDate localDate) {
        return super.getAllByDate(localDate);
    }

    @Override
    @GetMapping("{restId}/dishes/all")
    public List<Dish> getAllByRestaurantId(@PathVariable int restId) {
        return super.getAllByRestaurantId(restId);
    }

    @Override
    @GetMapping("/{restId}/dishes")
    public List<Dish> getAllByRestaurantIdToday(@PathVariable int restId) {
        return super.getAllByRestaurantIdToday(restId);
    }

    @Override
    @GetMapping("/{restId}/dishes/{localDate}")
    public List<Dish> getAllByRestaurantIdAndDate (@PathVariable int restId, @PathVariable LocalDate localDate) {
        return super.getAllByRestaurantIdAndDate(restId, localDate);
    }

    @PostMapping(value = "{restId}/dishes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createRest(@RequestBody Dish dish, @PathVariable int restId) {
        Dish created = super.create(dish, restId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/dishes/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @DeleteMapping("{restId}/dishes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @Override
    @PutMapping(value = "{restId}/dishes/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Dish dish, @PathVariable int restId, @PathVariable int id) {
        super.update(dish, restId, id);
    }
}