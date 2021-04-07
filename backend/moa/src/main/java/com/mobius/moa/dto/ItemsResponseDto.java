package com.mobius.moa.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ItemsResponseDto {
    private long total;
    private List<ItemResponseDto> itemResponseDtoList;

    public ItemsResponseDto(long total, List<ItemResponseDto> itemResponseDtoList) {
        this.total = total;
        this.itemResponseDtoList = itemResponseDtoList;
    }
}
