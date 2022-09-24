package com.dalsom.management;

import com.dalsom.management.admin.Admin;
import com.dalsom.management.character.CharacterData;
import com.dalsom.management.character.Characters;
import com.dalsom.management.character.Jobs;
import com.dalsom.management.guild.*;
import com.dalsom.management.user.User;
import com.dalsom.management.user.dto.QUserListDto;
import com.dalsom.management.user.dto.UserListDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

import static com.dalsom.management.character.QCharacters.*;
import static com.dalsom.management.guild.QGuildCharacters.*;
import static com.dalsom.management.guild.QGuilds.*;
import static com.dalsom.management.user.QUser.user;

@SpringBootTest
@Transactional
public class QueryDslTest {

    @PersistenceContext
    EntityManager em;
    JPAQueryFactory queryFactory;

    @BeforeEach
    void init() {
        queryFactory = new JPAQueryFactory(em);
        addTestData();
    }

    void addTestData() {
        Admin newAdmin = Admin.createNewAdmin("admin", "asd", "admin");
        em.persist(newAdmin);

        User newUser1 = User.createNewUser(LocalDateTime.now(), newAdmin);
        Characters user1ch1 = Characters.createNewCharacter(newUser1, new CharacterData("user1ch1", Jobs.데빌헌터, 60, 1500));
        Characters user1ch2 = Characters.createNewCharacter(newUser1, new CharacterData("user1ch2", Jobs.데빌헌터, 60, 1500));
        Characters user1ch3 = Characters.createNewCharacter(newUser1, new CharacterData("user1ch3", Jobs.데빌헌터, 60, 1500));
        Characters user1ch4 = Characters.createNewCharacter(newUser1, new CharacterData("user1ch4", Jobs.데빌헌터, 60, 1500));
        newUser1.addCharacters(user1ch1, user1ch2, user1ch3, user1ch4);
        newUser1.changeMainCharacter(user1ch1);

        em.persist(newUser1);
        em.persist(user1ch1);
        em.persist(user1ch2);
        em.persist(user1ch3);
        em.persist(user1ch4);

        User newUser2 = User.createNewUser(LocalDateTime.now(), newAdmin);
        Characters user2ch1 = Characters.createNewCharacter(newUser2, new CharacterData("user2ch1", Jobs.데빌헌터, 60, 1500));
        Characters user2ch2 = Characters.createNewCharacter(newUser2, new CharacterData("user2ch2", Jobs.데빌헌터, 60, 1500));
        Characters user2ch3 = Characters.createNewCharacter(newUser2, new CharacterData("user2ch3", Jobs.데빌헌터, 60, 1500));
        Characters user2ch4 = Characters.createNewCharacter(newUser2, new CharacterData("user2ch4", Jobs.데빌헌터, 60, 1500));
        newUser2.addCharacters(user2ch1, user2ch2, user2ch3, user2ch4);
        newUser2.changeMainCharacter(user2ch1);

        em.persist(newUser2);
        em.persist(user2ch1);
        em.persist(user2ch2);
        em.persist(user2ch3);
        em.persist(user2ch4);

        User newUser3 = User.createNewUser(LocalDateTime.now(), newAdmin);
        Characters user3ch1 = Characters.createNewCharacter(newUser3, new CharacterData("user3ch1", Jobs.데빌헌터, 60, 1500));
        Characters user3ch2 = Characters.createNewCharacter(newUser3, new CharacterData("user3ch2", Jobs.데빌헌터, 60, 1500));
        Characters user3ch3 = Characters.createNewCharacter(newUser3, new CharacterData("user3ch3", Jobs.데빌헌터, 60, 1500));
        Characters user3ch4 = Characters.createNewCharacter(newUser3, new CharacterData("user3ch4", Jobs.데빌헌터, 60, 1500));
        newUser3.addCharacters(user3ch1, user3ch2, user3ch3, user3ch4);
        newUser3.changeMainCharacter(user3ch1);

        em.persist(newUser3);
        em.persist(user3ch1);
        em.persist(user3ch2);
        em.persist(user3ch3);
        em.persist(user3ch4);

        Guilds guild1 = new Guilds("guild1", 60);
        Guilds guild2 = new Guilds("guild2", 60);
        Guilds guild3 = new Guilds("guild3", 60);
        guild1.addCharacter(new GuildCharacters(user1ch1, GuildRole.MASTER));
        guild1.addCharacter(new GuildCharacters(user2ch1, GuildRole.ADMIN));
        guild1.addCharacter(new GuildCharacters(user3ch1, GuildRole.MEMBER));

        guild2.addCharacter(new GuildCharacters(user1ch2, GuildRole.MASTER));
        guild2.addCharacter(new GuildCharacters(user1ch3, GuildRole.MEMBER));
        guild2.addCharacter(new GuildCharacters(user1ch4, GuildRole.MEMBER));
        guild2.addCharacter(new GuildCharacters(user2ch2, GuildRole.MEMBER));

        guild3.addCharacter(new GuildCharacters(user3ch2, GuildRole.MASTER));
        guild3.addCharacter(new GuildCharacters(user2ch3, GuildRole.MASTER));
        guild3.addCharacter(new GuildCharacters(user2ch4, GuildRole.MASTER));
        guild3.addCharacter(new GuildCharacters(user3ch3, GuildRole.MASTER));
        guild3.addCharacter(new GuildCharacters(user3ch4, GuildRole.MASTER));

        em.persist(guild1);
        em.persist(guild2);
        em.persist(guild3);
        em.flush();
        em.clear();
        System.out.println();
        System.out.println();
        System.out.println("======================");
        System.out.println("======================");
        System.out.println("init");
        System.out.println("======================");
        System.out.println("======================");
        System.out.println();
        System.out.println();
    }

    @Test
    void test() {
        List<UserListDto> fetch = queryFactory
                .select(new QUserListDto(
                        user.id,
                        characters.characterData.characterName,
                        guildCharacters.role
                ))
                .from(user)
                .join(user.mainCharacter, characters)
                .join(guilds)
                .on(guilds.guildName.eq("guild1"))
                .join(guilds.guildCharacters, guildCharacters)
                .on(guildCharacters.character.id.eq(characters.id))
                .fetch();

        for (UserListDto dto : fetch) {
            System.out.println("dto.getMainCharacterName() = " + dto.getMainCharacterName());
        }
    }
}
