package ru.tbcarus.topjava.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@NamedQueries({
        @NamedQuery(name = Dish.DELETE, query = "DELETE FROM Dish d WHERE d.id=:id AND d.restaurant.id=:restaurantId"),
        @NamedQuery(name = Dish.BY_NAME, query = "SELECT d FROM Dish d WHERE d.name=:name AND d.restaurant.id=:restaurantId"),
        @NamedQuery(name = Dish.ALL_RESTAURANT_SORTED, query = "SELECT d FROM Dish d WHERE d.restaurant.id=:restaurantId")
})
@Entity
@Table(name = "dish")
public class Dish extends AbstractNamedEntity {

    public static final String DELETE = "Dish.delete";
    public static final String BY_NAME = "Dish.getByName";
    public static final String ALL_RESTAURANT_SORTED = "Dish.getAllRestaurantSorted";

    @Column(name = "price", nullable = false)
    @Positive
    private int price;

    @Column(name = "input_date", nullable = false, columnDefinition = "timestamp default now()", updatable = false)
    @NotNull
    private LocalDate inputDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    @NotNull
    private Restaurant restaurant;

    public Dish() {

    }

    public Dish(Dish dish) {
        this.id = dish.getId();
        this.name = dish.getName();
        this.price = dish.getPrice();
        this.inputDate = dish.getInputDate();
        this.restaurant = dish.getRestaurant();
    }

    public Dish(String name, int price, LocalDate inputDate, Restaurant restaurant) {
        this(null, name, price, inputDate, restaurant);
    }

    public Dish(Integer id, String name, int price, LocalDate inputDate, Restaurant restaurant) {
        super(id, name);
        this.price = price;
        this.inputDate = inputDate;
        this.restaurant = restaurant;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDate getInputDate() {
        return inputDate;
    }

    public void setInputDate(LocalDate inputDate) {
        this.inputDate = inputDate;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name=" + name +
                ", price=" + price +
                ", restaurant=" + restaurant +
                '}';
    }
}
