package com.mobius.moa.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Constants {
    LOAD_ITEM_COUNT(6);

    private int number;

    private Constants(int number) {
        this.number = number;
    }

}
