package com.dalsom.management.user.repository;

import com.dalsom.management.user.UserDetailDto;

import java.util.List;

public interface UserRepositoryCustom {

    List<UserDetailDto> findUserDetailById(Long id);
}
