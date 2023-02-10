package ru.tbcarus.topjava.to;

import ru.tbcarus.topjava.model.Restaurant;
import ru.tbcarus.topjava.model.User;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class VoteTo {
    private final int id;
    private final LocalDate date;
    private final User user;
    private final Restaurant restaurant;
    private final boolean canRevote;

    public VoteTo(LocalDate date, boolean canRevote) {
        this.id = 0;
        this.date = date;
        this.user = null;
        this.restaurant = null;
        this.canRevote = canRevote;
    }

    public VoteTo(int id, LocalDate date, User user, Restaurant restaurant, boolean canRevote) {
        this.id = id;
        this.date = date;
        this.user = user;
        this.restaurant = restaurant;
        this.canRevote = canRevote;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public boolean isCanRevote() {
        return canRevote;
    }
}
