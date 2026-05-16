package com.rrdz.tvtracker.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rrdz.tvtracker.entity.Show;
import com.rrdz.tvtracker.service.TvdbService;

@RestController
@RequestMapping("/api/util")
public class TvdbController {

    private final TvdbService tvdbService;

    public TvdbController(TvdbService tvdbService) {
        this.tvdbService = tvdbService;
    }

    @PostMapping("/top-shows")
    public List<Show> fetchTopShows() {
        return tvdbService.fetchAndSaveTopShows();
    }
}
