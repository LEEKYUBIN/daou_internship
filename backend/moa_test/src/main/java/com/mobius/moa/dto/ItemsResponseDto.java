package com.mobius.moa.dto;

import com.mobius.moa.domain.Items;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ItemsResponseDto {
    private int no;
    private String name;
    private int review;
    private int reviewNum;
    private LocalDateTime regdate;
    private String img;

    public ItemsResponseDto(Items entity){
        this.no = entity.getNo();
        this.name = entity.getName();
        this.review = entity.getReview();
        this.reviewNum = entity.getReviewNum();
        this.regdate = entity.getRegdate();
        this.img = entity.getImg();
    }
}
