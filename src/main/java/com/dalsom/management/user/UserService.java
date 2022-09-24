package com.dalsom.management.user;

import com.dalsom.management.common.PageObject;
import com.dalsom.management.common.PageParameter;
import com.dalsom.management.common.SearchCondition;
import com.dalsom.management.user.dto.UserListDto;
import com.dalsom.management.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public PageObject<UserListDto> userList(PageParameter pageParameter, SearchCondition condition) {
        return new PageObject<>(userRepository.findUserListDtoPage(pageParameter.toPageable(), condition));
    }
}
