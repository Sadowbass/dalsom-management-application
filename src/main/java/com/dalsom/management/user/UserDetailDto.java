package com.dalsom.management.user;

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserDetailDto{");
        sb.append("userId=").append(userId);
        sb.append(", mainCharacterName='").append(mainCharacterName).append('\'');
        sb.append(", joinDate=").append(joinDate);
        sb.append(", userStatus=").append(userStatus);
        sb.append(", leaveReason='").append(leaveReason).append('\'');
        sb.append(", characters=").append(characters);
        sb.append('}');
        return sb.toString();
    }
}
