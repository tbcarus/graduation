package ru.tbcarus.topjava.web.dish.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.tbcarus.topjava.model.Dish;
import ru.tbcarus.topjava.web.dish.AbstractDishController;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = AdminRestDishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestDishController extends AbstractDishController {

    public static final String REST_URL = "/rest/admin/restaurants";

    @GetMapping("/{restId}/dishes/{id}")
    public Dish get(@PathVariable int id, @PathVariable int restId) {
        return super.get(id);
    }

    @Override
    @GetMapping("/dishes")
    public List<Dish> getAllToday() {
        return super.getAllToday();
    }

    @GetMapping("/dishes/by-date")
    public List<Dish> getAllByDate (@RequestParam String date) {
        return super.getAllByDate(LocalDate.parse(date));
    }

    @Override
    @GetMapping("/{restId}/dishes")
    public List<Dish> getAllByRestaurantId(@PathVariable int restId) {
        return super.getAllByRestaurantId(restId);
    }

    @Override
    @GetMapping("/{restId}/dishes/today")
    public List<Dish> getAllByRestaurantIdToday(@PathVariable int restId) {
        return super.getAllByRestaurantIdToday(restId);
    }

    @GetMapping("/{restId}/dishes/by-date")
    public List<Dish> getAllByRestaurantIdAndDate (@PathVariable int restId, @RequestParam String date) {
        return super.getAllByRestaurantIdAndDate(restId, LocalDate.parse(date));
    }

    @PostMapping(value = "/{restId}/dishes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createRest(@RequestBody Dish dish, @PathVariable int restId) {
        Dish created = super.create(dish, restId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/dishes/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
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