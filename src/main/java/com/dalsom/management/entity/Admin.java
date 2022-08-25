package com.dalsom.management.entity;

import com.dalsom.management.entity.enums.AdminRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Admin extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "adminId")
    private Long id;

    private String loginId;
    private String password;
    private String name;

    @Enumerated(EnumType.STRING)
    private AdminRole role;
}
