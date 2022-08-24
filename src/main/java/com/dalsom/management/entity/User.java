package com.dalsom.management.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_name")
    private Characters mainCharacter;

    private String madisonName;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    private LocalDateTime joinDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "join_approver")
    private Admin joinApprover;

    private LocalDateTime leaveDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leave_approver")
    private Admin leaveApprover;

    @OneToMany(mappedBy = "user")
    private List<Characters> characters = new ArrayList<>();
}
