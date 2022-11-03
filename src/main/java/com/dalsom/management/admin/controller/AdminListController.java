package com.dalsom.management.admin.controller;

import com.dalsom.management.admin.AdminService;
import com.dalsom.management.common.PageParameter;
import com.dalsom.management.common.SearchCondition;
import com.dalsom.management.common.config.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminListController {

    private final AdminService adminService;

    @GetMapping
    public String list(PageParameter pageParameter, SearchCondition condition, Model model) {
        // spring security를 사용한다면 애초에 해당 url에 접근할때 권한을 확인하기에
        // 아래처럼 확인하는 작업이 필요없다

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof PrincipalDetails) {
                PrincipalDetails principalDetails = (PrincipalDetails) principal;
                model.addAttribute("page", adminService.adminList(pageParameter, condition, principalDetails.getRole()));
            }
        }

        return "admin/admin-list";
    }
}
