package com.dalsom.management.character.lostarkapi;

import com.dalsom.management.character.Jobs;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CharacterInfoDto {

    private String characterName;
    private Jobs job;
    private int combatLevel;
    private int itemLevel;
    private String guild;
}
