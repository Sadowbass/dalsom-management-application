package com.dalsom.management.admin.repository;

import com.dalsom.management.admin.AdminRole;
import com.dalsom.management.admin.dto.AdminListDto;
import com.dalsom.management.admin.dto.QAdminListDto;
import com.dalsom.management.common.SearchCondition;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

import static com.dalsom.management.admin.QAdmin.admin;

@Component
public class AdminRepositoryImpl implements AdminRepositoryCustom {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public AdminRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<AdminListDto> find(Pageable pageParameter, SearchCondition condition) {
        // 복잡한 쿼리등에서 정상작동을 하지 않아 별도로 자바단에서 count 쿼리를 날리도록 deprecate되었지만
        // 여기에서는 복잡한 쿼리가 아님으로 fetchResult를 이용함
        QueryResults<AdminListDto> results = queryFactory
                .select(new QAdminListDto(admin.id, admin.name, admin.loginId, admin.role, admin.createdDate))
                .from(admin)
                .offset(pageParameter.getOffset())
                .limit(pageParameter.getPageSize())
                .where(
                        searchCondition(condition.getCategory(), condition.getKeyword())
                ).fetchResults();

        return new PageImpl<>(results.getResults(), pageParameter, results.getTotal());
    }

    public BooleanExpression searchCondition(String category, String keyword) {
        return category != null ? admin.name.like("%" + keyword + "%") : null;
    }
}
