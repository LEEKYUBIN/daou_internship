package com.mobius.moa.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Getter
@Entity
public class Filters {
    @Id
    @GeneratedValue
    private int no;

    private final String name;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_no")
    private final Categories categories;

    @OneToMany(mappedBy = "filters")
    private final List<Attributes> attributesList = new ArrayList<>();

    private final String text;

    @Builder
    public Filters(String name, Categories categories, String text) {
        this.name = name;
        this.categories = categories;
        this.text = text;
    }

    public List<String> getAttributesNameList() {
        List<String> attributesNameList = new ArrayList<>();
        for (Attributes attributes : this.attributesList) {
            attributesNameList.add(attributes.getName());
        }
        return attributesNameList;
    }
}
