package com.mobius.moa.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Items {
    @Id
    @GeneratedValue
    private int no;

    private String name;

    private int price;

    private int review;

    private int reviewNum;

    private LocalDateTime regdate;

    private String img;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_no")
    private Categories categories;

    @OneToMany(mappedBy = "items")
    private List<Information> informationList = new ArrayList<>();

    @Builder
    public Items(String name, int price, int reviewNum, Categories categories) {
        this.name = name;
        this.price = price;
        this.reviewNum = reviewNum;
        this.categories = categories;
    }

    public void setCategory(Categories categories) {
        this.categories = categories;
    }
}
