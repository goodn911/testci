package com.example.foodmap.dto.mypage;

import com.example.foodmap.model.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MyMeetingResponseDto {
    private String meetingTitle;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime meetingDate;
    private Location location;
    private int limitPeople;
    private int nowPeople;
    private String content;
    private String restaurant;
    private int viewCount;
    private LocalDateTime modifiedAt;
    private Long userId;
    private Long meetingId;

}
