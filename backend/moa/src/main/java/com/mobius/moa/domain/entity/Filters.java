package com.mobius.moa.domain.entity;

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

    private String name;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_no")
    private Categories categories;

    @OneToMany(mappedBy = "filters")
    private final List<Attributes> attributesList = new ArrayList<>();

    private String text;

    public List<String> getAttributesNameList() {
        List<String> attributesNameList = new ArrayList<>();
        for (Attributes attributes : this.attributesList) {
            attributesNameList.add(attributes.getName());
        }
        return attributesNameList;
    }

    public void setCategory(Categories categories) {
        this.categories = categories;
    }
}
