package com.rrdz.tvtracker.service;

import com.rrdz.tvtracker.model.ShowStatus;
import com.rrdz.tvtracker.repository.ShowRepository;
import com.rrdz.tvtracker.repository.UserRepository;
import com.rrdz.tvtracker.repository.UserShowRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.rrdz.tvtracker.dto.UserShowDto;
import com.rrdz.tvtracker.entity.Show;
import com.rrdz.tvtracker.entity.User;
import com.rrdz.tvtracker.entity.UserShow;

@Service
public class UserShowService {

    private final UserShowRepository userShowRepository;
    private final UserRepository userRepository;
    private final ShowRepository showRepository;

    public UserShowService(
            UserShowRepository userShowRepository,
            UserRepository userRepository,
            ShowRepository showRepository) {

        this.userShowRepository = userShowRepository;
        this.userRepository = userRepository;
        this.showRepository = showRepository;
    }

    /**
     * Get all shows belonging to a user.
     */
    public List<UserShow> getShowsForUser(Long userId) {
        return userShowRepository.findByUserId(userId);
    }

    /**
     * Get a specific UserShow relationship.
     */
    public Optional<UserShow> getUserShow(Long userId, Long showId) {
        return userShowRepository.findByUserIdAndShowId(userId, showId);
    }

    /**
     * Check whether a user has a show in My Shows.
     */
    public boolean isShowInUserShows(Long userId, Long showId) {
        return userShowRepository.existsByUserIdAndShowId(userId, showId);
    }

    public List<UserShowDto> findUserShowsDtoByUserId(Long userId) {

        List<UserShowDto> list = userShowRepository.findUserShowsByUserId(userId);
        return list;
    }

    /**
     * Add a show to a user's My Shows.
     */
    public UserShow addShow(Long userId, Long showId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found: " + userId));

        Show show = showRepository.findById(showId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Show not found: " + showId));

        // Prevent duplicate UserShow records
        Optional<UserShow> existing =
                userShowRepository.findByUserIdAndShowId(userId, showId);

        if (existing.isPresent()) {
            return existing.get();
        }

        UserShow userShow = new UserShow();

        userShow.setUser(user);
        userShow.setShow(show);
        userShow.setFavorite(false);
        userShow.setRating(null);
        userShow.setStatus(ShowStatus.QUEUED);

        return userShowRepository.save(userShow);
    }

    /**
     * Remove a show from a user's My Shows.
     */
    public void removeShow(Long userId, Long showId) {

        UserShow userShow = userShowRepository
                .findByUserIdAndShowId(userId, showId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Show is not in user's My Shows"));

        userShowRepository.delete(userShow);
    }

    /**
     * Toggle a show's favorite status.
     */
    public UserShow setFavorite(
            Long userId,
            Long showId,
            boolean favorite) {

        UserShow userShow = getRequiredUserShow(userId, showId);

        userShow.setFavorite(favorite);

        return userShowRepository.save(userShow);
    }

    /**
     * Update a user's rating for a show.
     */
    public UserShow setRating(
            Long userId,
            Long showId,
            Integer rating) {

        UserShow userShow = getRequiredUserShow(userId, showId);

        userShow.setRating(rating);

        return userShowRepository.save(userShow);
    }

    /**
     * Update a user's status for a show.
     */
    public UserShow setStatus(
            Long userId,
            Long showId,
            ShowStatus status) {

        UserShow userShow = getRequiredUserShow(userId, showId);

        userShow.setStatus(status);

        return userShowRepository.save(userShow);
    }

    /**
     * Get a UserShow or throw an error if it doesn't exist.
     */
    private UserShow getRequiredUserShow(Long userId, Long showId) {

        return userShowRepository
                .findByUserIdAndShowId(userId, showId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Show is not in user's My Shows"));
    }
}