package com.rrdz.tvtracker.entity;
import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class WatchedEpisodeId implements Serializable {

    private Long userId;
    private Long episodeId;

    @Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof WatchedEpisodeId)) return false;
    WatchedEpisodeId that = (WatchedEpisodeId) o;
    return Objects.equals(userId, that.userId) &&
           Objects.equals(episodeId, that.episodeId);
}

@Override
public int hashCode() {
    return Objects.hash(userId, episodeId);
}

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEpisodeId() {
        return this.episodeId;
    }

    public void setEpisodeId(Long episodeId) {
        this.episodeId = episodeId;
    }


}