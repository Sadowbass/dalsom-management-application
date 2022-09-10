package com.dalsom.management.admin;

import com.dalsom.management.admin.dto.AdminApprovalDto;
import com.dalsom.management.admin.dto.AdminForm;
import com.dalsom.management.admin.dto.AdminListDto;
import com.dalsom.management.common.PageObject;
import com.dalsom.management.common.PageParameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Long join(AdminForm form) {
        validDuplicateAdmin(form.getLoginId());

        Admin admin = Admin.createNewAdmin(form.getLoginId(), form.getPassword(), form.getName());
        adminRepository.save(admin);

        return admin.getId();
    }

    private void validDuplicateAdmin(String loginId) {
        if (adminRepository.findByLoginId(loginId).isPresent()) {
            throw new DuplicateAdminException();
        }
    }

    public PageObject<AdminListDto> adminList(PageParameter pageParameter, AdminRole role) {
        Page<Admin> adminPage = adminRepository.findAllByStatus(AdminStatus.APPROVED, pageParameter.toPageable());
        Page<AdminListDto> dtoPage = adminPage.map(admin -> AdminListDto.convertAdminToDto(admin, role));
        return new PageObject<>(dtoPage);
    }

    public List<AdminApprovalDto> approvalList() {
        Sort orders = Sort.by(Sort.Direction.ASC, "createdBy");
        List<Admin> adminPage = adminRepository.findAllByStatus(AdminStatus.NOT_APPROVED, orders);

        return adminPage.stream().map(AdminApprovalDto::convertAdminToDto).collect(Collectors.toList());
    }
}
