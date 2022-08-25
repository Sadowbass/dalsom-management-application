package com.dalsom.management.entity;

import com.dalsom.management.entity.enums.GuildRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class GuildCharacters extends BaseEntity {

    @Id
    @Column(name = "guild_character_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guild_id")
    private Guilds guild;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_id")
    private Characters character;

    @Enumerated(value = EnumType.STRING)
    private GuildRole role;
}
