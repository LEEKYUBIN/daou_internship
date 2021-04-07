package com.mobius.moa.dto;

import com.mobius.moa.domain.entity.Filters;
import lombok.Getter;

import java.util.List;

@Getter
public class FiltersResponseDto {
    private final String name;
    private final List<String> attributesList;

    public FiltersResponseDto(Filters filters) {
        this.name = filters.getName();
        this.attributesList = filters.getAttributesNameList();
    }
}
