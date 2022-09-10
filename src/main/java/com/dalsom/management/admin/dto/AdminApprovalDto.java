package com.dalsom.management.admin.dto;

import com.dalsom.management.admin.Admin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminApprovalDto {

    private Long id;
    private String loginId;
    private String name;
    private LocalDateTime applicationDate;

    public static AdminApprovalDto convertAdminToDto(Admin admin) {
        return new AdminApprovalDto(admin.getId(), admin.getLoginId(), admin.getName(), admin.getCreatedDate());
    }
}
