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
    private boolean canChange;

    public static AdminListDto convertAdminToDto(Admin admin) {
        return new AdminListDto(admin.getId(), admin.getName(), admin.getLoginId(), admin.getRole(), admin.getCreatedDate(), false);
    }

    public static AdminListDto convertAdminToDto(Admin admin, AdminRole handler) {
        return new AdminListDto(admin.getId(), admin.getName(), admin.getLoginId(), admin.getRole(), admin.getCreatedDate(), handler.canChangeTarget(admin.getRole()));
    }
}
