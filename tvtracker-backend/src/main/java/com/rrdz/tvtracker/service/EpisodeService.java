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


    public EpisodeService(EpisodeRepository episodeRepository,
                          UserShowRepository userShowRepository) {
        this.episodeRepository = episodeRepository;
        this.userShowRepository = userShowRepository;
                          }
}