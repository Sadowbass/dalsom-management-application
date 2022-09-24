package com.dalsom.management.guild;

import com.dalsom.management.admin.Admin;
import com.dalsom.management.admin.repository.AdminRepository;
import com.dalsom.management.character.CharacterData;
import com.dalsom.management.character.CharacterRepository;
import com.dalsom.management.character.Characters;
import com.dalsom.management.character.Jobs;
import com.dalsom.management.guild.repository.GuildRepository;
import com.dalsom.management.user.User;
import com.dalsom.management.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class GuildRepositoryTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    GuildRepository guildRepository;

    @Autowired
    CharacterRepository characterRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AdminRepository adminRepository;

    @Test
    void save() throws Exception {
        //given
        Guilds guild = new Guilds("달콤한 솜사탕", 60);

        //when
        guildRepository.save(guild);
        guildRepository.flush();
        em.clear();

        Guilds savedGuild = guildRepository.findByGuildName("달콤한 솜사탕").orElseThrow(RuntimeException::new);

        //then
        assertThat(savedGuild.getId()).isEqualTo(guild.getId());
        assertThat(savedGuild.getGuildCharacters()).isEmpty();
        List<GuildCharacters> guildCharacters = savedGuild.getGuildCharacters();
        assertThatThrownBy(guildCharacters::clear).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void addCharacter() throws Exception {
        //given
        persistDefaultValues();
        Guilds guild = new Guilds("달콤한 솜사탕", 60);

        //when
        guildRepository.save(guild);
        guildRepository.flush();

        Characters character = characterRepository.findByCharacterDataCharacterName("개발하다샷건침").orElseThrow(RuntimeException::new);
        GuildCharacters guildCharacter = new GuildCharacters(character, GuildRole.MEMBER);
        guild.addCharacter(guildCharacter);
        guildRepository.flush();

        //then
        List<GuildCharacters> guildCharacters = guild.getGuildCharacters();
        assertThat(guildCharacters)
                .hasSize(1)
                .contains(guildCharacter);
        assertThat(guildCharacters.get(0)).isEqualTo(guildCharacter);
    }

    void persistDefaultValues() {
        Admin admin = Admin.createNewAdmin("test", "test", "샷건");
        User user = User.createNewUser(null, admin);
        Characters character = Characters.createNewCharacter(user, new CharacterData("개발하다샷건침", Jobs.건슬링어, 60, 1540));

        adminRepository.save(admin);
        userRepository.save(user);
        characterRepository.save(character);
    }
}