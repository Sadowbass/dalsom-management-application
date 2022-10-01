package com.dalsom.management.character;

import com.dalsom.management.character.lostarkapi.CharacterInfoDto;
import com.dalsom.management.character.lostarkapi.CharacterInfoService;
import com.dalsom.management.guild.GuildCharacters;
import com.dalsom.management.guild.GuildRole;
import com.dalsom.management.guild.Guilds;
import com.dalsom.management.guild.repository.GuildRepository;
import com.dalsom.management.user.User;
import com.dalsom.management.user.dto.UserJoinForm;
import com.dalsom.management.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CharacterRegistryService {

    private final UserRepository userRepository;
    private final CharacterRepository characterRepository;
    private final CharacterInfoService characterInfoService;
    private final GuildRepository guildRepository;

    public Characters registerCharacter(User user, UserJoinForm form) {
        return saveCharacter(user, form.getCharacterName(), form.getJob(), form.getLevel(), form.getItemLevel());
    }

    public void registerAllCharacter(Long userId) throws IOException {
        User user = userRepository.findByIdUsingJPQL(userId).get();
        String characterName = user.getMainCharacter().getCharacterData().getCharacterName();

        List<CharacterInfoDto> allCharacterInfo = characterInfoService.createAllCharacterInfo(characterName);
        Map<String, Guilds> allByGuildName = guildRepository.findAllByGuildName(getCharactersGuilds(allCharacterInfo));

        for (CharacterInfoDto dto : allCharacterInfo) {
            Characters newCharacter = saveCharacter(user, dto.getCharacterName(), dto.getJob(), dto.getCombatLevel(), dto.getItemLevel());

            if (isOurGuild(allByGuildName, dto.getGuild())) {
                Guilds guilds = allByGuildName.get(dto.getGuild());
                guilds.addCharacter(new GuildCharacters(newCharacter, GuildRole.MEMBER));
            }
        }
    }

    private Characters saveCharacter(User user, String characterName, Jobs job, Integer level, Integer itemLevel) {
        Characters newCharacter = Characters.createNewCharacter(user, new CharacterData(
                characterName,
                job,
                level,
                itemLevel)
        );
        characterRepository.save(newCharacter);

        return newCharacter;
    }

    private List<String> getCharactersGuilds(List<CharacterInfoDto> allCharacterInfo) {
        return allCharacterInfo.stream()
                .map(CharacterInfoDto::getGuild)
                .collect(Collectors.toList());
    }

    private boolean isOurGuild(Map<String, Guilds> allByGuildName, String guildName) {
        return allByGuildName.containsKey(guildName);
    }
}
