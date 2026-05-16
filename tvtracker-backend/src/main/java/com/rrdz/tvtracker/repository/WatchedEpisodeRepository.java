package com.rrdz.tvtracker.repository;

import com.rrdz.tvtracker.entity.WatchedEpisode;
import com.rrdz.tvtracker.entity.WatchedEpisodeId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WatchedEpisodeRepository extends JpaRepository<WatchedEpisode, WatchedEpisodeId> {

    List<WatchedEpisode> findByUser_Id(Long userId);

    boolean existsByUser_IdAndEpisode_Id(Long userId, Long episodeId);
}