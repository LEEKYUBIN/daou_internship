package com.mobius.moa.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Categories {
    @Id
    @GeneratedValue
    private int no;

    @Column(nullable = false)
    private String img;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String text;

    @Builder
    public Categories(String img, String name, String text) {
        this.img = img;
        this.name = name;
        this.text = text;
    }
}