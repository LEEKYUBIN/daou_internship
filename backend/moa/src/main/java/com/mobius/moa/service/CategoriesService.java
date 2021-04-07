package com.mobius.moa.service;

import com.mobius.moa.domain.repository.CategoriesRepository;
import com.mobius.moa.dto.CategoriesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoriesService {
    private final CategoriesRepository categoriesRepository;

    public List<CategoriesResponseDto> findAll() {
        return categoriesRepository.findAll().stream().map(CategoriesResponseDto::new).collect(Collectors.toList());
    }
}
