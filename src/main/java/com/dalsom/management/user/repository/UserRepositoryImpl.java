package com.dalsom.management.user.repository;

import com.dalsom.management.character.QCharacters;
import com.dalsom.management.user.QUserCharacterDto;
import com.dalsom.management.user.UserDetailDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

import java.util.List;

import static com.dalsom.management.character.QCharacters.characters;
import static com.dalsom.management.guild.QGuildCharacters.guildCharacters;
import static com.dalsom.management.guild.QGuilds.guilds;
import static com.dalsom.management.user.QUser.user;
import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

public class UserRepositoryImpl implements UserRepositoryCustom {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public UserRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<UserDetailDto> findUserDetailById(Long id) {
        QCharacters mainCharacter = new QCharacters("mainCharacter");

        return queryFactory
                .from(guilds)
                .join(guilds.guildCharacters, guildCharacters)
                .join(guildCharacters.character, characters)
                .join(characters.user, user)
                .join(user.mainCharacter, mainCharacter)
                .where(user.id.eq(id))
                .transform(
                        groupBy(user.id).list(
                                Projections.fields(
                                        UserDetailDto.class,
                                        user.id.as("userId"),
                                        mainCharacter.characterData.characterName.as("mainCharacterName"),
                                        user.joinDate,
                                        user.userStatus,
                                        user.leaveReason,
                                        list(
                                                new QUserCharacterDto(
                                                        characters.characterData.characterName,
                                                        guilds.guildName,
                                                        characters.characterData.job,
                                                        characters.characterData.itemLevel
                                                )
                                        ).as("characters")
                                )
                        )
                );
    }
}
