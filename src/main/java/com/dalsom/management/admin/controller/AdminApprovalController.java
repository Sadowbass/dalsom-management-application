package com.dalsom.management.admin.controller;

import com.dalsom.management.admin.Admin;
import com.dalsom.management.admin.AdminRepository;
import com.dalsom.management.admin.AdminService;
import com.dalsom.management.admin.dto.AdminApprovalDto;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminApprovalController {

    private final AdminService adminService;
    private final AdminRepository adminRepository;

    public AdminApprovalController(AdminService adminService, AdminRepository adminRepository) {
        this.adminService = adminService;
        this.adminRepository = adminRepository;
    }

    @GetMapping("approval")
    public String showList(Model model) {
        List<AdminApprovalDto> list = adminService.approvalList();
        model.addAttribute("list", list);

        return "admin/admin-approval";
    }

    @PostMapping("approval/{id}")
    @ResponseBody
    @Transactional
    public String approve(@PathVariable long id) {
        Admin admin = adminRepository.findById(id).orElseThrow(RuntimeException::new);
        admin.approveJoin();

        return "{\"rsp-msg\": \"ok\"}";
    }

    @PostMapping("approval/refuse/{id}")
    @ResponseBody
    @Transactional
    public String refuse(@PathVariable long id) {
        Admin admin = adminRepository.findById(id).orElseThrow(RuntimeException::new);
        adminRepository.delete(admin);

        return "{\"rsp-msg\": \"ok\"}";
    }
}
