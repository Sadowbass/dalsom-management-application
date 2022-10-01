package com.dalsom.management.character.lostarkapi;

import com.dalsom.management.character.CannotFindCharacterException;
import com.dalsom.management.character.Jobs;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.dalsom.management.character.lostarkapi.LostArkHtmlParseConstants.*;

@Service
public class CharacterInfoService {

    private static final String LOSTARK_DEFAULT_URL = "https://lostark.game.onstove.com/Profile/Character/";

    public List<CharacterInfoDto> createAllCharacterInfo(String requestCharacterName) throws IOException {
        Document html = connectLostArk(requestCharacterName);

        Elements servers = html.body().getElementsByClass(CLASS_PROFILE_SERVER);
        Element serverCharacterUl = getCharactersUl(servers);
        List<CharacterInfoDto> allCharacter = getAllCharacter(serverCharacterUl);

        return allCharacter.stream()
                .filter(dto -> !dto.getCharacterName().equals(requestCharacterName))
                .collect(Collectors.toList());
    }

    private Document connectLostArk(String requestCharacterName) throws IOException {
        return Jsoup.connect(LOSTARK_DEFAULT_URL + requestCharacterName).get();
    }

    private Element getCharactersUl(Elements servers) {
        for (Element server : servers) {
            String characterServerName = server.text().replace("@", "");
            if ("아만".equals(characterServerName)) {
                return server.nextElementSibling();
            }
        }

        throw new IllegalArgumentException("아만 서버 캐릭터를 찾을수 없습니다");
    }

    private List<CharacterInfoDto> getAllCharacter(Element serverCharacterUl) throws IOException {
        List<CharacterInfoDto> list = new ArrayList<>();

        Elements children = serverCharacterUl.children();
        for (Element child : children) {
            String characterName = child.getElementsByTag("span").get(1).ownText();
            list.add(createCharacterInfo(characterName));
        }

        return list;
    }

    public CharacterInfoDto createCharacterInfo(String requestCharacterName) throws IOException {
        Document html = connectLostArk(requestCharacterName);
        verifyCharacter(html);

        String characterName = getCharacterName(html);
        Jobs job = getJob(html);
        int combatLevel = getLevel(html, CLASS_LEVEL_COMBAT);
        int itemLevel = getLevel(html, CLASS_LEVEL_ITEM);
        String guild = getGuild(html);

        return new CharacterInfoDto(characterName, job, combatLevel, itemLevel, guild);
    }

    private void verifyCharacter(Document html) throws CannotFindCharacterException {
        int size = html.body().getElementsByClass(CLASS_CHARACTER_NOT_FOUND).size();
        if (size > 0) {
            throw new CannotFindCharacterException("캐릭터명을 확인 할 수 없습니다");
        }
    }

    private String getCharacterName(Document html) {
        Element character = getElementByClassName(html, CLASS_CHARACTER_NAME);
        return character.attr(ATTR_CHARACTER_NAME);
    }

    private Jobs getJob(Document html) {
        Element profile = getElementByClassName(html, CLASS_CHARACTER_INFO);
        Element profileChild = getChild(profile, 0);
        String profileAlt = profileChild.attr(ATTR_CHARACTER_JOB);

        return Jobs.findJob(profileAlt);
    }

    private int getLevel(Document html, String levelType) {
        Element element = getElementByClassName(html, levelType);
        Element elementChild = getChild(element, 1);

        return Integer.parseInt(elementChild.ownText().trim().replace(",", ""));
    }

    private String getGuild(Document html) {
        Element guild = getElementByClassName(html, CLASS_GUILD_NAME);
        Element guildChild = getChild(guild, 1);

        return guildChild.ownText();
    }


    private Element getElementByClassName(Document html, String className) {
        return html.body().getElementsByClass(className).get(0);
    }

    private Element getChild(Element element, int index) {
        return element.child(index);
    }
}
