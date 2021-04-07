package com.mobius.moa.service;

import com.mobius.moa.domain.Categories;
import com.mobius.moa.dto.CategoriesResponseDto;
import com.mobius.moa.dto.CategoriesSaveRequestDto;
import com.mobius.moa.repository.CategoriesRepository;
import com.mobius.moa.domain.Categories;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CategoriesService {
    private final CategoriesRepository categoriesRepository;

    @Transactional
    public void save(CategoriesSaveRequestDto requestDto) {
        categoriesRepository.save(requestDto.toEntity());
    }

    public List<CategoriesResponseDto> findAll() {
        return categoriesRepository.findAll().stream().map(CategoriesResponseDto::new).collect(Collectors.toList());
    }
//    public void add(int i, int j){
//        Categories categories = new Categories();
//        어떤 변수 = categories.getName(1) + categories.getName(2);
//    }
}
