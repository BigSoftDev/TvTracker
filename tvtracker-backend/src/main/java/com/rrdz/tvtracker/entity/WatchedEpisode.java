package com.rrdz.tvtracker.entity;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "watched_episodes")
public class WatchedEpisode {

    @EmbeddedId
    private WatchedEpisodeId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("episodeId")
    @JoinColumn(name = "episode_id")
    private Episode episode;

    @Column(name = "watched_at")
    private LocalDateTime watchedAt;


    public WatchedEpisodeId getId() {
        return this.id;
    }

    public void setId(WatchedEpisodeId id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Episode getEpisode() {
        return this.episode;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
    }

    public LocalDateTime getWatchedAt() {
        return this.watchedAt;
    }

    public void setWatchedAt(LocalDateTime watchedAt) {
        this.watchedAt = watchedAt;
    }
    
}