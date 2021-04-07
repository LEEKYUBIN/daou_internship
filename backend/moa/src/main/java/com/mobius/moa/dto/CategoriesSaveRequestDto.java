package com.mobius.moa.dto;

import com.mobius.moa.domain.entity.Categories;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoriesSaveRequestDto {
    private String img;
    private String name;
    private String text;

    @Builder
    public CategoriesSaveRequestDto(String img, String name, String text) {
        this.img = img;
        this.name = name;
        this.text = text;
    }

    public Categories toEntity() {
        return Categories.builder().img(img).name(name).text(text).build();
    }
}
