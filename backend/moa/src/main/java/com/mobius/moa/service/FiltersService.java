package com.mobius.moa.service;

import com.mobius.moa.domain.entity.Filters;
import com.mobius.moa.domain.repository.FilterRepository;
import com.mobius.moa.dto.FiltersResponseDto;
import com.mobius.moa.web.exception.CategoryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FiltersService {
    private final FilterRepository filterRepository;

    public List<FiltersResponseDto> getFiltersList(String category) {
        List<Filters> filtersList = filterRepository.findByCategory(category);
        List<FiltersResponseDto> filtersResponseDtoList = new ArrayList<>();

        for (Filters filters : filtersList) {
            filtersResponseDtoList.add(new FiltersResponseDto(filters));
        }

        return filtersResponseDtoList;
    }
}
