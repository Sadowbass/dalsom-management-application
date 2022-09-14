package com.dalsom.management;

import com.dalsom.management.admin.Admin;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.dalsom.management.admin.QAdmin.admin;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class QueryDslTest {

    @PersistenceContext
    EntityManager em;
    JPAQueryFactory queryFactory;

    @BeforeEach
    void init() {
        queryFactory = new JPAQueryFactory(em);
    }

    @Test
    void test() {
        Admin newAdmin = Admin.createNewAdmin("testId1", "pwd", "test");
        em.persist(newAdmin);
        em.flush();

        List<Admin> result = queryFactory.select(admin).from(admin).fetch();

        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(newAdmin);
    }
}
