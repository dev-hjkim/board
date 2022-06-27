package com.example.common.dto;

import lombok.ToString;

@ToString
public class PageRequest {
    private Integer pageIndex;
    private Integer pageSize;

    public Integer getPageIndex() {
        return pageIndex == null ? 1 : pageIndex;
    }

    public Integer getPageSize() {
        return pageSize == null ? 10 : pageSize;
    }

    public Integer getStartPage() {
        return pageIndex == null ? 0 : (pageIndex-1) * pageSize;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
