package com.dalsom.management.user;

import com.dalsom.management.guild.Guilds;
import com.dalsom.management.guild.repository.GuildRepository;
import com.dalsom.management.user.dto.UserJoinForm;
import com.dalsom.management.user.service.UserJoinService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;

@SpringBootTest
@Transactional
@Slf4j
class UserJoinServiceTest {

    @Autowired
    UserJoinService userJoinService;
    @Autowired
    GuildRepository guildRepository;
    @PersistenceContext
    EntityManager em;

    @BeforeEach
    void init(){
        log.debug("==========");
        log.debug("init!!!!");
        log.debug("==========");
        guildRepository.save(new Guilds("달콤한솜사탕", 60));
        guildRepository.save(new Guilds("새콤한솜사탕", 60));
        guildRepository.save(new Guilds("매콤한솜사탕", 60));
        em.flush();
        em.clear();
    }

    @Test
    void joinTest() throws Exception {
        //given
        UserJoinForm form = new UserJoinForm("개발하다샷건침", LocalDate.now(), "건슬링어", 60, 1540);
        userJoinService.join(form, null);

        em.flush();
        em.clear();
        //when

        //then
    }

}