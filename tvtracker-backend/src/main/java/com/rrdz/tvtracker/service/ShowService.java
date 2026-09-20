package com.rrdz.tvtracker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rrdz.tvtracker.dto.UserShowDto;
import com.rrdz.tvtracker.entity.Show;
import com.rrdz.tvtracker.repository.ShowRepository;

@Service
public class ShowService {

    private final ShowRepository showRepository;

    public ShowService(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    public List<Show> getAllShows() {
        return showRepository.findAll();
    }

    public List<UserShowDto> getAllShowsForUser(Long userId) {
        return showRepository.findAllShowsForUser(userId);
    }
    
}
