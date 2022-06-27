package com.example.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class PageList<T> {
    private int totalCount;
    private int totalPage;
    private List<T> list;

    public PageList(int pageSize, int totalCount, List<T> list) {
        this.totalCount = totalCount;
        this.totalPage = totalCount / pageSize + 1;
        this.list = list;
    }
}
