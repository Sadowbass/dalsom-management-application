package com.dalsom.management.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Guilds extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "guild_id")
    private Long id;

    private String guildName;
    private int maxMember;
}
