package com.example.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class Pagenation<T> {
    int totalCount;
    int totalPage;
    List<T> list;

    public Pagenation(List<T> list) {
        this.totalCount = list.size();
        this.totalPage = list.size() / 10 + 1;
        this.list = list;
    }
}
