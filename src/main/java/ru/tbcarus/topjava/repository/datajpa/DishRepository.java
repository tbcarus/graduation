package ru.tbcarus.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.tbcarus.topjava.model.Dish;
import ru.tbcarus.topjava.model.Restaurant;
import ru.tbcarus.topjava.util.DateTimeUtil;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface DishRepository extends JpaRepository<Dish, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Dish d WHERE d.id = :id")
    int delete(@Param("id") int id);

    @Query("SELECT d FROM Dish d WHERE d.id = :id AND d.restaurant.id = :restaurantId")
    Dish get(@Param("id") int id, @Param("restaurantId") int restaurantId);

    @Query("SELECT d FROM Dish d ORDER BY d.restaurant.name, d.name DESC")
    List<Dish> getAll();

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id = :restaurantId ORDER BY d.name DESC")
    List<Dish> getAllByRestaurantId(@Param("restaurantId") int restaurantId);

    @Query("SELECT d FROM Dish d WHERE d.inputDate = :date ORDER BY d.restaurant.name, d.name DESC")
    List<Dish> getAllByDate(@Param("date") LocalDate date);

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id = :restaurantId AND d.inputDate = :inputDate ORDER BY d.name DESC")
    List<Dish> getAllByRestaurantIdAndDate(@Param("restaurantId") int restaurantId, @Param("inputDate") LocalDate inputDate);

//    List<Dish> getAllByRestaurantId(int restaurantId);
//    List<Dish> getAllByInputDateAndRestaurantId(LocalDate inputDate, int restaurantId);
}
