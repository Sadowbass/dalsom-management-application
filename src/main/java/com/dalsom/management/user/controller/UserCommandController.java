package com.dalsom.management.user.controller;

import com.dalsom.management.common.DuplicateMemberException;
import com.dalsom.management.user.dto.UserJoinForm;
import com.dalsom.management.user.service.UserJoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserCommandController {

    private final UserJoinService userJoinService;

    @PostMapping("join")
    public String join(@ModelAttribute("form") @Valid UserJoinForm form, BindingResult result, Model model) throws IOException {
        if (result.hasErrors()) {
            return "user/user-join";
        }

        try {
            userJoinService.join(form, null);
            model.addAttribute("form", new UserJoinForm());
        } catch (DuplicateMemberException exception) {
            //TODO refactor ExceptionHandler or ControllerAdvice
            result.addError(new FieldError("form", "characterName", "이미 존재하는 캐릭터입니다."));
        }

        return "user/user-join";
    }
}
