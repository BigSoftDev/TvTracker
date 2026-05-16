package com.rrdz.tvtracker.dto;

import java.time.LocalDate;

public class EpisodeDto {

    private Long episodeId;
    private Long showId;
    private String showName;
    private String episodeName;
    private int seasonNumber;
    private int episodeNumber;
    private LocalDate airDate;
    private boolean watched;

    // Constructor
    public EpisodeDto(Long episodeId, Long showId, String showName,
                      String episodeName, int seasonNumber, int episodeNumber,
                      LocalDate airDate, boolean watched) {
        this.episodeId = episodeId;
        this.showId = showId;
        this.showName = showName;
        this.episodeName = episodeName;
        this.seasonNumber = seasonNumber;
        this.episodeNumber = episodeNumber;
        this.airDate = airDate;
        this.watched = watched;
    }

    // Getters & setters
    public Long getEpisodeId() { return episodeId; }
    public Long getShowId() { return showId; }
    public String getShowName() { return showName; }
    public String getEpisodeName() { return episodeName; }
    public int getSeasonNumber() { return seasonNumber; }
    public int getEpisodeNumber() { return episodeNumber; }
    public LocalDate getAirDate() { return airDate; }
    public boolean isWatched() { return watched; }
}