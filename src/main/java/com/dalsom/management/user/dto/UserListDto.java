package com.dalsom.management.user.dto;

import com.dalsom.management.guild.GuildRole;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserListDto {

    private Long userId;
    private String mainCharacterName;
    private GuildRole guildRole;
    private int numberOfComments = 0;

    @QueryProjection
    public UserListDto(Long userId, String mainCharacterName, GuildRole guildRole) {
        this.userId = userId;
        this.mainCharacterName = mainCharacterName;
        this.guildRole = guildRole;
    }
}
