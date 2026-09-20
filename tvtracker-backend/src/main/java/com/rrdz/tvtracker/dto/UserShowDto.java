package com.rrdz.tvtracker.dto;

import com.rrdz.tvtracker.entity.Show;
import com.rrdz.tvtracker.model.ShowStatus;


public class UserShowDto {

    private Show show;
    private Long userShowId;
    private boolean favorite;
    private Integer rating;
    private ShowStatus status;

    public UserShowDto(
            Show show,
            Long userShowId,
            boolean favorite,
            Integer rating,
            ShowStatus status) {

        this.show = show;
        this.userShowId = userShowId;
        this.favorite = favorite;
        this.rating = rating;
        this.status = status;
    }

    public Show getShow() {
        return show;
    }

    public Long getUserShowId() {
        return userShowId;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public Integer getRating() {
        return rating;
    }

    public ShowStatus getStatus() {
        return status;
    }
}