package com.dalsom.management.admin.dto;

import com.dalsom.management.admin.Admin;
import com.dalsom.management.admin.AdminRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminListDto {

    private Long id;
    private String name;
    private String loginId;
    private AdminRole role;
    private LocalDateTime createdDate;
    private boolean canChange = false;

    public AdminListDto(Long id, String name, String loginId, AdminRole role, LocalDateTime createdDate) {
        this.id = id;
        this.name = name;
        this.loginId = loginId;
        this.role = role;
        this.createdDate = createdDate;
    }

    public static AdminListDto convertAdminToDto(Admin admin) {
        return new AdminListDto(admin.getId(), admin.getName(), admin.getLoginId(), admin.getRole(), admin.getCreatedDate());
    }

    public static AdminListDto convertAdminToDto(Admin admin, AdminRole handler) {
        return new AdminListDto(admin.getId(), admin.getName(), admin.getLoginId(), admin.getRole(), admin.getCreatedDate(), handler.canChangeTarget(admin.getRole()));
    }
}
