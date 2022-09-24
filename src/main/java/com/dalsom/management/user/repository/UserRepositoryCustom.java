package com.dalsom.management.user.repository;

import com.dalsom.management.common.SearchCondition;
import com.dalsom.management.user.dto.UserDetailDto;
import com.dalsom.management.user.dto.UserListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserRepositoryCustom {

    Page<UserListDto> findUserListDtoPage(Pageable pageParameter, SearchCondition searchCondition);

    List<UserDetailDto> findUserDetailById(Long id);
}
