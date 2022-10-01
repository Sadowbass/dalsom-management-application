package com.dalsom.management.guild.repository;

import com.dalsom.management.guild.Guilds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GuildRepository extends JpaRepository<Guilds, Long>, GuildRepositoryCustom {

    Optional<Guilds> findByGuildName(String guildName);

}
