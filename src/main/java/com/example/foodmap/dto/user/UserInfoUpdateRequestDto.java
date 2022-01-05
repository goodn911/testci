package com.example.foodmap.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoUpdateRequestDto {
    private String profileImage;
    private String nickname;

    public UserInfoUpdateRequestDto(String profileImage, String nickname) {
        this.profileImage = profileImage;
        this.nickname = nickname;
    }
}
