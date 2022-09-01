package com.dalsom.management.guild;

import com.dalsom.management.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Guilds extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "guild_id")
    private Long id;

    @Column(unique = true)
    private String guildName;

    private int maxCapacity;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "guild_characters", joinColumns = {@JoinColumn(name = "guild_id")})
    private List<GuildCharacters> guildCharacters = new ArrayList<>();

    public Guilds(String guildName, int maxCapacity) {
        this.guildName = guildName;
        this.maxCapacity = maxCapacity;
    }

    public List<GuildCharacters> getGuildCharacters() {
        return Collections.unmodifiableList(guildCharacters);
    }

    public void addCharacter(GuildCharacters guildCharacter) {
        guildCharacters.add(guildCharacter);
    }

    public void removeCharacter(GuildCharacters guildCharacter) {
        guildCharacters.remove(guildCharacter);
    }

    public void changeMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int calcRemainSpaces() {
        return maxCapacity - guildCharacters.size();
    }
}
