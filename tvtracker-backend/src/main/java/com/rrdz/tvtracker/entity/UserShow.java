
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
@Table(name = "user_shows")
public class UserShow {

    @EmbeddedId
    private UserShowId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("showId")
    @JoinColumn(name = "show_id")
    private Show show;

    @Column(name = "added_at")
    private LocalDateTime addedAt;


    public UserShowId getId() {
        return this.id;
    }

    public void setId(UserShowId id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Show getShow() {
        return this.show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public LocalDateTime getAddedAt() {
        return this.addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }
    
}