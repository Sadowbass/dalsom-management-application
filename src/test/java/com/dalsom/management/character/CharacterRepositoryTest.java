package com.dalsom.management.character;

import com.dalsom.management.admin.Admin;
import com.dalsom.management.admin.repository.AdminRepository;
import com.dalsom.management.user.User;
import com.dalsom.management.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CharacterRepositoryTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    CharacterRepository characterRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AdminRepository adminRepository;

    Logger logger = LoggerFactory.getLogger("test");

    @BeforeEach
    void init() {
        Admin admin = Admin.createNewAdmin("test", "test", "샷건");
        adminRepository.save(admin);

        User user = User.createNewUser(null, admin);
        userRepository.save(user);

        logger.info("================");
        logger.info("do init flush and clear");
        adminRepository.flush();
        userRepository.flush();
        em.clear();
        logger.info("================");
    }

    @Test
    void save() throws Exception {
        //given
        String name = "개발하다샷건침";
        Jobs job = Jobs.건슬링어;
        int level = 60;
        int itemLevel = 1540;

        User user = userRepository.findByJoinApproveAdminName("샷건").orElseThrow(RuntimeException::new);
        CharacterData charData = new CharacterData(name, job, level, itemLevel);
        Characters character = Characters.createNewCharacter(user, charData);

        //when
        characterRepository.save(character);
        characterRepository.flush();
        em.clear();

        //then
        Characters savedChar = characterRepository.findById(character.getId()).orElseThrow(RuntimeException::new);
        assertThat(savedChar).isNotEqualTo(character);
        assertThat(savedChar.getId()).isEqualTo(character.getId());
    }
}