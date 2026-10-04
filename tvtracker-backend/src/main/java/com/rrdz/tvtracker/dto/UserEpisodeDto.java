package com.rrdz.tvtracker.dto;

import com.rrdz.tvtracker.entity.Episode;

public class UserEpisodeDto {

    private Episode episode;
    private boolean watched;

    public UserEpisodeDto() {
    }

    public UserEpisodeDto(Episode episode, boolean watched) {
        this.episode = episode;
        this.watched = watched;
    }

    public Episode getEpisode() {
        return episode;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
    }

    public boolean isWatched() {
        return watched;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }
}