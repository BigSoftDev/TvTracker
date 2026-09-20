package com.rrdz.tvtracker.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "user_episodes",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_user_episode",
            columnNames = {"user_show_id", "episode_id"}
        )
    }
)
public class UserEpisode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_show_id", nullable = false)
    private UserShow userShow;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "episode_id", nullable = false)
    private Episode episode;

    @Column(name = "watched_at")
    private LocalDateTime watchedAt;

    public UserEpisode() {
    }

    public UserEpisode(UserShow userShow, Episode episode) {
        this.userShow = userShow;
        this.episode = episode;
        this.watchedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public UserShow getUserShow() {
        return userShow;
    }

    public void setUserShow(UserShow userShow) {
        this.userShow = userShow;
    }

    public Episode getEpisode() {
        return episode;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
    }

    public LocalDateTime getWatchedAt() {
        return watchedAt;
    }

    public void setWatchedAt(LocalDateTime watchedAt) {
        this.watchedAt = watchedAt;
    }
}