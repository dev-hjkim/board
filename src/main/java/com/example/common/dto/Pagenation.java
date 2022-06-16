package com.example.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class Pagenation<T> {
    @JsonIgnore
    Page page;

    int totalCount;
    int totalPage;
    List<T> list;

    public Pagenation(Page page, List<T> list) {
        this.totalCount = list.size();
        this.totalPage = list.size() / page.getPageSize() + 1;
        this.list = list;
    }
}
