package com.dalsom.management.guild.dto;

import com.dalsom.management.guild.GuildRole;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GuildCharactersDto {

    private Long userId;
    private String mainCharacterName;
    private String characterName;
    private GuildRole guildRole;
    private Integer level;
    private Integer itemLevel;

    @QueryProjection
    public GuildCharactersDto(Long userId, String mainCharacterName, String characterName, GuildRole guildRole, Integer level, Integer itemLevel) {
        this.userId = userId;
        this.mainCharacterName = mainCharacterName;
        this.characterName = characterName;
        this.guildRole = guildRole;
        this.level = level;
        this.itemLevel = itemLevel;
    }
}
