package com.mobius.moa.domain;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@Entity
public class Information {

    @Id
    @GeneratedValue
    private int no;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_no")
    private Items items;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "attribute_no")
    private Attributes attributes;

    private String attributeText;

}


