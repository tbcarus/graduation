package ru.tbcarus.topjava.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NamedQueries({
        @NamedQuery(name = Vote.DELETE, query = "DELETE FROM Vote v WHERE v.id=:id AND v.user.id=:userId"),
        @NamedQuery(name = Vote.ALL_SORTED, query = "SELECT v FROM Vote v " +
                "LEFT JOIN FETCH v.user " +
                "LEFT JOIN FETCH v.restaurant" +
                " ORDER BY v.date, v.user.name, v.user.email DESC"),
        @NamedQuery(name = Vote.ALL_TODAY, query = "SELECT v FROM Vote v " +
                "LEFT JOIN FETCH v.user " +
                "LEFT JOIN FETCH v.restaurant WHERE v.date>=:today " +
                " ORDER BY v.user.name, v.user.email DESC"),
        @NamedQuery(name = Vote.ALL_BY_USER, query = "SELECT v FROM Vote v " +
                "LEFT JOIN FETCH v.user " +
                "WHERE v.user.id=:userId " +
                "ORDER BY v.date DESC"),
        @NamedQuery(name = Vote.GET, query = "SELECT v FROM Vote v " +
                "LEFT JOIN FETCH v.user " +
                "WHERE v.user.id=:userId AND v.date>=:today"),
        @NamedQuery(name = Vote.ALL_BETWEEN, query = "SELECT v FROM Vote v " +
                "WHERE v.date between :startDate AND :andDate " +
                "ORDER BY v.date, v.user.name, v.user.email DESC"),
        @NamedQuery(name = Vote.ALL_BETWEEN_BY_USER, query = "SELECT v FROM Vote v LEFT JOIN FETCH v.user " +
                "WHERE v.user.id=:userId AND v.date between :startDate AND :andDate " +
                "ORDER BY v.date DESC"),

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

//    @Column(name = "date_time", nullable = false, columnDefinition = "timestamp default now()")
//    @NotNull
//    private LocalDateTime dateTime;

    @Column(name = "date", nullable = false, columnDefinition = "date default date(now())")
    @NotNull
    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    public Vote() {
    }

    public Vote(Vote vote) {
        this.id = vote.getId();
        this.date = vote.getDate();
        this.user = vote.getUser();
        this.restaurant = vote.getRestaurant();
    }

    public Vote(LocalDate date) {
        super(null);
        this.date = date;
    }

    public Vote(LocalDate date, User user, Restaurant restaurant) {
        this(null, date, user, restaurant);
    }

    public Vote(Integer id, LocalDate date, User user, Restaurant restaurant) {
        super(id);
        this.date = date;
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

//    public LocalDateTime getDateTime() {
//        return dateTime;
//    }
//
//    public void setDateTime(LocalDateTime dateTime) {
//        this.dateTime = dateTime;
//    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
