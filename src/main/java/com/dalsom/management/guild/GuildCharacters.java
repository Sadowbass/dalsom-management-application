package com.dalsom.management.guild;

import com.dalsom.management.common.entity.BaseEntity;
import com.dalsom.management.character.Characters;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;


/**
 * 이걸 별개의 엔티티로 만들것이냐
 * guild 애그리거트에 속한 value로서 elementCollection으로 만들것이냐 고민중
 * 캐릭터와는 별개로 길드에 속한 캐릭터임으로 일단 value로 처리
 */
@Embeddable
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GuildCharacters extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_id")
    private Characters character;

    @Enumerated(value = EnumType.STRING)
    private GuildRole role;

    public GuildCharacters(Characters character, GuildRole role) {
        this.character = character;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GuildCharacters that = (GuildCharacters) o;
        return getCharacter().equals(that.getCharacter());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCharacter());
    }
}
