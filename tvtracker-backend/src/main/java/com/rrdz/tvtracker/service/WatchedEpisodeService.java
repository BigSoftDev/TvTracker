package com.rrdz.tvtracker.service;

import com.rrdz.tvtracker.entity.Episode;
import com.rrdz.tvtracker.entity.User;
import com.rrdz.tvtracker.entity.WatchedEpisode;
import com.rrdz.tvtracker.entity.WatchedEpisodeId;
import com.rrdz.tvtracker.repository.EpisodeRepository;
import com.rrdz.tvtracker.repository.UserRepository;
import com.rrdz.tvtracker.repository.WatchedEpisodeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WatchedEpisodeService {

    private final WatchedEpisodeRepository watchedEpisodeRepository;
    private final UserRepository userRepository;
    private final EpisodeRepository episodeRepository;

    public WatchedEpisodeService(WatchedEpisodeRepository watchedEpisodeRepository,
                                 UserRepository userRepository,
                                 EpisodeRepository episodeRepository) {
        this.watchedEpisodeRepository = watchedEpisodeRepository;
        this.userRepository = userRepository;
        this.episodeRepository = episodeRepository;
    }

    /**
     * Mark an episode as watched for a user
     */
    public void markWatched(Long userId, Long episodeId) {
        if (watchedEpisodeRepository.existsByUser_IdAndEpisode_Id(userId, episodeId)) return;

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Episode episode = episodeRepository.findById(episodeId)
                .orElseThrow(() -> new IllegalArgumentException("Episode not found"));

        WatchedEpisode we = new WatchedEpisode();
        WatchedEpisodeId id = new WatchedEpisodeId();
        id.setUserId(userId);
        id.setEpisodeId(episodeId);

        we.setId(id);
        we.setUser(user);
        we.setEpisode(episode);
        we.setWatchedAt(LocalDateTime.now());

        watchedEpisodeRepository.save(we);
    }

    /**
     * Get all episodes a user has watched
     */
    public List<WatchedEpisode> getWatchedEpisodes(Long userId) {
        return watchedEpisodeRepository.findByUser_Id(userId);
    }
}