package com.rrdz.tvtracker.repository;

import java.util.List;

import com.rrdz.tvtracker.dto.UserShowDto;
import com.rrdz.tvtracker.entity.Show;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ShowRepository extends JpaRepository<Show, Long> {

    Optional<Show> findByTvdbId(Long tvdbId);

    @Query("""
    SELECT new com.rrdz.tvtracker.dto.UserShowDto(
        s,
        us.id,
        COALESCE(us.favorite, false),
        us.rating,
        us.status
    )
    FROM Show s
    LEFT JOIN UserShow us
        ON us.show = s
        AND us.user.id = :userId
    ORDER BY s.name
""")
public List<UserShowDto> findAllShowsForUser(@Param("userId") Long userId);
}