package com.commerce.member.api.domain;

import com.commerce.member.api.exception.SortException;
import com.commerce.member.global.exception.ApiException;
import java.util.Arrays;
import lombok.Getter;

@Getter
public enum SortType {

    CREATED_TIME("createdTime"),
    NAME("name");

    private final String text;

    SortType(String text) {
        this.text = text;
    }

    public static SortType from(String sort) {
        return Arrays.stream(SortType.values())
                .filter(sortType -> sortType.text.equals(sort))
                .findAny()
                .orElseThrow(() -> new ApiException(SortException.INVALID_SORT_TYPE));
    }
}
