package com.rrdz.tvtracker.repository;

import com.rrdz.tvtracker.entity.UserEpisode;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserEpisodeRepository extends JpaRepository<UserEpisode, Long> {

    Optional<UserEpisode> findByUserShowIdAndEpisodeId(
            Long userShowId,
            Long episodeId
    );

    List<UserEpisode> findByUserShowId(Long userShowId);

    boolean existsByUserShowIdAndEpisodeId(
            Long userShowId,
            Long episodeId
    );

    void deleteByUserShowIdAndEpisodeId(
            Long userShowId,
            Long episodeId
    );
}