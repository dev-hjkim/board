package com.example.board.dto;

import com.example.common.dto.PageList;

import java.util.List;

public class BoardList extends PageList<String> {
    public BoardList(int pageSize, int totalCount, List<String> list) {
        super(pageSize, totalCount, list);
    }
}
