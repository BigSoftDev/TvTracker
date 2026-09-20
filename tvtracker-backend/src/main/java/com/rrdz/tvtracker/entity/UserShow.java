package com.rrdz.tvtracker.entity;


import com.rrdz.tvtracker.model.ShowStatus;

import jakarta.persistence.*;

@Entity
@Table(
    name = "user_shows",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_user_show",
            columnNames = {"user_id", "show_id"}
        )
    }
)
public class UserShow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;

    @Column(nullable = false)
    private boolean favorite = false;

    @Column
    private Integer rating;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShowStatus status = ShowStatus.QUEUED;

    public UserShow() {
    }

    public UserShow(User user, Show show) {
        this.user = user;
        this.show = show;
        this.status = ShowStatus.QUEUED;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public ShowStatus getStatus() {
        return status;
    }

    public void setStatus(ShowStatus status) {
        this.status = status;
    }
}