package com.rrdz.tvtracker.repository;

import com.rrdz.tvtracker.entity.Episode;
import com.rrdz.tvtracker.entity.Show;
import com.rrdz.tvtracker.entity.UserShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {

    List<Episode> findByShow(Show show);

    List<Episode> findByAirDateBetween(LocalDate start, LocalDate end);

    @Query("""
        SELECT e FROM Episode e
        JOIN UserShow us ON us.show = e.show
        WHERE us.user.id = :userId
        AND e.airDate >= :startDate
    """)
    List<Episode> findRecentEpisodesForUser(Long userId, LocalDate startDate);
}