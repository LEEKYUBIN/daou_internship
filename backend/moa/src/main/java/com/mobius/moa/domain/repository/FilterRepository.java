package com.mobius.moa.domain.repository;

import com.mobius.moa.domain.entity.Filters;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.mobius.moa.domain.entity.QCategories.categories;
import static com.mobius.moa.domain.entity.QFilters.filters;

@Repository
public class FilterRepository extends QuerydslRepositorySupport {
    private JPAQueryFactory queryFactory;

    public FilterRepository(JPAQueryFactory queryFactory) {
        super(Filters.class);
        this.queryFactory = queryFactory;
    }

    public List<Filters> findByCategory(String category) {
        return queryFactory
                .selectFrom(filters)
                .innerJoin(filters.categories, categories).on(categories.text.eq(category))
                .fetch();
    }

}