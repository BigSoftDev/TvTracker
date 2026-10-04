package com.rrdz.tvtracker.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rrdz.tvtracker.dto.UserEpisodeDto;
import com.rrdz.tvtracker.service.EpisodeService;

@RestController
@RequestMapping("/api/episodes")
public class EpisodeController {

    private final EpisodeService episodeService;

    public EpisodeController(EpisodeService episodeService) {
        this.episodeService = episodeService;
    }

    @GetMapping("/show/{showId}")
    public List<UserEpisodeDto> getEpisodesByShow(
            @PathVariable Long showId,
            @RequestParam Long userId) {

        return episodeService.getEpisodesByShowAndUser(showId, userId);
    }

    @PostMapping("/{episodeId}/watched")
    public ResponseEntity<Void> markWatched(
            @PathVariable Long episodeId,
            @RequestParam Long userId) {

        episodeService.markWatched(episodeId, userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{episodeId}/watched")
    public ResponseEntity<Void> markUnwatched(
            @PathVariable Long episodeId,
            @RequestParam Long userId) {

        episodeService.markUnwatched(episodeId, userId);
        return ResponseEntity.ok().build();
    }
}