package com.dalsom.management.admin.controller;

import com.dalsom.management.admin.Admin;
import com.dalsom.management.admin.AdminService;
import com.dalsom.management.admin.DuplicateAdminException;
import com.dalsom.management.admin.dto.AdminForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class AdminLoginController {

    private final AdminService adminService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("form") AdminForm form) {
        if (isAlreadyLoggedIn()) {
            return "redirect:/";
        }

        return "login-form";
    }

    private boolean isAlreadyLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!ObjectUtils.isEmpty(authentication)) {
            long count = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .filter(Admin::isCorrectAdminRole)
                    .count();

            return count > 0;
        }

        return false;
    }

    @PostMapping("/login-failed")
    public String loginFailed(@ModelAttribute("form") @Valid AdminForm form, BindingResult result, HttpServletRequest request) {
        Object error = request.getAttribute("error");
        if (!ObjectUtils.isEmpty(error)) {
            result.addError(new ObjectError("form", error.toString()));
        }

        return "login-form";
    }

    @GetMapping("/join")
    public String joinForm(@ModelAttribute("form") AdminForm form) {
        return "join-form";
    }

    @PostMapping("/join")
    public String adminJoin(@ModelAttribute("form") @Valid AdminForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "join-form";
        }

        try {
            adminService.join(form);
        } catch (DuplicateAdminException exception) {
            //TODO refactor ExceptionHandler or ControllerAdvice
            bindingResult.addError(new FieldError("form", "loginId", "이미 사용중인 id입니다"));

            return "join-form";
        }

        return "redirect:/login";
    }
}
