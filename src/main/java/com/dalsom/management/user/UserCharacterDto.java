package com.dalsom.management.user;

import com.dalsom.management.character.Jobs;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserCharacterDto {

    private String characterName;
    private String guildName;
    private Jobs job;
    private int itemLevel;

    @QueryProjection
    public UserCharacterDto(String characterName, String guildName, Jobs job, int itemLevel) {
        this.characterName = characterName;
        this.guildName = guildName;
        this.job = job;
        this.itemLevel = itemLevel;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserCharacterDto{");
        sb.append("characterName='").append(characterName).append('\'');
        sb.append(", guildName='").append(guildName).append('\'');
        sb.append(", job=").append(job);
        sb.append(", itemLevel=").append(itemLevel);
        sb.append('}');
        return sb.toString();
    }
}
