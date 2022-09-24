package com.dalsom.management.user;

import com.dalsom.management.common.DuplicateMemberException;
import com.dalsom.management.common.PageObject;
import com.dalsom.management.common.PageParameter;
import com.dalsom.management.common.SearchCondition;
import com.dalsom.management.user.dto.UserDetailDto;
import com.dalsom.management.user.dto.UserJoinForm;
import com.dalsom.management.user.dto.UserListDto;
import com.dalsom.management.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserJoinService userJoinService;
    private final UserService userService;

    @GetMapping
    public String list(PageParameter pageParameter, SearchCondition condition, Model model) {
        PageObject<UserListDto> page = userService.userList(pageParameter, condition);

        model.addAttribute("page", page);
        return "user/user-list";
    }

    @GetMapping("join")
    public String joinForm(@ModelAttribute("form") UserJoinForm form) {
        return "user/user-join";
    }

    @PostMapping("join")
    public String join(@ModelAttribute("form") @Valid UserJoinForm form, BindingResult result, Model model) {
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

    @GetMapping("{userId}")
    public String userDetail(@PathVariable Long userId, Model model) {
        UserDetailDto userDetail = userRepository.findUserDetailById(userId).get(0);
        model.addAttribute("userDetail", userDetail);

        return "user/user-detail";
    }
}
