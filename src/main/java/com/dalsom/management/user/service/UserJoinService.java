package com.dalsom.management.user.service;

import com.dalsom.management.admin.Admin;
import com.dalsom.management.character.CharacterRegistryService;
import com.dalsom.management.character.CharacterRepository;
import com.dalsom.management.character.Characters;
import com.dalsom.management.common.DuplicateMemberException;
import com.dalsom.management.guild.GuildCharacters;
import com.dalsom.management.guild.GuildRole;
import com.dalsom.management.guild.Guilds;
import com.dalsom.management.guild.repository.GuildRepository;
import com.dalsom.management.user.User;
import com.dalsom.management.user.dto.UserJoinForm;
import com.dalsom.management.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserJoinService {

    private final UserRepository userRepository;
    private final CharacterRepository characterRepository;
    private final GuildRepository guildRepository;
    private final CharacterRegistryService characterRegistryService;

    @Transactional
    public Long join(UserJoinForm form, Admin admin) throws IOException {
        checkDuplicateCharacter(form);

        User user = User.createNewUser(form.getJoinDate().atStartOfDay(), admin);
        userRepository.save(user);

        Characters newCharacter = characterRegistryService.registerCharacter(user, form);
        user.changeMainCharacter(newCharacter);

        Guilds dalsom = guildRepository.findByGuildName("달콤한솜사탕").get();
        dalsom.addCharacter(new GuildCharacters(newCharacter, GuildRole.MEMBER));

        characterRegistryService.registerAllCharacter(user.getId());

        return user.getId();
    }

    private void checkDuplicateCharacter(UserJoinForm form) {
        if (characterRepository.findByCharacterDataCharacterName(form.getCharacterName()).isPresent()) {
            throw new DuplicateMemberException("이미 존재하는 캐릭터입니다.");
        }
    }
}
