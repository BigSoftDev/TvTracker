package com.rrdz.tvtracker.controller;

import com.rrdz.tvtracker.entity.WatchedEpisode;
import com.rrdz.tvtracker.service.WatchedEpisodeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/watched")
public class WatchedEpisodeController {

    private final WatchedEpisodeService watchedEpisodeService;

    public WatchedEpisodeController(WatchedEpisodeService watchedEpisodeService) {
        this.watchedEpisodeService = watchedEpisodeService;
    }

    @PostMapping("/mark")
    public void markWatched(@RequestParam Long userId, @RequestParam Long episodeId) {
        watchedEpisodeService.markWatched(userId, episodeId);
    }

    @GetMapping("/user/{userId}")
    public List<WatchedEpisode> getWatched(@PathVariable Long userId) {
        return watchedEpisodeService.getWatchedEpisodes(userId);
    }
}