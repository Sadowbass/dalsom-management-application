package com.dalsom.management.admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;
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

    @Test
    void approvalList() throws Exception {
        //given
        Admin admin1 = Admin.createNewAdmin("id1", "qwe", "name1");
        Admin admin2 = Admin.createNewAdmin("id2", "qwe", "name2");
        Admin admin3 = Admin.createNewAdmin("id3", "qwe", "name3");
        Admin admin4 = Admin.createNewAdmin("id4", "qwe", "name4");
        Admin admin5 = Admin.createNewAdmin("id5", "qwe", "name5");

        adminRepository.saveAll(Arrays.asList(admin1, admin2, admin3, admin4, admin5));
        adminRepository.flush();
        em.clear();

        //when
        List<Admin> result = adminRepository.findAllByStatus(AdminStatus.NOT_APPROVED, Sort.by(Sort.Direction.ASC, "createdDate"));

        //then
        assertThat(result).hasSize(2);
    }
}