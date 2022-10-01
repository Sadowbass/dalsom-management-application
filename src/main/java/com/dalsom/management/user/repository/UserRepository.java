package com.dalsom.management.user.repository;

import com.dalsom.management.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

    Optional<User> findByJoinApproveAdminName(String adminName);

    @Query("select u from User u join fetch u.mainCharacter where u.id = :id")
    Optional<User> findByIdUsingJPQL(@Param("id") Long id);
}
