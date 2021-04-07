package com.mobius.moa.service;

import com.mobius.moa.domain.Information;
import com.mobius.moa.domain.Items;
import com.mobius.moa.dto.ItemsResponseDto;
import com.mobius.moa.repository.CategoriesRepository;
import com.mobius.moa.repository.InformationRepository;
import com.mobius.moa.repository.ItemsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ItemsService {
    private final ItemsRepository itemsRepository;
    private final CategoriesRepository categoriesRepository;
    private final InformationRepository informationRepository;


    public List<ItemsResponseDto> find(int i) {
        return itemsRepository.find(i).stream().map(ItemsResponseDto::new).collect(Collectors.toList());
    }

    public List<ItemsResponseDto> getItemsListByText(String categoriesName, String attributesText, int pageIdx) {
        int categoriesNo = categoriesRepository.findByName(categoriesName).getNo();
        List<Information> informationList = informationRepository.findByAttributeText(attributesText);
        int idx = 0;
        Map<Integer, Integer> itemNoSet = new HashMap<>();
        for (Information information : informationList) {
            Items items = information.getItems();
            if (items.getCategories().getNo() == categoriesNo && items.getAttributesText().contains(attributesText)) {
                itemNoSet.put(idx, items.getNo());
                idx++;
            }
        }

        List<ItemsResponseDto> itemsList = new ArrayList<>();
        if (6*pageIdx > itemNoSet.size()){
            return itemsList;
        }
        for(int i = 6*pageIdx; i < 6*(pageIdx+1) ; i++){
            if (i == itemNoSet.size()) {
                return itemsList;
            }
            else{
                itemsList.add(new ItemsResponseDto(itemsRepository.findByItemsNo(itemNoSet.get(i))));
            }

        }
        return itemsList;
    }
}
