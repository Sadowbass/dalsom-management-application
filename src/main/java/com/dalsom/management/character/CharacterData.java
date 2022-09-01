package com.dalsom.management.character;

import lombok.*;
import org.springframework.util.ObjectUtils;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@Embeddable
@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CharacterData {

    private String characterName;

    @Enumerated(EnumType.STRING)
    private Jobs job;

    private int level;
    private int itemLevel;

    public void verifyCharacterData() {
        if (ObjectUtils.isEmpty(characterName)) {
            throw new IllegalArgumentException("character name is required");
        } else if (ObjectUtils.isEmpty(job)) {
            throw new IllegalArgumentException("job is required");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CharacterData newData = (CharacterData) o;
        return getCharacterName().equals(newData.getCharacterName())
                && getJob() == newData.getJob()
                && getLevel() == newData.getLevel()
                && getItemLevel() == newData.getItemLevel();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCharacterName(), getJob(), getLevel(), getItemLevel());
    }
}
