package ru.tbcarus.topjava.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NamedQueries({
        @NamedQuery(name = Vote.DELETE, query = "DELETE FROM Vote v WHERE v.id=:id AND v.user.id=:userId"),
        @NamedQuery(name = Vote.ALL_SORTED, query = "SELECT v FROM Vote v " +
                "LEFT JOIN FETCH v.user " +
                "LEFT JOIN FETCH v.restaurant" +
                " ORDER BY v.dateTime, v.user.name, v.user.email DESC"),
        @NamedQuery(name = Vote.ALL_TODAY, query = "SELECT v FROM Vote v " +
                "LEFT JOIN FETCH v.user " +
                "LEFT JOIN FETCH v.restaurant WHERE v.dateTime>=:today " +
                " ORDER BY v.user.name, v.user.email DESC"),
        @NamedQuery(name = Vote.ALL_BY_USER, query = "SELECT v FROM Vote v " +
                "LEFT JOIN FETCH v.user " +
                "WHERE v.user.id=:userId " +
                "ORDER BY v.dateTime DESC"),
        @NamedQuery(name = Vote.GET, query = "SELECT v FROM Vote v " +
                "LEFT JOIN FETCH v.user " +
                "WHERE v.user.id=:userId AND v.dateTime>=:today"),
        @NamedQuery(name = Vote.ALL_BETWEEN, query = "SELECT v FROM Vote v " +
                "WHERE v.dateTime between :startDate AND :andDate " +
                "ORDER BY v.dateTime, v.user.name, v.user.email DESC"),
        @NamedQuery(name = Vote.ALL_BETWEEN_BY_USER, query = "SELECT v FROM Vote v LEFT JOIN FETCH v.user " +
                "WHERE v.user.id=:userId AND v.dateTime between :startDate AND :andDate " +
                "ORDER BY v.dateTime DESC"),

})
@Entity
@Table(name = "vote")
public class Vote extends AbstractBaseEntity{

    public static final String DELETE = "Vote.delete";
    public static final String ALL_SORTED = "Vote.getAllSorted";
    public static final String ALL_TODAY = "Vote.getAllToday";
    public static final String ALL_BY_USER = "Vote.getAllByUser";
    public static final String GET = "Vote.get";
    public static final String ALL_BETWEEN = "Vote.getBetween";
    public static final String ALL_BETWEEN_BY_USER = "Vote.getBetweenByUser";

    @Column(name = "date_time", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    private Restaurant restaurant;

    public Vote() {
    }

    public Vote(LocalDateTime dateTime, User user, Restaurant restaurant) {
        this(null, dateTime, user, restaurant);
    }

    public Vote(Integer id, LocalDateTime dateTime, User user, Restaurant restaurant) {
        super(id);
        this.dateTime = dateTime;
        this.user = user;
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", user=" + user +
                ", restaurant=" + restaurant +
                '}';
    }
}
