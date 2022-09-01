package com.dalsom.management.admin;

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
class AdminRepositoryTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    AdminRepository adminRepository;

    @Test
    void save() {
        //given
        Admin admin = Admin.createNewAdmin("test", "test", "샷건");

        adminRepository.save(admin); // for check query
        em.flush();
        em.clear();

        //when
        Admin savedAdmin = adminRepository.findById(admin.getId()).get();

        //then
        assertThat(savedAdmin).isNotNull();
        assertThat(admin.getId()).isNotNull();
        assertThat(admin.getId()).isEqualTo(savedAdmin.getId());
        assertThat(admin.getName()).isEqualTo(savedAdmin.getName());
    }

    @Test
    void delete() throws Exception {
        //given
        Admin admin = Admin.createNewAdmin("test", "test", "샷건");
        adminRepository.save(admin);
        em.flush();

        //when
        adminRepository.delete(admin);
        em.flush();

        //then
        Optional<Admin> result = adminRepository.findById(admin.getId());
        assertThat(result).isEmpty();
        assertThatThrownBy(result::get).isInstanceOf(NoSuchElementException.class);
    }
}