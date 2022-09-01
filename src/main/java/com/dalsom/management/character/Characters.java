package com.dalsom.management.character;

import com.dalsom.management.common.entity.BaseEntity;
import com.dalsom.management.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
public class Characters extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "character_id")
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Embedded
    private CharacterData characterData;

    protected Characters() {

    }

    public static Characters createNewCharacter(User user, CharacterData characterData) {
        characterData.verifyCharacterData();

        Characters character = new Characters();
        character.setCharacterData(characterData);
        return character;
    }

    public void changeCharacterData(CharacterData characterData) {
        characterData.verifyCharacterData();
        this.characterData = characterData;
    }
}
