package com.rrdz.tvtracker.repository;

import com.rrdz.tvtracker.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShowRepository extends JpaRepository<Show, Long> {

    Optional<Show> findByTvdbId(Long tvdbId);
}