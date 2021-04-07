package com.mobius.moa.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "attributes")
    private final List<Information> informationList = new ArrayList<>();

    private String value;

    private String name;

    @Override
    public String toString() {
        return "Attributes{" +
                "no=" + no +
                ", value='" + value + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}