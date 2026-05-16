package com.rrdz.tvtracker.service;

import com.rrdz.tvtracker.dto.EpisodeDto;
import com.rrdz.tvtracker.entity.Episode;
import com.rrdz.tvtracker.entity.UserShow;
import com.rrdz.tvtracker.entity.WatchedEpisode;
import com.rrdz.tvtracker.repository.EpisodeRepository;
import com.rrdz.tvtracker.repository.UserShowRepository;
import com.rrdz.tvtracker.repository.WatchedEpisodeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EpisodeService {

    private final EpisodeRepository episodeRepository;
    private final UserShowRepository userShowRepository;
    private final WatchedEpisodeRepository watchedEpisodeRepository;

    public EpisodeService(EpisodeRepository episodeRepository,
                          UserShowRepository userShowRepository,
                          WatchedEpisodeRepository watchedEpisodeRepository) {
        this.episodeRepository = episodeRepository;
        this.userShowRepository = userShowRepository;
        this.watchedEpisodeRepository = watchedEpisodeRepository;
    }

    /**
     * Get all episodes released in the past week for all shows a user follows,
     * including whether the user has watched each episode.
     */
    public List<EpisodeDto> getRecentEpisodesWithWatchedStatus(Long userId) {
        LocalDate oneWeekAgo = LocalDate.now().minusDays(7);

        // 1️⃣ Get all shows the user follows
        List<UserShow> followedShows = userShowRepository.findByUserId(userId);
        if (followedShows.isEmpty()) return Collections.emptyList();

        // 2️⃣ Gather all episodes in the last week for those shows
        List<Episode> recentEpisodes = followedShows.stream()
                .flatMap(us -> episodeRepository.findByShow(us.getShow()).stream())
                .filter(e -> e.getAirDate() != null && !e.getAirDate().isBefore(oneWeekAgo))
                .collect(Collectors.toList());

        // 3️⃣ Fetch watched episodes for this user
        Set<Long> watchedIds = watchedEpisodeRepository.findByUser_Id(userId).stream()
                .map(we -> we.getEpisode().getId())
                .collect(Collectors.toSet());

        // 4️⃣ Map to DTOs
        return recentEpisodes.stream()
                .map(e -> new EpisodeDto(
                        e.getId(),
                        e.getShow().getId(),
                        e.getShow().getName(),
                        e.getName(),
                        e.getSeasonNumber(),
                        e.getEpisodeNumber(),
                        e.getAirDate(),
                        watchedIds.contains(e.getId())
                ))
                .sorted(Comparator.comparing(EpisodeDto::getAirDate).reversed())
                .collect(Collectors.toList());
    }
}