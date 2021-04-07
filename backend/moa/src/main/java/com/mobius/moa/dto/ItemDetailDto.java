package com.mobius.moa.dto;

import lombok.Getter;

@Getter
public class ItemDetailDto {
    private String attribute;
    private String attributeDetail;

    public ItemDetailDto(String attribute, String attributeDetail) {
        this.attribute = attribute;
        this.attributeDetail = attributeDetail;
    }
}
