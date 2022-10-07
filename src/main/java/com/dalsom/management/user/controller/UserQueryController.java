package com.dalsom.management.user.controller;

import com.dalsom.management.common.PageObject;
import com.dalsom.management.common.PageParameter;
import com.dalsom.management.common.SearchCondition;
import com.dalsom.management.user.dto.UserDetailDto;
import com.dalsom.management.user.dto.UserJoinForm;
import com.dalsom.management.user.dto.UserListDto;
import com.dalsom.management.user.repository.UserRepository;
import com.dalsom.management.user.service.UserJoinService;
import com.dalsom.management.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserPageController {

    private final UserRepository userRepository;
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

    @GetMapping("{userId}")
    public String userDetail(@PathVariable Long userId, Model model) {
        UserDetailDto userDetail = userRepository.findUserDetailById(userId).get(0);
        model.addAttribute("userDetail", userDetail);

        return "user/user-detail";
    }
}
