package com.mobius.moa.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@NoArgsConstructor
@Getter
@Entity
public class Information {

    @Id
    @GeneratedValue
    private int no;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_no")
    private Items items;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "attribute_no")
    private Attributes attributes;

    private String attributeText;

    @Builder
    public Information(String attributeText) {
        this.attributeText = attributeText;
    }

    public void setItems(Items items) {
        this.items = items;
        items.getInformationList().add(this);
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }
}

