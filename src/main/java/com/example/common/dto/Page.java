package com.example.common.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class Page {
    @Setter
    int pageIndex = 1;
    @Setter
    int pageSize = 10;

    int startPage = (pageIndex-1) * pageSize;
}
