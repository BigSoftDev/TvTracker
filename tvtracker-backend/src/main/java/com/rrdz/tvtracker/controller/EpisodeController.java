package com.rrdz.tvtracker.controller;

import com.rrdz.tvtracker.dto.EpisodeDto;
import com.rrdz.tvtracker.service.EpisodeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/episodes")
public class EpisodeController {

    private final EpisodeService episodeService;

    public EpisodeController(EpisodeService episodeService) {
        this.episodeService = episodeService;
    }

    @GetMapping("/recent/{userId}")
    public List<EpisodeDto> getRecentEpisodes(@PathVariable Long userId) {
        return episodeService.getRecentEpisodesWithWatchedStatus(userId);
    }
}