package com.example.foodmap.repository;

import com.example.foodmap.model.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting,Long> {
    List<Meeting> findAllByOrderByModifiedAtDesc();

    // 게시판 조회수 기능 추가
    @Modifying
    @Query("update Meeting b set b.viewCount = b.viewCount + 1 where b.id = :id")
    int updateView(@Param("id") Long id);

    void deleteAllByMeetingDateLessThan(LocalDateTime daytime);

}
