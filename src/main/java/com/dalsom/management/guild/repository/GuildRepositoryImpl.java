package com.dalsom.management.guild.repository;

import com.dalsom.management.character.QCharacters;
import com.dalsom.management.guild.GuildRole;
import com.dalsom.management.guild.Guilds;
import com.dalsom.management.guild.dto.GuildDetailDto;
import com.dalsom.management.guild.dto.GuildListDto;
import com.dalsom.management.guild.dto.QGuildCharactersDto;
import com.dalsom.management.guild.dto.QGuildListDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

import static com.dalsom.management.character.QCharacters.characters;
import static com.dalsom.management.guild.QGuildCharacters.guildCharacters;
import static com.dalsom.management.guild.QGuilds.guilds;
import static com.dalsom.management.user.QUser.user;
import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

@Component
public class GuildRepositoryImpl implements GuildRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public GuildRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<GuildListDto> findGuildsDto() {
        return queryFactory
                .select(new QGuildListDto(
                        guilds.id,
                        guilds.guildName,
                        guilds.maxCapacity,
                        guilds.guildCharacters.size(),
                        guildCharacters.character.characterData.characterName.as("guildMaster")
                ))
                .from(guilds)
                .leftJoin(guilds.guildCharacters, guildCharacters)
                .on(guildCharacters.role.eq(GuildRole.MASTER))
                .leftJoin(guildCharacters.character, characters)
                .fetch();
    }

    @Override
    public List<GuildDetailDto> findGuildDetailById(Long id) {
        QCharacters mainCharacter = new QCharacters("mainCharacter");
        return queryFactory
                .from(guilds)
                .leftJoin(guilds.guildCharacters, guildCharacters)
                .leftJoin(guildCharacters.character, characters)
                .leftJoin(characters.user, user)
                .leftJoin(user.mainCharacter, mainCharacter)
                .where(guilds.id.eq(id))
                .transform(
                        groupBy(guilds.guildName).list(
                                Projections.fields(
                                        GuildDetailDto.class,
                                        guilds.guildName,
                                        guilds.maxCapacity,
                                        list(
                                                new QGuildCharactersDto(
                                                        user.id.as("userId"),
                                                        user.mainCharacter.characterData.characterName.as("mainCharacterName"),
                                                        characters.characterData.characterName,
                                                        characters.characterData.job,
                                                        guildCharacters.role.as("guildRole"),
                                                        characters.characterData.level,
                                                        characters.characterData.itemLevel
                                                )
                                        ).as("characters")
                                )
                        )
                );
    }

    @Override
    public Map<String, Guilds> findAllByGuildName(List<String> guildNames) {
        return queryFactory
                .from(guilds)
                .where(guilds.guildName.in(guildNames))
                .transform(groupBy(guilds.guildName).as(guilds));
    }
}
