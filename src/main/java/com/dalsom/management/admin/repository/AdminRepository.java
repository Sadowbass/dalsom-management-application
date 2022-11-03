package com.dalsom.management.admin.repository;

import com.dalsom.management.admin.Admin;
import com.dalsom.management.admin.AdminRole;
import com.dalsom.management.admin.AdminStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>, AdminRepositoryCustom {

    Optional<Admin> findByLoginId(String loginId);

    List<Admin> findAllByRole(AdminRole role);

    List<Admin> findAllByStatus(AdminStatus status, Sort sort);
}

