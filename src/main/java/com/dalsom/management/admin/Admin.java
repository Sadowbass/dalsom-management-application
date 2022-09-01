package com.dalsom.management.admin;

import com.dalsom.management.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
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

    protected Admin() {

    }

    public static Admin createNewAdmin(String loginId, String password, String name) {
        Admin admin = new Admin();
        admin.setLoginId(loginId);
        admin.setPassword(password);
        admin.setName(name);
        admin.setRole(AdminRole.ADMIN);

        return admin;
    }

    public void changeAdminRole(AdminRole role) {
        this.role = role;
    }

    public void changeName(String name) {
        this.name = name;
    }
}
