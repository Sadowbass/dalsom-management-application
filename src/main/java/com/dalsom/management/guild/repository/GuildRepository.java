package com.dalsom.management.guild;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuildRepository extends JpaRepository<Guilds, Long>, GuildRepositoryCustom {

    Optional<Guilds> findByGuildName(String guildName);
}
