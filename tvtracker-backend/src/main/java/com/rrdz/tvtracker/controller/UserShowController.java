package com.rrdz.tvtracker.controller;

import com.rrdz.tvtracker.entity.UserShow;
import com.rrdz.tvtracker.service.UserShowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-shows")
public class UserShowController {

    private final UserShowService userShowService;

    public UserShowController(UserShowService userShowService) {
        this.userShowService = userShowService;
    }

    @PostMapping("/follow")
    public void followShow(@RequestParam Long userId, @RequestParam Long showId) {
        userShowService.followShow(userId, showId);
    }

    @GetMapping("/following/{userId}")
    public List<UserShow> getFollowing(@PathVariable Long userId) {
        return userShowService.getFollowedShows(userId);
    }
}