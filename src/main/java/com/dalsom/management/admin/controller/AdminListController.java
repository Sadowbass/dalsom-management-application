package com.dalsom.management.admin.controller;

import com.dalsom.management.admin.AdminRole;
import com.dalsom.management.admin.AdminService;
import com.dalsom.management.common.PageParameter;
import com.dalsom.management.common.config.auth.PrincipalDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminListController {

    private final AdminService adminService;

    public AdminListController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public String list(PageParameter pageParameter, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof PrincipalDetails) {
                PrincipalDetails principalDetails = (PrincipalDetails) principal;
                model.addAttribute("page", adminService.adminList(pageParameter, principalDetails.getRole()));
            } else {
                model.addAttribute("page", adminService.adminList(pageParameter, AdminRole.UNKNOWN));
            }
        } else {
            //for test
            model.addAttribute("page", adminService.adminList(pageParameter, AdminRole.MASTER));
        }

        return "admin/admin-list";
    }

    @GetMapping("test")
    @ResponseBody
    public String test(HttpSession session) {
        return "ok";
    }
}
