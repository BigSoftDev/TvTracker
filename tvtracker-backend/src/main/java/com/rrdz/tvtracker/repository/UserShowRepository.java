package com.rrdz.tvtracker.repository;

import com.rrdz.tvtracker.entity.UserShow;
import com.rrdz.tvtracker.entity.UserShowId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserShowRepository extends JpaRepository<UserShow, UserShowId> {

    List<UserShow> findByUserId(Long userId);

    List<UserShow> findByShowId(Long showId);

    boolean existsByUserIdAndShowId(Long userId, Long showId);
}