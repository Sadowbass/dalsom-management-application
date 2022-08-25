package com.dalsom.management.entity;

import com.dalsom.management.entity.enums.AdminRole;
import com.dalsom.management.repository.AdminRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class EntityTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    AdminRepository adminRepository;

    @Test
    void signInAdmin() {
        //given
        Admin admin = new Admin();
        admin.setName("샷건");
        admin.setLoginId("asd");
        admin.setPassword("asd");
        admin.setRole(AdminRole.DEVELOPER);
        adminRepository.saveAndFlush(admin); // for check query

        //when
        Admin savedAdmin = adminRepository.findById(admin.getId()).get();

        //then
        assertThat(savedAdmin).isNotNull();
        assertThat(admin.getId()).isNotNull();
        assertThat(admin.getId()).isEqualTo(savedAdmin.getId());
        assertThat(admin.getName()).isEqualTo(savedAdmin.getName());
    }

    @Test
    void deleteAdmin() throws Exception {
        //given
        Admin admin = new Admin();
        admin.setName("샷건");
        admin.setLoginId("asd");
        admin.setPassword("asd");
        admin.setRole(AdminRole.DEVELOPER);

        adminRepository.save(admin);
        adminRepository.flush();

        //when
        adminRepository.delete(admin);
        adminRepository.flush();

        //then
        Optional<Admin> result = adminRepository.findById(admin.getId());
        assertThat(result).isEmpty();
        assertThatThrownBy(result::get).isInstanceOf(NoSuchElementException.class);
    }
}
