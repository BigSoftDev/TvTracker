package com.rrdz.tvtracker.service;

import com.rrdz.tvtracker.dto.UserEpisodeDto;
import com.rrdz.tvtracker.entity.Episode;
import com.rrdz.tvtracker.entity.Show;
import com.rrdz.tvtracker.entity.WatchedEpisode;
import com.rrdz.tvtracker.repository.EpisodeRepository;
import com.rrdz.tvtracker.repository.UserShowRepository;
import com.rrdz.tvtracker.repository.WatchedEpisodeRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EpisodeService {

    private final EpisodeRepository episodeRepository;
    private final WatchedEpisodeRepository watchedEpisodeRepository;
    private final TvdbService tvdbService;


    public EpisodeService(EpisodeRepository episodeRepository,
                          WatchedEpisodeRepository watchedEpisodeRepository,
                          TvdbService tvdbService) {
        this.episodeRepository = episodeRepository;
        this.watchedEpisodeRepository = watchedEpisodeRepository;
        this.tvdbService = tvdbService;
    }


public List<UserEpisodeDto> getEpisodesByShowAndUser(
        Long showId,
        Long userId) {

    if (!episodeRepository.existsByShowId(showId)) {
      List<Episode> episodes = tvdbService.fetchEpisodesForShow(showId); 

    }

    return episodeRepository.findEpisodesByShowAndUser(showId, userId);
}

    public void markWatched(Long episodeId, Long userId) {
          watchedEpisodeRepository.save(new WatchedEpisode(episodeId, userId));

    }


    public void markUnwatched(Long episodeId, Long userId) {
       watchedEpisodeRepository.deleteByUserIdAndEpisodeId(episodeId, userId);
    }
}