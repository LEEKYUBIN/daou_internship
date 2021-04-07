package com.mobius.moa.dto;

import com.mobius.moa.domain.entity.Items;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ItemResponseDto {
    private String img;

    private String name;

    private int no;

    private int price;

    private int review;

    private int reviewNum;

    public ItemResponseDto(Items items) {
        this.img = items.getImg();
        this.name = items.getName();
        this.no = items.getNo();
        this.price = items.getPrice();
        this.review = items.getReview();
        this.reviewNum = items.getReviewNum();
    }
}
