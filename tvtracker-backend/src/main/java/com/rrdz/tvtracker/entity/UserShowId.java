package com.rrdz.tvtracker.entity;
import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class UserShowId implements Serializable {

    private Long userId;
    private Long showId;

    @Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UserShowId)) return false;
    UserShowId that = (UserShowId) o;
    return Objects.equals(userId, that.userId) &&
           Objects.equals(showId, that.showId);
}

@Override
public int hashCode() {
    return Objects.hash(userId, showId);
}


    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getShowId() {
        return this.showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

}