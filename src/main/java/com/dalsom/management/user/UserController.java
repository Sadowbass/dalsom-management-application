package com.dalsom.management.user;

import com.dalsom.management.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    public String list() {
        return "user/user-list";
    }

    @GetMapping("{userId}")
    public String userDetail(@PathVariable Long userId, Model model) {
        UserDetailDto userDetail = userRepository.findUserDetailById(userId).get(0);
        model.addAttribute("userDetail", userDetail);

        return "user/user-detail";
    }
}
