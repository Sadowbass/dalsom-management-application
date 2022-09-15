package com.dalsom.management.admin.repository;

import com.dalsom.management.admin.dto.AdminListDto;
import com.dalsom.management.common.PageParameter;
import com.dalsom.management.common.SearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminRepositoryCustom {

    Page<AdminListDto> find(Pageable pageParameter, SearchCondition condition);
}
