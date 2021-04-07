package com.mobius.moa.domain.repository;

import com.mobius.moa.dto.ItemDetailDto;
import com.mobius.moa.dto.ItemResponseDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.mobius.moa.domain.entity.QAttributes.attributes;
import static com.mobius.moa.domain.entity.QFilters.filters;
import static com.mobius.moa.domain.entity.QInformation.information;
import static com.mobius.moa.domain.entity.QItems.items;

@RequiredArgsConstructor
public class ItemsRepositoryImpl implements ItemsRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ItemResponseDto> searchAllItems(String category, int offset, int limit) {
        return queryFactory
                .select(Projections.constructor(ItemResponseDto.class, items))
                .from(items)
                .where(categoryEq(category))
                .offset(offset)
                .limit(limit)
                .fetch();
    }

    @Override
    public List<ItemResponseDto> searchItemsByAttribute(String category, long count, List<String> attributeList, int offset, int limit) {
        return queryFactory
                .select(Projections.constructor(ItemResponseDto.class, items))
                .from(items)
                .leftJoin(information)
                .on(items.no.eq(information.items.no))
                .where(attributeEq(attributeList), categoryEq(category))
                .groupBy(items.no)
                .having(items.no.count().eq(count))
                .offset(offset)
                .limit(limit)
                .fetch();
    }

    @Override
    public long searchItemsCount(String category) {
        return queryFactory
                .select(Projections.constructor(ItemResponseDto.class, items))
                .from(items)
                .where(categoryEq(category)).fetch().size();
    }

    @Override
    public long searchItemsByAttributeCount(String category, long count, List<String> attributeList) {
        return queryFactory
                .select(Projections.constructor(ItemResponseDto.class, items))
                .from(items)
                .leftJoin(information)
                .on(items.no.eq(information.items.no))
                .where(attributeEq(attributeList), categoryEq(category))
                .groupBy(items.no)
                .having(items.no.count().eq(count))
                .fetch().size();
    }

    @Override
    public List<ItemDetailDto> findItemDetail(int no) {
        return queryFactory
                .select(Projections.constructor(ItemDetailDto.class, filters.name, information.attributeText))
                .from(filters, attributes, items, information)
                .where(filters.no.eq(attributes.filters.no),
                        attributes.no.eq(information.attributes.no),
                        items.no.eq(information.items.no),
                        items.no.eq(no))
                .fetch();
    }

    private BooleanExpression categoryEq(String category) {
        return items.categories.text.eq(category);
    }

    private BooleanBuilder attributeEq(List<String> attributeList) {
        BooleanBuilder builder = new BooleanBuilder();
        for (String attribute : attributeList) {
            builder.or(information.attributeText.contains(attribute));
        }
        return builder;
    }
}