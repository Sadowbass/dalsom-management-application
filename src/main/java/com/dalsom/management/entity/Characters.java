package com.dalsom.management.entity;

import com.dalsom.management.entity.enums.Jobs;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Characters extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "character_id")
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String characterName;

    @Enumerated(EnumType.STRING)
    private Jobs job;

    private int level;
    private int itemLevel;
}
