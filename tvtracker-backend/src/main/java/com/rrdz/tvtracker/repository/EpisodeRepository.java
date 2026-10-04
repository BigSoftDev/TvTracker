package com.rrdz.tvtracker.repository;

import com.rrdz.tvtracker.dto.UserEpisodeDto;
import com.rrdz.tvtracker.entity.Episode;
import com.rrdz.tvtracker.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {

    List<Episode> findByShow(Show show);

    boolean existsByShowId(Long showId);

    List<Episode> findByAirDateBetween(LocalDate start, LocalDate end);

    @Query("""
        SELECT e FROM Episode e
        JOIN UserShow us ON us.show = e.show
        WHERE us.user.id = :userId
        AND e.airDate >= :startDate
    """)
    List<Episode> findRecentEpisodesForUser(
            @Param("userId") Long userId,
            @Param("startDate") LocalDate startDate
    );

    @Query("""
        SELECT new com.rrdz.tvtracker.dto.UserEpisodeDto(
            e,
            CASE WHEN we.id IS NOT NULL THEN true ELSE false END
        )
        FROM Episode e
        LEFT JOIN WatchedEpisode we
            ON we.episodeId = e.id
            AND we.userId = :userId
        WHERE e.show.id = :showId
        ORDER BY e.seasonNumber, e.episodeNumber
    """)
    List<UserEpisodeDto> findEpisodesByShowAndUser(
            @Param("showId") Long showId,
            @Param("userId") Long userId
    );
}