package com.dalsom.management.guild.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GuildDetailDto {

    private String guildName;
    private Integer maxCapacity;
    private List<GuildCharactersDto> characters;
}
