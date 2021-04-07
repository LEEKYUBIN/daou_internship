package com.mobius.moa.dto;

import com.mobius.moa.domain.entity.Categories;
import lombok.Getter;

@Getter
public class CategoriesResponseDto {
    private int no;
    private String name;
    private String text;
    private String img;

    public CategoriesResponseDto(Categories entity) {
        this.no = entity.getNo();
        this.name = entity.getName();
        this.text = entity.getText();
        this.img = entity.getImg();
    }
}
