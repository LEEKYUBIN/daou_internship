package com.mobius.moa.domain.entity;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@Entity
public class Attributes {
    @Id
    @GeneratedValue
    private int no;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "filter_no")
    private Filters filters;

    private String value;

    private String name;

    public void setFilters(Filters filters) {
        this.filters = filters;
        filters.getAttributesList().add(this);
    }
}