package com.rrdz.tvtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rrdz.tvtracker.entity.WatchedEpisode;

public interface WatchedEpisodeRepository extends JpaRepository<WatchedEpisode, Long> { 

    WatchedEpisode findByUserIdAndEpisodeId(Long userId, Long episodeId);

    void deleteByUserIdAndEpisodeId(Long userId, Long episodeId);
    
}
