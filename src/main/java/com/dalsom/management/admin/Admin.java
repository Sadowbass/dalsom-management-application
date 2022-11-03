package com.dalsom.management.admin;

import com.dalsom.management.common.PasswordEncoderFactory;
import com.dalsom.management.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Admin extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "admin_id")
    private Long id;

    @Column(unique = true)
    private String loginId;
    private String password;
    private String name;

    @Enumerated(EnumType.STRING)
    private AdminRole role;

    @Enumerated(EnumType.STRING)
    private AdminStatus status;

    public static Admin createNewAdmin(String loginId, String password, String name) {
        Admin admin = new Admin();
        admin.setLoginId(loginId);
        admin.setPassword(password);
        admin.setName(name);
        admin.setRole(AdminRole.ADMIN);
        admin.setStatus(AdminStatus.NOT_APPROVED);

        return admin;
    }

    public static boolean isCorrectAdminRole(String role) {
        try {
            AdminRole adminRole = AdminRole.valueOf(role);
            return adminRole != AdminRole.UNKNOWN;
        } catch (Exception e) {
            return false;
        }
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changeAdminRole(AdminRole role) {
        this.role = role;
    }

    public void approveJoin() {
        this.status = AdminStatus.APPROVED;
    }

    private void setPassword(String password) {
        this.password = PasswordEncoderFactory.getDefaultEncoder().encode(password);
    }
}
