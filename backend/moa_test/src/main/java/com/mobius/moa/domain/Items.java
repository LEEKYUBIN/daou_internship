package com.mobius.moa.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public String getAttributesText() {
        StringBuilder sb = new StringBuilder();
        for (Information information : this.informationList) {
            sb.append(information.getAttributeText());
            sb.append(", ");
        }
        return sb.toString();
    }
}
