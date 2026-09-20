package com.rrdz.tvtracker.controller;

import com.rrdz.tvtracker.dto.UserShowDto;
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
    public UserShow followShow(@RequestParam Long userId, @RequestParam Long showId) {
        return userShowService.addShow(userId, showId);
    }

    @GetMapping("/following/{userId}")
    public List<UserShow> getFollowing(@PathVariable Long userId) {
        return userShowService.getShowsForUser(userId);
    }

    @DeleteMapping("/unfollow")
    public void unfollowShow(@RequestParam Long userId, @RequestParam Long showId) {
        userShowService.removeShow(userId, showId);
    }   

    @GetMapping ("get-user-shows/{userId}")
    public List<UserShowDto> getUserShows(@PathVariable Long userId) {
        return userShowService.findUserShowsDtoByUserId(userId);
    }
}