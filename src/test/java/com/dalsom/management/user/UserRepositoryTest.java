package com.dalsom.management.user;

import com.dalsom.management.admin.Admin;
import com.dalsom.management.admin.AdminRepository;
import com.dalsom.management.admin.AdminRole;
import com.dalsom.management.character.CharacterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CharacterRepository characterRepository;

    @PersistenceContext
    EntityManager em;

    @BeforeEach
    void init() {
        Admin admin = Admin.createNewAdmin("test", "test", "샷건");
        admin.changeAdminRole(AdminRole.DEVELOPER);

        adminRepository.save(admin);
        adminRepository.flush();
        em.clear();
    }

    @Test
    void save() throws Exception {
        //given
        Admin admin = adminRepository.findAllByRole(AdminRole.DEVELOPER).get(0);

        User user = User.createNewUser(LocalDateTime.now(), admin);
        userRepository.save(user);
        em.flush();
        em.clear();

        //when
        User savedUser = userRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException("no result"));
        List<User> userList = userRepository.findAll();

        //then
        assertThat(userList).hasSize(1);
        assertThat(userList.get(0)).isEqualTo(savedUser);
        assertThat(savedUser.getId()).isEqualTo(user.getId());
    }
}