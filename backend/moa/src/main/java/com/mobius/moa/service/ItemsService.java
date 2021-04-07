package com.mobius.moa.service;

import com.mobius.moa.domain.repository.ItemsRepository;
import com.mobius.moa.dto.ItemDetailDto;
import com.mobius.moa.dto.ItemResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class ItemsService {
    private final ItemsRepository itemsRepository;

    public List<ItemResponseDto> searchAllItems(String category, int offset, int limit) {
        return itemsRepository.searchAllItems(category, offset, limit);
    }

    public List<ItemResponseDto> searchItemsByAttribute(String category, Map<String, String> attributesMap, int offset, int limit) {
        long mapSize = attributesMap.size();
        List<String> attributeList = new ArrayList<>();
        for (String value : attributesMap.values()) {
            attributeList.addAll(Arrays.asList(value.split(",")));
        }
        return itemsRepository.searchItemsByAttribute(category, mapSize, attributeList, offset, limit);
    }

    public long searchItemsCount(String category) {
        return itemsRepository.searchItemsCount(category);
    }

    public long searchItemsByAttributeCount(String category, Map<String, String> attributesMap) {
        long mapSize = attributesMap.size();
        List<String> attributeList = new ArrayList<>();
        for (String value : attributesMap.values()) {
            attributeList.addAll(Arrays.asList(value.split(",")));
        }
        return itemsRepository.searchItemsByAttributeCount(category, mapSize, attributeList);
    }

    public List<List<ItemDetailDto>> getItemInformation(List<Integer> items) {
        List<List<ItemDetailDto>> itemsDtoList = items
                .stream()
                .map(itemNo -> itemsRepository.findItemDetail(itemNo))
                .collect(Collectors.toList());
        return itemsDtoList;
    }
}
