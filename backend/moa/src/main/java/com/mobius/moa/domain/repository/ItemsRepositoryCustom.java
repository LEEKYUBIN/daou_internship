package com.mobius.moa.domain.repository;


import com.mobius.moa.dto.ItemResponseDto;
import com.mobius.moa.dto.ItemDetailDto;

import java.util.List;

public interface ItemsRepositoryCustom {
    List<ItemResponseDto> searchAllItems(String category, int offset, int limit);

    List<ItemResponseDto> searchItemsByAttribute(String category, long count, List<String> attributesList, int offset, int limit);

    long searchItemsCount(String category);

    long searchItemsByAttributeCount(String category, long count, List<String> attributesList);

    List<ItemDetailDto> findItemDetail(int no);

}
