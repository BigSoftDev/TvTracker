package com.rrdz.tvtracker.service;

import com.rrdz.tvtracker.entity.Show;
import com.rrdz.tvtracker.entity.User;
import com.rrdz.tvtracker.entity.UserShow;
import com.rrdz.tvtracker.entity.UserShowId;
import com.rrdz.tvtracker.repository.ShowRepository;
import com.rrdz.tvtracker.repository.UserRepository;
import com.rrdz.tvtracker.repository.UserShowRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserShowService {

    private final UserShowRepository userShowRepository;
    private final UserRepository userRepository;
    private final ShowRepository showRepository;

    public UserShowService(UserShowRepository userShowRepository,
                           UserRepository userRepository,
                           ShowRepository showRepository) {
        this.userShowRepository = userShowRepository;
        this.userRepository = userRepository;
        this.showRepository = showRepository;
    }

    /**
     * User follows a show
     */
    public void followShow(Long userId, Long showId) {
        if (userShowRepository.existsByUserIdAndShowId(userId, showId)) return;

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new IllegalArgumentException("Show not found"));

        UserShow us = new UserShow();
        UserShowId id = new UserShowId();
        id.setUserId(userId);
        id.setShowId(showId);
        us.setId(id);
        us.setUser(user);
        us.setShow(show);
        us.setAddedAt(LocalDateTime.now());

        userShowRepository.save(us);
    }

    /**
     * List all shows a user is following
     */
    public List<UserShow> getFollowedShows(Long userId) {
        return userShowRepository.findByUserId(userId);
    }
}