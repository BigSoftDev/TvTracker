package com.rrdz.tvtracker.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.List;

import com.rrdz.tvtracker.dto.UserShowDto;
import com.rrdz.tvtracker.entity.UserShow;

public interface UserShowRepository extends JpaRepository<UserShow, Long> {

    Optional<UserShow> findByUserIdAndShowId(Long userId, Long showId);

    List<UserShow> findByUserId(Long userId);

    boolean existsByUserIdAndShowId(Long userId, Long showId);

    void deleteByUserIdAndShowId(Long userId, Long showId);

        @Query("""
        SELECT new com.rrdz.tvtracker.dto.UserShowDto(
            us.show,
            us.id,
            us.favorite,
            us.rating,
            us.status
        )
        FROM UserShow us
        WHERE us.user.id = :userId
        ORDER BY us.show.name
    """)
   public List<UserShowDto> findUserShowsByUserId(
            @Param("userId") Long userId
    );


}