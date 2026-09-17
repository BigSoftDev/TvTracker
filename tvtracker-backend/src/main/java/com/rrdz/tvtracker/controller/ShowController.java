package com.rrdz.tvtracker.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rrdz.tvtracker.entity.Show;
import com.rrdz.tvtracker.service.ShowService;

@RestController
@RequestMapping("/api/shows")
public class ShowController {
    
    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @GetMapping("/allShows")
    public List<Show> getAllShows() {
        return showService.getAllShows();
    }
}
