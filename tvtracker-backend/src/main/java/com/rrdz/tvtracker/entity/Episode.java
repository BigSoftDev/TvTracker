package com.rrdz.tvtracker.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "episodes")
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "series_id", nullable = false)
    private Show show;

    @Column(name = "season_number", nullable = false)
    private Integer seasonNumber;

    @Column(name = "episode_number", nullable = false)
    private Integer episodeNumber;

    private String name;

    @Column(name = "air_date")
    private LocalDate airDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Show getShow() {
        return this.show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public Integer getSeasonNumber() {
        return this.seasonNumber;
    }

    public void setSeasonNumber(Integer seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public Integer getEpisodeNumber() {
        return this.episodeNumber;
    }

    public void setEpisodeNumber(Integer episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getAirDate() {
        return this.airDate;
    }

    public void setAirDate(LocalDate airDate) {
        this.airDate = airDate;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}