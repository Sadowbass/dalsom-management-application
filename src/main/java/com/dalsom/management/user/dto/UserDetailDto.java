package com.dalsom.management.user.dto;

import com.dalsom.management.user.UserStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserDetailDto {

    private Long userId;
    private String mainCharacterName;
    private LocalDateTime joinDate;
    private UserStatus userStatus;
    private String leaveReason;
    //    private int numberOfComments;
    //    private int numberOfAccidents;
    private List<UserCharacterDto> characters;

    @QueryProjection
    public UserDetailDto(Long userId, String mainCharacterName, LocalDateTime joinDate, UserStatus userStatus, String leaveReason, List<UserCharacterDto> characters) {
        this.userId = userId;
        this.mainCharacterName = mainCharacterName;
        this.joinDate = joinDate;
        this.userStatus = userStatus;
        this.leaveReason = leaveReason;
        this.characters = characters;
    }
}
