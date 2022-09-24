package com.dalsom.management.user.repository;

import com.dalsom.management.character.QCharacters;
import com.dalsom.management.common.SearchCondition;
import com.dalsom.management.guild.GuildRole;
import com.dalsom.management.user.dto.QUserCharacterDto;
import com.dalsom.management.user.dto.QUserListDto;
import com.dalsom.management.user.dto.UserDetailDto;
import com.dalsom.management.user.dto.UserListDto;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.dalsom.management.character.QCharacters.characters;
import static com.dalsom.management.guild.QGuildCharacters.guildCharacters;
import static com.dalsom.management.guild.QGuilds.guilds;
import static com.dalsom.management.user.QUser.user;
import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public UserRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<UserListDto> findUserListDtoPage(Pageable pageParameter, SearchCondition searchCondition) {
        QueryResults<UserListDto> userResult = queryFactory
                .select(new QUserListDto(
                        user.id,
                        characters.characterData.characterName,
                        guildCharacters.role
                ))
                .from(user)
                .join(user.mainCharacter, characters)
                .join(guilds)
                .on(guilds.guildName.eq("달콤한 솜사탕"))
                .join(guilds.guildCharacters, guildCharacters)
                .on(guildCharacters.character.id.eq(characters.id))
                .where(createWhereCondition(searchCondition))
                .offset(pageParameter.getOffset())
                .limit(pageParameter.getPageSize())
                .fetchResults();

        return new PageImpl<>(userResult.getResults(), pageParameter, userResult.getTotal());
    }

    private BooleanExpression createWhereCondition(SearchCondition searchCondition) {
        if (ObjectUtils.isEmpty(searchCondition.getCategory()) || searchCondition.getCategory().equals("name")) {
            return user.mainCharacter.characterData.characterName.containsIgnoreCase(searchCondition.getKeyword());
        } else {
            return guildCharacters.role.eq(GuildRole.valueOf(searchCondition.getKeyword()));
        }
    }

    @Override
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
