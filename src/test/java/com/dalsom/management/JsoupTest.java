package com.dalsom.management;

import com.dalsom.management.character.Jobs;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.util.List;

class JsoupTest {

    @Test
    void jsoupTest() throws Exception {
        Document doc = Jsoup.connect("https://lostark.game.onstove.com/Profile/Character/%EA%B0%9C%EB%B0%9C%ED%95%98%EB%8B%A4%EC%83%B7%EA%B1%B4%EC%B9%A8").get();

        Element combat = doc.body().getElementsByClass("level-info__item").get(0);
        Element combatChild = combat.child(1);
        int combatLevel = Integer.parseInt(combatChild.ownText().trim().replace(",", ""));
        System.out.println("combatLevel = " + combatLevel);

        Element item = doc.body().getElementsByClass("level-info2__expedition").get(0);
        Element itemChild = item.child(1);
        int itemLevel = Integer.parseInt(itemChild.ownText().trim().replace(",", ""));
        System.out.println("itemLevel = " + itemLevel);

        Element profile = doc.body().getElementsByClass("profile-character-info").get(0);
        Element profileChild = profile.child(0);
        String profileAlt = profileChild.attr("alt");
        Jobs job = Jobs.findJob(profileAlt);
        System.out.println("job = " + job);

        String characterName = doc.body().getElementsByClass("profile-character-info__name").get(0).attr("title");
        System.out.println("characterName = " + characterName);
    }

    @Test
    void noCharacterTest() throws Exception {
        Document doc = Jsoup.connect("https://lostark.game.onstove.com/Profile/Character/개발하다샷건침2").get();

        Elements title = doc.getElementsByClass("title");
        System.out.println("title.size() = " + title.size());
    }

    @Test
    void characterListTest() throws Exception {
        Document doc = Jsoup.connect("https://lostark.game.onstove.com/Profile/Character/개발하다샷건침").get();

        Elements servers = doc.body().getElementsByClass("profile-character-list__server");
        for (Element server : servers) {
            String serverName = server.text().replace("@", "");
            if ("아만".equals(serverName)) {
                Element characterUl = server.nextElementSibling();
                Elements children = characterUl.children();
                for (Element child : children) {
                    String characterName = child.getElementsByTag("span").get(1).ownText();
                    System.out.println("characterName = " + characterName);
                }
            }
        }

    }


}
