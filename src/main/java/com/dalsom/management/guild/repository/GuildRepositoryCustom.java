package com.dalsom.management.guild.repository;

import com.dalsom.management.guild.Guilds;
import com.dalsom.management.guild.dto.GuildDetailDto;
import com.dalsom.management.guild.dto.GuildListDto;

import java.util.List;
import java.util.Map;

public interface GuildRepositoryCustom {

    List<GuildListDto> findGuildsDto();

    List<GuildDetailDto> findGuildDetailById(Long id);

    Map<String, Guilds> findAllByGuildName(List<String> guildNames);
}
